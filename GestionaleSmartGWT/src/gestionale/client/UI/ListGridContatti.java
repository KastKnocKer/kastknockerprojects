package gestionale.client.UI;


import gestionale.client.DataBase.DataSourceContatti;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.Label;
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

public class ListGridContatti extends ListGrid{
	
	private static ListGridRecord lastContactClicked;

	private ListGridContatti thisGrid;
	
	private ListGridField nameField;
	private ListGridField tiposoggettoField;
	
	public ListGridContatti(){
		super();
		thisGrid = this;
		this.setWidth100();
		this.setHeight100(); 
		this.setShowEdges(false);
		setGroupStartOpen(GroupStartOpen.NONE);
		setGroupByField("tiposoggetto");
		setDataSource(DataSourceContatti.getIstance());
		this.setCanReorderFields(false); 
		this.setCanAcceptDroppedRecords(false);  
		this.setCanDragRecordsOut(false);
		
		nameField = new ListGridField("ragionesociale", "Ragione Sociale");
		nameField.setCanEdit(false);
		tiposoggettoField = new ListGridField("tiposoggetto", "Tipo Soggetto");
		tiposoggettoField.setCanEdit(false);
		tiposoggettoField.setHidden(true);
		setFields(nameField, tiposoggettoField);
		
		this.setAutoFetchData(true);
		this.sort("ragionesociale", SortDirection.ASCENDING);
		this.setCanDrag(false);
		
		
		
		//CLICK DESTRO
		addRowContextClickHandler(new RowContextClickHandler() {
			
			public void onRowContextClick(RowContextClickEvent event) {
				lastContactClicked = event.getRecord();
				System.out.println("Contatto cliccato: " + lastContactClicked );
				Menu menu = new Menu();
				
				MenuItem mi_dettagli = new MenuItem("Mostra dettagli");
				MenuItem mi_modifica = new MenuItem("Modifica Contatto");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Contatto");
				
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						
						Finestra window = new Finestra();  
						window.setTitle(lastContactClicked.getAttribute("ragionesociale"));
						Contatto contatto = null;
						for(int i=0; i<DataSourceContatti.getVettoreContatti().size(); i++){
							contatto = DataSourceContatti.getVettoreContatti().get(i);
							if( contatto.getRagioneSociale().equals(lastContactClicked.getAttribute("ragionesociale"))){
								window.addItem(new Label(contatto.getHtmlText()));
								break;
							}
						}
						window.setWidth(300);  
						window.setHeight(230);
						window.setCanDragReposition(true);  
						window.setCanDragResize(true);  
						window.centerInPage();
						window.draw();
						
					}
				});
				
				mi_modifica.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						Contatto contatto = null;
						for(int i=0; i<DataSourceContatti.getVettoreContatti().size(); i++){
							contatto = DataSourceContatti.getVettoreContatti().get(i);
							if( contatto.getRagioneSociale().equals(lastContactClicked.getAttribute("ragionesociale"))){
								new PanelContatti(contatto);
								break;
							}
						}
					}
				});
				
				mi_rimuovi.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						if( Window.confirm("Sei sicuro di voler rimuovere: "+lastContactClicked.getAttribute("ragionesociale")+"?") ){
							thisGrid.removeSelectedData();
							DataSourceContatti.rimuoviContatto(lastContactClicked);
					
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
		
		//DCLICK
		addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				lastContactClicked = (ListGridRecord) event.getRecord();
				
				Finestra window = new Finestra();  
				window.setTitle(lastContactClicked.getAttribute("ragionesociale"));
				Contatto contatto = null;
				for(int i=0; i<DataSourceContatti.getVettoreContatti().size(); i++){
					contatto = DataSourceContatti.getVettoreContatti().get(i);
					if( contatto.getRagioneSociale().equals(lastContactClicked.getAttribute("ragionesociale"))){
						window.addItem(new Label(contatto.getHtmlText()));
						break;
					}
				}
				window.setWidth(300);  
				window.setHeight(230);
				window.setCanDragReposition(true);  
				window.setCanDragResize(true);  
				window.centerInPage();
				window.draw();
			}
		});
		
		
		
	}
}
