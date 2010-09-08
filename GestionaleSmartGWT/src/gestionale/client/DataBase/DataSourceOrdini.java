package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Ordine;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceOrdini extends DataSource{
	
	private static Vector<Ordine> vettoreOrdini;
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceOrdini istance;
	private static boolean caricato = false;
	private boolean ready = false;
	
	public static DataSourceOrdini getIstance(){
		if (istance == null) {  
            istance = new DataSourceOrdini();
        } 
		return istance;
	}
	
	public DataSourceOrdini(){
		setID(id);
		DataSourceTextField idField = new DataSourceTextField("id","id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		
		DataSourceIntegerField idnField = new DataSourceIntegerField("idn","idn");

		DataSourceTextField idordineField = new DataSourceTextField("idordine","idordine");
		DataSourceTextField idprodottoField = new DataSourceTextField("idprodotto","idprodotto");
		DataSourceTextField idclienteField = new DataSourceTextField("idcliente","idcliente");
		DataSourceTextField idimballaggioField = new DataSourceTextField("idimballaggio","idimballaggio");
		DataSourceTextField quantitaField = new DataSourceTextField("quantita","quantita");
		DataSourceTextField userField = new DataSourceTextField("user","user");
		/*
		 * record.setAttribute("id",ordine.getID());
						record.setAttribute("idn",Integer.parseInt(ordine.getID()));
						record.setAttribute("datacreazioneordine",ordine.getDataCreazioneOrdine());
						record.setAttribute("datainvioordine",ordine.getDataInvioOrdine());
						record.setAttribute("datapartenzamerce",ordine.getDataPartenzaMerce());
						record.setAttribute("note",ordine.getNote());
						record.setAttribute("idtrasportatore",ordine.getIDTrasportatore());
						record.setAttribute("convalidato",ordine.getConvalidato());
						record.setAttribute("tipoordine",ordine.getTipoOrdine());
		 */
		 
        setFields(idField, idnField, idordineField, idprodottoField, idclienteField, idimballaggioField, quantitaField, userField); 
		
		setClientOnly(true);
		getNewRecords();
	}

	

	 public void getNewRecords() {
		 ready = false;
		 String query = "SELECT * FROM ordini";
		 
			rpc.eseguiQueryOrdine(query,new AsyncCallback<Ordine[]>(){
				
				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Ordini");
				}

				public void onSuccess(Ordine[] result) {
					vettoreOrdini = new Vector<Ordine>();
					
					for(int i=0; i<result.length; i++){
						
						Ordine ordine = result[i];
						
						vettoreOrdini.add(ordine);
						ListGridRecord record = new ListGridRecord();
						record.setAttribute("id",ordine.getID());
						record.setAttribute("idn",Integer.parseInt(ordine.getID()));
						record.setAttribute("datacreazioneordine",ordine.getDataCreazioneOrdine());
						record.setAttribute("datainvioordine",ordine.getDataInvioOrdine());
						record.setAttribute("datapartenzamerce",ordine.getDataPartenzaMerce());
						record.setAttribute("note",ordine.getNote());
						record.setAttribute("idtrasportatore",ordine.getIDTrasportatore());
						record.setAttribute("idfornitore",ordine.getIDFornitore());
						record.setAttribute("convalidato",ordine.getConvalidato());
						record.setAttribute("tipoordine",ordine.getTipoOrdine());
						record.setAttribute("descrizioneordine",ordine.getDataCreazioneOrdine()+" - ["+ordine.getTipoOrdine()+"]");
						
						if(istance.caricato)
						istance.updateData(record);
						else
						istance.addData(record);
						
					}
					istance.caricato = true;
					ready = true;
				}
				
			});
		 
		
	}
	 
	 
	 public static void aggiungiOrdine(final Ordine ordine){
		 
		 String query = "INSERT INTO ordini (`DataCreazioneOrdine`,`DataInvioOrdine`,`DataPartenzaMerce`,`Note`,`IDTrasportatore`,`Convalidato`,`TipoOrdine`,`IDFornitore`) VALUES ('"+ordine.getDataCreazioneOrdine()+"','"+ordine.getDataInvioOrdine()+"','"+ordine.getDataPartenzaMerce()+"','"+ordine.getNote()+"','"+ordine.getIDTrasportatore()+"','"+ordine.getConvalidato()+"','"+ordine.getTipoOrdine()+"','"+ordine.getIDFornitore()+"')";
			
		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(Boolean result) {
				if(!result) {
					Window.alert("Non e' stato possibile creare l'ordine. \nPossibili valori duplicati!");
					return;
				}
				String query = "SELECT * FROM ordini WHERE ID = (SELECT MAX(ID) FROM ordini)";
				rpc.eseguiQueryOrdine(query,new AsyncCallback<Ordine[]>(){
					
					public void onFailure(Throwable caught) {
						Window.alert("Errore: Caricamento da DB Ordini");
					}

					public void onSuccess(Ordine[] result) {
						vettoreOrdini = new Vector<Ordine>();
						
						for(int i=0; i<result.length; i++){
							
							Ordine ordine = result[i];
							
							vettoreOrdini.add(ordine);
							
							ListGridRecord record = new ListGridRecord();
							record.setAttribute("id",ordine.getID());
							record.setAttribute("datacreazioneordine",ordine.getDataCreazioneOrdine());
							record.setAttribute("datainvioordine",ordine.getDataInvioOrdine());
							record.setAttribute("datapartenzamerce",ordine.getDataPartenzaMerce());
							record.setAttribute("note",ordine.getNote());
							record.setAttribute("idtrasportatore",ordine.getIDTrasportatore());
							record.setAttribute("idfornitore",ordine.getIDFornitore());
							record.setAttribute("convalidato",ordine.getConvalidato());
							record.setAttribute("tipoordine",ordine.getTipoOrdine());
							
							istance.addData(record);
							
							DataSourceOrdini.getIstance().getNewRecords();
						}
						
					}
					
				});
				
			}
				
				
		});
			
		
			
	 }

	 public static void rimuoviOrdine(final String idOrdine){
		 
		String query = "DELETE FROM ordini WHERE ID='" + idOrdine + "'";
 		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
 			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
 			public void onSuccess(Boolean result) {
				if(!result){
					Window.alert("L'ordine non è stato rimosso correttamente.");
					return;
				}
				for(int i=0; i<vettoreOrdini.size();i++){
		 			if( DataSourceOrdini.vettoreOrdini.get(i).getID().equals(idOrdine) ){
		 				DataSourceOrdini.vettoreOrdini.remove(i);
		 				 break;
		 			}
		 		 }
			}
 			
		});
 		 
	 }

	public static Ordine getOrdineByID(String idordine) {
		for(int i=0; i<vettoreOrdini.size(); i++){
			if(idordine.equals( vettoreOrdini.get(i).getID() )){
				return vettoreOrdini.get(i);
			}
		}
		return null;
	}

	public boolean isReady() {
		return ready;
	}
}
