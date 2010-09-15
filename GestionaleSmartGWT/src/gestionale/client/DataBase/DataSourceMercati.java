package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceMercati extends DataSource{
	
	private static DataSourceMercati 	istance = null;
	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static Vector<Contatto>	vettoreFornitori = null;
	private static Vector<Contatto>	vettoreTrasportatori = null;
	private static String[] tipoMercato = null;
	private static String[][] mercati = null;
	
	private boolean ready = false;
	private boolean ready1 = false;
	private boolean ready2 = false;
	
	public static DataSourceMercati getIstance(){
		if (istance == null) {  
            istance = new DataSourceMercati();  
        } 
		return istance;
	}
	
	public DataSourceMercati(){
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id");  
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		
		DataSourceTextField tipomercatoField = new DataSourceTextField("tipomercato","Tipo Mercato");
		DataSourceTextField mercatoField = new DataSourceTextField("mercato","Mercato");
		  
        setFields(idField, tipomercatoField, mercatoField); 
		
		setClientOnly(true);
		newRecords();
	}
	
	
	public void newRecords(){
			ready = false;
			rpc.eseguiQuery("SELECT DISTINCT TipoMercato FROM mercati;", new AsyncCallback<String[][]>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(String[][] result) {
					if(result == null) return;
					if(result.length == 0) return;
					
					String[] tipmer = new String[result.length];
					for(int i=0;i<result.length; i++){
						tipmer[i] = result[i][0];
					}
					tipoMercato = tipmer;
				}
			});
			
			rpc.eseguiQuery("SELECT * FROM mercati;", new AsyncCallback<String[][]>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(String[][] result) {
					if(result == null) return;
					if(result.length == 0) return;
					
					mercati = result;
				}
			});

			
			
	}
	
	public static String[] getMercatiMap(String tipomercato){
		String[][] matrix = new String[mercati.length][];
		if(mercati == null) return null;
		int indice = 0;
		for(int i=0; i<mercati.length; i++){
			String[] rec = mercati[i];
			if(rec[2].equals(tipomercato)){  
				matrix[indice] = rec;
				indice++;
			}
			
		}
		
		String[] map = new String[indice];
		for(int i=0; i<indice; i++){
			map[i] = matrix[i][1];
		}
		return map;
	}
	


	public static String[] getTipoMercato() {
		return tipoMercato;
	}
	
	public static String getIDMercato(String mercato, String tipomercato){
		for(int i=0; i<mercati.length; i++){
			if( mercati[i][1].equals(mercato) ){
				if( mercati[i][2].equals(tipomercato) ) return mercati[i][0];
			}
		}
		return null;
	}
	
	public static String[] getMercatoFromID(String idmercato){
		String[] mercato = new String[2];
		for(int i=0; i<mercati.length;i++){
			if(mercati[i][0].equals(idmercato)){
				mercato[0] = mercati[i][1];
				mercato[1] = mercati[i][2];
				break;
			}
		}
		return mercato;
	}

	
	
}
