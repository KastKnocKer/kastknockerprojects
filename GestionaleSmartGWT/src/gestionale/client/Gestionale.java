package gestionale.client;


import gestionale.client.UI.GUIManager;
import gestionale.client.UI.Menubar;
import gestionale.client.UI.PanelContatti;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;



public class Gestionale implements EntryPoint {

	public void onModuleLoad() {
		
		GUIManager GUI = new GUIManager(); //Inizializzazione
		GUIManager.visualizzaLogin();
		//GUIManager.enterAfterLogin();
		
	
		Liste l = new Liste();
		new ProdottoOBJ();
	}
	
	
	
	
	
	
}
