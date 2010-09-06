package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.client.DataBase.DataSourceImballaggi;
import gestionale.shared.Imballaggio;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class PanelGestioneImballaggi extends VLayout{
	
	private TextItem larghezza;
	private TextItem lunghezza;
	private TextItem altezza;
	private TextItem tara;
	private TextItem materiale;
	private TextItem marchio;
	private DynamicForm form;
	private ListGrid lg;
	
	private Record lastImballaggioClicked;
	
	
	public PanelGestioneImballaggi(){
		
		this.setWidth100();
		this.setHeight100();
		
		lg = new ListGrid();
		lg.setAutoFetchData(true);
		lg.setDataSource(DataSourceImballaggi.getIstance());
		
		lg.addRowContextClickHandler(new RowContextClickHandler() {
			
			public void onRowContextClick(RowContextClickEvent event) {

				lastImballaggioClicked = event.getRecord();
				final int rowrecord = lg.getRecordIndex(lastImballaggioClicked);
				System.out.println("Contatto cliccato: " + lastImballaggioClicked + " @ riga: "+rowrecord );
				Menu menu = new Menu();
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Imballaggio");
				
				

				mi_rimuovi.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler(){

					@Override
					public void onClick(MenuItemClickEvent event) {
						ListGridRecord lgr = (ListGridRecord) lg.getEditedRecord(rowrecord);
						if( Window.confirm("Sei sicuro di voler rimuovere:\n\n"+lgr.getAttribute("descrizione")+" ?") ){
							
							System.out.println("SelectedRecord: " + lgr+ "  " +  lgr.getAttribute("id"));
							
							if(lgr.getAttribute("id") == null){
								//
							}else{
								DataSourceImballaggi.rimuoviImballaggio(lgr);
								lg.removeData(lgr);
							}
							
						}
					}
					
				});
				
				
				
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
				
			}
		});
		
		this.addMember(lg);
		
		form = new DynamicForm();
		
		larghezza = new TextItem();  
				larghezza.setType("int");
				larghezza.setName("larghezza");
				larghezza.setTitle("Larghezza (cm)");
				larghezza.setRequired(true);
				
		lunghezza = new TextItem();  
				lunghezza.setType("int");
				lunghezza.setName("lunghezza");
				lunghezza.setTitle("Lunghezza (cm)");
				lunghezza.setRequired(true);
		
		altezza = new TextItem();  
				altezza.setType("int"); 
				altezza.setName("altezza");
				altezza.setTitle("Altezza (cm)");
				altezza.setRequired(true);
		
		tara = new TextItem();  
				tara.setType("int");
				tara.setName("tara");
				tara.setTitle("Tara (g)");
				tara.setRequired(true);
		
		materiale = new TextItem();
				materiale.setTitle("Materiale");
				materiale.setName("materiale");
				materiale.setRequired(true);
		
		marchio = new TextItem();
				marchio.setTitle("Marchio");
				marchio.setName("marchio");
				marchio.setRequired(true);
		
		//Selez
		ButtonItem button = new ButtonItem("Aggiungi");
				
				
		form.setFields(new FormItem[]{larghezza,lunghezza,altezza,tara,materiale,marchio,button});
		
		this.addMember(form);
		
		button.addClickHandler(new ClickHandler() {  
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				form.validate();
				if(form.hasErrors()){
					Window.alert("Campi non validi o mancanti!");
					return;
				}else{
					Imballaggio imballaggio = new Imballaggio();
					imballaggio.setLarghezza(	form.getValueAsString("larghezza")	);
					imballaggio.setLunghezza(	form.getValueAsString("lunghezza")	);
					imballaggio.setAltezza(		form.getValueAsString("altezza")	);
					imballaggio.setTara(		form.getValueAsString("tara")		);
					imballaggio.setMateriale(	form.getValueAsString("materiale")	);
					imballaggio.setMarchio(		form.getValueAsString("marchio")	);
					String descrizione = form.getValueAsString("larghezza")+"x"+form.getValueAsString("lunghezza")+"x"+form.getValueAsString("altezza")+" - "+form.getValueAsString("materiale")+" - "+form.getValueAsString("tara")+"g - "+form.getValueAsString("marchio");
					imballaggio.setDescrizione(descrizione);
					imballaggio.setIsSelezionato("1");
					DataSourceImballaggi.aggiungiImballaggio(imballaggio);
				}
			}  
        }); 

		/*
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.validate();
				if(form.hasErrors()){
					Window.alert("Campi non validi o mancanti!");
					return;
				}else{
					Imballaggio imballaggio = new Imballaggio();
					imballaggio.setLarghezza((String) larghezza.getValue());
					imballaggio.setLunghezza((String) lunghezza.getValue());
					imballaggio.setAltezza((String) altezza.getValue());
					imballaggio.setTara((String) tara.getValue());
					imballaggio.setMateriale((String) materiale.getValue());
					imballaggio.setMarchio((String) marchio.getValue());
					
					DataSourceImballaggi.aggiungiImballaggio(imballaggio);
				}
				
				
			}
		});*/
		
		/*
		 * 
		
		
		ImgButton addButton = new ImgButton();  
		addButton.setSrc("[SKIN]actions/add.png");  
		addButton.setSize(16);  
		addButton.setShowFocused(false);  
		addButton.setShowRollOver(false);  
		addButton.setShowDown(false);  
		addButton.addClickHandler(new ClickHandler() {  
             public void onClick(ClickEvent event) {  
            	lg.startEditingNew();  
             }  
         });
		
		SectionStack SS = new SectionStack();
		SS.setWidth100();
        SS.setHeight100(); 

        SectionStackSection section = new SectionStackSection();  
        section.addItem(lg);
        section.setCanCollapse(false);  
        section.setExpanded(true);  
        section.setControls(addButton);
        
		SS.setSections(section);
		
		this.addMember(SS);
		*/
		
		
	}

}
