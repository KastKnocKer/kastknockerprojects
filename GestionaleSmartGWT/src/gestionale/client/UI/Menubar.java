package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	
	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public Menubar(){

		Menu fileMenu = new Menu();
		Menu inserisciMenu = new Menu();
		Menu visualizzaMenu = new Menu();
		Menu modificaMenu = new Menu();
		Menu helpMenu = new Menu();
		
		
		ToolStripMenuButton fileButton = 		new ToolStripMenuButton("File", fileMenu);
		ToolStripMenuButton inserisciButton = 	new ToolStripMenuButton("Inserisci", inserisciMenu);
		ToolStripMenuButton visualizzaButton =	new ToolStripMenuButton("Visualizza", visualizzaMenu);
	    //ToolStripMenuButton modificaButton = 	new ToolStripMenuButton("Modifica", modificaMenu);
	    ToolStripMenuButton helpButton = 		new ToolStripMenuButton("?", helpMenu);

	    
	    this.addMember(fileButton);
	    this.addMember(inserisciButton);
	    this.addMember(visualizzaButton);
	    //this.addMember(modificaButton);
	    this.addMember(helpButton);

	    this.setWidth100();
	    this.setVisible(true);
	    this.setKeepInParentRect(false);
	    
	    MenuItem logOutMI =new MenuItem("Logout");
	    logOutMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.clearGUI();
				GUIManager.visualizzaLogin();
			}
		});
	    
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
				PanelInserimentoOrdine pco = new PanelInserimentoOrdine();
				pco.setFinestra(finestra);
				finestra.addItem(pco);
				finestra.draw();
				finestra.centerInPage();
				
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
	    
	    MenuItem visualGiacenzaMagazzinoMI =new MenuItem("Giacenza magazzino");
	    visualGiacenzaMagazzinoMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Giacenza Magazzino", new PanelGestioneMagazzino(), true);
			}
		});
	    
	    MenuItem visualMappaGoogleMapsMI =new MenuItem("Mappa (Google Maps)");
	    visualMappaGoogleMapsMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Mappa (Google Maps)", new PanelMappaGoogleMaps(), true);
			}
		});
	    
	    MenuItem visualListaFileMI =new MenuItem("Lista documenti");
	    visualListaFileMI.addClickHandler( new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {

				rpc.getListaDocumenti(new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						if(result == null) return;
						Label label = new Label();
						label.setContents(result);
						Finestra finestra = new Finestra();
						finestra.setTitle("Lista documenti");
						finestra.setWidth(300);
						finestra.setHeight(300);
						finestra.addItem(label);
						finestra.centerInPage();
						finestra.draw();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			}
		});
	    
	    MenuItem visualAssociazioneCPMI =new MenuItem("Associazione FornitoreProdotti");
	    visualAssociazioneCPMI.addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Associazione FornitoreProdotti", new PanelAssociazioneFornitoriProdotti(), true);
			}
		});
	    
	    MenuItem visualAssociazioneCPalletMI =new MenuItem("Associazione FornitorePallet");
	    visualAssociazioneCPalletMI.addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				GUIManager.addToTabPanel("Associazione FornitorePallet", new PanelAssociazioneFornitoriPallet(), true);
			}
		});
	    
	    
	    fileMenu.addItem(logOutMI);
	    
	    inserisciMenu.addItem( nuovoContattoMI );
	    inserisciMenu.addItem( nuovoOrdineMI );
	    
	    visualizzaMenu.addItem( visualGestProdottiMI );
	    visualizzaMenu.addItem( visualCatalogoProdottiMI );
	    visualizzaMenu.addItem( visualGestImballaggiMI );
	    visualizzaMenu.addItem( visualGiacenzaMagazzinoMI );
	    visualizzaMenu.addItem( visualMappaGoogleMapsMI );
	    visualizzaMenu.addItem( visualListaFileMI );
	    visualizzaMenu.addItem( visualAssociazioneCPMI );
	    visualizzaMenu.addItem( visualAssociazioneCPalletMI );
	    
	    

	    
	}

}
