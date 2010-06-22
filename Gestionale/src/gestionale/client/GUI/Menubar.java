package gestionale.client.GUI;

import gestionale.client.Liste;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class Menubar extends MenuBar{
	

	
	public Menubar(){
		super();
		
		
		MenuBar fileMenu = new MenuBar(true);
		//fileMenu.addItem("the", onKeyUp());
		//fileMenu.addItem("foo", onKeyUp());
		fileMenu.addSeparator();
		fileMenu.addItem("Esci", onKeyUp());

	    MenuBar visualizzaMenu = new MenuBar(true);
	    //visualizzaMenu.addItem("Lalala", onKeyUp());
	    //visualizzaMenu.addItem("bar", onKeyUp());
	    //visualizzaMenu.addItem("menu", onKeyUp());
	    
	    MenuBar modificaMenu = new MenuBar(true);
	    modificaMenu.addItem("Aggiungi contatto", openDialogBoxGestioneContatti);
	    //modificaMenu.addItem("bar", onKeyUp());
	    //modificaMenu.addItem("menu", onKeyUp());

	    MenuBar helpMenu = new MenuBar(true);
	    //helpMenu.addItem("the", onKeyUp());
	    //helpMenu.addItem("baz", onKeyUp());
	    //helpMenu.addItem("menu", onKeyUp());

	    // Make a new menu bar, adding a few cascading menus to it.
	    
	    this.addItem("File", fileMenu);
	    this.addItem("Visualizza", visualizzaMenu);
	    this.addItem("Modifica", modificaMenu);
	    this.addItem("?", helpMenu);
		
		
	}
	



	private Command onKeyUp() {
		return null;
	}
	
	
	

	Command openDialogBoxGestioneContatti = new Command() {
	      
	      public void execute() {
	    	new PanelGestioneContattiMain(Liste.getVettoreContatti().get(0));
			System.out.println("KISSSSSSSSSSSSSSSSSSSSs");
	      }
	};



}
