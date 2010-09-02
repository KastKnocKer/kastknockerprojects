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
import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
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
	private Layout panelTabellaComplessiva;
	private Layout panelFiltroProdotti = new Layout();
	
	private ListGridDettaglioOrdini lgdettaglioordini;
	private FlexTable ftClienti;
	
	private int indiceTipologia;
	private int indiceVarieta;
	private int indiceSottovarieta;
	private int indiceCalibro;
	
	
	private Vector<Contatto> vettoreContattiFiltrato = null;
	private Vector<String[]> vettoreProdottiDaVisualizzare = null;

	private String[] arrayCodProd = null;
	private String[] arrayDescProd = null;
	
	private String idOrdine;
	
	private Vector<LabelOrdinazione> vLabel = null;
	private DataSourceDettaglioOrdini dsdo = null;
	
	
	public PanelOrdine(String idOrdine){
		super();
		thisTabSet = this;
		if(idOrdine == null){
			this.destroy();
		}else{
			
		}
		
		this.idOrdine = idOrdine;
		dsdo = new DataSourceDettaglioOrdini(idOrdine,null,null);
		
		arrayCodProd = new String[1000];
		arrayDescProd = new String[1000];
		
		vettoreContattiFiltrato	=		new Vector<Contatto>();
		vettoreProdottiDaVisualizzare =	new Vector<String[]>();
		
		vettoreContattiFiltrato = DataSourceContatti.getVettoreContatti();
		//Contatto contatto = DataSourceContatti.getVettoreContatti().get(0);
		//vettoreContattiFiltrato.add(contatto);
		
		
		
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","A"});
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","Arance"});
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","Mele"});
		
		
		this.setHeight100();
		this.setWidth100();
		
		ftClienti = new FlexTable();
		
		tabTabella				= new Tab("Composizione Ordine");
		tabTabellaComplessiva	= new Tab("Visualizzazione Ordine");
		tabFiltroClienti		= new Tab("Filtro Clienti");
		tabFiltroProdotti		= new Tab("Filtro Prodotti");
		
		panelTabella = new Layout();
		panelTabella.addMember(ftClienti);
		
		panelTabellaComplessiva = new Layout();
		lgdettaglioordini = new ListGridDettaglioOrdini(idOrdine);
		panelTabellaComplessiva.addMember(lgdettaglioordini);

		panelFiltroProdotti = new Layout();
		panelFiltroProdotti.addMember(new ListGridContatti());
		
		
		tabTabella.setPane(panelTabella);
		tabTabellaComplessiva.setPane(panelTabellaComplessiva);
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
				}else if(event.getTab() == tabTabellaComplessiva){
					lgdettaglioordini.destroy();
					lgdettaglioordini = new ListGridDettaglioOrdini(PanelOrdine.this.idOrdine);
					panelTabellaComplessiva.addMember(lgdettaglioordini);
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
		tab.setPane( thisTabSet );
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
		 
		 //Imposto il nome della tipologia del prodotto ed indico quante caselle deve occupare
		 cellFormatter.setColSpan(0, indiceTipologia, vTemp.size());
		 ftClienti.setText(0, indiceTipologia, tipologia); indiceTipologia++;
		 
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
		    			
		    		}else{ //Stessa Varietà e non Stessa Svarietà
		    			ftClienti.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			indVar++;
		    			indSVar = 1;
		    		}
		    		
		    	}else{//non Stessa Varietà
		    			ftClienti.setText(1, indiceVarieta, prodotto.getVarieta());		indiceVarieta++;
		    			ftClienti.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			indVar = 1;
		    		    indSVar = 1;
		    	}
		    	
		    	cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    	
		    	lastVar = prodotto.getVarieta();
    			lastSVar = prodotto.getSottoVarieta();
    			
		    	ftClienti.setText(3, indiceCalibro, prodotto.getCalibro());
		    	arrayCodProd[indiceCalibro] = prodotto.getID();
		    	arrayDescProd[indiceCalibro] = prodotto.getTipologia() + " " +prodotto.getVarieta() +"  "+  prodotto.getSottoVarieta() +"  "+ prodotto.getCalibro();
		    	indiceCalibro++;
		    }
	}
	
	//Prepara la tabella con la lista dei clienti e dei prodotti
	private void creaTabella(){
		dsdo = new DataSourceDettaglioOrdini(idOrdine,null,null);

		ftClienti.removeAllRows();
		
		indiceTipologia		= 1;
		indiceVarieta		= 1;
		indiceSottovarieta	= 1;
		indiceCalibro	= 1;
		
		//Prepara clienti
		ftClienti.addStyleName("cw-FlexTable");
		ftClienti.setCellSpacing(5);
		ftClienti.setCellPadding(3);
		Contatto contatto = null;
	    for(int i=0; i<vettoreContattiFiltrato.size(); i++){
	    	contatto = vettoreContattiFiltrato.get(i);
	    	ftClienti.setHTML(i+4, 0, "<b>"+contatto.getRagioneSociale()+"</b>");
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
		vLabel = new Vector<LabelOrdinazione>();

		FlexCellFormatter cellFormatter = ftClienti.getFlexCellFormatter();
		int row = ftClienti.getRowCount();
		int col = indiceCalibro;
		for(int j=1; j<col; j++)
			for(int i=4; i<row; i++){

				
				String idProdotto = arrayCodProd[j];
				String idCliente = vettoreContattiFiltrato.get(i-4).getID();
				LabelOrdinazione label = new LabelOrdinazione(idOrdine, idCliente, idProdotto);
				label.setWidth(27);
				label.setHeight(25);
				label.setTooltip("Cliente: " + vettoreContattiFiltrato.get(i-4).getRagioneSociale() +"\nProdotto: " + arrayDescProd[j]);
				
				ftClienti.setWidget(i, j, label);
				vLabel.add(label);
			}
		
		
		panelTabella.destroy();
		panelTabella = new Layout();
		panelTabella.addMember(ftClienti);
		tabTabella.setPane(panelTabella);
		
		
		
		System.out.println("LABLES: "+vLabel.size());
		for(int k=0; k<vLabel.size(); k++){
			LabelOrdinazione lo = vLabel.get(k);
			String lidP = lo.getIdprodotto();
			String lidC = lo.getIdcliente();
			System.out.println("LABEL"+ lidP +"  "+ lidC);
		}
		
		
		
		new Timer(){

			@Override
			public void run() {
				System.out.println("Run Timer");
				DettaglioOrdine[] array = dsdo.getArrayDettaglioOrdini();
				LabelOrdinazione lo = null;
				if(array == null) {schedule(500);}else{
				int num;
				for(int i=0; i<array.length; i++){
					
					num = 0;
					String idP = array[i].getId_Prodotto();
					String idC = array[i].getId_Cliente();
					System.out.println("I	: "+idP +" 		"+idC);
					
					for(int k=0; k<vLabel.size(); k++){
						lo = vLabel.get(k);
						String lidP = lo.getIdprodotto();
						String lidC = lo.getIdcliente();
						if(idP.equals(lidP) && idC.equals(lidC)){
							lo.aumentaContatore( Integer.parseInt(array[i].getQuantita()) );
							break;
						}
					}
					
				}
			}}
			
		}.schedule(500);
		
	}
}
