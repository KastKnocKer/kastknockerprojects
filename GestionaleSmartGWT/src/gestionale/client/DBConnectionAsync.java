package gestionale.client;

import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Imballaggio;
import gestionale.shared.Ordine;
import gestionale.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {
	
	public void authenticateUser(User utente, AsyncCallback<User> asyncCallback);

	public void eseguiQueryContatto(String query, AsyncCallback<Contatto[]> asyncCallback);

	public void eseguiQueryOrdine(String query,AsyncCallback<Ordine[]> asyncCallback);

	public void eseguiQueryDettaglioOrdine(String query,AsyncCallback<DettaglioOrdine[]> asyncCallback);
	
	public void eseguiQueryImballaggio(String query,AsyncCallback<Imballaggio[]> asyncCallback);
	
	public void eseguiUpdate(String query, AsyncCallback<Boolean> asyncCallback);

	public void eseguiQuery(String query, AsyncCallback<String[][]> asyncCallback);


	

	

	
}
