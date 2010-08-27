package gestionale.client.UI;

import java.util.Vector;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;
import gestionale.shared.Prodotto;

public class PanelOrdine extends TabSet{
	
	private Tab tab;
	private Tab tabTabella;
	private Tab tabFiltroClienti;
	private Tab tabFiltroProdotti;
	
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
		if(idOrdine == null){
	
		}else{
			
		}
		
		vettoreContattiFiltrato	=		new Vector<Contatto>();
		vettoreProdottiDaVisualizzare =	new Vector<String[]>();
		
		vettoreContattiFiltrato	=		DataSourceContatti.getVettoreContatti();
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","Arance"});
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","Arance"});
		
		
		
		thisPanel=this;
		this.setHeight100();
		this.setWidth100();
		//this.setShowCustomScrollbars(true);
		//this.setShowEdges(true);
		this.setShowResizeBar(true);
		
		ftClienti = new FlexTable();
		
		this.creaTabella();
		
		
		tabTabella			= new Tab("Ordine");
		tabFiltroClienti	= new Tab("Filtro Clienti");
		tabFiltroProdotti	= new Tab("Filtro Prodotti");
		
		Layout panelTabella = new Layout();
		panelTabella.addMember(ftClienti);
		Layout panelFiltroClienti = new Layout();
		panelFiltroClienti.addMember(new ListGridProdotti());
		Layout panelFiltroProdotti = new Layout();
		panelFiltroProdotti.addMember(new ListGridContatti());
		
		tabTabella.setPane(panelTabella);
		tabFiltroClienti.setPane(panelFiltroClienti);
		tabFiltroProdotti.setPane(panelFiltroProdotti);
		
		this.addTab(tabTabella);
		this.addTab(tabFiltroClienti);
		this.addTab(tabFiltroProdotti);
		
		
		this.addToTabPanel("Visualizzazione Ordine: " + idOrdine, true);
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
		/*
		new Timer(){

			public void run() {
				Vector<Prodotto> v = DataSourceProdottiCatalogati.getvProdottiCatalogati();
				if(v == null) schedule(500);
			}
			
		}.schedule(500);
		*/
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
		 
		 
		    cellFormatter.setColSpan(0, indiceTipologia, vTemp.size()); ftClienti.setText(0, indiceTipologia, prodotto.getTipologia()); indiceTipologia++;
		    
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
	    
	    
	}
	
	//Carica i dati degli ordini nella tabella già pronta
	private void aggiornaTabella(){
		
	}
}
