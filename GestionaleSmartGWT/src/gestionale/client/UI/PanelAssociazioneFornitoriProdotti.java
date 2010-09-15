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

public class PanelAssociazioneFornitoriProdotti extends HLayout{

	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static ListGridRecord[] records_merce = null;
	private String IDFornitore = null;
	
	private ListGrid lg_associazione_merce_contatto;
	private ListGrid lg_merce;
	
	private SectionStackSection SSS_rif = null;
	
	public PanelAssociazioneFornitoriProdotti(){
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
					SSS_rif.setTitle("Merce trattata da: "+event.getRecord().getAttribute("ragionesociale"));
					String query = "SELECT * FROM fornitore_tipologie_trattate WHERE IDFornitore = '"+IDFornitore+"'";
					rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(String[][] result) {
							if(result == null) return;
							ListGridRecord[] records_merce = new ListGridRecord[result.length];
							for(int i=0; i<result.length; i++){
								String[] tmp = result[i];
								records_merce[i] = new ListGridRecord();
								records_merce[i].setAttribute("categoria", tmp[1]);
								records_merce[i].setAttribute("tipologia", tmp[2]);
								
							}
							lg_associazione_merce_contatto.setData(records_merce);
							lg_associazione_merce_contatto.setFields(new ListGridField("categoria", "Categoria"),new ListGridField("tipologia", "Tipologia"));
							
						}
						
					});
					
					query = "SELECT p.Categoria, t.Tipologia FROM prodotto_categoria p JOIN prodotto_tipologia t ON p.ID = t.IDCategoria";
					rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
						
						@Override
						public void onSuccess(String[][] result) {
							if(result == null) return;
							ListGridRecord[] records_merce = new ListGridRecord[result.length];
							PanelAssociazioneFornitoriProdotti.records_merce = new ListGridRecord[result.length];
							for(int i=0; i<result.length; i++){
								String[] tmp = result[i];
								ListGridRecord rec = new ListGridRecord();
								rec.setAttribute("categoria", tmp[0]);
								rec.setAttribute("tipologia", tmp[1]);
								records_merce[i] = rec;
								PanelAssociazioneFornitoriProdotti.records_merce[i] = rec;
							}
							lg_merce.setData(records_merce);
							lg_merce.setFields(new ListGridField("categoria", "Categoria"),new ListGridField("tipologia", "Tipologia"));
							
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}
					});
					
				}
			});
			
			
			lg_merce = new ListGrid();
			lg_merce.setCanDrag(true);
			lg_merce.setCanDragRecordsOut(true);  
			lg_merce.setCanAcceptDroppedRecords(true);
			lg_merce.setHeight100();
			
			
			lg_associazione_merce_contatto = new ListGrid();
			lg_associazione_merce_contatto.setCanAcceptDrop(true); 
			lg_associazione_merce_contatto.setCanDragRecordsOut(true);  
			lg_associazione_merce_contatto.setCanAcceptDroppedRecords(true);  
			lg_associazione_merce_contatto.setCanDrag(true);
			lg_associazione_merce_contatto.setHeight100();
			lg_associazione_merce_contatto.setFields(new ListGridField("categoria", "Categoria"),new ListGridField("tipologia", "Tipologia"));
		
			
			lg_associazione_merce_contatto.addRecordDropHandler(new RecordDropHandler() {
				
				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String query = "INSERT INTO fornitore_tipologie_trattate (IDFornitore, Categoria, Tipologia) VALUES ('"+IDFornitore+"', '"+lgr[i].getAttribute("categoria")+"', '"+lgr[i].getAttribute("tipologia")+"')";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
							@Override
							public void onSuccess(Boolean result) {}
							
						});
					}
				}
			});
			
			lg_merce.addRecordDropHandler(new RecordDropHandler() {

				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String query = "DELETE FROM fornitore_tipologie_trattate WHERE IDFornitore = '"+IDFornitore+"' AND Categoria = '"+lgr[i].getAttribute("categoria")+"' AND Tipologia = '"+lgr[i].getAttribute("tipologia")+"';";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
							@Override
							public void onSuccess(Boolean result) {}
							
						});
					}
					
				}
				
			});
			
			
			/*
			lg_associazione_merce_contatto.addFocusChangedHandler(new FocusChangedHandler() {
				
				@Override
				public void onFocusChanged(FocusChangedEvent event) {
					ListGridRecord[] lgr = lg_associazione_merce_contatto.getRecords();
					System.out.println("FOCUS");
					for(int i=0;i<lgr.length; i++){
						System.out.println("Rec: " + lgr[i].getAttribute("categoria")+" "+lgr[i].getAttribute("tipologia"));
					}
				}
			});
			
			*/
			/*
			lg_associazione_merce_contatto.addDropOverHandler(new DropOverHandler() {
				
				@Override
				public void onDropOver(DropOverEvent event) {
					String query = "DELETE FROM fornitore_tipologie_trattate WHERE IDFornitore = '"+IDFornitore+"'";
					rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

						@Override
						public void onSuccess(Boolean result) {
							ListGridRecord[] lgr = lg_associazione_merce_contatto.getRecords();
							for(int i=0; i<lgr.length; i++){
								ListGridRecord rec = lgr[i];
								String query = "INSERT INTO fornitore_tipologie_trattate (IDFornitore, Categoria, Tipologia) VALUES ('"+IDFornitore+"', '"+rec.getAttribute("Categoria")+"', '"+rec.getAttribute("Categoria")+"')";
								rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Boolean result) {
										// TODO Auto-generated method stub
										
									}
									
									
								});
							}
						}
						
					});
				}
			});
			*/
			/*
			lg_associazione_merce_contatto.addDropHandler(new DropHandler() {
				
				@Override
				public void onDrop(DropEvent event) {
					
				
				}
			});
			*/
			
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
		SSS.setTitle("Merce");
		SSS.addItem(lg_merce);
		SS.addSection(SSS);
		this.addMember(SS);
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Merce trattata");
		SSS.addItem(lg_associazione_merce_contatto);
		SS.addSection(SSS);
		SSS_rif = SSS;
		this.addMember(SS);
		
		
		
	}
	
}
