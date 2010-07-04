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
	private static Vector<String> 	vettoreTipoSoggetto = null;
	
	private final DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public Liste(){
		vettoreContatti = new Vector<Contatto>();
		vettoreProdotti = new Vector<Prodotto>();
		vettoreTipoSoggetto = new Vector<String>();
		aggiornaVettoreContatti();
		aggiornaVettoreTipoSoggetto();
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
	
	public void aggiornaVettoreTipoSoggetto(){
		rpc.eseguiQuery("SELECT * FROM tiposoggetto", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}

			public void onSuccess(String[][] result) {
				Vector<String> v = new Vector<String>();
				for(int i=0 ; i<result.length; i++){
					v.add(result[i][0]);
				}
				Liste.setVettoreTipoSoggetto(v);
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

	public static void setVettoreTipoSoggetto(Vector<String> vettoreTipoSoggetto) {
		Liste.vettoreTipoSoggetto = vettoreTipoSoggetto;
	}

	public static Vector<String> getVettoreTipoSoggetto() {
		return vettoreTipoSoggetto;
	}

}
