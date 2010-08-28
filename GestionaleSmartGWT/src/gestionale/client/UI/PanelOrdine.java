package gestionale.client.UI;

import java.util.Vector;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Scrollbar;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;
import gestionale.shared.Prodotto;

public class PanelOrdine extends TabSet{
	
	private Tab tab;
	private Tab tabTabella;
	private Tab tabTabellaComplessiva;
	private Tab tabFiltroClienti;
	private Tab tabFiltroProdotti;
	private TabSet thisTabSet;
	
	private Layout panelTabella;
	
	private DynamicForm form;
	
	private PanelOrdine thisPanel;
	private ListGridDettaglioOrdini lgdettaglioordini;
	private FlexTable ftClienti;
	private FlexTable ftProdotti;
	private FlexTable ftOrdinazione;
	
	private int indiceTipologia;
	private int indiceVarieta;
	private int indiceSottovarieta;
	private int indiceCalibro;
	
	
	private Vector<Contatto> vettoreContattiFiltrato = null;
	private Vector<String[]> vettoreProdottiDaVisualizzare = null;
	
	
	public PanelOrdine(String idOrdine){
		super();
		thisTabSet = this;
		if(idOrdine == null){
	
		}else{
			
		}

		
		vettoreContattiFiltrato	=		new Vector<Contatto>();
		vettoreProdottiDaVisualizzare =	new Vector<String[]>();

		vettoreContattiFiltrato = DataSourceContatti.getVettoreContatti();
		
		thisPanel=this;
		this.setHeight100();
		this.setWidth100();
		//this.setShowCustomScrollbars(true);
		//this.setShowEdges(true);
		//this.setShowResizeBar(true);
		
		ftClienti = new FlexTable();
		
		tabTabella				= new Tab("Composizione Ordine");
		tabTabellaComplessiva	= new Tab("Visualizzazione Ordine");
		tabFiltroClienti		= new Tab("Filtro Clienti");
		tabFiltroProdotti		= new Tab("Filtro Prodotti");
		
		panelTabella = new Layout();
		panelTabella.addMember(ftClienti);

		Layout panelFiltroProdotti = new Layout();
		panelFiltroProdotti.addMember(new ListGridContatti());
		
		
		tabTabella.setPane(panelTabella);
		tabFiltroClienti.setPane(new PanelFiltroContatti(vettoreContattiFiltrato));
		tabFiltroProdotti.setPane(new PanelFiltroProdotti(vettoreProdottiDaVisualizzare));
		
		
		
		this.addTab(tabTabella);
		this.addTab(tabTabellaComplessiva);
		this.addTab(tabFiltroClienti);
		this.addTab(tabFiltroProdotti);
		
		
		
		this.addToTabPanel("Visualizzazione Ordine: " + idOrdine, true);
		
		this.addTabSelectedHandler(new TabSelectedHandler() {
			
			public void onTabSelected(TabSelectedEvent event) {
				if(event.getTab() == tabTabella){
					((PanelOrdine) thisTabSet).creaTabella();
				}
				
			}
		});
	}
	
	
	
	
	private void closePanel(){
		GUIManager.getTopTabset().removeTab(tab);
	}

	private void addToTabPanel(String tabName, boolean canclose){

		Tab[] tabArray = GUIManager.getTopTabset().getTabs();
		
        for( int i=0; i<tabArray.length; i++){
        	if( tabArray[i].getTitle().equals( tabName ) ){
        		GUIManager.getTopTabset().selectTab(tabArray[i]);
        		return;
        		}
        }
		
		tab = new Tab(tabName);
		tab.setPane( thisPanel );
        tab.setCanClose(canclose);
        GUIManager.getTopTabset().addTab(tab);
        GUIManager.getTopTabset().selectTab(tab);
		
		return;
		
	}
	
	private void addProdotto(String categoria, String tipologia){
		System.out.println("Aggiungo prodotto: "+categoria + tipologia);
		
		FlexCellFormatter cellFormatter = ftClienti.getFlexCellFormatter();
		 Vector<Prodotto> v = DataSourceProdottiCatalogati.getvProdottiCatalogati();
		 Vector<Prodotto> vTemp = new Vector<Prodotto>();
		 
		 Prodotto prodotto = null;
		 for(int i=0; i<v.size(); i++){
			 prodotto = v.get(i);
			 if(prodotto.getCategoria().equals(categoria) && prodotto.getTipologia().equals(tipologia) ){
				 vTemp.add(prodotto);
			 }
		 }
		 
		 
		 cellFormatter.setColSpan(0, indiceTipologia, vTemp.size()); ftClienti.setText(0, indiceTipologia, tipologia); indiceTipologia++;
		    
		    String lastVar = "";
		    String lastSVar = "";
		    int indVar = 1;
		    int indSVar = 1;
		    
		    for(int i=0; i<vTemp.size(); i++){
		    	
		    	prodotto = vTemp.get(i);
		    	
		    	if(prodotto.getVarieta().equals(lastVar)){ //Stessa Varietà
		    		
		    		if(prodotto.getSottoVarieta().equals(lastSVar)){ //Stessa Varietà e Stessa Svarietà
		    			indVar++;
		    			indSVar++;
		    			cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
		    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    			
		    		}else{ //Stessa Varietà e non Stessa Svarietà
		    			ftClienti.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			lastSVar = prodotto.getSottoVarieta();
		    			indVar++;
		    			cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
		    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    			indSVar = 1;
		    		}
		    		
		    	}else{
		    		
		    		if(prodotto.getSottoVarieta().equals(lastSVar)){ //non Stessa Varietà e Stessa Svarietà
		    			ftClienti.setText(1, indiceVarieta, prodotto.getVarieta());		indiceVarieta++;
		    			lastVar = prodotto.getVarieta();
		    			indSVar++;
		    			cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
		    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    			indVar = 1;
		    			
		    		}else{ //non Stessa Varietà e non Stessa Svarietà
		    			ftClienti.setText(1, indiceVarieta, prodotto.getVarieta());		indiceVarieta++;
		    			ftClienti.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			lastVar = prodotto.getVarieta();
		    			lastSVar = prodotto.getSottoVarieta();
		    			
		    			cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
		    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    			indVar = 1;
		    		    indSVar = 1;
		    		}
		    	}
		    	
		    	
		    	ftClienti.setText(3, indiceCalibro, prodotto.getCalibro());		indiceCalibro++;
		    }
		    
		    /*
		    cellFormatter.setColSpan(1, 1, 3); ftClienti.setText(1, 1, "Economy");
		    cellFormatter.setColSpan(1, 2, 4); ftClienti.setText(1, 2, "Gallo");
		    ftClienti.setText(2, 1, "1");
		    ftClienti.setText(2, 2, "2");
		    ftClienti.setText(2, 3, "3");
		    ftClienti.setText(2, 4, "1");
		    ftClienti.setText(2, 5, "2");
		    ftClienti.setText(2, 6, "3");
		    ftClienti.setText(2, 7, "4");*/
	}
	
	//Prepara la tabella con la lista dei clienti e dei prodotti
	private void creaTabella(){
		
		ftClienti.removeAllRows();
		
		indiceTipologia		= 1;
		indiceVarieta		= 1;
		indiceSottovarieta	= 1;
		indiceCalibro	= 1;
		
		//Prepara parte clienti
		ftClienti.addStyleName("cw-FlexTable");
		ftClienti.setCellSpacing(5);
		ftClienti.setCellPadding(3);
		Contatto contatto = null;
	    for(int i=0; i<vettoreContattiFiltrato.size(); i++){
	    	contatto = vettoreContattiFiltrato.get(i);
	    	ftClienti.setText(i+4, 0, contatto.getRagioneSociale());
	    }
	    
	    //Prepara i prodotti
	    for(int i=0; i<vettoreProdottiDaVisualizzare.size(); i++){
	    	String[] prodotto = vettoreProdottiDaVisualizzare.get(i);
	    	addProdotto(prodotto[0], prodotto[1]);
	    }
	    
	    aggiornaTabella();
	}
	
	//Carica i dati degli ordini nella tabella già pronta
	private void aggiornaTabella(){
		
		FlexCellFormatter cellFormatter = ftClienti.getFlexCellFormatter();
		int row = ftClienti.getRowCount();
		int col = indiceCalibro;
		for(int j=1; j<col; j++)
			for(int i=4; i<row; i++){
				//ftClienti.setText(i, j, "");
				Label label = new Label();
				label.setWidth(27);
				label.setHeight(25);
				label.addDoubleClickHandler(new DoubleClickHandler() {
					
					public void onDoubleClick(DoubleClickEvent event) {
						((Label) event.getSource()).setContents("222");
						((Label) event.getSource()).setTooltip("KAKSKSKDKASDASD");
						
					}
				});
				
				ftClienti.setWidget(i, j, label);
			}
		
		
		panelTabella.destroy();
		panelTabella = new Layout();
		panelTabella.addMember(ftClienti);
		tabTabella.setPane(panelTabella);
		
	}
}
