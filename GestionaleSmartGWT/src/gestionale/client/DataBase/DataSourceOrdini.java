package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DB;
import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.shell.CloseButton.Callback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.CriteriaPolicy;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceOrdini extends DataSource{
	
	private static Vector<Ordine> vettoreOrdini;
	private static RecordOrdini[] records;
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceOrdini istance;
	
	public static DataSourceOrdini getIstance(){
		if (istance == null) {  
            istance = new DataSourceOrdini();
            //istance.setCanMultiSort(false);
        } 
		return istance;
	}
	
	public DataSourceOrdini(){
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id","id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		
		DataSourceIntegerField idnField = new DataSourceIntegerField("idn","idn");

		DataSourceTextField datacreazioneordineField = new DataSourceTextField("datacreazioneordine","datacreazioneordine");
		DataSourceTextField datainvioordineField = new DataSourceTextField("datainvioordine","datainvioordine");
		DataSourceTextField datapartenzamerceField = new DataSourceTextField("datapartenzamerce","datapartenzamerce");
		DataSourceTextField noteField = new DataSourceTextField("note","note");
		DataSourceTextField idtrasportatoreField = new DataSourceTextField("idtrasportatore","idtrasportatore");
		DataSourceTextField convalidatoField = new DataSourceTextField("convalidato","convalidato");
		DataSourceTextField tipoordineField = new DataSourceTextField("tipoordine","tipoordine");
		 
        setFields(idField, idnField, datacreazioneordineField, datainvioordineField, datapartenzamerceField, noteField, idtrasportatoreField, convalidatoField, tipoordineField); 
		
		setClientOnly(true);
		getNewRecords();
	}

	

	 public static void getNewRecords() {
		 
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
						record.setAttribute("convalidato",ordine.getConvalidato());
						record.setAttribute("tipoordine",ordine.getTipoOrdine());
						
						istance.addData(record);
						
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
