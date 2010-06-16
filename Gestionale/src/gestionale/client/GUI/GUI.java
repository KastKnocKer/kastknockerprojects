package gestionale.client.GUI;

import com.google.gwt.user.client.ui.RootPanel;

public class GUI {
	
	static private Menubar MB = null;
	static private PanelLogin PL = null;
	static private PanelMain PM = null;
	
	
	public GUI(){
		
		MB = new Menubar();
		PL = new PanelLogin();
		PM = new PanelMain();
		

	}
	
	public static void enterAfterLogin(){
		clearGUI();
		RootPanel.get().add(MB);
		RootPanel.get().add(PM);
		
	}
	
	
	public static void visualizzaMenuBar(){
		RootPanel.get().add(MB);
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
