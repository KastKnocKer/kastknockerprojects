package gestionale.client;


import gestionale.client.GUI.GUI;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gestionale implements EntryPoint {

	public void onModuleLoad() {
	
	//INIZIALIZZAZIONI
	new GUI();
	GUI.visualizzaLoginPanel();
	//GUI.enterAfterLogin();
	//new DB().eseguiQueryContatto("SELECT * FROM contatti");
	Liste l = new Liste();
	
	}
	
}
