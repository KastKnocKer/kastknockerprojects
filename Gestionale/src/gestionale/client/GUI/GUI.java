package gestionale.client.GUI;

import gestionale.client.Liste;

import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

public class GUI {
	
	static private Menubar MB = null;
	static private PanelLogin PL = null;
	static private PanelMain PM = null;
	
	
	public GUI(){
		
		
		PL = new PanelLogin();
		
		

	}
	
	public static void enterAfterLogin(){
		MB = new Menubar();
		PM = new PanelMain();
		clearGUI();
		RootPanel.get().add(MB);
		RootPanel.get().add(PM);
		
	}
	
	
	public static void visualizzaMenuBar(){
		//RootPanel.get().add(MB);
		RootLayoutPanel.get().add(MB);
	}
	
	public static void visualizzaLoginPanel(){
		PL.visualizzaPLogin();
	}
	
	public static void clearGUI(){
		RootPanel.get().clear();
		RootPanel.get("centro0").clear();
		RootPanel.get("centro1").clear();
	}

}
