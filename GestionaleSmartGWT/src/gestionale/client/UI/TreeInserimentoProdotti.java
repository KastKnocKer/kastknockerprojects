package gestionale.client.UI;

import gestionale.client.DB;
import gestionale.client.DataBase.DataSourceProdotti;

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
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class TreeInserimentoProdotti extends TreeGrid{
	
	private static TreeGrid tg;
	private static Record selezionato = null;
	
	public TreeInserimentoProdotti(){
		super();
		tg = this;
		setLoadDataOnDemand(false);  
        setWidth100();
        setHeight100(); 
        setDataSource( DataSourceProdotti.getIstance() );  
        setCanEdit(true);  
        setAutoFetchData(true);
        setAutoSaveEdits(true);
        setCanFreezeFields(true);  
        setCanReparentNodes(true); 
		
        TreeGridField nameField = new TreeGridField("Name");
        //nameField.setCanEdit(true);
        //nameField.setFrozen(true);
        
        setFields(nameField);
        
        this.addRecordClickHandler(new RecordClickHandler() {
			
			public void onRecordClick(RecordClickEvent event) {
				
			}
			
		});
        
        
        this.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			
        	public void onRecordDoubleClick(RecordDoubleClickEvent event) {
        		
        		if(event.isCtrlKeyDown()){
        			return;
        		}
        		
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
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("Imballaggio")){
					menu.addItem( mi_aggiungi_tn );
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
				DB db = new DB();
				String id = record.getAttribute("ID");
				System.out.println("RECORD: " + record.getAttribute("Tipo") + " " + record.getAttribute("ID"));
				
				if (tipo.equals("Tipologia")){
					query = "UPDATE prodotto_tipologia SET Tipologia='"+ event.getNewValue() +"' WHERE ID="+ id.substring(1) +";";
				}else if (tipo.equals("Varieta")){
					query = "UPDATE prodotto_varieta SET Varieta='"+ event.getNewValue() +"' WHERE ID="+ id.substring(1) +";";
				}else if (tipo.equals("SottoVarieta")){
					query = "UPDATE prodotto_sottovarieta SET Sottovarieta='"+ event.getNewValue() +"' WHERE ID="+ id.substring(2) +";";
				}else if (tipo.equals("Calibro")){
					query = "UPDATE prodotto_calibro SET Calibro='"+ event.getNewValue() +"' WHERE ID="+ id.substring(2) +";";
				}else if (tipo.equals("Imballaggio")){
					query = "UPDATE prodotto_imballaggio SET Imballaggio='"+ event.getNewValue() +"' WHERE ID="+ id.substring(1) +";";
				}

				
				db.eseguiUpdateToDB(query);
			}
		});
        
	}
	
	
	public TreeGrid getTreeGrid(){/*
		tg = new TreeGrid();
        tg.setWidth100();
        tg.setHeight100();
        tg.setCanEdit(true);
        //tg.setAutoFetchData(true);
        TreeGridField nameField = new TreeGridField("Name", 150);
        tg.setFields(nameField);
        tg.setData( DataSourceProdotti.getIstance() );*/
		/*
		EmployeeXmlDS employeesDS = EmployeeXmlDS.getInstance();  
		  
        final TreeGrid treeGrid = new TreeGrid();  
        treeGrid.setLoadDataOnDemand(false);  
        treeGrid.setWidth(500);  
        treeGrid.setHeight(400);  
        treeGrid.setDataSource(employeesDS);  
        treeGrid.setCanEdit(true);  
        treeGrid.setAutoFetchData(true);  
        treeGrid.setCanFreezeFields(true);  
        treeGrid.setCanReparentNodes(true);          
  
        TreeGridField nameField = new TreeGridField("Name");  
        nameField.setFrozen(true);  
        treeGrid.setFields(nameField);  
  
    
        treeGrid.draw();  
        
     
  
/*
        tg.addNodeClickHandler(new NodeClickHandler() {
			
			public void onNodeClick(NodeClickEvent event) {
				tn_selezionato = event.getNode();
				
				
				String tipo = tn_selezionato.getAttribute("Tipo");
				
				Menu menu = new Menu();
				
				MenuItem mi_aggiungi_tn = new MenuItem("Aggiungi voce");
				mi_aggiungi_tn.addClickHandler(new ClickHandler() {
					
					public void onClick(MenuItemClickEvent event) {
						InsertItem(tn_selezionato);
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
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}else if (tipo.equals("Imballaggio")){
					menu.addItem( mi_aggiungi_tn );
					menu.addItem( mi_rimuovi_tn );					
					menu.setAutoDraw(true);
					menu.showContextMenu();
					
				}
				
				
				
			}
		});
        
        */
        
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
		
		final ListGridRecord nr = new ListGridRecord();
		String tipo = selezionato.getAttribute("Tipo");
		
		nr.setAttribute("PID", selezionato.getAttribute("ID"));
		nr.setAttribute("Name", name);
		
		DB db = new DB();
		String query = null;
		//"INSERT INTO contatti (`RagioneSociale`,`Precisazione`,`PIVA`,`Logo`,`Indirizzo`,`Telefono`,`Cellulare`,`Fax`,`Email`,`SitoWeb`,`TipoSoggetto`,`Provvigione`,`Note`) VALUES ('"+contatto.getRagioneSociale()+"','"+contatto.getPrecisazione()+"','"+contatto.getPIVA()+"','"+contatto.getLogo()+"','"+contatto.getIndirizzo()+"','"+contatto.getTelefono()+"','"+contatto.getCellulare()+"','"+contatto.getFax()+"','"+contatto.geteMail()+"','"+contatto.getSitoWeb()+"','"+contatto.getTipoSoggetto()+"','"+contatto.getProvvigione()+"','"+contatto.getNote()+"')";
		String PID 	= selezionato.getAttribute("ID");
		
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
			nr.setAttribute("ID", "C"+id);
			PID = PID.substring(2);
			query = "INSERT INTO prodotto_calibro (`ID`,`Calibro`,`IDSottovarieta`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		}else if (tipo.equals("Calibro")){
			String id = DataSourceProdotti.getNewIDMaxImballaggio();
			nr.setAttribute("Tipo", "Imballaggio");
			nr.setAttribute("ID", "I"+id);
			PID = PID.substring(1);
			query = "INSERT INTO prodotto_imballaggio (`ID`,`Imballaggio`,`IDCalibro`) VALUES ('"+ id +"','"+ name +"','"+ PID +"')";
		}else if (tipo.equals("Imballaggio")){
			// NIENTE
		}
		
		db.eseguiUpdateToDB(query);
		
		DataSourceProdotti.getIstance().addData(nr);
		tg.fetchData();
	}
	
	
}
