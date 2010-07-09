package gestionale.client;

import java.sql.ResultSet;
import java.util.Vector;

import gestionale.shared.Contatto;
import gestionale.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DBConnection")
public interface DBConnection extends RemoteService {
	
	public User authenticateUser(User utente);
	
	public Contatto[] eseguiQueryContatto(String query);

	public String[][] eseguiQuery(String query);

	public boolean eseguiUpdate(String query);
}