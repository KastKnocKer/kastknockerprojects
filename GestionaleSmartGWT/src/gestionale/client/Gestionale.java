package gestionale.client;


import gestionale.client.UI.GUIManager;
import com.google.gwt.core.client.EntryPoint;



public class Gestionale implements EntryPoint {

	public void onModuleLoad() {
		
		GUIManager GUI = new GUIManager(); //Inizializzazione
		GUIManager.visualizzaLogin();
		//GUIManager.enterAfterLogin();
		
	}
	
	
	
	
	
	
}
