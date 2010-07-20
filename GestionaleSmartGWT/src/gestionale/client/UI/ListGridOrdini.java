package gestionale.client.UI;

import gestionale.client.DB;
import gestionale.client.Liste;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class ListGridOrdini extends ListGrid{
	
	private  ListGridOrdini lgo;
	
	public ListGridOrdini(){
		super();
		
		this.setWidth100();
		this.setHeight100(); 
		this.setShowEdges(false);
		this.setData(DataSourceOrdini.getRecords(this));
		this.setCanReorderRecords(true);  
		this.setCanAcceptDroppedRecords(false);  
		this.setCanDragRecordsOut(true);
		
		ListGridField campo = new ListGridField("datacreazioneordine", "Ordini");  
		campo.setCanEdit(false);  
		this.setFields(campo); 
		lgo = this;
		
		
		//////////////
		this.addDoubleClickHandler(new  DoubleClickHandler() {
			
			public void onDoubleClick(DoubleClickEvent event) {
				//System.out.println("Click: "+ event.getNode().getTitle());
				Menu menu = new Menu();
				
				MenuItem mi_dettagli = new MenuItem("Mostra Ordine");
				MenuItem mi_modifica = new MenuItem("Modifica Ordine");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Ordine");
				
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						lgo.aggiorna();
					}
				});
				
				mi_modifica.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						Contatto contatto = null;
						for(int i=0; i<Liste.getVettoreContatti().size(); i++){
							contatto = Liste.getVettoreContatti().get(i);
							
						}
					}
				});
				
				
				
				
				
				menu.addItem( mi_dettagli );
				menu.addItem( mi_modifica );
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
			}
			
			public void onNodeClick(NodeClickEvent event) {
			}
		});
		/////////////
	}
	
	public void aggiorna(){
		this.setData(DataSourceOrdini.getRecords(this));
	}

}
