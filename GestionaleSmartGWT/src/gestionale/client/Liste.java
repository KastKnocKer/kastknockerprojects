package gestionale.client;

import gestionale.shared.Contatto;
import gestionale.shared.Ordine;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Liste {
	
	private static Vector<Contatto> vettoreContatti = null;
	private static Vector<Prodotto> vettoreProdotti = null;
	private static Vector<String> 	vettoreTipoSoggetto = null;
	private static Vector<Ordine> 	vettoreOrdini = null;
	
	private final DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public Liste(){
		vettoreContatti = new Vector<Contatto>();
		vettoreProdotti = new Vector<Prodotto>();
		vettoreTipoSoggetto = new Vector<String>();
		//aggiornaVettoreContatti();
		aggiornaVettoreTipoSoggetto();
		//aggiornaVettoreOrdini();
	}
	
	
	public void aggiornaVettoreTipoSoggetto(){/*
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
		});*/
		vettoreTipoSoggetto = new Vector<String>();
		vettoreTipoSoggetto.add("Cliente");
		vettoreTipoSoggetto.add("Fornitore");
		vettoreTipoSoggetto.add("Intermediario");
		vettoreTipoSoggetto.add("Trasportatore");
		
	}

	

	public static Vector<String> getVettoreTipoSoggetto() {
		return vettoreTipoSoggetto;
	}

}
