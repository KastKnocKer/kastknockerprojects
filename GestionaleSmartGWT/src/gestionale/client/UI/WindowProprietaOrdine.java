package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class WindowProprietaOrdine extends Finestra{

	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private DynamicForm form;
	private DateItem dataCreazioneOrdine;
	private DateItem dataPartenzaMerce;
	private SelectItem TipoOrdine;
	private SelectItem Trasportatore;
	private SelectItem Fornitore;
	private TextAreaItem note;
	private ButtonItem confermaButton;
	private ButtonItem convalidaButton;
	private Finestra finestra;
	
	private String[] trasportatori;
	private String[] fornitori;
	private String[] trasportatoriID;
	private String[] fornitoriID;
	
	public WindowProprietaOrdine(final ListGridRecord selectedRecord){
		super();
		finestra = this;
		
		form = new DynamicForm();
		//form.setWidth(250);
		form.setAutoWidth();
		form.setTitleOrientation(TitleOrientation.LEFT);
		
		dataCreazioneOrdine = new DateItem();
		dataCreazioneOrdine.setInputFormat("YMD");
		dataCreazioneOrdine.setDisabled(true);
		dataCreazioneOrdine.setName("dataCreazioneOrdine");  
		dataCreazioneOrdine.setTitle("Data creazione ordine");  
		dataCreazioneOrdine.setRequired(true);  
		dataCreazioneOrdine.setVisible(true);
		
		dataPartenzaMerce = new DateItem();
		//dataPartenzaMerce.setUseTextField(true);
		//dataPartenzaMerce.setInputFormat("YMD");
		dataPartenzaMerce.setName("dataPartenzaMerce");  
		dataPartenzaMerce.setTitle("Data partenza merce");  
		dataPartenzaMerce.setRequired(true);  
		dataPartenzaMerce.setVisible(true);
		dataPartenzaMerce.setValue( selectedRecord.getAttribute("datapartenzamerce") );
		
		TipoOrdine = new SelectItem();
		TipoOrdine.setWidth(220);
		TipoOrdine.setTitle("Tipo ordine"); 
		TipoOrdine.setType("comboBox");
		TipoOrdine.setValueMap("Ordinario", "Speciale");
		TipoOrdine.setRequired(true);
		TipoOrdine.setDisabled(true);
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
		
		final Vector<Contatto> vettTrasp = DataSourceContatti.getVettoreTrasportatori();
		final Vector<Contatto> vettForn = DataSourceContatti.getVettoreFornitori();
		trasportatori = new String[vettTrasp.size()];
		fornitori = new String[vettForn.size()];
		trasportatoriID = new String[vettTrasp.size()];
		fornitoriID = new String[vettForn.size()];
		
		for(int i=0; i<vettTrasp.size(); i++){
			trasportatori[i] = vettTrasp.get(i).getRagioneSociale();
			trasportatoriID[i] = vettTrasp.get(i).getID();
			if( trasportatoriID[i].equals( selectedRecord.getAttribute("idtrasportatore")) ) Trasportatore.setValue(trasportatori[i]);
		}
		
		for(int i=0; i<vettForn.size(); i++){
			fornitori[i] = vettForn.get(i).getRagioneSociale();
			fornitoriID[i] = vettForn.get(i).getID();
			if( fornitoriID[i].equals( selectedRecord.getAttribute("idfornitore")) ) Fornitore.setValue(fornitori[i]);
		}
		
		Trasportatore.setValueMap( trasportatori );
		Fornitore.setValueMap( fornitori );
		
		note = new TextAreaItem();
		note.setTitle("Note");
		note.setValue( selectedRecord.getAttribute("note") );
		
		
		confermaButton = new ButtonItem();
		confermaButton.setTitle("Conferma");
		convalidaButton = new ButtonItem();
		convalidaButton.setTitle("Convalida");
		
		//dataCreazioneOrdine, dataPartenzaMerce, 
		form.setFields( new FormItem[] {TipoOrdine, Trasportatore, Fornitore, note,confermaButton, convalidaButton} );
		
		final VLayout vLayout = new VLayout();
		vLayout.addMember(form);
		
		
		this.setAutoSize(true);
		this.setTitle("Proprieta' ordine: ");
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.setCanDragReposition(true);  
		this.setCanDragResize(true);
		this.setShowCloseButton(true);
		this.addItem(vLayout);
		this.draw();
		this.centerInPage();
		
		confermaButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String query = "";
				rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Boolean result) {
						String fornitore = (String) Fornitore.getValue();
						String trasportatore = (String) Trasportatore.getValue();
						String idtrasp="";
						String idforn="";
						for(int i=0; i<trasportatori.length;i++){
							if(trasportatori[i].equals(trasportatore)){
								idtrasp = trasportatoriID[i];
								break;
							}
						}
						for(int i=0; i<fornitori.length;i++){
							if(fornitori[i].equals(fornitore)){
								idforn = fornitoriID[i];
								break;
							}
						}
						
						selectedRecord.setAttribute("idtrasportatore", idtrasp);
						selectedRecord.setAttribute("idfornitore", idforn);
						selectedRecord.setAttribute("note", note.getValue());
						
						String query = "UPDATE ordini SET IDTrasportatore = '"+idtrasp+"' , IDFornitore = '"+idforn+"' , Note = '"+note.getValue()+"' WHERE ID = '"+selectedRecord.getAttribute("id")+"';";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Boolean result) {
								finestra.destroy();
							}
							
						});

					}
					
				});
			}
			
		});
		
		convalidaButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!Window.confirm("La seguente procedura convalidera' l'ordine rendendolo non piu' modificabile. Inoltre avviera' la procedura automatica di creazione dei documenti allegati. Continuare la procedura?")) return;
				
				rpc.eseguiCreazioneDocumentiOrdine(selectedRecord.getAttribute("id"), new AsyncCallback<String[][]>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(String[][] result) {
						
						if(result == null){
							finestra.destroy();
							DataSourceOrdini.getIstance().getNewRecords();
							return;
						}
						
						ListGrid lg = new ListGrid();
						lg.setFields(new ListGridField("nome","Titolo"),new ListGridField("url","URL"));
						
						ListGridRecord rec = null;
						for(int i=0; i<result.length; i++){
							String[] str = result[i];
							rec = new ListGridRecord();
							rec.setAttribute("nome", str[1]);
							rec.setAttribute("url", str[0]);
							lg.addData(rec);
						}
						lg.getField("url").setType(ListGridFieldType.LINK);
						finestra.clear();
						finestra.addItem(lg);
						
						
						DataSourceOrdini.getIstance().getNewRecords();
						
						
						String query = "UPDATE ordini SET Convalidato = 1, DataInvioOrdine = CURDATE() WHERE ID = '"+selectedRecord.getAttribute("id")+"';";
						rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

							@Override
							public void onSuccess(Boolean result) {
								
							}
						});
					}
				});
				
				
				
				
				
				
				
			}
		});
		if(selectedRecord.getAttribute("convalidato").equals("1")){
			convalidaButton.setDisabled(true);
			confermaButton.setDisabled(true);
		}
	}
	
}
