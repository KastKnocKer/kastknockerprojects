package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceImballaggi;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;  
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;  
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class WindowDettaglioOrdiniSpeciale extends Finestra{

	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		
	private WindowDettaglioOrdiniSpeciale thiswind;
	
	private String IDPedana;
	
	private ListGrid lg_mod_pedana;
	
	private String IDFornitore;
	
	private SelectItem prodotto;
	private SelectItem imballaggio;
	private SpinnerItem numlivelli;
	private SpinnerItem numcolli;
	
	private Progressbar progressbar;
	private Label progressbarlabel;
	
	private int colliperlivello = 0;
	private int areapallet = 0;
	private int areaimballaggio = 0;
	
	private int altezzaOccupabile = 205;
	private int altezzaOccupata = 0;
	private int altezzaTmp = 0;
	private int altezzaImballaggioTmp = 0;
	
	private DynamicForm form;
	
	private ListGridRecord lastSelectedRecord;
	private PanelOrdineSpeciale panelordinespeciale;
	
	
	public WindowDettaglioOrdiniSpeciale(final ListGridRecord record_pedana, PanelOrdineSpeciale panelordinespeciale){
		super();
		thiswind = this;
		this.panelordinespeciale = panelordinespeciale;
		this.setTitle("Pedana: ");
		IDPedana = record_pedana.getAttribute("id");
		IDFornitore = record_pedana.getAttribute("idfornitore");
		
		HLayout hlayout = new HLayout();
		VLayout vlayout = new VLayout();
		VLayout vlayout2 = new VLayout();
		
		vlayout.setHeight100();
		vlayout.setWidth100();
		vlayout2.setHeight100();
		vlayout2.setWidth(80);
		hlayout.setHeight100();
		hlayout.setWidth100();
		
		
		progressbar = new Progressbar();  
		progressbar.setHeight100();
		progressbar.setWidth100();
        progressbar.setVertical(true);
        progressbar.setPercentDone(10);
        progressbarlabel = new Label("10%");
        progressbarlabel.setHeight(25);
        
        hlayout.addMember(vlayout);
        hlayout.addMember(vlayout2);
        vlayout2.addMember(progressbar);
        vlayout2.addMember(progressbarlabel);
		
		lg_mod_pedana = new ListGrid();
		ListGridField prodotto = new ListGridField("prodotto","Prodotto");
		ListGridField imballaggio = new ListGridField("imballaggio","Imballaggio");
		ListGridField numerolivelli = new ListGridField("numlivelli","N Livelli");
		ListGridField numerocolli = new ListGridField("numcolli","N Colli");
		ListGridField prezzocollo = new ListGridField("prezzocollo","Prezzo/Collo");
		ListGridField prezzolivello = new ListGridField("prezzoriga","Prezzo");
		
		lg_mod_pedana.setFields(prodotto,imballaggio,numerolivelli,numerocolli,prezzocollo,prezzolivello);
		lg_mod_pedana.addRowContextClickHandler(new RowContextClickHandler() {
			
			@Override
			public void onRowContextClick(RowContextClickEvent event) {
				lastSelectedRecord = event.getRecord();
				if(lastSelectedRecord == null) return;
				Menu menu = new Menu();
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Contatto");
				
				mi_rimuovi.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
					
					@Override
					public void onClick(MenuItemClickEvent event) {
						if( Window.confirm("Sei sicuro di voler rimuovere questa merce?") ){
							String query = "DELETE FROM ordine_dettaglio_speciale_livello WHERE ID = '"+lastSelectedRecord.getAttribute("id")+"'";
							
							rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Boolean result) {
									caricaDatiListGrid();
									WindowDettaglioOrdiniSpeciale.this.panelordinespeciale.caricaDatiListGridPedane();
								}
							});
						}
					}
				});
				
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
			}
		});
		
		caricaDatiListGrid();
		
		vlayout.addMember(lg_mod_pedana);
		form = getForm();
		vlayout.addMember(form);
		
		
		//this.setAutoSize(true);
		this.setIsModal(true);
		this.addItem(hlayout);
		this.setWidth(800);
		this.setHeight(400);
		this.centerInPage();
		this.draw();
		
		
		String query = "SELECT p.LatoLungo,p.LatoCorto,p.Altezza FROM pallet p WHERE ID = '"+record_pedana.getAttribute("idpallet")+"';";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String[][] result) {
				// TODO Auto-generated method stub
				areapallet = Integer.parseInt(result[0][0])*Integer.parseInt(result[0][1]);
			}
			
		});
	}
	
	private DynamicForm getForm(){
		DynamicForm localForm = new DynamicForm();
		
		prodotto = new SelectItem("idprodotto","Prodotto");
		prodotto.setDisplayField("prodotto");
		prodotto.setRequired(true);
		
		imballaggio = new SelectItem("idimballaggio","Imballaggio");
		imballaggio.setDisplayField("imballaggio");
		imballaggio.setRequired(true);
		
		numlivelli = new SpinnerItem("numlivelli","Numero livelli");
		numlivelli.setMin(0);
		numlivelli.setRequired(true);
		
		numcolli = new SpinnerItem("numcolli","Numero colli");
		numcolli.setMin(0);
		numcolli.setRequired(true);
		
		ButtonItem okbutton = new ButtonItem("Aggiungi");
		okbutton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				aggiungi();
			}
		});
		
		localForm.setFields(prodotto,imballaggio,numlivelli,numcolli,okbutton);
		
		String query = "SELECT pc.* FROM fornitore_tipologie_trattate f JOIN prodotti_catalogati pc JOIN assoc_prodotto_imballaggio_peso apip JOIN assoc_fornitore_prodotto_prezzo afpp JOIN imballaggio i ON pc.Categoria = f.Categoria AND pc.Tipologia = f.Tipologia AND apip.IDProdotto = pc.ID AND i.ID = apip.IDImballaggio AND afpp.IDFornitore = f.IDFornitore AND afpp.IDProdotto = pc.ID AND afpp.IDImballaggio = apip.IDImballaggio WHERE f.IDFornitore = '"+IDFornitore+"' AND afpp.Prezzo > 0 AND afpp.DataInserimento = ( SELECT MAX(DataInserimento) FROM assoc_fornitore_prodotto_prezzo afpp2 WHERE afpp2.IDFornitore = afpp.IDFornitore AND afpp2.IDProdotto = afpp.IDProdotto AND afpp2.IDImballaggio = afpp.IDImballaggio);";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			@Override
			public void onSuccess(String[][] result) {
				if(result == null) return;
				if(result.length == 0)Window.alert("Potrebbe esser necessario dover associare al fornitore i prodotti ed i loro relativi prezzi");
				
				DataSource ds = new DataSource();
				DataSourceTextField idField = new DataSourceTextField("idprodotto");  
							idField.setHidden(true);  
							idField.setPrimaryKey(true);
				DataSourceTextField descrizioneField = new DataSourceTextField("prodotto","Prodotto");
				ds.setFields(idField, descrizioneField);
				ds.setClientOnly(true);
				prodotto.setOptionDataSource(ds);
				
				for(int i=0; i<result.length;i++){
					Record record = new Record();
					record.setAttribute("idprodotto", result[i][0]);
					record.setAttribute("prodotto", result[i][2] +" "+ result[i][3] +" "+ result[i][4] +" "+ result[i][5]);
					ds.addData(record);
				}
				
				prodotto.fetchData();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		prodotto.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				//idprodotto event.getValue()
				String query = "SELECT i.ID,i.Descrizione FROM assoc_prodotto_imballaggio_peso a JOIN imballaggio i ON i.ID = a.IDImballaggio WHERE i.ID = '"+event.getValue()+"';";
				rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

					@Override
					public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

					@Override
					public void onSuccess(String[][] result) {
						if(result == null) return;
						if(result.length == 0)Window.alert("Potrebbe esser necessario dover associare al prodotto gli imballaggi!");
						
						DataSource ds = new DataSource();
						DataSourceTextField idField = new DataSourceTextField("idimballaggio");  
									idField.setHidden(true);  
									idField.setPrimaryKey(true);
						DataSourceTextField descrizioneField = new DataSourceTextField("imballaggio","Imballaggio");
						ds.setFields(idField, descrizioneField);
						ds.setClientOnly(true);
						imballaggio.setOptionDataSource(ds);
						
						for(int i=0; i<result.length;i++){
							Record record = new Record();
							record.setAttribute("idimballaggio", result[i][0]);
							record.setAttribute("imballaggio", result[i][1]);
							ds.addData(record);
						}
						
						imballaggio.fetchData();
					}
				});
						
			}
		});
		
		imballaggio.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				if(event.getValue() == null) return;
				int[] dim = DataSourceImballaggi.getIstance().getDimensioniImballaggioFromID((String) event.getValue());
				areaimballaggio = dim[0]*dim[1];
				colliperlivello = areapallet/areaimballaggio;
				altezzaImballaggioTmp = dim[2];
				
				System.out.println(colliperlivello+" "+areapallet+" "+areaimballaggio);
			}
		});
		
		numlivelli.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				//Integer.toString(colliperlivello*Integer.parseInt((String)numlivelli.getValue()))
				numcolli.setValue( ((Integer) numlivelli.getValue())*colliperlivello );
				altezzaTmp = altezzaImballaggioTmp*((Integer) numlivelli.getValue());
				int percentuale = (altezzaOccupata+altezzaTmp)*100/altezzaOccupabile;
				aggiornaBarra(percentuale);
			}
		});
		
		
		return localForm;
	}
	
	private void caricaDatiListGrid(){
		String query = "SELECT odsl.*, pc.Categoria,pc.Tipologia,pc.Varieta,pc.Sottovarieta, i.Descrizione, i.Altezza FROM ordine_dettaglio_speciale_livello odsl JOIN prodotti_catalogati pc JOIN imballaggio i ON odsl.IDProdotto = pc.ID AND odsl.IDImballaggio = i.ID WHERE IDPedana = '"+IDPedana+"';";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

			@Override
			public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

			@Override
			public void onSuccess(String[][] result) {
				altezzaOccupata = 0;
				DataSource ds = new DataSource();
				DataSourceTextField idField = new DataSourceTextField("id");  
							idField.setHidden(true);  
							idField.setPrimaryKey(true);
				DataSourceTextField descProdField = new DataSourceTextField("descprodotto","Prodotto");
				DataSourceTextField descImbField = new DataSourceTextField("descimballaggio","Imballaggio");
				DataSourceTextField numLivField = new DataSourceTextField("numlivelli","N Livelli");
				DataSourceTextField numColliField = new DataSourceTextField("numcolli","N Colli");
					
				
				ds.setFields(idField, descProdField, descImbField, numLivField, numColliField);
				ds.setClientOnly(true);
				lg_mod_pedana.setDataSource(ds);
				
				
				/**
				ID  	int(11)  	 	  	 No  	None  	 	  Naviga tra i valori DISTINCT   	  Modifica   	  Elimina   	  Primaria   	  Unica   	  Indice   	 Testo completo
				IDPedana 	int(11) 			No 	None 		Naviga tra i valori DISTINCT 	Modifica 	Elimina 	Primaria 	Unica 	Indice 	Testo completo
				IDProdotto 	int(11) 			No 	None 		Naviga tra i valori DISTINCT 	Modifica 	Elimina 	Primaria 	Unica 	Indice 	Testo completo
				IDImballaggio 	int(11) 			No 	None 		Naviga tra i valori DISTINCT 	Modifica 	Elimina 	Primaria 	Unica 	Indice 	Testo completo
				NumLivelli 	int(11) 			No 	None 		Naviga tra i valori DISTINCT 	Modifica 	Elimina 	Primaria 	Unica 	Indice 	Testo completo
				NumColli
				*/
				
				for(int i=0; i<result.length;i++){
					ListGridRecord record = new ListGridRecord();
					record.setAttribute("id", result[i][0]);
					record.setAttribute("idpedana", result[i][1]);
					record.setAttribute("idprodotto", result[i][2]);
					record.setAttribute("idimballaggio", result[i][3]);
					record.setAttribute("numlivelli", result[i][4]);
					record.setAttribute("numcolli", result[i][5]);
					record.setAttribute("descprodotto", result[i][6] +" "+ result[i][7] +" "+ result[i][8] +" "+ result[i][9]);
					record.setAttribute("descimballaggio", result[i][10]);
					altezzaOccupata = altezzaOccupata + Integer.parseInt(result[i][4])*Integer.parseInt(result[i][11]);
					ds.addData(record);
				}
				aggiornaBarra( altezzaOccupata*100/altezzaOccupabile );
				lg_mod_pedana.fetchData();
				aggiornaPercentualeOnDB();
			}
			
			
		});
	}
	
	private void aggiungi(){
		form.validate();
		if(form.hasErrors()){
			Window.alert("Campi mancanti!!");
			return;
		}
		String query = "INSERT INTO ordine_dettaglio_speciale_livello (IDProdotto,IDImballaggio,NumLivelli,NumColli,IDPedana) VALUES ('"+prodotto.getValue()+"','"+imballaggio.getValue()+"','"+numlivelli.getValue()+"','"+numcolli.getValue()+"','"+IDPedana+"')";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

			@Override
			public void onSuccess(Boolean result) {
				if(!result){
					Window.alert("L'inserimento non è avvenuto correttamente");
					return;
				}
				form.reset();
				caricaDatiListGrid();
			}
		});
		
	}
	
	private void aggiornaBarra(int percentuale){
		progressbar.setPercentDone(percentuale);
        progressbarlabel.setContents(percentuale+"%");
	}
	
	private void aggiornaPercentualeOnDB(){
		String query = "UPDATE ordine_dettaglio_speciale SET PercentualeCompletamento = '"+altezzaOccupata*100/altezzaOccupabile+"' WHERE ID = '"+IDPedana+"';";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Boolean result) {

				panelordinespeciale.caricaDatiListGridPedane();
			}
		});
	}
	
	
}
