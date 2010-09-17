package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceContatti;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.DragRepositionStopEvent;
import com.smartgwt.client.widgets.events.DragRepositionStopHandler;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.events.DropOverEvent;
import com.smartgwt.client.widgets.events.DropOverHandler;
import com.smartgwt.client.widgets.events.FocusChangedEvent;
import com.smartgwt.client.widgets.events.FocusChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VStack;

public class PanelAssociazioneFornitoriPallet extends HLayout{

	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static ListGridRecord[] records_pallet = null;
	private String IDFornitore = null;
	
	private ListGrid lg_associazione_pallet_contatto;
	private ListGrid lg_pallet;
	
	private SectionStackSection SSS_rif = null;
	
	public PanelAssociazioneFornitoriPallet(){
		super();
		Record[] records = DataSourceContatti.getIstance().getCacheData();
		Record[] rec = new Record[records.length];
		int indice=0;
		for(int i=0; i<records.length; i++){
			if( records[i].getAttribute("tiposoggetto").equals("Fornitore")){
				rec[indice] = records[i];
				indice++;
			}
		}
		ListGrid lg_contatti = new ListGrid();
			lg_contatti.setData(rec);
			lg_contatti.setFields(new ListGridField("ragionesociale", "Ragione Sociale"));
			lg_contatti.setHeight100();
			lg_contatti.addRecordClickHandler(new RecordClickHandler() {
				
				@Override
				public void onRecordClick(RecordClickEvent event) {
					IDFornitore = event.getRecord().getAttribute("id");
					SSS_rif.setTitle("Pallet utilizzati da: "+event.getRecord().getAttribute("ragionesociale"));
					String query = "SELECT p.* FROM fornitore_pallet_utilizzato fpu JOIN pallet p WHERE fpu.IDPallet = p.ID AND IDFornitore = '"+IDFornitore+"'";
					rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(String[][] result) {
							if(result == null) return;
							ListGridRecord[] records_pallet = new ListGridRecord[result.length];
							for(int i=0; i<result.length; i++){
								String[] tmp = result[i];
								records_pallet[i] = new ListGridRecord();
								records_pallet[i].setAttribute("id", tmp[0]);
								records_pallet[i].setAttribute("descrizione", tmp[1]+"x"+tmp[2]+"x"+tmp[3]+" - Tara: "+tmp[4]+" - Capienza: "+tmp[5]+" "+tmp[6]);
								
							}
							lg_associazione_pallet_contatto.setData(records_pallet);
							lg_associazione_pallet_contatto.setFields(new ListGridField("descrizione", "Descrizione"));
							
						}
						
					});
					
					query = "SELECT * FROM pallet";
					rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
						
						@Override
						public void onSuccess(String[][] result) {
							if(result == null) return;
							ListGridRecord[] records_pallet = new ListGridRecord[result.length];
							PanelAssociazioneFornitoriPallet.records_pallet = new ListGridRecord[result.length];
							for(int i=0; i<result.length; i++){
								String[] tmp = result[i];
								ListGridRecord rec = new ListGridRecord();
								rec.setAttribute("id", tmp[0]);
								rec.setAttribute("descrizione", tmp[1]+"x"+tmp[2]+"x"+tmp[3]+" - Tara: "+tmp[4]+" - Capienza: "+tmp[5]+" "+tmp[6]);
								records_pallet[i] = rec;
								PanelAssociazioneFornitoriPallet.records_pallet[i] = rec;
							}
							lg_pallet.setData(records_pallet);
							lg_pallet.setFields(new ListGridField("descrizione", "Descrizione"));
							
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}
					});
					
				}
			});
			
			
			lg_pallet = new ListGrid();
			lg_pallet.setCanDrag(true);
			lg_pallet.setCanDragRecordsOut(true);  
			lg_pallet.setCanAcceptDroppedRecords(true);
			lg_pallet.setHeight100();
			
			
			lg_associazione_pallet_contatto = new ListGrid();
			lg_associazione_pallet_contatto.setCanAcceptDrop(true); 
			lg_associazione_pallet_contatto.setCanDragRecordsOut(true);  
			lg_associazione_pallet_contatto.setCanAcceptDroppedRecords(true);  
			lg_associazione_pallet_contatto.setCanDrag(true);
			lg_associazione_pallet_contatto.setHeight100();
			lg_associazione_pallet_contatto.setFields(new ListGridField("descrizione", "Descrizione"));
		
			
			lg_associazione_pallet_contatto.addRecordDropHandler(new RecordDropHandler() {
				
				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String query = "INSERT INTO fornitore_pallet_utilizzato (IDFornitore, IDPallet) VALUES ('"+IDFornitore+"', '"+lgr[i].getAttribute("id")+"')";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
							@Override
							public void onSuccess(Boolean result) {}
							
						});
					}
				}
			});
			
			lg_pallet.addRecordDropHandler(new RecordDropHandler() {

				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String query = "DELETE FROM fornitore_pallet_utilizzato WHERE IDFornitore = '"+IDFornitore+"' AND IDPallet = '"+lgr[i].getAttribute("id")+"';";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
							@Override
							public void onSuccess(Boolean result) {}
							
						});
					}
					
				}
				
			});
			
			
		SectionStack SS;
		SectionStackSection SSS;
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Fornitori");
		SSS.addItem(lg_contatti);
		SS.addSection(SSS);
		this.addMember(SS);
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Pallet");
		SSS.addItem(lg_pallet);
		SS.addSection(SSS);
		this.addMember(SS);
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Pallet utilizzati");
		SSS.addItem(lg_associazione_pallet_contatto);
		SS.addSection(SSS);
		SSS_rif = SSS;
		this.addMember(SS);
		
		
		
	}
	
}
