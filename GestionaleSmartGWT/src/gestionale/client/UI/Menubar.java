package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.shared.Contatto;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
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
	    
	    MenuItem nuovoOrdineMI =new MenuItem("Nuovo ordine");
	    nuovoOrdineMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				final Finestra finestra = new Finestra();
				finestra.setAutoSize(true);
				finestra.setTitle("Nuovo ordine");
				finestra.setIsModal(true);
				finestra.setShowModalMask(true);
				finestra.setCanDragReposition(true);  
				finestra.setCanDragResize(false);
				finestra.centerInPage();
				PanelCreazioneOrdine pco = new PanelCreazioneOrdine();
				pco.setFinestra(finestra);
				finestra.addItem(pco);
				finestra.draw();
				
				finestra.addCloseClickHandler(new CloseClickHandler() {  
					public void onCloseClick(CloseClientEvent event) {
						finestra.destroy();
						}  
						});  
			}
		});
	    
	    MenuItem visualGestProdottiMI =new MenuItem("Gestione Prodotti");
	    visualGestProdottiMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Gestione Prodotti", new PanelInserimentoProdotti(), true);
			}
		});
	    
	    MenuItem visualCatalogoProdottiMI =new MenuItem("Catalogo Prodotti");
	    visualCatalogoProdottiMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Catalogo Prodotti", new ListGridProdotti(), true);
			}
		});
	    
	    MenuItem visualGestImballaggiMI =new MenuItem("Gestione Imballaggi");
	    visualGestImballaggiMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Gestione Imballaggi", new PanelGestioneImballaggi(), true);
			}
		});
	    
	    
	    
	    
	    inserisciMenu.addItem( nuovoContattoMI );
	    inserisciMenu.addItem( nuovoOrdineMI );
	    
	    visualizzaMenu.addItem( visualGestProdottiMI );
	    visualizzaMenu.addItem( visualCatalogoProdottiMI );
	    visualizzaMenu.addItem( visualGestImballaggiMI );
	    

	    
	}

}
