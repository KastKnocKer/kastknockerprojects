package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceOrdini;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class ListGridOrdini extends ListGrid{
	
	private ListGridOrdini thisListGrid;
	private ListGridRecord selectedRecord;
	
	public ListGridOrdini(){
		super();
		thisListGrid = this;
		this.setWidth100();
		this.setHeight100(); 
		this.setShowEdges(false);
		this.setDataSource(DataSourceOrdini.getIstance());
		this.setCanReorderFields(false); 
		this.setCanAcceptDroppedRecords(false);  
		this.setCanDragRecordsOut(false);
		
		ListGridField campo = new ListGridField("descrizioneordine", "Ordini");
		ListGridField idcampo = new ListGridField("idn", "ID");
		campo.setCanEdit(false);
		idcampo.setHidden(true);
		this.setFields(campo,idcampo);
		this.fetchData();
		this.sort("idn", SortDirection.DESCENDING);
		this.setCanSort(false);
		this.setCanDrag(false);
		
		//////////////
		
		this.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				
				ListGridRecord record = (ListGridRecord) event.getRecord();
				if( record.getAttribute("tipoordine").equals("Ordinario") ){
					PanelOrdine po = new PanelOrdine( record );
				}else{
					PanelOrdineSpeciale po = new PanelOrdineSpeciale( record );
				}
				
			}
		});
		
		
		
		
		
		this.addRowContextClickHandler(new RowContextClickHandler() {
			
			public void onRowContextClick(RowContextClickEvent event) {
				
				selectedRecord = (ListGridRecord) event.getRecord();
				
				Menu menu = new Menu();
				
				MenuItem mi_modifica = new MenuItem("Modifica Ordine");
				MenuItem mi_dettagli = new MenuItem("Proprieta' Ordine");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Ordine");
				
				
				mi_modifica.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						if( selectedRecord.getAttribute("tipoordine").equals("Ordinario") ){
							PanelOrdine po = new PanelOrdine( selectedRecord );
						}else{
							PanelOrdineSpeciale po = new PanelOrdineSpeciale( selectedRecord );
						}
					}
				});
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						new WindowProprietaOrdine(selectedRecord);
					}
				});
				
				mi_rimuovi.addClickHandler( new ClickHandler() {
					
					@Override
					public void onClick(MenuItemClickEvent event) {
						String message = "Sei sicuro di rimuovere l'ordine: " + selectedRecord.getAttribute("datacreazioneordine") +" - "+ selectedRecord.getAttribute("tipoordine")+" ?";
						if( Window.confirm(message) ){
							DataSourceOrdini.rimuoviOrdine( selectedRecord.getAttribute("id") );
							thisListGrid.removeData(selectedRecord);
						}else{
							
						}
						
					}
				});
				menu.addItem( mi_modifica );
				menu.addItem( mi_dettagli );
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
				
			}
		});
		
		
		
		
		

		
		
		
		
	}

}
