package gestionale.client;

import gestionale.shared.Contatto;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Liste {
	
	private static Vector<Contatto> vettoreContatti = null;
	private static Vector<Prodotto> vettoreProdotti = null;
	
	private final DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public Liste(){
		aggiornaVettoreContatti();
	}
	
	public void aggiornaVettoreContatti(){
		String query = "SELECT * FROM contatti ORDER BY RagioneSociale";
		rpc.eseguiQueryContatto(query,new AsyncCallback<Contatto[]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}

			public void onSuccess(Contatto[] result) {
				Vector<Contatto> v = new Vector<Contatto>();
				
				for(int i=0; i<result.length; i++){
					v.add(result[i]);
				}
				
				Liste.setVettoreContatti(v);
			}
			
		});
		
		
	}

	public static void setVettoreContatti(Vector<Contatto> vettoreContatti) {
		Liste.vettoreContatti = vettoreContatti;
	}

	public static Vector<Contatto> getVettoreContatti() {
		return vettoreContatti;
	}

	public static void setVettoreProdotti(Vector<Prodotto> vettoreProdotti) {
		Liste.vettoreProdotti = vettoreProdotti;
	}

	public static Vector<Prodotto> getVettoreProdotti() {
		return vettoreProdotti;
	}

}
