package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.Liste;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;

public class DataSourceOrdini extends DataSource{
	
private static RecordOrdini[] records;

	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);



	public static RecordOrdini[] getRecords(ListGrid lg) {  
		if (records == null) {  
		getNewRecords(lg);  
		}
		return records;  
	}
	

	 public static void getNewRecords(final ListGrid lg) {
		 
		 String query = "SELECT * FROM ordini";
		 
			rpc.eseguiQueryOrdine(query,new AsyncCallback<Ordine[]>(){
				
				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Ordini");
				}

				public void onSuccess(Ordine[] result) {
					records = new RecordOrdini[result.length];
					
					for(int i=0; i<result.length; i++){
						records[i] = new RecordOrdini( result[i] );
					}
					
					if(lg!=null) lg.setData(records);
					
				}
				
			});
		 
		 
		
	}
	 
	 
	 /*
	   public static RecordOrdini[] getNewRecords() {
		 Vector<Ordine> v = Liste.getVettoreOrdini();
		 RecordOrdini[] records = new RecordOrdini[v.size()];
		        
		 for(int i=0; i<v.size(); i++){
			 records[i] = new RecordOrdini( v.get(i) );
		 }
		 
		  return records;
	}
	  */

	
}
