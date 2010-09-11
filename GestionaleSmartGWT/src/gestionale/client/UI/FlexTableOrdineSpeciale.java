package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;

public class FlexTableOrdineSpeciale extends FlexTable{

	private int indiceTipologia;
	private int indiceVarieta;
	private int indiceSottovarieta;
	private int indiceCalibro;
	
	private String IDOrdine;
	
	private Vector<Contatto> vettoreContatti = null;
	private Vector<String[]> vettoreProdottiDaVisualizzare = null;
	private Vector<LabelOrdinazione> vLabel = null;

	private String[] arrayCodProd = null;
	private String[] arrayDescProd = null;
	
	private FlexTableOrdineSpeciale thisTable;
	private DataSourceDettaglioOrdini dsdo = null;
	
	private PanelFiltroContatti panelfiltrocontatti = null;
	private PanelFiltroProdotti panelfiltroprodotti = null;
	
	public FlexTableOrdineSpeciale(String idordine){
		thisTable = this;
		IDOrdine = idordine;
		
		arrayCodProd = new String[1000];
		arrayDescProd = new String[1000];
		
	}


	
	private void addProdotto(String categoria, String tipologia){
		
		FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
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
		 this.setText(0, indiceTipologia, tipologia); indiceTipologia++;
		 
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
		    			this.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			indVar++;
		    			indSVar = 1;
		    		}
		    		
		    	}else{//non Stessa Varietà
		    			this.setText(1, indiceVarieta, prodotto.getVarieta());		indiceVarieta++;
		    			this.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			indVar = 1;
		    		    indSVar = 1;
		    	}
		    	
		    	cellFormatter.setColSpan(1, indiceVarieta-1, indVar);
    			cellFormatter.setColSpan(2, indiceSottovarieta-1, indSVar);
		    	
		    	lastVar = prodotto.getVarieta();
    			lastSVar = prodotto.getSottoVarieta();
    			
    			this.setText(3, indiceCalibro, prodotto.getCalibro());
		    	arrayCodProd[indiceCalibro] = prodotto.getID();
		    	arrayDescProd[indiceCalibro] = prodotto.getTipologia() + " " +prodotto.getVarieta() +"  "+  prodotto.getSottoVarieta() +"  "+ prodotto.getCalibro();
		    	indiceCalibro++;
		    }
	}
	
	//Prepara la tabella con la lista dei clienti e dei prodotti
	public FlexTableOrdineSpeciale creaTabella(){
		//if(panelfiltrocontatti != null)		vettoreContattiFiltrato = panelfiltrocontatti.getVettoreContattiFiltrato();
		if(panelfiltroprodotti != null)		vettoreProdottiDaVisualizzare = panelfiltroprodotti.getVettoreProdottiFiltrato();
		
		dsdo = new DataSourceDettaglioOrdini(IDOrdine,null,null,DataSourceDettaglioOrdini.MOD_TabellaComposizione);

		this.removeAllRows();
		
		indiceTipologia		= 1;
		indiceVarieta		= 1;
		indiceSottovarieta	= 1;
		indiceCalibro		= 1;
		
		//Prepara clienti
		this.addStyleName("FlexTable");
		this.setCellSpacing(5);
		this.setCellPadding(3);
		vettoreContatti = new Vector<Contatto>();
		Contatto contatto = new Contatto();
		contatto.setRagioneSociale("Magazzino");
		vettoreContatti.add(contatto);
	    for(int i=0; i<vettoreContatti.size(); i++){
	    	contatto = vettoreContatti.get(i);
	    	this.setHTML(i+4, 0, "<b>"+contatto.getRagioneSociale()+"</b>");
	    }
	    
	    //Prepara i prodotti
	    for(int i=0; i<vettoreProdottiDaVisualizzare.size(); i++){
	    	String[] prodotto = vettoreProdottiDaVisualizzare.get(i);
	    	addProdotto(prodotto[0], prodotto[1]);
	    }
	    
	    return aggiornaTabella();
	}
	
	//Carica i dati degli ordini nella tabella già pronta
	private FlexTableOrdineSpeciale aggiornaTabella(){
		vLabel = new Vector<LabelOrdinazione>();

		FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
		int row = this.getRowCount();
		int col = indiceCalibro;
		for(int j=1; j<col; j++)
			for(int i=4; i<row; i++){

				
				String idProdotto = arrayCodProd[j];
				String idCliente = vettoreContatti.get(i-4).getID();
				LabelOrdinazione label = new LabelOrdinazione(IDOrdine, idCliente, idProdotto);
				label.setWidth(27);
				label.setHeight(25);
				label.setTooltip("Cliente: " + vettoreContatti.get(i-4).getRagioneSociale() +"\nProdotto: " + arrayDescProd[j]);
				
				this.setWidget(i, j, label);
				vLabel.add(label);
			}
		
		/*
		panelTabella.destroy();
		panelTabella = new Layout();
		panelTabella.addMember(this);
		tabTabella.setPane(panelTabella);
		*/
		
		
		for(int k=0; k<vLabel.size(); k++){
			LabelOrdinazione lo = vLabel.get(k);
			String lidP = lo.getIdprodotto();
			String lidC = lo.getIdcliente();
		}
		
		for (int i = 4; i < this.getRowCount(); i++) {
			
			if ((i % 2) == 0) {
				for (int j = 0; j < this.getCellCount(i); j++) {
		        	this.getFlexCellFormatter().setStyleName(i, j, "FlexTable_Even_Row");
		        }
            } else {
            	for (int j = 0; j < this.getCellCount(i); j++) {
    				this.getFlexCellFormatter().setStyleName(i, j, "FlexTable_Odd_Row");
    	        }
            }
			
	        
	    }
		
		
		
		new Timer(){

			@Override
			public void run() {
				DettaglioOrdine[] array = dsdo.getArrayDettaglioOrdini();
				LabelOrdinazione lo = null;
				if(array == null) {
					schedule(500);
				}else{
				for(int i=0; i<array.length; i++){
					
					String idP = array[i].getId_Prodotto();
					String idC = array[i].getId_Cliente();
					
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
		return thisTable;
	}



	public void setPanelFiltroContatti(PanelFiltroContatti panelfiltrocontatti) {
		this.panelfiltrocontatti = panelfiltrocontatti;
	}



	public void setPanelFiltroProdotti(PanelFiltroProdotti panelfiltroprodotti) {
		this.panelfiltroprodotti = panelfiltroprodotti;
	}

}
