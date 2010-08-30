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

public class DataSourceDettaglioOrdini extends DataSource{
	
	private static Vector<Ordine> vettoreOrdini;
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceDettaglioOrdini istance;
	
	private String idOrdine;
	
	public DataSourceDettaglioOrdini(String idOrdine){
		this.idOrdine = idOrdine;
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id","id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		
		DataSourceIntegerField idnField = new DataSourceIntegerField("idn","idn");

		DataSourceTextField idordineField = new DataSourceTextField("idOrdine","idOrdine");
		DataSourceTextField idprodottoField = new DataSourceTextField("idProdotto","idProdotto");
		DataSourceTextField idclienteField = new DataSourceTextField("idCliente","idCliente");
		DataSourceTextField idimballaggioField = new DataSourceTextField("idImballaggio","idImballaggio");
		DataSourceTextField quantitaField = new DataSourceTextField("quantita","quantita");
		DataSourceTextField userField = new DataSourceTextField("user","user");
		 
        setFields(idField, idnField, idordineField, idprodottoField, idclienteField, idimballaggioField, quantitaField, userField); 
		
		setClientOnly(true);
		this.getNewRecords();
	}

	

	 public void getNewRecords() {
		 
		 String query = "SELECT * FROM ordine_dettaglio WHERE IDOrdine = " + idOrdine;
		 
			rpc.eseguiQuery(query,new AsyncCallback<String[][]>(){

				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Dettaglio Ordini");
				}

				public void onSuccess(String[][] result) {
					
					String[] record = null;
					ListGridRecord lgr = null;
					
					for(int i=0; i<result.length; i++){
						record = result[i];
						lgr = new ListGridRecord();
						
						lgr.setAttribute("id", record[0]);
						lgr.setAttribute("idOrdine", record[1]);
						lgr.setAttribute("idProdotto", record[2]);
						lgr.setAttribute("idCliente", record[3]);
						lgr.setAttribute("idImballaggio", record[5]);
						lgr.setAttribute("quantita", record[4]);
						lgr.setAttribute("user", record[6]);
								
						addData(lgr);
					}
				}
			});
	}
	 
	 
	 public static void aggiungiOrdine(final Ordine ordine){
		 
		 String query = "INSERT INTO ordini (`DataCreazioneOrdine`,`DataInvioOrdine`,`DataPartenzaMerce`,`Note`,`IDTrasportatore`,`Convalidato`,`TipoOrdine`) VALUES ('"+ordine.getDataCreazioneOrdine()+"','"+ordine.getDataInvioOrdine()+"','"+ordine.getDataPartenzaMerce()+"','"+ordine.getNote()+"','"+ordine.getIDTrasportatore()+"','"+ordine.getConvalidato()+"','"+ordine.getTipoOrdine()+"')";
			
		 rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(Boolean result) {
				String query = "SELECT * FROM ordini WHERE DataCreazioneOrdine = '"+ordine.getDataCreazioneOrdine()+"' AND " +
						"DataInvioOrdine = '"+ordine.getDataInvioOrdine()+"' AND " +
								"DataPartenzaMerce = '"+ordine.getDataPartenzaMerce()+"' AND " +
										"Note = '"+ordine.getNote()+"' AND " +
												"IDTrasportatore = '"+ordine.getIDTrasportatore()+"' AND " +
														"Convalidato = 0 AND " +
																"TipoOrdine = '"+ordine.getTipoOrdine()+"' ";
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
							record.setAttribute("convalidato",ordine.getConvalidato());
							record.setAttribute("tipoordine",ordine.getTipoOrdine());
							
							istance.addData(record);
						}
						
					}
					
				});
				
			}
				
				
		});
			
			
			
			
		 /*
		 	vettoreOrdini.add(ordine);
			
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id",ordine.getID());
			record.setAttribute("datacreazioneordine",ordine.getDataCreazioneOrdine());
			record.setAttribute("datainvioordine",ordine.getDataInvioOrdine());
			record.setAttribute("datapartenzamerce",ordine.getDataPartenzaMerce());
			record.setAttribute("note",ordine.getNote());
			record.setAttribute("idtrasportatore",ordine.getIDTrasportatore());
			record.setAttribute("convalidato",ordine.getConvalidato());
			record.setAttribute("tipoordine",ordine.getTipoOrdine());
			
			istance.addData(record);
			*/
			
	 }

	
}
