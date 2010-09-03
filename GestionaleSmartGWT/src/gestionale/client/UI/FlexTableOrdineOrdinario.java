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

public class FlexTableOrdineOrdinario extends FlexTable{

	private int indiceTipologia;
	private int indiceVarieta;
	private int indiceSottovarieta;
	private int indiceCalibro;
	
	private String IDOrdine;
	
	private Vector<Contatto> vettoreContattiFiltrato = null;
	private Vector<String[]> vettoreProdottiDaVisualizzare = null;
	private Vector<LabelOrdinazione> vLabel = null;

	private String[] arrayCodProd = null;
	private String[] arrayDescProd = null;
	
	private FlexTableOrdineOrdinario thisTable;
	private DataSourceDettaglioOrdini dsdo = null;
	
	public FlexTableOrdineOrdinario(String idordine){
		thisTable = this;
		IDOrdine = idordine;
		
		arrayCodProd = new String[1000];
		arrayDescProd = new String[1000];
		
		vettoreContattiFiltrato	=		new Vector<Contatto>();
		vettoreProdottiDaVisualizzare =	new Vector<String[]>();
		
		vettoreContattiFiltrato = DataSourceContatti.getVettoreContatti();
		//Contatto contatto = DataSourceContatti.getVettoreContatti().get(0);
		//vettoreContattiFiltrato.add(contatto);
		
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","A"});
		vettoreProdottiDaVisualizzare.add(new String[] {"Frutta","Arance"});
		
		
	}


	
	private void addProdotto(String categoria, String tipologia){
		System.out.println("Aggiungo prodotto: "+categoria + tipologia);
		
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
		    	
		    	if(prodotto.getVarieta().equals(lastVar)){ //Stessa Variet�
		    		
		    		if(prodotto.getSottoVarieta().equals(lastSVar)){ //Stessa Variet� e Stessa Svariet�
		    			indVar++;
		    			indSVar++;
		    			
		    		}else{ //Stessa Variet� e non Stessa Svariet�
		    			this.setText(2, indiceSottovarieta, prodotto.getSottoVarieta());		indiceSottovarieta++;
		    			indVar++;
		    			indSVar = 1;
		    		}
		    		
		    	}else{//non Stessa Variet�
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
	public FlexTableOrdineOrdinario creaTabella(){
		dsdo = new DataSourceDettaglioOrdini(IDOrdine,null,null,DataSourceDettaglioOrdini.MOD_TabellaComposizione);

		this.removeAllRows();
		
		indiceTipologia		= 1;
		indiceVarieta		= 1;
		indiceSottovarieta	= 1;
		indiceCalibro	= 1;
		
		//Prepara clienti
		this.addStyleName("FlexTable");
		this.setCellSpacing(5);
		this.setCellPadding(3);
		Contatto contatto = null;
	    for(int i=0; i<vettoreContattiFiltrato.size(); i++){
	    	contatto = vettoreContattiFiltrato.get(i);
	    	this.setHTML(i+4, 0, "<b>"+contatto.getRagioneSociale()+"</b>");
	    }
	    
	    //Prepara i prodotti
	    for(int i=0; i<vettoreProdottiDaVisualizzare.size(); i++){
	    	String[] prodotto = vettoreProdottiDaVisualizzare.get(i);
	    	addProdotto(prodotto[0], prodotto[1]);
	    }
	    
	    return aggiornaTabella();
	}
	
	//Carica i dati degli ordini nella tabella gi� pronta
	private FlexTableOrdineOrdinario aggiornaTabella(){
		vLabel = new Vector<LabelOrdinazione>();

		FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
		int row = this.getRowCount();
		int col = indiceCalibro;
		for(int j=1; j<col; j++)
			for(int i=4; i<row; i++){

				
				String idProdotto = arrayCodProd[j];
				String idCliente = vettoreContattiFiltrato.get(i-4).getID();
				LabelOrdinazione label = new LabelOrdinazione(IDOrdine, idCliente, idProdotto);
				label.setWidth(27);
				label.setHeight(25);
				label.setTooltip("Cliente: " + vettoreContattiFiltrato.get(i-4).getRagioneSociale() +"\nProdotto: " + arrayDescProd[j]);
				
				this.setWidget(i, j, label);
				vLabel.add(label);
			}
		
		/*
		panelTabella.destroy();
		panelTabella = new Layout();
		panelTabella.addMember(this);
		tabTabella.setPane(panelTabella);
		*/
		
		
		System.out.println("LABLES: "+vLabel.size());
		for(int k=0; k<vLabel.size(); k++){
			LabelOrdinazione lo = vLabel.get(k);
			String lidP = lo.getIdprodotto();
			String lidC = lo.getIdcliente();
			System.out.println("LABEL"+ lidP +"  "+ lidC);
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
		return thisTable;
	}

}