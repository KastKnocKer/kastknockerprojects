package gestionale.client.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;  
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;  
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.grid.events.RowEditorExitEvent;
import com.smartgwt.client.widgets.grid.events.RowEditorExitHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class WindowDettaglioOrdini extends Finestra{

	private WindowDettaglioOrdini thiswind;
	private Record lastDettaglioOrdineClicked;
	
	public WindowDettaglioOrdini(final LabelOrdinazione lo){
		super();
		thiswind = this;
		
		final ListGrid lg =  new ListGrid() {
			
			@Override
			public void removeData(Record record) {
			// my pre-remove code goes here
				System.out.println("SelectedRecord: " + record.getAttribute("id"));
				if( Window.confirm("Sei sicuro di voler rimuovere:\n\n"+record.getAttribute("quantita")+" "+record.getAttribute("fornitore")+" "+record.getAttribute("trasportatore")+" "+record.getAttribute("imballaggio")+" ?") ){
					
				}	
				
			super.removeData(record);
			}
			
			  
        };  
		
		
		
		
		
		
		ListGridField quantita		= new ListGridField("quantita","Quantita");
		ListGridField fornitore		= new ListGridField("fornitore","Fornitore");
		ListGridField imballaggio	= new ListGridField("imballaggio","Imballaggio");
		ListGridField trasportatore	= new ListGridField("trasportatore","Trasportatore");
		
		quantita.setRequired(true);
		fornitore.setRequired(true);
		imballaggio.setRequired(true);
		trasportatore.setRequired(true);
		
		Vector<Contatto> vF = DataSourceContatti.getVettoreFornitori();
		Vector<Contatto> vT = DataSourceContatti.getVettoreTrasportatori();
		
		final String[] aF = new String[vF.size()];	final String[] aFID = new String[vF.size()];
		final String[] aT = new String[vT.size()];		final String[] aTID = new String[vT.size()];

		SelectItem fornitoriSelectItem = new SelectItem(); 
		SelectItem trasportatoriSelectItem = new SelectItem(); 
		SelectItem imballaggiSelectItem = new SelectItem(); 
		
		for(int i=0; i<vF.size(); i++){
			aF[i] = vF.get(i).getRagioneSociale();
			aFID[i] = vF.get(i).getID();
		}
		for(int i=0; i<vT.size(); i++){
			aT[i] = vT.get(i).getRagioneSociale();
			aTID[i] = vT.get(i).getID();
		}
		
		fornitoriSelectItem.setValueMap(aF);
		trasportatoriSelectItem.setValueMap(aT);

		fornitoriSelectItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				System.out.println("RIGA: "+event.getValue());
				//lg.getSelectedRecord().setAttribute("idfornitore", "GAY");
			}
		});
		
		trasportatoriSelectItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				System.out.println("RIGA: "+event.getValue()+" RECORD: "+lg.getSelectedRecord()+event.getItem());
				//lg.getSelectedRecord().setAttribute("idtrasportatore", "GAY");
			}
		});
		
		
		
		fornitore.setEditorType(fornitoriSelectItem);
		trasportatore.setEditorType(trasportatoriSelectItem);
		
		  
		
		
		lg.setShowRecordComponents(true);          
		lg.setShowRecordComponentsByCell(true);
  
        lg.setShowAllRecords(true);
		
		lg.setCanEdit(true);
		lg.setCanRemoveRecords(true);
		lg.setAutoFetchData(true);
		lg.setDataSource(new DataSourceDettaglioOrdini(lo.getIdordine(), lo.getIdcliente(), lo.getIdprodotto(),DataSourceDettaglioOrdini.MOD_TabellaDettaglio));
		
		lg.setFields(quantita, fornitore, imballaggio,trasportatore);
		lg.setAutoSaveEdits(false);
		lg.setWidth100();
		lg.setHeight100();
		
		
		
		
		//CLICK DESTRO
		lg.addRowContextClickHandler(new RowContextClickHandler() {
			
			public void onRowContextClick(RowContextClickEvent event) {
				lastDettaglioOrdineClicked = event.getRecord();
				final int rowrecord = lg.getRecordIndex(lastDettaglioOrdineClicked);
				System.out.println("Contatto cliccato: " + lastDettaglioOrdineClicked + " @ riga: "+rowrecord );
				Menu menu = new Menu();
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Dettaglio Ordine");
				
				

				mi_rimuovi.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler(){

					@Override
					public void onClick(MenuItemClickEvent event) {
						ListGridRecord lgr = (ListGridRecord) lg.getEditedRecord(rowrecord);
						if( Window.confirm("Sei sicuro di voler rimuovere:\n\n"+lgr.getAttribute("quantita")+" "+lgr.getAttribute("fornitore")+" "+lgr.getAttribute("trasportatore")+" "+lgr.getAttribute("imballaggio")+" ?") ){
							
							System.out.println("SelectedRecord: " + lgr+ "  " +  lgr.getAttribute("id"));
							
							if(lgr.getAttribute("id") == null){
								lg.removeSelectedData();
							}else{
								DataSourceDettaglioOrdini.rimuoviDettaglioOrdine(lgr);
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
		
		Button confermaButton = new Button("Conferma");
		confermaButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				if(lg.hasErrors()){
					Window.alert("Sono presenti errori nei dati.\nPrima di proseguire controllare.");
					return;
				}
				
				int sum = 0;
				int[] editrows = lg.getAllEditRows();
				for(int i=0; i<editrows.length; i++){
				Record record = lg.getEditedRecord(editrows[i]);
				
				System.out.println("RECORD: ??? : "+ record.getAttribute("id"));
				record.setAttribute("idprodotto", lo.getIdprodotto());
				record.setAttribute("idcliente", lo.getIdcliente());
				record.setAttribute("idordine", lo.getIdordine());
				record.setAttribute("user", SessioneUtente.getUsername());
				
				for(int k=0; i<aF.length;k++){
					if(aF[k].equals(record.getAttribute("fornitore"))){
						record.setAttribute("idfornitore", aFID[k] );
						break;
					}
				}
				
				for(int k=0; i<aT.length;k++){
					if(aT[k].equals(record.getAttribute("trasportatore"))){
						record.setAttribute("idtrasportatore", aTID[k]);
						break;
					}
				}
				/*
				for(int k=0; i<aF.length;k++){
					if(aF[k].equals(record.getAttribute("fornitore"))){
						record.setAttribute("idimballaggio", SessioneUtente.getUsername());
						break;
					}
				}*/
				
				
				
				
				
					if(record.getAttribute("id") == null){
						DataSourceDettaglioOrdini.aggiungiDettaglioOrdine(record);
					}else{
						DataSourceDettaglioOrdini.modificaDettaglioOrdine(record);
					}
					sum = sum + Integer.parseInt(record.getAttribute("quantita"));
				}
				lg.saveAllEdits();
				sum=0;
				ListGridRecord[] rec =  lg.getRecords();
				
				for(int i=0; i<rec.length; i++){
					sum = sum + Integer.parseInt(rec[i].getAttribute("quantita"));
				}
				lo.setBackgroundColor("#66FF33");
				lo.setContents(Integer.toString(sum));
				thiswind.destroy();
			}
		});
		
		
		
		
		this.setTitle("Dettaglio ordine");
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.setCanDragReposition(true);  
		this.setCanDragResize(true);
		this.setWidth(400);
		this.setHeight(200);
		this.centerInPage();
		this.addItem(SS);
		this.addItem(confermaButton);
		this.draw();
	}
	
	
}
