package gestionale.client.UI;

import gestionale.client.Liste;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.shared.Contatto;
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

public class ListGridDettaglioOrdini extends ListGrid{
	
	private  ListGridDettaglioOrdini lgo;
	
	public ListGridDettaglioOrdini(String IDOrdine){
		super();
		
		this.setWidth100();
		this.setHeight100(); 
		this.setShowEdges(false);
		this.setDataSource(new DataSourceDettaglioOrdini(IDOrdine,null,null,DataSourceDettaglioOrdini.MOD_TabellaCompleta));
		this.setCanReorderFields(false); 
		this.setCanAcceptDroppedRecords(false);  
		this.setCanDragRecordsOut(false);
		
		ListGridField campo = new ListGridField("datacreazioneordine", "Ordini");
		ListGridField idcampo = new ListGridField("idn", "ID");
		campo.setCanEdit(false);
		idcampo.setHidden(true);
		//this.setFields(campo,idcampo);
		this.fetchData();
		//this.sort("idn", SortDirection.DESCENDING);
		this.setCanSort(false);
		this.setCanDrag(false);
		
		//////////////
		/*
		this.addRowContextClickHandler(new RowContextClickHandler() {
			
			public void onRowContextClick(RowContextClickEvent event) {

				Menu menu = new Menu();
				
				MenuItem mi_dettagli = new MenuItem("Mostra Ordine");
				MenuItem mi_modifica = new MenuItem("Modifica Ordine");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Ordine");
				
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
					}
				});
				
				mi_modifica.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						Contatto contatto = null;
						for(int i=0; i<DataSourceContatti.getVettoreContatti().size(); i++){
							contatto = DataSourceContatti.getVettoreContatti().get(i);
							
						}
					}
				});
				
				menu.addItem( mi_dettagli );
				menu.addItem( mi_modifica );
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
				
			}
		});
		
		this.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				
				System.out.println("Sorgente lieta: " + event.getRecord().getAttribute("id") );
				ListGridRecord record = (ListGridRecord) event.getRecord();
				
				PanelOrdine po = new PanelOrdine( record );
				
			}
		});
		
		
		*/

		
		
		
		
	}

}
