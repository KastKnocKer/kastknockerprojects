package gestionale.client.UI;

import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class WindowDettaglioOrdini extends Window{

	private WindowDettaglioOrdini thiswind;
	
	public WindowDettaglioOrdini(final LabelOrdinazione lo){
		super();
		thiswind = this;
		final ListGrid lg = new ListGrid();
		lg.setCanEdit(true);
		lg.setAutoFetchData(true);
		lg.setDataSource(new DataSourceDettaglioOrdini(lo.getIdOrdine(), lo.getIdCliente(), lo.getIdProdotto()));
		
		ListGridField quantita		= new ListGridField("quantita","Quantita");
		ListGridField fornitore		= new ListGridField("fornitore","Fornitore");
		ListGridField imballaggio	= new ListGridField("imballaggio","Imballaggio");
		ListGridField trasportatore	= new ListGridField("trasportatore","Trasportatore");
		
		lg.setFields(quantita, fornitore, imballaggio,trasportatore);
		lg.setAutoSaveEdits(false);
		lg.setWidth100();
		lg.setHeight100();
		
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
				int sum = 0;
				int[] editrows = lg.getAllEditRows();
				for(int i=0; i<editrows.length; i++){
				Record record = lg.getEditedRecord(editrows[i]);
				
				System.out.println("RECORD: ??? : "+ record.getAttribute("id"));
				record.setAttribute("idProdotto", lo.getIdProdotto());
				record.setAttribute("idCliente", lo.getIdCliente());
				record.setAttribute("idOrdine", lo.getIdOrdine());
				record.setAttribute("user", SessioneUtente.getUsername());
				
				
					if(record.getAttribute("id") == null){
						DataSourceDettaglioOrdini.aggiungiDettaglioOrdine(record);
					}else{
						DataSourceDettaglioOrdini.modificaDettaglioOrdine(record);
					}
					sum = sum + Integer.parseInt(record.getAttribute("quantita"));
				}
				
				ListGridRecord[] rec =  lg.getRecords();
				
				for(int i=0; i<rec.length; i++){
					sum = sum + Integer.parseInt(rec[i].getAttribute("quantita"));
				}
				
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
