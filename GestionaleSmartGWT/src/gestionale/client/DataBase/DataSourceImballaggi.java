package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Imballaggio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceImballaggi extends DataSource{
	
	private static 	DataSourceImballaggi 	istance = null;
	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static  Vector<Imballaggio>	vettoreImballaggi = null;
	private boolean ready = false;
	
	public static DataSourceImballaggi getIstance(){
		if (istance == null) {  
            istance = new DataSourceImballaggi();  
        } 
		return istance;
	}
	
	public DataSourceImballaggi(){
		setID(id);
		vettoreImballaggi = new Vector<Imballaggio>();
		DataSourceTextField idField = new DataSourceTextField("id");  
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		
		DataSourceTextField descrizioneField = new DataSourceTextField("descrizione","Descrizione");
		descrizioneField.setCanEdit(false);
		DataSourceTextField lunghezzaField = new DataSourceTextField("lunghezza","Lunghezza (cm)");
		DataSourceTextField larghezzaField = new DataSourceTextField("larghezza","Larghezza (cm)");
		DataSourceTextField altezzaField = new DataSourceTextField("altezza","Altezza (cm)");
		DataSourceTextField taraField = new DataSourceTextField("tara","Tara (g)");
		DataSourceTextField materialeField = new DataSourceTextField("materiale","Materiale");
		DataSourceTextField marchioField = new DataSourceTextField("marchio","Marchio");
			
		setFields(idField, descrizioneField, lunghezzaField, larghezzaField, altezzaField, taraField, materialeField, marchioField); 
		
		setClientOnly(true);
		newRecords();
	}
	
	
	
	public void newRecords(){
		ready = false;
		String query = "SELECT * FROM imballaggio";
		System.out.println("Client - Eseguo query: " + query);
		rpc.eseguiQueryImballaggio(query, new AsyncCallback<Imballaggio[]>(){

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
			
			public void onSuccess(Imballaggio[] result) {
				vettoreImballaggi.removeAllElements();
				Imballaggio imballaggio = null;
				System.out.println("A");
				for(int i=0; i<result.length; i++){
					System.out.println("B");
					imballaggio = result[i];
					vettoreImballaggi.add( imballaggio );
					
					ListGridRecord lgr = new ListGridRecord();
					
					lgr.setAttribute("id", imballaggio.getID());
					lgr.setAttribute("descrizione", imballaggio.getDescrizione());
					lgr.setAttribute("lunghezza", imballaggio.getLunghezza());
					lgr.setAttribute("larghezza", imballaggio.getLarghezza());
					lgr.setAttribute("altezza", imballaggio.getAltezza());
					lgr.setAttribute("tara", imballaggio.getTara());
					lgr.setAttribute("materiale", imballaggio.getMateriale());
					lgr.setAttribute("marchio", imballaggio.getMarchio());
					
					istance.addData(lgr);
				}
				ready = true;
			}
			
		});
	}
	
	
	
	public static void aggiungiImballaggio(final Imballaggio imballaggio){
		final ListGridRecord lgr = new ListGridRecord();
		lgr.setAttribute("id", imballaggio.getID());
		lgr.setAttribute("descrizione", imballaggio.getDescrizione());
		lgr.setAttribute("lunghezza", imballaggio.getLunghezza());
		lgr.setAttribute("larghezza", imballaggio.getLarghezza());
		lgr.setAttribute("altezza", imballaggio.getAltezza());
		lgr.setAttribute("tara", imballaggio.getTara());
		lgr.setAttribute("materiale", imballaggio.getMateriale());
		lgr.setAttribute("marchio", imballaggio.getMarchio());

		
		String query = "INSERT INTO imballaggio (`Descrizione`,`Lunghezza`,`Larghezza`,`Altezza`,`Tara`,`Materiale`,`Marchio`,`Selezionato`) " +
				"VALUES ('"+imballaggio.getDescrizione()+"',"+imballaggio.getLarghezza()+","+imballaggio.getLunghezza()+","+imballaggio.getAltezza()+","+imballaggio.getTara()+",'"+imballaggio.getMateriale()+"','"+imballaggio.getMarchio()+"',"+imballaggio.getIsSelezionato()+");";

		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
			public void onSuccess(Boolean result) {
				if(result){
					String query = "SELECT MAX(ID) FROM imballaggio";

					rpc.eseguiQuery(query, new AsyncCallback<String[][]>(){

						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}
						public void onSuccess(String[][] result) {
							// TODO Auto-generated method stub
							System.out.println("GIMME POWWA LIKE AN ID: " + result[0][0]);
							imballaggio.setID(result[0][0]);
							lgr.setAttribute("id", imballaggio.getID());
							vettoreImballaggi.add(imballaggio);
							istance.addData(lgr);
						}
						
					});
					
				}
				
			}
			
		});
		
		//ID 	Descrizione 	Lunghezza 	Larghezza 	Altezza 	Tara 	Materiale 	Marchio 	Selezionato 
		//Aggiungo al Vettore

		
		//Aggiungo ai listgridrecords
		
		
	}
	
	public static void rimuoviImballaggio(ListGridRecord record){
		String query = "DELETE FROM imballaggio WHERE ID = '"+record.getAttribute("id")+"'";
		
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				Window.alert("Chiamata fallita!");
			}

			public void onSuccess(Boolean result) {
				if(result){
					//OK
				}else{
					Window.alert("La procedura di rimozione potrebbe non esser andata a buon fine!!");
				}
			}
				
				
		});
	}
	
	public static Vector<Imballaggio> getVettoreImballaggi(){
		return vettoreImballaggi;
	}

	public boolean isReady() {
		return ready;
	}
	


	
}
