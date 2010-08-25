package gestionale.client.UI;

import com.google.gwt.user.client.Timer;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class ListGridContatti extends ListGrid{
	
	private static ListGridRecord lastContactClicked;

	public ListGridContatti(){
		super();
		this.setWidth100();
		this.setHeight100(); 
		this.setShowEdges(false);
		setGroupStartOpen(GroupStartOpen.NONE);
		setGroupByField("tiposoggetto");
		setDataSource(DataSourceContatti.getIstance());
		this.setCanReorderFields(false); 
		this.setCanAcceptDroppedRecords(false);  
		this.setCanDragRecordsOut(false);
		
		ListGridField nameField = new ListGridField("ragionesociale", "Ragione Sociale");
		nameField.setCanEdit(false);
		ListGridField tiposoggettoField = new ListGridField("tiposoggetto", "Tipo Soggetto");
		tiposoggettoField.setCanEdit(false);
		tiposoggettoField.setHidden(true);
		setFields(nameField, tiposoggettoField);
		
		this.fetchData();
		this.sort("ragionesociale", SortDirection.ASCENDING);
		this.setCanDrag(false);
		
		/*
		int i = 0;
		new Timer(){
			int i = 0;
			public void run() {
				System.out.println("MA DOVE VAI BELLEZZA IN BICICLETTA: " + i);
				i++;
				if(i<10)	this.schedule(1000);
			}
			
		}.schedule(1000);
		*/
		/*
		setCanEdit(false);
		setHeight100();
		setWidth100();
		setShowAllRecords(true);
		setGroupStartOpen(GroupStartOpen.NONE);
		setGroupByField("tiposoggetto");
		sort("ragionesociale", SortDirection.ASCENDING);
		
		setDataSource(DataSourceContatti.getIstance());
		
		ListGridField nameField = new ListGridField("ragionesociale", "Ragione Sociale");
		ListGridField tiposoggettoField = new ListGridField("tiposoggetto", "Tipo Soggetto");  
		tiposoggettoField.setHidden(true);
		setFields(nameField, tiposoggettoField);
		
		this.fetchData();
		this.setCanDrag(false);*/
		
		addRecordClickHandler(new RecordClickHandler() {
			
        	public void onRecordClick(RecordClickEvent event) {
				lastContactClicked = (ListGridRecord) event.getRecord();
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
		
		
	}
}
