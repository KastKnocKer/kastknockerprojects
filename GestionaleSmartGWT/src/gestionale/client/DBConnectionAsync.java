package gestionale.client;

import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Ordine;
import gestionale.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {
	
	public void authenticateUser(User utente, AsyncCallback<User>callback);

	public void eseguiQueryContatto(String query, AsyncCallback<Contatto[]> callback);

	public void eseguiUpdate(String query, AsyncCallback<Boolean> callback);

	public void eseguiQuery(String query, AsyncCallback<String[][]> callback);

	public void eseguiQueryOrdine(String query,AsyncCallback<Ordine[]> asyncCallback);

	public void eseguiQueryDettaglioOrdine(String query,AsyncCallback<DettaglioOrdine[]> callback);

	
}
