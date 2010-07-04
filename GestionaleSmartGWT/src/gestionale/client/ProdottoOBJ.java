package gestionale.client;


import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;



public class ProdottoOBJ {
	
	private final static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	
	private static Vector<String[]> tabCategoria = null;
	private static Vector<String[]> tabTipologia = null;
	private static Vector<String[]> tabVarieta = null;
	private static Vector<String[]> tabSottovarieta = null;
	private static Vector<String[]> tabCalibro = null;
	private static Vector<String[]> tabImballaggio = null;
	
	
	public ProdottoOBJ(){
		super();
		aggiornaFromDB();
	}
	
	public static void aggiornaFromDB(){
		
		tabCategoria = new Vector<String[]>();
		tabTipologia = new Vector<String[]>();
		tabVarieta = new Vector<String[]>();
		tabSottovarieta = new Vector<String[]>();
		tabCalibro = new Vector<String[]>();
		tabImballaggio = new Vector<String[]>();
		
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_categoria", new AsyncCallback<String[][]>(){
			
					public void onFailure(Throwable caught) {
						Window.alert("Errore: Caricamento da DB Contatti");
					}
		
					public void onSuccess(String[][] result) {
							for(int i=0; i<result.length; i++){
								tabCategoria.add( result[i] );
							}
					}	
});
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_tipologia", new AsyncCallback<String[][]>(){
	
	public void onFailure(Throwable caught) {
		Window.alert("Errore: Caricamento da DB Contatti");
	}

	public void onSuccess(String[][] result) {
			for(int i=0; i<result.length; i++){
				tabTipologia.add( result[i] );
			}
	}	
});
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_varieta", new AsyncCallback<String[][]>(){
	
	public void onFailure(Throwable caught) {
		Window.alert("Errore: Caricamento da DB Contatti");
	}

	public void onSuccess(String[][] result) {
			for(int i=0; i<result.length; i++){
				tabVarieta.add( result[i] );
			}
	}	
});
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_sottovarieta", new AsyncCallback<String[][]>(){
	
	public void onFailure(Throwable caught) {
		Window.alert("Errore: Caricamento da DB Contatti");
	}

	public void onSuccess(String[][] result) {
			for(int i=0; i<result.length; i++){
				tabSottovarieta.add( result[i] );
			}
	}	
});
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_calibro", new AsyncCallback<String[][]>(){
	
	public void onFailure(Throwable caught) {
		Window.alert("Errore: Caricamento da DB Contatti");
	}

	public void onSuccess(String[][] result) {
			for(int i=0; i<result.length; i++){
				tabCalibro.add( result[i] );
			}
	}	
});
////////////////////////////////
rpc.eseguiQuery("SELECT * FROM prodotto_imballaggio", new AsyncCallback<String[][]>(){
	
	public void onFailure(Throwable caught) {
		Window.alert("Errore: Caricamento da DB Contatti");
	}

	public void onSuccess(String[][] result) {
			for(int i=0; i<result.length; i++){
				tabImballaggio.add( result[i] );
			}
	}	
});
		

		
		//setTabCategoria( db.eseguiQuery("SELECT * FROM prodotto_categoria") );
		//setTabTipologia(db.eseguiQuery("SELECT * FROM prodotto_tipologia"));
		//setTabVarieta(db.eseguiQuery("SELECT * FROM prodotto_varieta"));
		//setTabSottovarieta(db.eseguiQuery("SELECT * FROM prodotto_sottovarieta"));
		//setTabCalibro(db.eseguiQuery("SELECT * FROM prodotto_calibro"));
		//setTabImballaggio(db.eseguiQuery("SELECT * FROM prodotto_imballaggio"));
		
		
		for(int i=0 ; i<tabCalibro.size(); i++){
			System.out.println("TREE: " + tabCalibro.get(i));
		}
		
	}
	

	public static void setTabCategoria(Vector<String[]> tabCategoria) {
		ProdottoOBJ.tabCategoria = tabCategoria;
	}

	public static Vector<String[]> getTabCategoria() {
		return tabCategoria;
	}

	public static void setTabTipologia(Vector<String[]> tabTipologia) {
		ProdottoOBJ.tabTipologia = tabTipologia;
	}

	public static Vector<String[]> getTabTipologia() {
		return tabTipologia;
	}

	public static void setTabSottovarieta(Vector<String[]> tabSottovarieta) {
		ProdottoOBJ.tabSottovarieta = tabSottovarieta;
	}

	public static Vector<String[]> getTabSottovarieta() {
		return tabSottovarieta;
	}

	public static void setTabVarieta(Vector<String[]> tabVarieta) {
		ProdottoOBJ.tabVarieta = tabVarieta;
	}

	public static Vector<String[]> getTabVarieta() {
		return tabVarieta;
	}

	public static void setTabCalibro(Vector<String[]> tabCalibro) {
		ProdottoOBJ.tabCalibro = tabCalibro;
	}

	public static Vector<String[]> getTabCalibro() {
		return tabCalibro;
	}

	public static void setTabImballaggio(Vector<String[]> tabImballaggio) {
		ProdottoOBJ.tabImballaggio = tabImballaggio;
	}

	public static Vector<String[]> getTabImballaggio() {
		return tabImballaggio;
	}

}
