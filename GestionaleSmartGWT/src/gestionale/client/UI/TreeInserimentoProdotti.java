package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceProdotti;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class TreeInserimentoProdotti extends TreeGrid{
	
	private static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static TreeGrid tg;
	private static Record selezionato = null;
	
	public TreeInserimentoProdotti(){
		super();
		tg = this;
		
        setWidth100();
        setHeight100();
        setAutoFetchData(true);
        setCanReparentNodes(true);
        setCanFreezeFields(true);
        setAutoSaveEdits(true);
        
        setLoadDataOnDemand(false);  
        setCanReorderRecords(false);  
        setCanAcceptDroppedRecords(false);  
        setShowDropIcons(false);  
        setShowOpenIcons(false);
        setDataSource( DataSourceProdotti.getIstance() );
        
        
        fetchData();
        
       this.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
		
		public void onRecordDoubleClick(RecordDoubleClickEvent event) {
			
		}
	});
       

       
        this.addCellContextClickHandler(new CellContextClickHandler() {
			
			public void onCellContextClick(CellContextClickEvent event) {
				
				selezionato = event.getRecord();
        		String tipo = selezionato.getAttribute("Tipo");
				
				
				Menu menu = new Menu();
				
				MenuItem mi_aggiungi_tn = new MenuItem("Aggiungi voce");
				mi_aggiungi_tn.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
					
					public void onClick(MenuItemClickEvent event) {
						insertItem("Nuovo Oggetto");
					}
				});
				
				MenuItem mi_rimuovi_tn = new MenuItem("Rimuovi voce");
				
				
				
				
				if(tipo.equals("Categoria")){
					menu.addItem( mi_aggiungi_tn );
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("Tipologia")){
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("Varieta")){
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("SottoVarieta")){
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("Calibro")){
					//menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}
				
			}
		});
        
        
        
        this.addEditorExitHandler(new EditorExitHandler() {
			
			public void onEditorExit(EditorExitEvent event) {
				Record record = event.getRecord();
				String tipo = record.getAttribute("Tipo");
				String query = null;
				String id = record.getAttribute("ID");
				System.out.println("RECORD: " + record.getAttribute("Tipo") + " " + record.getAttribute("ID"));
				
				if(event.getNewValue() == null) return;
				
				if (tipo.equals("Tipologia")){
					query = "UPDATE prodotto_tipologia SET Tipologia='"+ event.getNewValue() +"' WHERE ID="+ id.substring(1) +";";
				}else if (tipo.equals("Varieta")){
					query = "UPDATE prodotto_varieta SET Varieta='"+ event.getNewValue() +"' WHERE ID="+ id.substring(1) +";";
				}else if (tipo.equals("SottoVarieta")){
					query = "UPDATE prodotto_sottovarieta SET Sottovarieta='"+ event.getNewValue() +"' WHERE ID="+ id.substring(2) +";";
				}else if (tipo.equals("Calibro")){
					query = "UPDATE prodotto_calibro SET Calibro='"+ event.getNewValue() +"' WHERE ID="+ id.substring(2) +";";
				}

				rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						if(!result){
							com.google.gwt.user.client.Window.alert("La modifica non è avvenuta correttamente");
							return;
						}
						
					}
					public void onFailure(Throwable caught) {
						com.google.gwt.user.client.Window.alert(caught.getMessage());
					}
				});
			}
		});
        
        
      //Aspetta che siano completamente caricati i dati dal db
        
        new Timer(){
        	public void run() {
        		
        		if(DataSourceProdotti.ready == 5){
        			
        			TreeGridField nameField = new TreeGridField("Name");
        	        nameField.setCanEdit(true);
        	        setFields(nameField);
        	        
        	        fetchData();
        		}else{
        			this.schedule(500);
        		}
			}
        	
        }.schedule(500);
        
	}
	
	
	public TreeGrid getTreeGrid(){
		return tg;
	}
	


	private void InsertItem(){
		
		final Window winModal = new Window();  
		winModal.setAutoSize(true); 
		winModal.setTitle("Inserimento Oggetto");  
		winModal.setShowMinimizeButton(false);  
		winModal.setIsModal(true);  
		winModal.setShowModalMask(true);  
		winModal.centerInPage();  
		
		
		DynamicForm form = new DynamicForm();
		                 form.setHeight100();
		                 form.setWidth100();
		                 form.setPadding(5);
		                 form.setLayoutAlign(VerticalAlignment.BOTTOM);
		final TextItem textItem = new TextItem();
		                 textItem.setTitle("Nome");
		
		                 
		winModal.addCloseClickHandler(new CloseClickHandler() {  
		         			
		             		public void onCloseClick(CloseClientEvent event) {
		             			String nome = textItem.getDisplayValue();
		        				if(nome == null || nome.length()==0) {
		        					winModal.destroy();
		        					return;
		        					}else{
		        				insertItem(nome);
		        				winModal.destroy();}
		             		}

		             		});
		winModal.addKeyPressHandler(new KeyPressHandler() {
			
			public void onKeyPress(KeyPressEvent event) {
				event.getKeyName();
				System.out.println(event.getKeyName());
				if(event.getKeyName().equals("")){
					
				}
			}
		});
		
		winModal.addItem(form);
		//winModal.addItem(hp);
		winModal.show();
		
		
		
		
	}
	
	public void insertItem(String name){
		
		String tipo = selezionato.getAttribute("Tipo");
		String PID 	= selezionato.getAttribute("ID");
		
		final ListGridRecord nr = new ListGridRecord();
		nr.setAttribute("PID", PID);
		nr.setAttribute("Name", name);
		
		String query = null;
		
		if(tipo.equals("Categoria")){
			String id = DataSourceProdotti.getNewIDMaxTipologia();
			nr.setAttribute("Tipo", "Tipologia");
			nr.setAttribute("ID", "T"+id);
			PID = PID.substring(1);
			query = "INSERT INTO prodotto_tipologia (`ID`,`Tipologia`,`IDCategoria`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		
		}else if (tipo.equals("Tipologia")){
			String id = DataSourceProdotti.getNewIDMaxVarieta();
			nr.setAttribute("Tipo", "Varieta");
			nr.setAttribute("ID", "V"+id);
			PID = PID.substring(1);
			query = "INSERT INTO prodotto_varieta (`ID`,`Varieta`,`IDTipologia`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		
		}else if (tipo.equals("Varieta")){
			String id = DataSourceProdotti.getNewIDMaxSottoVarieta();
			nr.setAttribute("Tipo", "SottoVarieta");
			nr.setAttribute("ID", "SV"+id);
			PID = PID.substring(1);
			query = "INSERT INTO prodotto_sottovarieta (`ID`,`Sottovarieta`,`IDVarieta`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		
		}else if (tipo.equals("SottoVarieta")){
			String id = DataSourceProdotti.getNewIDMaxCalibro();
			nr.setAttribute("Tipo", "Calibro");
			nr.setAttribute("ID", "CA"+id);
			PID = PID.substring(2);
			query = "INSERT INTO prodotto_calibro (`ID`,`Calibro`,`IDSottovarieta`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		
		}else if (tipo.equals("Calibro")){
			
		}else if (tipo.equals("Imballaggio")){
			// NIENTE
		}
		
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				com.google.gwt.user.client.Window.alert(caught.getMessage());
			}
			public void onSuccess(Boolean result) {
				if(!result){
					com.google.gwt.user.client.Window.alert("L'oggetto non è stato inserito. Riprovare!");
					return;
				}
			}
			
		});
		
		DataSourceProdotti.getIstance().addData(nr);
	}
	
	
}
