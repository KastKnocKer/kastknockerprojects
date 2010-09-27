package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceMercati;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;



public class PanelFiltroContatti extends Layout{
	
	private static Vector<Contatto> V = null;
	private Vector<Contatto> v = null;
	private DynamicForm form = null;
	
	private SelectItem ti_TipoMercatoDiRiferimento;
	private SelectItem ti_MercatoDiRiferimento;
	private CheckboxItem setSelezionePreferita;
	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	private String[][] risultatoQueryFiltroPerMercato = null;
	
	private DynamicForm form1,form2,form3,form4;
	private VLayout lay1,lay2,lay3,lay4;
	
	public PanelFiltroContatti(){
		super();
		
		VLayout vlayout = new VLayout();
		vlayout.setWidth100();
		vlayout.setHeight100();
		
		HLayout hlayout1 = new HLayout();
		hlayout1.setWidth100();
		hlayout1.setHeight100();
		
		HLayout hlayout2 = new HLayout();
		hlayout2.setWidth100();
		hlayout2.setHeight100();
		
		vlayout.addMember(hlayout1);
		vlayout.addMember(hlayout2);
		
		lay1 = new VLayout();
		lay2 = new VLayout();
		lay3 = new VLayout();
		lay4 = new VLayout();
		
		hlayout1.addMember(lay1);
		hlayout1.addMember(lay2);
		hlayout2.addMember(lay3);
		hlayout2.addMember(lay4);
		
		form1 = new DynamicForm();
		form2 = new DynamicForm();
		form3 = new DynamicForm();
		form4 = new DynamicForm();
		
		createFilter1();
		createFilter2();
		createFilter4();
		
		this.addMember(vlayout);
	}
	
	private void createFilter1(){
		final ListGrid lg = new ListGrid();
		lg.setWidth100();
		lg.setHeight(200);
		lg.setDataSource(DataSourceContatti.getIstance());
		ListGridField lgf = new ListGridField("tiposoggetto","TipoSoggetto");
		lgf.setHidden(true);
		lg.setFields(new ListGridField("ragionesociale","Ragione Sociale"),lgf);
		lg.setGroupByField("tiposoggetto");
		lg.setSelectionType(SelectionStyle.MULTIPLE);  
		lg.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
		lg.fetchData();
		
		lay1.addMember(lg);
		
		Button button = new Button("Filtra");
		button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				ListGridRecord[] records = lg.getSelection();
				Vector<Contatto> vett = DataSourceContatti.getVettoreContatti();
				v = new Vector<Contatto>();
				for(int i=0; i<records.length; i++){
					for(int j=0; j<vett.size(); j++){
						Contatto contatto = vett.get(j);
						
						if(contatto.getID().equals(records[i].getAttribute("id"))){
							v.add(contatto);
							break;
						}
					}
				}
				
				
			}
		});
		
		lay1.addMember(button);
		
	}

	private void createFilter2(){
		lay2.addMember(form2);
		
		final SelectItem selectCategorie;
		final SelectItem selectTipologie;
		
		selectCategorie = new SelectItem();  
		selectCategorie.setTitle("Seleziona categorie");  
		selectCategorie.setMultiple(true);  
		selectCategorie.setMultipleAppearance(MultipleAppearance.PICKLIST);
		selectCategorie.setValueMap("Frutta","Frutta Secca","Verdura");
		
		selectTipologie = new SelectItem();
		selectTipologie.setTitle("Seleziona tipologie");  
		selectTipologie.setMultiple(true);  
		selectTipologie.setMultipleAppearance(MultipleAppearance.PICKLIST);

		
		selectCategorie.addBlurHandler(new BlurHandler() {
			
			public void onBlur(BlurEvent event) {
				String[] selezione = selectCategorie.getValues();
				Vector<Prodotto> vTemp = DataSourceProdottiCatalogati.getvProdottiCatalogati();
				Prodotto prodotto = null;
				
				String[] map = new String[vTemp.size()];
				int indicemap = 0;
				
				
					
				for(int i=0; i<vTemp.size(); i++){
						prodotto = vTemp.get(i);
						
					for(int k=0; k<selezione.length; k++){
						if(prodotto.getCategoria().equals( selezione[k] )){
							
							if(indicemap == 0){
								map[indicemap] = prodotto.getTipologia();
								indicemap++;
								break;
							}
							if(map[indicemap-1].equals(prodotto.getTipologia())){
								
							}else{
								map[indicemap] = prodotto.getTipologia();
								indicemap++;
								break;
							}
						}
					}
					
				}
				
				
				
				String selezionato = "";
				for(int i=0; i<selezione.length; i++){
					selezionato = selezionato + " " + selezione[i];
				}
				selectTipologie.setValueMap(map);
			}
		});
		
		
		Button button = new Button("Filtra");
		button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				String query = "SELECT DISTINCT IDFornitore FROM fornitore_tipologie_trattate";
				String[] tipologie = selectTipologie.getValues();
				if(tipologie.length > 0){
					query = query + " WHERE ";
					for(int i=0;i<tipologie.length;i++){
						query = query + " Tipologia = '"+tipologie[i]+"' ";
						if(i+1<tipologie.length) query = query + " OR ";
					}
				}
				rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(String[][] result) {
						v = new Vector<Contatto>();
						Vector<Contatto> vett = DataSourceContatti.getVettoreContatti();
						for(int i=0; i<result.length; i++){
							String id = result[i][0];
							Contatto contatto = vett.get(i);
							if(contatto.getID().equals(id)){
								v.add(contatto);
								continue;
							}
						}
					}
					
					
				});
			}
		});
		form2.setItems(selectCategorie,selectTipologie);
		lay2.addMember(button);
	}
	
	private void createFilter4(){
		
		ti_TipoMercatoDiRiferimento = new SelectItem();
		ti_TipoMercatoDiRiferimento.setName("tipomercato");
		ti_TipoMercatoDiRiferimento.setTitle("Tipo Mercato");
		ti_TipoMercatoDiRiferimento.setValueMap( DataSourceMercati.getTipoMercato() );
		ti_TipoMercatoDiRiferimento.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				ti_MercatoDiRiferimento.setValueMap( DataSourceMercati.getMercatiMap((String) event.getValue()));
				ti_MercatoDiRiferimento.setValue("");
				/////////
			}
		});
		
		ti_MercatoDiRiferimento = new SelectItem();
		ti_MercatoDiRiferimento.setName("mercato");
		ti_MercatoDiRiferimento.setTitle("Mercato di riferimento");
		ti_MercatoDiRiferimento.setMultiple(true);
		ti_MercatoDiRiferimento.setMultipleAppearance(MultipleAppearance.PICKLIST);
		
		ti_MercatoDiRiferimento.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				String querybase = "SELECT C.ID, C.RagioneSociale, m.Mercato, m.TipoMercato FROM contatti c JOIN mercati m ON c.IDMercato = m.ID";
				String query = "";
				
				String[] mercatiSelezionati = ti_MercatoDiRiferimento.getValues();
				if( (mercatiSelezionati.length == 0)||(mercatiSelezionati.length ==1 && mercatiSelezionati[0].equals("")) ){
					query = querybase +" WHERE m.TipoMercato = '"+ti_TipoMercatoDiRiferimento.getValue()+"'";
				}else{
					query = querybase +" WHERE";
					for(int i=0; i<mercatiSelezionati.length;i++){
						if(i==0){
							query = query +" m.Mercato = '"+mercatiSelezionati[i]+"'";
						}else{
							query = query +" OR m.Mercato = '"+mercatiSelezionati[i]+"'";
						}
					}
				}
				
				rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
					
					@Override
					public void onSuccess(String[][] result) {
						risultatoQueryFiltroPerMercato = result;
					}
					
					@Override
					public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
				});
			}
		});
		form4.setItems(ti_TipoMercatoDiRiferimento,ti_MercatoDiRiferimento);
		
		Button button = new Button("Filtra");
		
		button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				Vector<Contatto> newVector = new Vector<Contatto>();
				Vector<Contatto> temp = DataSourceContatti.getVettoreContatti();
				Contatto contatto = null;
				boolean mercato = false;
				String valueMercato = null;
				if( form4.getValue("tipomercato") != null){
					valueMercato = (String) form4.getValue("tipomercato");
					valueMercato.toLowerCase();
					if( valueMercato.length() > 0 ) mercato = true;
				}
				
				
				for(int i=0; i<temp.size(); i++){
					contatto = temp.get(i);
					System.out.println("ANALIZZO	1	: " + contatto.getRagioneSociale());
					//Filtro clienti e intermediari
					if(contatto.getTipoSoggetto().equals("Trasportatore") || contatto.getTipoSoggetto().equals("Fornitore")){continue;}
					System.out.println("ANALIZZO	2	: " + contatto.getRagioneSociale()+"	"+valueMercato+"	"+contatto.getIndirizzo().toLowerCase().contains("valueMercato"));
					
					if(mercato){
						boolean trovato = false;
						for(int k=0; k<risultatoQueryFiltroPerMercato.length;k++){
							if( contatto.getID().equals(risultatoQueryFiltroPerMercato[k][0]) ){
								trovato = true;
								break;
							}
						}
						if(!trovato) continue;	
					}
					
					newVector.add(contatto);
					System.out.println("Aggiungo alla lista: " + contatto.getRagioneSociale());
				}
				
				v = newVector;
				
				
				
				ti_TipoMercatoDiRiferimento.setValue("");
				ti_MercatoDiRiferimento.setValue("");
			}
		});
		
		
		lay2.addMember(form4);
		lay2.addMember(button);
	}
	
	public Vector<Contatto> getVettoreContattiFiltrato() {
		if(v == null) return DataSourceContatti.getVettoreContatti();
		return v;
	}
	
	
}
