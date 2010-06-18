package gestionale.client.GUI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import gestionale.shared.User;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class Liste {
	
	private static Vector<Contatto> listaContatti = null;
	
	private static Vector vettoreTemporaneo = null;
	
	private static DBConnectionAsync rpc;	/*Per connessione al DB*/
	private Liste l=null;
	
	public Liste(){
		setL(this);
		rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		
	}

	public Vector<Contatto> getListaContatti(String query) {
		
		rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		
		
		
		Vector<Contatto> vector = new Vector<Contatto>();
		String[] record = null;
		Contatto contatto = null;
		for(int i=0; i<vettoreTemporaneo.size(); i++){
			record = (String[]) vettoreTemporaneo.get(i);
			
			contatto = new Contatto();
			contatto.setRagioneSociale(record[1]);
			
			vector.add(contatto);
		}
		
		
		return vector;
	}

	public Vector<Contatto> getListaContatti() {
		return listaContatti;
	}
	
	
	
	
	public void setL(Liste l) {
		this.l = l;
	}
	public Liste getL() {
		return l;
	}




	
	
}
