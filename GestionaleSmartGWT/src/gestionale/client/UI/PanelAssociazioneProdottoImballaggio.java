package gestionale.client.UI;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceImballaggi;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Imballaggio;

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
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VStack;

public class PanelAssociazioneProdottoImballaggio extends HLayout{

	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static ListGridRecord[] records_imballaggio = null;
	private String IDProdotto = null;
	
	private ListGrid lg_associazione_prodotto_imballaggio_peso;
	private ListGrid lg_imballaggi;
	
	private SectionStackSection SSS_rif = null;
	
	public PanelAssociazioneProdottoImballaggio(){
		super();
		
		ListGrid lg_prodotti = new ListGrid();
			lg_prodotti.setDataSource(DataSourceProdottiCatalogati.getIstance());
			lg_prodotti.setFields(new ListGridField("descrizione", "Prodotto"));
			lg_prodotti.setHeight100();
			lg_prodotti.fetchData();
			lg_prodotti.addRecordClickHandler(new RecordClickHandler() {
				
				@Override
				public void onRecordClick(RecordClickEvent event) {
					IDProdotto = event.getRecord().getAttribute("id");
					SSS_rif.setTitle("Prodotto: "+event.getRecord().getAttribute("descrizione"));
					String query = "SELECT i.Descrizione, apip.Peso, apip.IDImballaggio, apip.IDProdotto FROM assoc_prodotto_imballaggio_peso apip JOIN imballaggio i ON apip.IDImballaggio = i.ID WHERE IDProdotto = '"+IDProdotto+"'";
					rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(String[][] result) {
							if(result == null) return;
							ListGridRecord[] records_associazione = new ListGridRecord[result.length];
							for(int i=0; i<result.length; i++){
								String[] tmp = result[i];
								records_associazione[i] = new ListGridRecord();
								records_associazione[i].setAttribute("id", tmp[2]);
								records_associazione[i].setAttribute("peso", tmp[1]);
								records_associazione[i].setAttribute("descrizione", tmp[0]);
								records_associazione[i].setAttribute("idprodotto", tmp[3]);
								
							}
							lg_associazione_prodotto_imballaggio_peso.setData(records_associazione);
							ListGridField desc = new ListGridField("descrizione", "Descrizione");
							ListGridField peso = new ListGridField("peso", "Peso medio (Kg)", 100);
							peso.setCanEdit(true);
							peso.addEditorExitHandler(new EditorExitHandler() {
								
								@Override
								public void onEditorExit(EditorExitEvent event) {
									Record record = event.getRecord();
									
									String query = "UPDATE assoc_prodotto_imballaggio_peso SET Peso = '"+event.getNewValue()+"' WHERE IDProdotto = '"+record.getAttribute("idprodotto")+"' AND IDImballaggio = '"+record.getAttribute("id")+"';";
									rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

										@Override
										public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
										@Override
										public void onSuccess(Boolean result) {}
										
									});
								}
							});
							
							
							
							lg_associazione_prodotto_imballaggio_peso.setFields(desc,peso);
							
						}
						
					});
					
					Vector<Imballaggio> v_imb = DataSourceImballaggi.getVettoreImballaggi();
					if(v_imb.size() == 0) return;
					ListGridRecord[] records_imballaggio = new ListGridRecord[v_imb.size()];
					
					for(int i=0; i<v_imb.size();i++){
						records_imballaggio[i] = new ListGridRecord();
						records_imballaggio[i].setAttribute("id", v_imb.get(i).getID());
						records_imballaggio[i].setAttribute("descrizione", v_imb.get(i).getDescrizione());
						
					}
					
					lg_imballaggi.setData(records_imballaggio);
					lg_imballaggi.setFields(new ListGridField("descrizione", "Descrizione"));
					
				}
			});
			
			
			lg_imballaggi = new ListGrid();
			lg_imballaggi.setCanDrag(true);
			lg_imballaggi.setCanDragRecordsOut(true);  
			lg_imballaggi.setCanAcceptDroppedRecords(true);
			lg_imballaggi.setHeight100();
			
			
			lg_associazione_prodotto_imballaggio_peso = new ListGrid();
			lg_associazione_prodotto_imballaggio_peso.setCanAcceptDrop(true); 
			lg_associazione_prodotto_imballaggio_peso.setCanDragRecordsOut(true);  
			lg_associazione_prodotto_imballaggio_peso.setCanAcceptDroppedRecords(true);  
			lg_associazione_prodotto_imballaggio_peso.setCanDrag(true);
			lg_associazione_prodotto_imballaggio_peso.setHeight100();
			lg_associazione_prodotto_imballaggio_peso.setFields(new ListGridField("descrizione", "Descrizione"));
		
			
			lg_associazione_prodotto_imballaggio_peso.addRecordDropHandler(new RecordDropHandler() {
				
				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String peso = lgr[i].getAttribute("peso");
						if(peso == null) peso = "0";
						String query = "INSERT INTO assoc_prodotto_imballaggio_peso (IDProdotto, IDImballaggio, Peso) VALUES ('"+IDProdotto+"', '"+lgr[i].getAttribute("id")+"', '"+peso+"')";
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
			lg_associazione_prodotto_imballaggio_peso.addEditorExitHandler(new EditorExitHandler() {
				
				@Override
				public void onEditorExit(EditorExitEvent event) {
					Record record = event.getRecord();
					String query = "UPDATE assoc_prodotto_imballaggio_peso SET Peso = '"+record.getAttribute("peso")+"'WHERE IDProdotto = '"+record.getAttribute("idprodotto")+"' AND IDImballaggio = '"+record.getAttribute("id")+"';";
					rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
						@Override
						public void onSuccess(Boolean result) {}
						
					});
				}
			});
			*/
			lg_imballaggi.addRecordDropHandler(new RecordDropHandler() {

				@Override
				public void onRecordDrop(RecordDropEvent event) {
					ListGridRecord[] lgr = event.getDropRecords();
					for(int i=0; i<lgr.length; i++){
						String query = "DELETE FROM assoc_prodotto_imballaggio_peso WHERE IDProdotto = '"+IDProdotto+"' AND IDImballaggio = '"+lgr[i].getAttribute("id")+"';";
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
		SSS.setTitle("Prodotti");
		SSS.addItem(lg_prodotti);
		SS.addSection(SSS);
		this.addMember(SS);
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Imballaggi");
		SSS.addItem(lg_imballaggi);
		SS.addSection(SSS);
		this.addMember(SS);
		
		SS 	= new SectionStack();
		SSS	= new SectionStackSection();
		SSS.setTitle("Prodotto-Imballaggio-Peso");
		SSS.addItem(lg_associazione_prodotto_imballaggio_peso);
		SS.addSection(SSS);
		SSS_rif = SSS;
		this.addMember(SS);
		
		
		
	}
	
}
