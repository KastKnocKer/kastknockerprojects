package gestionale.client.UI;


import java.util.Date;
import java.util.Vector;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;



public class PanelCreazioneOrdine extends VLayout{
	
	private DynamicForm form;
	private DateItem dataCreazioneOrdine;
	private DateItem dataPartenzaMerce;
	private SelectItem TipoOrdine;
	private SelectItem Trasportatore;
	private SelectItem Fornitore;
	private TextAreaItem note;
	private Button confermaButton;
	private Finestra finestra;
	
	private boolean modifica;
	private Tab tab;
	
	
	
	public PanelCreazioneOrdine(){
		this.addForm();			//Aggiungo il form
	}
	
	public void setFinestra(Finestra fin){
		finestra=fin;
	}
	
	private void addForm(){
		
		form = new DynamicForm();
		//form.setWidth(250);
		form.setAutoWidth();
		form.setTitleOrientation(TitleOrientation.LEFT);
		
		dataCreazioneOrdine = new DateItem();  
		dataCreazioneOrdine.setDisabled(true);
		dataCreazioneOrdine.setName("dataCreazioneOrdine");  
		dataCreazioneOrdine.setTitle("Data creazione ordine");  
		dataCreazioneOrdine.setRequired(true);  
		dataCreazioneOrdine.setVisible(true);
		
		dataPartenzaMerce = new DateItem();  
		dataPartenzaMerce.setName("dataPartenzaMerce");  
		dataPartenzaMerce.setTitle("Data partenza merce");  
		dataPartenzaMerce.setRequired(true);  
		dataPartenzaMerce.setVisible(true);
		
		TipoOrdine = new SelectItem();
		TipoOrdine.setWidth(220);
		TipoOrdine.setTitle("Tipo ordine"); 
		TipoOrdine.setType("comboBox");
		TipoOrdine.setValueMap("Ordinario", "Speciale");
		TipoOrdine.setRequired(true);
		TipoOrdine.setDefaultValue("Ordinario");
		
		Trasportatore = new SelectItem();
		Trasportatore.setWidth(220);
		Trasportatore.setTitle("Trasportatore predefinito"); 
		Trasportatore.setType("comboBox");
		Trasportatore.setRequired(true);
		
		Fornitore = new SelectItem();
		Fornitore.setWidth(220);
		Fornitore.setTitle("Fornitore predefinito"); 
		Fornitore.setType("comboBox");
		Fornitore.setRequired(true);
		
		
		
		
		Vector<Contatto> vettTrasp = DataSourceContatti.getVettoreTrasportatori();
		Vector<Contatto> vettForn = DataSourceContatti.getVettoreFornitori();
		String[] trasportatori = new String[vettTrasp.size()];
		String[] fornitori = new String[vettForn.size()];
		
		for(int i=0; i<vettTrasp.size(); i++){
			trasportatori[i] = vettTrasp.get(i).getRagioneSociale();
		}
		
		for(int i=0; i<vettForn.size(); i++){
			fornitori[i] = vettForn.get(i).getRagioneSociale();
		}
		
		Trasportatore.setValueMap( trasportatori );
		Fornitore.setValueMap( fornitori );
		
		note = new TextAreaItem();
		note.setTitle("Note");
		
		
		form.setFields( new FormItem[] {dataCreazioneOrdine, dataPartenzaMerce, TipoOrdine, Trasportatore, Fornitore, note} );
		this.addMember(form);
		
		confermaButton = new Button("Inserisci");
		confermaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					if(!form.validate()){
						Window.alert("Campi mancanti!");
						return;
					}
					
					if(form.hasErrors()){
						Window.alert("Campi errati!");
						return;
					}
					
					
					Ordine ordine = new Ordine();
					
					ordine.setDataCreazioneOrdine(dataCreazioneOrdine.getDisplayValue());
					ordine.setDataPartenzaMerce(dataPartenzaMerce.getDisplayValue());
					ordine.setNote((String) note.getValue());
					ordine.setIDTrasportatore((String) Trasportatore.getValue());
					ordine.setConvalidato("0");
					ordine.setTipoOrdine((String) TipoOrdine.getValue());
					
					DataSourceOrdini.aggiungiOrdine(ordine);
					finestra.destroy();
				}
			});

		this.addMember(confermaButton);
		
	}
	 
}
