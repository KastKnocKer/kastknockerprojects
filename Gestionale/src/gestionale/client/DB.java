package gestionale.client;

import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class DB {
	
	public static Vector vettoreTemporaneo = null;
	public static Contatto[] arrayContatto = null;
	public static Vector<Contatto> vettoreContatto = null;
	
	
	public DB(){
		
	}
//////////////////////////////////////////////////////////////////////////////////////////
	public Vector<String[]> eseguiQuery(String query){
		
		DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	    ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		
		QueryHandler<String[][]> callback = new QueryHandler<String[][]>();
		rpc.eseguiQuery(query,callback);
		
		
		return vettoreTemporaneo;
	}
	
	private class QueryHandler<T> implements AsyncCallback<String[][]> {

		public void onFailure(Throwable caught) {
			
		}

		public void onSuccess(String[][] result) {
			if(result == null) return;
			vettoreTemporaneo = new Vector<String[]>();
			
			int k=0;
			
			
			try{
				while(true){
					String s = result[0][k];
					k++;
				}
				
			}catch(Exception e){
				
			}
			
			String[] record = null;
			
			for(int i=0; i<result.length ;i++){
				System.out.println("");
				System.out.print("CLIENT: ");
				record = new String[k];
				for(int j=0 ; j<k ; j++){
					record[j]=result[i][j];
					System.out.print(record[j]+" - ");
				}
				
			}
			
			
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	
	public Contatto[] eseguiQueryContatto(String query){
		
		DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	    ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		
		AsyncCallback<Contatto[]> callback = new queryContattoHandler<Contatto[]>();
		rpc.eseguiQueryContatto(query,callback);
		
		return arrayContatto;
	}
	
	private class queryContattoHandler<T> implements AsyncCallback<Contatto[]>{

		public void onFailure(Throwable caught) {
			
		}

		public void onSuccess(Contatto[] result) {
			vettoreContatto = new Vector<Contatto>();
			for(int i=0; i<result.length; i++){
				vettoreContatto.add(result[i]);
			}
		}
		
	}
	
	

}
