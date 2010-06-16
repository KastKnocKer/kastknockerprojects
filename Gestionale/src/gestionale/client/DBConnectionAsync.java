package gestionale.client;

import java.sql.ResultSet;
import java.util.Vector;

import gestionale.shared.Contatto;
import gestionale.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {
	
	public void authenticateUser(User utente, AsyncCallback<User>callback);

	public void getContatto(String query, AsyncCallback<Contatto[]> callback);
	
}
