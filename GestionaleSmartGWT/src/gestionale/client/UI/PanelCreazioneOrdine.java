package gestionale.client.UI;


import java.util.Vector;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;

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
	private DateItem dataInvioOrdine;
	private DateItem dataPartenzaMerce;
	private SelectItem TipoOrdine;
	private SelectItem Trasportatore;
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
		dataCreazioneOrdine.setName("dataCreazioneOrdine");  
		dataCreazioneOrdine.setTitle("Data creazione ordine");  
		dataCreazioneOrdine.setRequired(true);  
		dataCreazioneOrdine.setVisible(true);
		
		dataInvioOrdine = new DateItem();  
		dataInvioOrdine.setName("dataInvioOrdine");  
		dataInvioOrdine.setTitle("Data invio ordine");  
		dataInvioOrdine.setRequired(true);  
		dataInvioOrdine.setVisible(true);
		
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
		
		Trasportatore = new SelectItem();
		Trasportatore.setWidth(220);
		Trasportatore.setTitle("Trasportatore"); 
		Trasportatore.setType("comboBox");
		Trasportatore.setRequired(true);
		
		Vector<Contatto> vettcontatti = DataSourceContatti.getVettoreContatti();
		Vector<Contatto> vtemp = new Vector<Contatto>();
		Contatto contatto;
		for(int i=0; i<vettcontatti.size(); i++){
			contatto = vettcontatti.get(i);
			if(contatto.getTipoSoggetto().equals("Trasportatore")){
				vtemp.add(contatto);
			}
		}
		
		String[] trasportatori = new String[vtemp.size()];
		for(int i=0; i<trasportatori.length; i++){
			trasportatori[i] = vtemp.get(i).getRagioneSociale();
		}
		Trasportatore.setValueMap( trasportatori );
		
		
		note = new TextAreaItem();
		note.setTitle("Note");
		
		
		form.setFields( new FormItem[] {dataCreazioneOrdine, dataInvioOrdine, dataPartenzaMerce, TipoOrdine, Trasportatore, note} );
		this.addMember(form);
		
		confermaButton = new Button("Inserisci");
			confermaButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					Ordine ordine = new Ordine();
					
					ordine.setDataCreazioneOrdine(dataCreazioneOrdine.getDisplayValue());
					ordine.setDataInvioOrdine(dataInvioOrdine.getDisplayValue());
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
		
		/*
		RagioneSocialeItem = new TextItem();
		RagioneSocialeItem.setWidth(220);
		RagioneSocialeItem.setTitle("Ragione Sociale");
		RagioneSocialeItem.setRequired(true);
        
		PrecisazioneItem = new TextItem();
		PrecisazioneItem.setWidth(220);
		PrecisazioneItem.setTitle("Precisazione");
		PrecisazioneItem.setRequired(false);
        
		PIVAItem = new TextItem();
		PIVAItem.setWidth(220);
		PIVAItem.setTitle("P.IVA C.FISC");
		PIVAItem.setRequired(true);
        
		SitoWebItem = new TextItem();
		SitoWebItem.setWidth(220);
		SitoWebItem.setTitle("Sito Web");
		SitoWebItem.setRequired(false);
        
		ProvvigioneItem = new TextItem();
		ProvvigioneItem.setWidth(220);
		ProvvigioneItem.setTitle("Provvigione");
		ProvvigioneItem.setRequired(true);
        
		TipoSoggettoItem = new SelectItem();
		TipoSoggettoItem.setWidth(220);
		TipoSoggettoItem.setTitle("Tipo soggetto"); 
		TipoSoggettoItem.setType("comboBox");
		TipoSoggettoItem.setValueMap("Cliente", "Fornitore", "Intermediario","Trasportatore");
		TipoSoggettoItem.setRequired(true);
		
		form.setFields( new FormItem[] {RagioneSocialeItem, PrecisazioneItem, PIVAItem, SitoWebItem, ProvvigioneItem, TipoSoggettoItem} );
		
		Button confermaButton = new Button("Conferma");
		confermaButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
					contatto.setRagioneSociale( (String) RagioneSocialeItem.getValue() );
					contatto.setPrecisazione( (String) PrecisazioneItem.getValue() );
					contatto.setPIVA( (String) PIVAItem.getValue() );
					contatto.setSitoWeb( (String) SitoWebItem.getValue() );
					contatto.setProvvigione( (String) ProvvigioneItem.getValue() );
					contatto.setTipoSoggetto( (String) TipoSoggettoItem.getValue());
	        	  
					ListGridRecord[] RC;
					
					// Records Indirizzi
					ListGridRecord[] RCI = listGridIndirizzi.getRecords();
					String stringaIndirizzi=new String("");
					for(int i=0;i<RCI.length;i++){
						String temp = new String("*");
						temp = temp + RCI[i].getAttribute("etichetta") + "+";
						temp = temp + RCI[i].getAttribute("via") + "+";
						temp = temp + RCI[i].getAttribute("ncivico") + "+";
						temp = temp + RCI[i].getAttribute("cap") + "+";
						temp = temp + RCI[i].getAttribute("frazione") + "+";
						temp = temp + RCI[i].getAttribute("citta") + "+";
						temp = temp + RCI[i].getAttribute("provincia") + "+";
						temp = temp + RCI[i].getAttribute("regione") + "+";
						temp = temp + RCI[i].getAttribute("nazione") + "+";
						temp = temp + RCI[i].getAttribute("predefinito") + "*";
						
						stringaIndirizzi = stringaIndirizzi + temp;
					}
					
					contatto.setIndirizzo( stringaIndirizzi );
					
					// Records Telefono
					RC = listGridTelefono.getRecords();
					String stringa=new String("");
					for(int i=0;i<RC.length;i++){
						String temp = "*" + RC[i].getAttribute("etichetta") + "?" + RC[i].getAttribute("valore") + "*";
						stringa = stringa + temp;
					}
					contatto.setTelefono( stringa );
					
					// Records Cellulare
					RC = listGridCellulare.getRecords();
					stringa=new String("");
					for(int i=0;i<RC.length;i++){
						String temp = "*" + RC[i].getAttribute("etichetta") + "?" + RC[i].getAttribute("valore") + "*";
						
						stringa = stringa + temp;
					}
					contatto.setCellulare( stringa );
					
					// Records Fax
					RC = listGridFax.getRecords();
					stringa=new String("");
					for(int i=0;i<RC.length;i++){
						String temp = "*" + RC[i].getAttribute("etichetta") + "?" + RC[i].getAttribute("valore") + "*";
						
						stringa = stringa + temp;
					}
					contatto.setFax( stringa );
					
					// Records eMail
					RC = listGridEmail.getRecords();
					stringa=new String("");
					for(int i=0;i<RC.length;i++){
						String temp = "*" + RC[i].getAttribute("etichetta") + "?" + RC[i].getAttribute("valore") + "*";
						
						stringa = stringa + temp;
					}
					contatto.seteMail( stringa );
					
					stringa = richTextEditor.getValue();
					contatto.setNote( stringa );
					
					
					
					
					
					
	        	  if( modifica ){

	        		  Vector<Contatto> v = Liste.getVettoreContatti();
	        		  
	        		  for(int i=0;i<v.size();i++){
	        			  if( v.get(i).getID().equals(contatto.getID()) ){v.setElementAt(contatto, i); break;}
	        		  }
	        		  
	        		  DB db = new DB();
	        		  String query = "UPDATE contatti SET RagioneSociale='"+contatto.getRagioneSociale()+"',Precisazione='"+contatto.getPrecisazione()+"',PIVA='"+contatto.getPIVA()+"',Logo='"+contatto.getLogo()+"',Indirizzo='"+contatto.getIndirizzo()+"',Telefono='"+contatto.getTelefono()+"',Cellulare='"+contatto.getCellulare()+"',Fax='"+contatto.getFax()+"',Email='"+contatto.geteMail()+"',SitoWeb='"+contatto.getSitoWeb()+"',TipoSoggetto='"+contatto.getTipoSoggetto()+"',Provvigione='"+contatto.getProvvigione()+"',Note='"+contatto.getNote()+"' WHERE ID='"+contatto.getID()+"'";
	        		  db.eseguiUpdateToDB(query);
	        		  //dialog.removeFromParent();
	        		  
	        	  }else{
	        		  DataSourceContatti.aggiungiContatto(contatto);
	        	  }
	        	  
	        	  thisPanel.closePanel();
	        	  
	          }
			});
		
		Button annullaButton = new Button("Annulla");
		annullaButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if ( Window.confirm("Sei sicuro di voler annullare l'operazione?")){
								thisPanel.closePanel();
							}
						}});
		
		Button rimuoviContattoButton = new Button("Rimuovi contatto");
		rimuoviContattoButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if ( Window.confirm("Sei sicuro di voler rimuovere questo contatto?")){

								DB db = new DB();
				        		String query = "DELETE FROM contatti WHERE ID='" + contatto.getID()+ "'";
				        		db.eseguiUpdateToDB(query);
				        		
				        		Vector<Contatto> v = Liste.getVettoreContatti();
				        		
				        		for(int i=0; i<v.size(); i++){
				        			if( v.get(i).getID().equals(contatto.getID()) ){
				        				v.remove(i);
				        				break;
				        			}
				        		}
				        		
				        		//TreeContatti.aggiornaTreeContatti();
					        	thisPanel.closePanel();	
							}
						}});
		
		this.addMember(form);
		
		ToolbarItem toolbarItem = new ToolbarItem();
		if(modifica)
			toolbarItem.setButtons(rimuoviContattoButton,annullaButton,confermaButton);
		else
			toolbarItem.setButtons(annullaButton,confermaButton);
		
		this.addMember(new HTMLFlow("<br><br>"));
		
		form.setFields( new FormItem[] {RagioneSocialeItem, PrecisazioneItem, PIVAItem, SitoWebItem, ProvvigioneItem, TipoSoggettoItem, toolbarItem} );
		
		*/
	}
	
	private void closePanel(){
		GUIManager.getTopTabset().removeTab(tab);
	}
	/*
	private void addToTabPanel(String tabName, boolean canclose){

		Tab[] tabArray = GUIManager.getTopTabset().getTabs();
		
        for( int i=0; i<tabArray.length; i++){
        	if( tabArray[i].getTitle().equals( tabName ) ){
        		GUIManager.getTopTabset().selectTab(tabArray[i]);
        		return;
        		}
        }
		
		tab = new Tab(tabName);
        tab.setPane( thisPanel );
        tab.setCanClose(canclose);
        GUIManager.getTopTabset().addTab(tab);
        GUIManager.getTopTabset().selectTab(tab);
		
		return;
		
	}
	 */
	 
}
