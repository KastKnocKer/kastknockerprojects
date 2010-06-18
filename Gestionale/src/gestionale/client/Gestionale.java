package gestionale.client;

import gestionale.client.GUI.GUI;
import com.google.gwt.core.client.EntryPoint;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gestionale implements EntryPoint {

	public void onModuleLoad() {
	
	//INIZIALIZZAZIONI
	new GUI();
	//GUI.visualizzaLoginPanel();
	GUI.enterAfterLogin();
	

	}
	
}
