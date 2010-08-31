package gestionale.client.DataBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Ordine;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceDettaglioOrdini extends DataSource{
	
	private static Vector<Ordine> vettoreDettaglioOrdini;
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceDettaglioOrdini istance;
	
	private String idOrdine,idCliente,idProdotto;
	private boolean dettaglioSelezionato = false;
	
	public DataSourceDettaglioOrdini(String idOrdine, String idCliente, String idProdotto){
		this.idOrdine = idOrdine;
		this.idCliente = idCliente;
		this.idProdotto = idProdotto;
		
		if(idCliente != null || idProdotto != null) dettaglioSelezionato = true;
		
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id","id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		
		DataSourceIntegerField idnField = new DataSourceIntegerField("idn","idn");
		idnField.setHidden(true);
		
		DataSourceTextField idordineField = new DataSourceTextField("idOrdine","idOrdine");
		idordineField.setHidden(true);
		DataSourceTextField idprodottoField = new DataSourceTextField("idProdotto","idProdotto");
		DataSourceTextField idclienteField = new DataSourceTextField("idCliente","idCliente");
		DataSourceTextField idimballaggioField = new DataSourceTextField("idImballaggio","idImballaggio");
		DataSourceTextField idfornitoreField = new DataSourceTextField("idFornitore","idFornitore");
		DataSourceTextField idtrasportatoreField = new DataSourceTextField("idTrasportatore","idTrasportatore");
		DataSourceIntegerField quantitaField = new DataSourceIntegerField("quantita","Quantita");
		DataSourceTextField fornitoreField = new DataSourceTextField("fornitore","Fornitore");
		DataSourceTextField trasportatoreField = new DataSourceTextField("trasportatore","Trasportatore");
		DataSourceTextField userField = new DataSourceTextField("user","user");
		 
        setFields(idField, idnField, idordineField, idprodottoField, idclienteField, idimballaggioField, quantitaField, userField, fornitoreField, trasportatoreField); 
		
        
        if(dettaglioSelezionato){
			Vector<Contatto> vF = DataSourceContatti.getVettoreFornitori();
			Vector<Contatto> vT = DataSourceContatti.getVettoreTrasportatori();
			
			final Map<String, String> mapFornitori = new HashMap<String, String>();
			final Map<String, String> mapTrasportatori = new HashMap<String, String>();
			
			/*
	        departments.put("Marketing", new String[]{"Advertising", "Community Relations"});  
	        departments.put("Sales", new String[]{"Channed Sales", "Direct Sales"});  
	        departments.put("Manufacturing", new String[]{"Design", "Development", "QA"});  
	        departments.put("Services", new String[]{"Support", "Consulting"});
	        */
			
			String[] aF = new String[vF.size()];
			String[] aT = new String[vT.size()];
			
			for(int i=0; i<vF.size(); i++){
				aF[i] = vF.get(i).getRagioneSociale();
				mapFornitori.put(vF.get(i).getRagioneSociale(), vF.get(i).getID());
			}
			
			for(int i=0; i<vT.size(); i++){
				aT[i] = vT.get(i).getRagioneSociale();
				mapTrasportatori.put(vF.get(i).getRagioneSociale(), vF.get(i).getID());
			}
			
			fornitoreField.setValueMap(aF);
			trasportatoreField.setValueMap(mapTrasportatori);
			
		}
        
		setClientOnly(true);
		this.getNewRecords();
	}

	

	 public void getNewRecords() {
		 
		 String query = "SELECT * FROM ordine_dettaglio WHERE IDOrdine = " + idOrdine;
		 
		 if(dettaglioSelezionato){
			 query = "SELECT * FROM ordine_dettaglio WHERE IDOrdine = " + idOrdine + " AND IDProdotto = " +idProdotto+ " AND IDCliente = "+idCliente;
		 }
		 
			rpc.eseguiQueryDettaglioOrdine(query,new AsyncCallback<DettaglioOrdine[]>(){

				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Dettaglio Ordini");
				}

				public void onSuccess(DettaglioOrdine[] result) {
					
					DettaglioOrdine record = null;
					ListGridRecord lgr = null;
					
					for(int i=0; i<result.length; i++){
						record = result[i];
						lgr = new ListGridRecord();
						
						lgr.setAttribute("id", record.getId());
						
						lgr.setAttribute("idOrdine", record.getId_Ordine());
						lgr.setAttribute("idProdotto", record.getId_Prodotto());
						lgr.setAttribute("idCliente", record.getId_Cliente());
						lgr.setAttribute("idImballaggio", record.getId_Imballaggio());
						lgr.setAttribute("idFornitore", record.getId_Fornitore());
						lgr.setAttribute("idTrasportatore", record.getId_Trasportatore());
						
						lgr.setAttribute("quantita", record.getQuantita());
						lgr.setAttribute("user", record.getUtente());
						
								
						addData(lgr);
					}
				}
			});
	}
	 
	 
	 public static void aggiungiDettaglioOrdine(Record record){
		 
		 String query = "INSERT INTO ordine_dettaglio (`IDOrdine`, `IDProdotto`,`IDCliente`,`Quantita`,`IDImballaggio`,`User`,`IDFornitore`,`IDTrasportatore`) VALUES ('"+record.getAttribute("idOrdine")+"','"+record.getAttribute("idProdotto")+"','"+record.getAttribute("idCliente")+"','"+record.getAttribute("quantita")+"','"+record.getAttribute("idImballaggio")+"','"+record.getAttribute("user")+"','"+record.getAttribute("fornitore")+"','"+record.getAttribute("trasportatore")+"')";

		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Boolean result) {
				
			}
				
				
		});
			
	 }
	 
	 public static void modificaDettaglioOrdine(Record record){/*
		 
		 String query = "INSERT INTO ordine_dettaglio (`DataCreazioneOrdine`,`DataInvioOrdine`,`DataPartenzaMerce`,`Note`,`IDTrasportatore`,`Convalidato`,`TipoOrdine`) VALUES ('"+ordine.getDataCreazioneOrdine()+"','"+ordine.getDataInvioOrdine()+"','"+ordine.getDataPartenzaMerce()+"','"+ordine.getNote()+"','"+ordine.getIDTrasportatore()+"','"+ordine.getConvalidato()+"','"+ordine.getTipoOrdine()+"')";
			
		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Boolean result) {
				
			}
				
				
		});*/
			
	 }

	
}
