package gestionale.client.GUI;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class Menubar extends MenuBar{
	

	
	public Menubar(){
		super();
		
		MenuBar fileMenu = new MenuBar(true);
		fileMenu.addItem("the", onKeyUp());
		fileMenu.addItem("foo", onKeyUp());
		fileMenu.addSeparator();
		fileMenu.addItem("Esci", onKeyUp());

	    MenuBar visualizzaMenu = new MenuBar(true);
	    visualizzaMenu.addItem("Lalala", onKeyUp());
	    visualizzaMenu.addItem("bar", onKeyUp());
	    visualizzaMenu.addItem("menu", onKeyUp());

	    MenuBar helpMenu = new MenuBar(true);
	    helpMenu.addItem("the", onKeyUp());
	    helpMenu.addItem("baz", onKeyUp());
	    helpMenu.addItem("menu", onKeyUp());

	    // Make a new menu bar, adding a few cascading menus to it.
	    
	    this.addItem("File", fileMenu);
	    this.addItem("Visualizza", visualizzaMenu);
	    this.addItem("?", helpMenu);
		
		
	}
	



	private Command onKeyUp() {
		// TODO Auto-generated method stub
		return null;
	}




}
