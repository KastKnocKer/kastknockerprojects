package gestionale.client.UI;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class Menubar extends ToolStrip{
	
	public Menubar(){

		Menu fileMenu = new Menu();
		Menu inserisciMenu = new Menu();
		Menu visualizzaMenu = new Menu();
		Menu modificaMenu = new Menu();
		Menu helpMenu = new Menu();
		
		
		ToolStripMenuButton fileButton = 		new ToolStripMenuButton("File", fileMenu);
		ToolStripMenuButton inserisciButton = 	new ToolStripMenuButton("Inserisci", inserisciMenu);
		ToolStripMenuButton visualizzaButton =	new ToolStripMenuButton("Visualizza", visualizzaMenu);
	    ToolStripMenuButton modificaButton = 	new ToolStripMenuButton("Modifica", modificaMenu);
	    ToolStripMenuButton helpButton = 		new ToolStripMenuButton("?", helpMenu);

	    this.addMember(fileButton);
	    this.addMember(inserisciButton);
	    this.addMember(visualizzaButton);
	    this.addMember(modificaButton);
	    this.addMember(helpButton);

	    this.setWidth100();
	    this.setVisible(true);
	    this.setKeepInParentRect(false);
	    
	    
	    
	    MenuItem nuovoContattoMI =new MenuItem("Nuovo contatto");
	    nuovoContattoMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				new PanelContatti(null);
			}
		});
	    
	    MenuItem visualGestProdottiMI =new MenuItem("Gestione Prodotti");
	    visualGestProdottiMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Gestione Prodotti", new PanelInserimentoProdotti(), true);
			}
		});
	    
	    
	    
	    
	    inserisciMenu.addItem( nuovoContattoMI );
	    visualizzaMenu.addItem( visualGestProdottiMI );
	    

	    
	}

}
