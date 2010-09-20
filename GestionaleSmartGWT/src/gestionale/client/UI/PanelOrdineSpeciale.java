package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceGoogleMaps;
import gestionale.shared.Contatto;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.DirectionsPanel;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Route;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class PanelOrdineSpeciale extends TabSet{
	
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	
	private String IDOrdine;
	private PanelOrdineSpeciale thisTabSet;
	private Tab tab;
	private Tab tabTabella;
	private Tab tabFiltroProdotti;
	private Tab tabPedane;
	private ListGrid lg_pedane;
	
	private DynamicForm form;
	
	private SelectItem cliente;
	private SelectItem fornitore;
	private SelectItem pallet;
	private TextItem nomepedana;
	
	private ListGridRecord lastContactClicked = null;
	
	
	public PanelOrdineSpeciale(ListGridRecord ordine){
		super();
		IDOrdine = ordine.getAttribute("id");
		thisTabSet = this;
		if(IDOrdine == null){
			this.destroy();
		}
		
		this.addToTabPanel("Visualizzazione Ordine: " + ordine.getAttribute("datacreazioneordine")+" - ["+ordine.getAttribute("tipoordine")+"]", true);
		
		tabPedane = new Tab("Pedane");
		lg_pedane = new ListGrid(){  
            @Override  
            protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {  
                if (getFieldName(colNum).equals("percentualecompletamento")) {  
                   float perc = Float.parseFloat(record.getAttribute("percentualecompletamento"));
                   if (perc > 130) {  
                       return "font-weight:bold; color:#FF0000;";  
                   	} else if(perc > 80){  
                        return "font-weight:bold; color:#008800;";  
                    } else if (perc > 50) {  
                        return "font-weight:bold; color:#DAA520;";  
                    } else if (perc >= 0) {  
                        return "font-weight:bold; color:#FF0000;";  
                    } else {  
                        return super.getCellCSSText(record, rowNum, colNum);  
                    }
                    
                } else {  
                    return super.getCellCSSText(record, rowNum, colNum);  
                }  
            }  
        }; 
		lg_pedane.setWidth100();
		lg_pedane.setHeight100();
		
		//CreoDatasource
		/*
		DataSource ds = new DataSource();
		DataSourceTextField idField = new DataSourceTextField("id");  
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		ds.setFields(idField);
		ds.setClientOnly(true);
		lg_pedane.setDataSource(ds);
		*/
		
		ListGridField pedana = new ListGridField("pedana","Pedana");
		ListGridField fornitore = new ListGridField("fornitore","Fornitore");
		ListGridField cliente = new ListGridField("cliente","Cliente");
		ListGridField pallet = new ListGridField("pallet","Pallet");
		ListGridField percentualecompletamento = new ListGridField("percentualecompletamento","Perc.Completamento");
		percentualecompletamento.setType(ListGridFieldType.FLOAT);
		
		lg_pedane.setFields(pedana,cliente,fornitore,pallet,percentualecompletamento);
		lg_pedane.addRowContextClickHandler(new RowContextClickHandler() {
			
			@Override
			public void onRowContextClick(RowContextClickEvent event) {
				lastContactClicked = event.getRecord();
				Menu menu = new Menu();
				
				MenuItem mi_modifica = new MenuItem("Modifica pedana");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi pedana");
				
				menu.addItem(mi_modifica);
				menu.addItem(mi_rimuovi);
				
				
				mi_modifica.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						new WindowDettaglioOrdiniSpeciale(lastContactClicked, thisTabSet);
					}
				});
				
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
			}
		});
		
		
		VLayout layout = new VLayout();
		layout.addMember(lg_pedane);
		form = this.getForm();
		layout.addMember(form);
		
		tabPedane.setPane(layout);
		
		this.addTab(tabPedane);
		
		
		
		caricaDatiListGridPedane();
		
		
		this.addTabSelectedHandler(new TabSelectedHandler() {
			
			public void onTabSelected(TabSelectedEvent event) {
				/*
				if(event.getTab() == tabTabella){
					panelTabella.destroy();
					panelTabella = new Layout();
					panelTabella.addMember(flextable.creaTabella());
					tabTabella.setPane(panelTabella);
					
				}else if(event.getTab() == tabTabellaComplessiva){
					if(lgdettaglioordini != null)lgdettaglioordini.destroy();
					lgdettaglioordini = new ListGridDettaglioOrdini(IDOrdine);
					panelTabellaComplessiva.addMember(lgdettaglioordini);
				}*/
				
			}
		});
		
	}



	public void caricaDatiListGridPedane(){
		String query = "SELECT o.*,cf.ID,cf.RagioneSociale,cc.ID,cc.RagioneSociale,p.Nome FROM ordine_dettaglio_speciale o JOIN contatti cc JOIN contatti cf JOIN pallet p ON o.IDFornitore = cf.ID AND o.IDCliente = cc.ID AND o.IDPallet = p.ID WHERE IDOrdine = '"+IDOrdine+"'";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			@Override
			public void onSuccess(String[][] result) {
				if(result == null) return;
				ListGridRecord[] records = new ListGridRecord[result.length];
				for(int i=0; i<result.length;i++){
					records[i] = new ListGridRecord();
					records[i].setAttribute("id", result[i][0]);
					records[i].setAttribute("idordine", result[i][1]);
					records[i].setAttribute("idfornitore", result[i][2]);
					records[i].setAttribute("idcliente", result[i][3]);
					records[i].setAttribute("idpallet", result[i][4]);
					records[i].setAttribute("pedana", result[i][5]);
					records[i].setAttribute("percentualecompletamento", result[i][6]);
					records[i].setAttribute("idfornitore", result[i][7]);
					records[i].setAttribute("fornitore", result[i][8]);
					records[i].setAttribute("idcliente", result[i][9]);
					records[i].setAttribute("cliente", result[i][10]);
					records[i].setAttribute("pallet", result[i][11]);
					
				}
				lg_pedane.setData(records);
			}
			
			@Override
			public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
		});
	}

	
	
	
	private DynamicForm getForm() {
		DynamicForm form = new DynamicForm();
		
		DataSourceTextField idField = new DataSourceTextField("idcliente");
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		DataSourceTextField ragsocField = new DataSourceTextField("ragionesocialecliente");
		
		
		final DataSource ds_cliente = new DataSource();
		ds_cliente.setFields(idField, ragsocField);
		ds_cliente.setClientOnly(true);
		
		///
		
		DataSourceTextField idField2 = new DataSourceTextField("idfornitore");
		idField2.setHidden(true);  
		idField2.setPrimaryKey(true);
		DataSourceTextField ragsocField2 = new DataSourceTextField("ragionesocialefornitore");
		
		
		final DataSource ds_fornitore = new DataSource();
		ds_fornitore.setFields(idField2, ragsocField2);
		ds_fornitore.setClientOnly(true);
		
		///
		
		DataSourceTextField idField3 = new DataSourceTextField("idpallet");
		idField3.setHidden(true);  
		idField3.setPrimaryKey(true);
		DataSourceTextField descField3 = new DataSourceTextField("nomepallet");
		
		final DataSource ds_pallet = new DataSource();
		ds_pallet.setFields(idField3, descField3);
		ds_pallet.setClientOnly(true);
		
		
		
		
		String query = "SELECT * FROM contatti WHERE TipoSoggetto = 'Cliente'";
		
		rpc.eseguiQueryContatto(query, new AsyncCallback<Contatto[]>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Contatto[] result) {
				for(int i=0; i<result.length;i++){
					Record record = new Record();
					record.setAttribute("idcliente", result[i].getID());
					record.setAttribute("ragionesocialecliente", result[i].getRagioneSociale());
				    ds_cliente.addData(record);
				}
			}
		});
		
		query = "SELECT * FROM contatti WHERE TipoSoggetto = 'Fornitore'";
		
		rpc.eseguiQueryContatto(query, new AsyncCallback<Contatto[]>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Contatto[] result) {
				for(int i=0; i<result.length;i++){
					Record record = new Record();
					record.setAttribute("idfornitore", result[i].getID());
					record.setAttribute("ragionesocialefornitore", result[i].getRagioneSociale());
				    ds_fornitore.addData(record);
				}
			}
		});
		
		
		
		cliente = new SelectItem("idcliente","Cliente");
		cliente.setRequired(true);
		cliente.setDisplayField("ragionesocialecliente");
		cliente.setOptionDataSource(ds_cliente);
		cliente.setAutoFetchData(true);
		
		fornitore = new SelectItem("idfornitore","Fornitore");
		fornitore.setRequired(true);
		fornitore.setDisplayField("ragionesocialefornitore");
		fornitore.setOptionDataSource(ds_fornitore);
		fornitore.setAutoFetchData(true);
		
		pallet = new SelectItem("idpallet","Pallet");
		pallet.setRequired(true);
		pallet.setDisplayField("nomepallet");
		pallet.setOptionDataSource(ds_pallet);
		pallet.setAutoFetchData(true);
		
		nomepedana = new TextItem("nomepedana","Pedana");
		
		fornitore.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				
				
				String query = "SELECT p.ID, p.Nome FROM pallet p JOIN fornitore_pallet_utilizzato fpu ON p.ID = fpu.IDPallet WHERE fpu.IDFornitore = '"+event.getValue()+"';";
				
				rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(String[][] result) {
						/*
						DataSourceTextField idField = new DataSourceTextField("id");
						idField.setHidden(true);  
						idField.setPrimaryKey(true);
						DataSourceTextField descField = new DataSourceTextField("nomepallet");
						
						DataSource ds_pallet = new DataSource();
						ds_pallet.setFields(idField, descField);
						ds_pallet.setClientOnly(true);
						*/
						Record[] records = ds_pallet.getCacheData();
						for(int i=0;i<records.length;i++){
							ds_pallet.removeData(records[i]);
						}
						
						for(int i=0;i<result.length;i++){
							Record record = new Record();
							record.setAttribute("idpallet", result[i][0]);
							record.setAttribute("nomepallet", result[i][1]);
							ds_pallet.addData(record);
						}
						//pallet.setOptionDataSource(ds_pallet);
						//pallet.fetchData();
					}
					
				});
				
			}
		});
		
		/*
		ListGridField pedana = new ListGridField("pedana","Pedana");
		ListGridField fornitore = new ListGridField("fornitore","Fornitore");
		ListGridField cliente = new ListGridField("cliente","Cliente");
		ListGridField pallet = new ListGridField("pallet","Pallet");
		*/
		
		ButtonItem okbutton = new ButtonItem();
		okbutton.setTitle("Aggiungi pedana");
		
		okbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DynamicForm form = PanelOrdineSpeciale.this.form;
				form.validate();
				if(form.hasErrors()){
					Window.alert("Campi mancanti!");
					return;
				}
				
				String query = "INSERT INTO ordine_dettaglio_speciale (IDOrdine,IDCliente,IDFornitore,IDPallet,Pedana) VALUES ('"+IDOrdine+"','"+cliente.getValue()+"','"+fornitore.getValue()+"','"+pallet.getValue()+"','"+nomepedana.getValue()+"');";
				form.reset();
				rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Boolean result) {
						if(!result){
							Window.alert("Errore durante l'inserimento della pedana!");
							return;
						}
						caricaDatiListGridPedane();
					}
					
				});

			}
		});
		
		form.setFields(nomepedana,cliente,fornitore,pallet,okbutton);
		return form;
	}







	private void closePanel(){
		GUIManager.getTopTabset().removeTab(tab);
	}

	private void addToTabPanel(String tabName, boolean canclose){

		Tab[] tabArray = GUIManager.getTopTabset().getTabs();
		
        for( int i=0; i<tabArray.length; i++){
        	if( tabArray[i].getTitle().equals( tabName ) ){
        		GUIManager.getTopTabset().selectTab(tabArray[i]);
        		return;
        		}
        }
		
		tab = new Tab(tabName);
		tab.setPane( thisTabSet );
        tab.setCanClose(canclose);
        GUIManager.getTopTabset().addTab(tab);
        GUIManager.getTopTabset().selectTab(tab);
		
		return;
		
	}

}
