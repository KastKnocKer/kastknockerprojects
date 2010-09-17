package gestionale.client.UI;


import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceGiacenzaMagazzino;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Imballaggio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.docs.Date;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class PanelGestioneMagazzino extends VLayout{
	
	private ListGrid lg;
	private DynamicForm form;
	private SelectItem prodotto;
	private SelectItem imballaggio;
	private DateItem dataarrivomerce;
	private SpinnerItem numpedane;
	
	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	
	public PanelGestioneMagazzino(){
		super();
		
		lg = new ListGrid();
		lg.setWidth100();
		lg.setHeight100();
		lg.setDataSource(new DataSourceGiacenzaMagazzino());
		lg.fetchData();
		
		
		this.addMember(lg);
		form = getForm();
		this.addMember(form);
	}
	
	private DynamicForm getForm(){
		DynamicForm nform = new DynamicForm();
		
		prodotto = new SelectItem();
		prodotto.setOptionDataSource(DataSourceProdottiCatalogati.getIstance());
		prodotto.setTitle("Prodotto");
		prodotto.setName("codiceprodotto");
		prodotto.setDisplayField("descrizione");
		prodotto.setRequired(true);
		 
		prodotto.addChangedHandler(new ChangedHandler() {
		
			@Override
			public void onChanged(ChangedEvent event) {
				String query = "SELECT i.* FROM assoc_prodotto_imballaggio_peso a JOIN imballaggio i ON a.IDImballaggio = i.ID WHERE a.IDProdotto = '"+event.getValue()+"';";
				rpc.eseguiQueryImballaggio(query, new AsyncCallback<Imballaggio[]>() {
					
					@Override
					public void onSuccess(Imballaggio[] result) {
						if (result == null) return;
						DataSource ds = new DataSource();
						DataSourceTextField idField = new DataSourceTextField("id");  
									idField.setHidden(true);  
									idField.setPrimaryKey(true);
						DataSourceTextField descrizioneField = new DataSourceTextField("descrizione","Descrizione");
						ds.setFields(idField, descrizioneField);
						ds.setClientOnly(true);
						imballaggio.setOptionDataSource(ds);
						
						Record record = new Record();
						for(int i=0; i<result.length; i++){
						Imballaggio imballaggio = result[i];
						record = new Record();
						record.setAttribute("id", imballaggio.getID());
						record.setAttribute("descrizione", imballaggio.getDescrizione());
						ds.addData(record);
						}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
				});
			
			}
		}); 
		
		
		
		/*
		SelectItem selectItem = new SelectItem();  
        selectItem.setDefaultToFirstOption(true);   
        selectItem.setTitle("Item");  
        selectItem.setDisplayField("itemName");  
        selectItem.setPickListWidth(250);  
        selectItem.setOptionDataSource(supplyItemDS);  
        selectItem.addChangeHandler(new ChangeHandler() {  
            public void onChange(ChangeEvent event) {  
                label.setContents("Selected itemID : " + event.getValue());  
            }  
        });  */
		
		
		
		imballaggio = new SelectItem("id","Imballaggio");
		imballaggio.setDisplayField("descrizione");
		imballaggio.setRequired(true);
		
		
		dataarrivomerce = new DateItem("dataarrivomerce","Data arrivo merce");
		dataarrivomerce.setUseTextField(true);  
		dataarrivomerce.setUseMask(true);
		dataarrivomerce.setInputFormat("YMD");
		dataarrivomerce.setMaskDateSeparator("-");
		dataarrivomerce.setRequired(true);
		
		numpedane = new SpinnerItem("numpedane","N Pedane");
		numpedane.setStep(0.5);
		numpedane.setMin(0.5);
		numpedane.setRequired(true);
		
		ButtonItem okbutton = new ButtonItem("ok","Aggiungi");
		
		okbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.validate();
				if(form.hasErrors()){
					Window.alert("Campi mancanti o errati!");
					return;
				}
				
				String data = dataarrivomerce.getDisplayValue();
				data = data.replaceAll("/", "-");

				String query = "INSERT INTO magazzino (IDProdotto, IDImballaggio, DataArrivoMerce, NumPedane, ValiditaMerce) VALUES ('"+prodotto.getValue()+"','"+imballaggio.getValue()+"','"+data+"','"+numpedane.getValue()+"','VALIDA')";
				
				rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

					@Override
					public void onSuccess(Boolean result) {
						if(result){
							//ok
						}else{
							//fail
						}
					}
					
				});
				
				form.reset();
			}
		});
		
		nform.setFields(prodotto,imballaggio,dataarrivomerce,numpedane,okbutton );
		
		return nform;
	}

}
