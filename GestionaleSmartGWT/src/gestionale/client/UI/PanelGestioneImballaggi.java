package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceImballaggi;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.grid.events.RowEditorExitEvent;
import com.smartgwt.client.widgets.grid.events.RowEditorExitHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class PanelGestioneImballaggi extends Layout{
	
	public PanelGestioneImballaggi(){
		
		final ListGrid lg = new ListGrid();
		lg.setDataSource(DataSourceImballaggi.getIstance());
		
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
		
		lg.addRowEditorExitHandler(new RowEditorExitHandler() {
			
			@Override
			public void onRowEditorExit(RowEditorExitEvent event) {

				Record record = (ListGridRecord) event.getRecord();
				if(record == null) return;
				System.out.println("Rec: " + record.getAttribute("id"));
				if(record.getAttribute("id") == null){
					//inserisci
					System.out.println("INS");
				}else{
					//modifica
					System.out.println("MOD");
				}
				
			}
		});
		
		
	}

}
