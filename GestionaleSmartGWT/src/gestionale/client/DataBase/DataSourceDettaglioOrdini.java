package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceDettaglioOrdini extends DataSource{
	
	private static Vector<DettaglioOrdine> vettoreDettaglioOrdini;
	private DettaglioOrdine[] arrayDettaglioOrdini = null;
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceDettaglioOrdini istance;
	
	private String idOrdine,idCliente,idProdotto;
	private int modalita;
	
	private String[] aF;
	private String[] aT;
	private String[] aFID;
	private String[] aTID;
	
	private DataSourceDettaglioOrdini thisDataSource;
	
	public static final int MOD_TabellaComposizione = 1;
	public static final int  MOD_TabellaDettaglio = 2;
	public static final int MOD_TabellaCompleta = 3;
	
	public DataSourceDettaglioOrdini(String idOrdine, String idCliente, String idProdotto, int mod){
		this.idOrdine = idOrdine;
		this.idCliente = idCliente;
		this.idProdotto = idProdotto;
		this.modalita = mod;
		
		thisDataSource = this;
		
		
		setID(id);
		this.setCanMultiSort(true);
		DataSourceTextField idField = new DataSourceTextField("id","id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		
		DataSourceIntegerField idnField = new DataSourceIntegerField("idn","idn");
		idnField.setHidden(true);
		
		DataSourceTextField idordineField = new DataSourceTextField("idordine","idOrdine");
		DataSourceTextField idprodottoField = new DataSourceTextField("idprodotto","idProdotto");
		DataSourceTextField idclienteField = new DataSourceTextField("idcliente","idCliente");
		DataSourceTextField idimballaggioField = new DataSourceTextField("idimballaggio","idImballaggio");
		DataSourceTextField idfornitoreField = new DataSourceTextField("idfornitore","idFornitore");
		DataSourceTextField idtrasportatoreField = new DataSourceTextField("idtrasportatore","idTrasportatore");
		
		DataSourceIntegerField quantitaField = new DataSourceIntegerField("quantita","Quantita");
		DataSourceTextField fornitoreField = new DataSourceTextField("fornitore","Fornitore");
		DataSourceTextField trasportatoreField = new DataSourceTextField("trasportatore","Trasportatore");
		DataSourceTextField userField = new DataSourceTextField("user","user");
		DataSourceTextField clienteField = new DataSourceTextField("cliente","Cliente");
		DataSourceTextField prodottoField = new DataSourceTextField("descrizioneprodotto","Prodotto");
		DataSourceTextField imballaggioField = new DataSourceTextField("descrizioneimballaggio","Imballaggio");
		
		idordineField.setHidden(true);
		idprodottoField.setHidden(true);
		idclienteField.setHidden(true);
		idimballaggioField.setHidden(true);
		idfornitoreField.setHidden(true);
		idtrasportatoreField.setHidden(true);
		userField.setHidden(true);
		
		
		
		
        setFields(clienteField, quantitaField, prodottoField, fornitoreField, trasportatoreField, imballaggioField, idField, idnField, idordineField, idprodottoField, idclienteField, idimballaggioField, userField); 
		
    
        if(modalita == MOD_TabellaDettaglio){
			Vector<Contatto> vF = DataSourceContatti.getVettoreFornitori();
			Vector<Contatto> vT = DataSourceContatti.getVettoreTrasportatori();
			
	
			aF = new String[vF.size()];
			aT = new String[vT.size()];
			aFID = new String[vF.size()];
			aTID = new String[vT.size()];
			
			for(int i=0; i<vF.size(); i++){
				aF[i] = vF.get(i).getRagioneSociale();
				aFID[i] = vF.get(i).getID();
			}
			
			for(int i=0; i<vT.size(); i++){
				aT[i] = vT.get(i).getRagioneSociale();
				aTID[i] = vT.get(i).getID();
			}
			
			fornitoreField.setValueMap(aF);
			trasportatoreField.setValueMap(aT);
			
		}
        
		setClientOnly(true);
		
		
		
		if(mod == MOD_TabellaComposizione){
			this.getNewRecords();
		}else if (mod == MOD_TabellaDettaglio){
			//this.getNewRecordsComplete();
			this.getNewRecordsComplete();
		}else if(mod == MOD_TabellaCompleta){
			this.getNewRecordsComplete();
		}else return;
		
		
	}

	

	 private void getNewRecordsComplete() {
		 String query = "SELECT o.ID, o.Quantita, o.User, c.RagioneSociale, c1.RagioneSociale, c2.RagioneSociale, p.*, i.Descrizione, i.ID, c.ID, c1.ID, c2.ID FROM ordine_dettaglio o JOIN contatti c JOIN contatti c1 JOIN contatti c2 JOIN prodotti_catalogati p JOIN imballaggio i ON c.ID = o.IDCliente AND c1.ID = o.IDFornitore AND c2.ID = o.IDTrasportatore AND p.ID = o.IDProdotto AND i.ID = o.IDImballaggio WHERE o.IDOrdine = '"+idOrdine+"';";
		 
		 rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String[][] result) {
				ListGridRecord lgr = null;
				for(int i=0; i < result.length; i++){
					String[] rec = result[i];
					lgr = new ListGridRecord();
					
					lgr.setAttribute("id", rec[0]);
					
					lgr.setAttribute("quantita", rec[1]);
					lgr.setAttribute("user", rec[2]);
					lgr.setAttribute("cliente", rec[3]);
					lgr.setAttribute("fornitore", rec[4]);
					lgr.setAttribute("trasportatore", rec[5]);
					lgr.setAttribute("idprodotto", rec[6]);
					lgr.setAttribute("descrizioneprodotto", rec[7]+" "+rec[8]+" "+rec[9]+" "+rec[10]+" "+rec[11]);
					
					lgr.setAttribute("descrizioneimballaggio", rec[12]);
					
					lgr.setAttribute("idimballaggio", rec[13]);
					lgr.setAttribute("idcliente", rec[14]);
					lgr.setAttribute("idfornitore", rec[15]);
					lgr.setAttribute("idtrasportatore", rec[16]);
					
					//DATI INCOMPLETI
					/*
					array[i].setId(rec[0]);
					array[i].setId_Cliente(rec[14]);
					array[i].setId_Fornitore(rec[15]);
					array[i].setId_Trasportatore(rec[16]);
					array[i].setQuantita(rec[1]);
					*/
					addData(lgr);
				}
				//thisDataSource.arrayDettaglioOrdini=array;
			}
			 
			 
		 });
		 
		
	}



	public void getNewRecords() {
		 
		 String query = "SELECT * FROM ordine_dettaglio WHERE IDOrdine = " + idOrdine;
		 
		 if(modalita == MOD_TabellaDettaglio){
			 query = "SELECT * FROM ordine_dettaglio WHERE IDOrdine = " + idOrdine + " AND IDProdotto = " +idProdotto+ " AND IDCliente = "+idCliente;
		 }
		 
			rpc.eseguiQueryDettaglioOrdine(query,new AsyncCallback<DettaglioOrdine[]>(){

				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Dettaglio Ordini");
				}

				public void onSuccess(DettaglioOrdine[] result) {
					setArrayDettaglioOrdini(result);
					DettaglioOrdine record = null;
					ListGridRecord lgr = null;
					
					for(int i=0; i<result.length; i++){
						record = result[i];
						lgr = new ListGridRecord();
						
						lgr.setAttribute("id", record.getId());
						
						lgr.setAttribute("idordine", record.getId_Ordine());
						lgr.setAttribute("idprodotto", record.getId_Prodotto());
						lgr.setAttribute("idcliente", record.getId_Cliente());
						lgr.setAttribute("idimballaggio", record.getId_Imballaggio());
						lgr.setAttribute("idfornitore", record.getId_Fornitore());
						lgr.setAttribute("idtrasportatore", record.getId_Trasportatore());
						
						lgr.setAttribute("quantita", record.getQuantita());
						lgr.setAttribute("user", record.getUtente());
						
						if(modalita == MOD_TabellaDettaglio){
							
							for(int j=0; j<aFID.length; j++){
								if(aFID[j].equals(record.getId_Fornitore())){
									lgr.setAttribute("fornitore", aF[j]);
									break;
								}
							}
							for(int j=0; j<aTID.length; j++){
								if(aTID[j].equals(record.getId_Trasportatore())){
									lgr.setAttribute("trasportatore", aT[j]);
									break;
								}
							}
							
							
						}
						
								
						addData(lgr);
					}
				}
			});
	}
	 
	 
	 public static void aggiungiDettaglioOrdine(Record record){
		 
		 String query = "INSERT INTO ordine_dettaglio (`IDOrdine`, `IDProdotto`,`IDCliente`,`Quantita`,`IDImballaggio`,`User`,`IDFornitore`,`IDTrasportatore`) VALUES ('"+record.getAttribute("idordine")+"','"+record.getAttribute("idprodotto")+"','"+record.getAttribute("idcliente")+"','"+record.getAttribute("quantita")+"','"+record.getAttribute("idimballaggio")+"','"+record.getAttribute("user")+"','"+record.getAttribute("idfornitore")+"','"+record.getAttribute("idtrasportatore")+"')";

		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Boolean result) {
				
			}
				
				
		});
			
	 }
	 
	 public static void modificaDettaglioOrdine(Record record){
		String query = "UPDATE ordine_dettaglio SET Quantita = '"+ record.getAttribute("quantita") +"',IDImballaggio = '"+ record.getAttribute("idimballaggio") +"', IDFornitore = '"+ record.getAttribute("idfornitore") +"', IDTrasportatore = '"+ record.getAttribute("idtrasportatore") +"' WHERE ID = '"+ record.getAttribute("id") +"'";

		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Boolean result) {
				
			}
				
				
		});
			
	 }
	 
	 public static void rimuoviDettaglioOrdine(Record record){
			String query = "DELETE FROM ordine_dettaglio WHERE ID = '"+record.getAttribute("id")+"'";

			 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

				public void onFailure(Throwable caught) {
					
				}

				public void onSuccess(Boolean result) {
					
				}
					
					
			});
				
		 }



	public void setArrayDettaglioOrdini(DettaglioOrdine[] arrayDettaglioOrdini) {
		this.arrayDettaglioOrdini = arrayDettaglioOrdini;
	}



	public DettaglioOrdine[] getArrayDettaglioOrdini() {
		return arrayDettaglioOrdini;
	}




	
}
