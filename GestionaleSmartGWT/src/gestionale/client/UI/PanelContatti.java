package gestionale.client.UI;

import java.util.Vector;

import gestionale.client.DB;
import gestionale.client.Liste;
import gestionale.client.DataBase.DataSourceContattiCampiVari;
import gestionale.client.DataBase.DataSourceContattiIndirizzi;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.ToolbarItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;



public class PanelContatti extends VLayout{
	
	private PanelContatti thisPanel;
	private TextItem RagioneSocialeItem;
	private TextItem PrecisazioneItem;
	private TextItem PIVAItem;
	private TextItem SitoWebItem;
	private SelectItem TipoSoggettoItem;
	private TextItem ProvvigioneItem;
	
	private RichTextEditor richTextEditor;
	
	private boolean modifica;
	private Contatto contatto;
	
	private ListGrid listGridIndirizzi;
	private ListGrid listGridTelefono;
	private ListGrid listGridCellulare;
	private ListGrid listGridFax;
	private ListGrid listGridEmail;
	
	private Tab tab;
	
	
	
	public PanelContatti(Contatto contattoIn){
		
		thisPanel=this;
		
		
		if (contattoIn == null){
			modifica = false;
			contatto = new Contatto();
			addToTabPanel("Crea contatto", true);
		} else{ modifica = true;
			contatto = contattoIn;
			addToTabPanel("Modifica: "+contatto.getRagioneSociale(), true);
		}
		
		
		this.addForm();			//Aggiungo il form
		
		///////////Creazione SectionStack
		SectionStack sectionStack = new SectionStack();
		
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setAnimateSections(true);
		sectionStack.setOverflow(Overflow.HIDDEN);
		sectionStack.setWidth100();
		sectionStack.setHeight100();
		
		this.addMember(sectionStack);
		
		
		///////////CreazioneBottoni
		
		//INDIRIZZI
ImgButton addIndirizzo = new ImgButton();  
		         addIndirizzo.setSrc("[SKIN]actions/add.png");  
		         addIndirizzo.setSize(16);  
		         addIndirizzo.setShowFocused(false);  
		         addIndirizzo.setShowRollOver(false);  
		         addIndirizzo.setShowDown(false);  
		         addIndirizzo.addClickHandler(new ClickHandler() {  
		             public void onClick(ClickEvent event) {  
		                 listGridIndirizzi.startEditingNew();  
		             }  
		         });  
		   
ImgButton removeIndirizzo = new ImgButton();  
		         removeIndirizzo.setSrc("[SKIN]actions/remove.png");  
		         removeIndirizzo.setSize(16);  
		         removeIndirizzo.setShowFocused(false);  
		         removeIndirizzo.setShowRollOver(false);  
		         removeIndirizzo.setShowDown(false);  
		         removeIndirizzo.addClickHandler(new ClickHandler() {  
		             public void onClick(ClickEvent event) {  
		            	 if (listGridIndirizzi.getSelectedRecord() == null) return;
		            	 if (Window.confirm("Sicuro di voler cancellare: "+listGridIndirizzi.getSelectedRecord().getAttribute("etichetta"))){
		            		 listGridIndirizzi.removeSelectedData();
		            	 }
		             }  
		         }); 
		
		 		//Telefono
ImgButton addTelefono = new ImgButton();  
			 		         addTelefono.setSrc("[SKIN]actions/add.png");  
			 		         addTelefono.setSize(16);  
			 		         addTelefono.setShowFocused(false);  
			 		         addTelefono.setShowRollOver(false);  
			 		         addTelefono.setShowDown(false);  
			 		         addTelefono.addClickHandler(new ClickHandler() {  
			 		             public void onClick(ClickEvent event) {  
			 		            	listGridTelefono.startEditingNew();  
			 		             }  
			 		         });  
			 		   
ImgButton removeTelefono = new ImgButton();  
			 		         removeTelefono.setSrc("[SKIN]actions/remove.png");  
			 		         removeTelefono.setSize(16);  
			 		         removeTelefono.setShowFocused(false);  
			 		         removeTelefono.setShowRollOver(false);  
			 		         removeTelefono.setShowDown(false);  
			 		         removeTelefono.addClickHandler(new ClickHandler() {  
			 		             public void onClick(ClickEvent event) {  
			 		            	 if (listGridTelefono.getSelectedRecord() == null) return;
			 		            	 if (Window.confirm("Sicuro di voler cancellare: "+listGridTelefono.getSelectedRecord().getAttribute("etichetta"))){
			 		            		 listGridTelefono.removeSelectedData();
			 		            	 }
			 		             }  
			 		         });
			 		         ///// CELLULARE
			 		        ImgButton addCellulare = new ImgButton();  
			 		        				 		         addCellulare.setSrc("[SKIN]actions/add.png");  
			 		        				 		         addCellulare.setSize(16);  
			 		        				 		         addCellulare.setShowFocused(false);  
			 		        				 		         addCellulare.setShowRollOver(false);  
			 		        				 		         addCellulare.setShowDown(false);  
			 		        				 		         addCellulare.addClickHandler(new ClickHandler() {  
			 		        				 		             public void onClick(ClickEvent event) {  
			 		        				 		            	listGridCellulare.startEditingNew();  
			 		        				 		             }  
			 		        				 		         });  
			 		        				 		   
			 		        ImgButton removeCellulare = new ImgButton();  
			 		        		removeCellulare.setSrc("[SKIN]actions/remove.png");  
			 		        		removeCellulare.setSize(16);  
			 		        		removeCellulare.setShowFocused(false);  
			 		        		removeCellulare.setShowRollOver(false);  
			 		        		removeCellulare.setShowDown(false);  
			 		        		removeCellulare.addClickHandler(new ClickHandler() {  
			 		        			public void onClick(ClickEvent event) {  
			 		        				if (listGridCellulare.getSelectedRecord() == null) return;
			 		        				if (Window.confirm("Sicuro di voler cancellare: "+listGridCellulare.getSelectedRecord().getAttribute("etichetta"))){
			 		        				 	listGridCellulare.removeSelectedData();
			 		        				 	}
			 		        				}  
			 		        		});

 ///// FAX
ImgButton addFax = new ImgButton();  
				 		         addFax.setSrc("[SKIN]actions/add.png");  
				 		         addFax.setSize(16);  
				 		         addFax.setShowFocused(false);  
				 		         addFax.setShowRollOver(false);  
				 		         addFax.setShowDown(false);  
				 		         addFax.addClickHandler(new ClickHandler() {  
				 		             public void onClick(ClickEvent event) {  
				 		            	listGridFax.startEditingNew();  
				 		             }  
				 		         });  
				 		   
ImgButton removeFax = new ImgButton();  
		removeFax.setSrc("[SKIN]actions/remove.png");  
		removeFax.setSize(16);  
		removeFax.setShowFocused(false);  
		removeFax.setShowRollOver(false);  
		removeFax.setShowDown(false);  
		removeFax.addClickHandler(new ClickHandler() {  
			public void onClick(ClickEvent event) {  
				if (listGridFax.getSelectedRecord() == null) return;
				if (Window.confirm("Sicuro di voler cancellare: "+listGridFax.getSelectedRecord().getAttribute("etichetta"))){
				 	listGridFax.removeSelectedData();
				 	}
				}  
		});


 /////MAIL
ImgButton addEmail = new ImgButton();  
				 		         addEmail.setSrc("[SKIN]actions/add.png");  
				 		         addEmail.setSize(16);  
				 		         addEmail.setShowFocused(false);  
				 		         addEmail.setShowRollOver(false);  
				 		         addEmail.setShowDown(false);  
				 		         addEmail.addClickHandler(new ClickHandler() {  
				 		             public void onClick(ClickEvent event) {  
				 		            	listGridEmail.startEditingNew();  
				 		             }  
				 		         });  
				 		   
ImgButton removeEmail = new ImgButton();  
		removeEmail.setSrc("[SKIN]actions/remove.png");  
		removeEmail.setSize(16);  
		removeEmail.setShowFocused(false);  
		removeEmail.setShowRollOver(false);  
		removeEmail.setShowDown(false);  
		removeEmail.addClickHandler(new ClickHandler() {  
			public void onClick(ClickEvent event) {  
				if (listGridEmail.getSelectedRecord() == null) return;
				if (Window.confirm("Sicuro di voler cancellare: "+listGridEmail.getSelectedRecord().getAttribute("etichetta"))){
				 	listGridEmail.removeSelectedData();
				 	}
				}  
		});
			
			
		///////////FineCreazioneBottoni
		
		SectionStackSection sezioneIndirizzo = new SectionStackSection("Indirizzi");
		sezioneIndirizzo.setControls(addIndirizzo,removeIndirizzo);
		sectionStack.addSection(sezioneIndirizzo);  
		
		SectionStackSection sezioneTelefono = new SectionStackSection("Telefono");
		sezioneTelefono.setControls(addTelefono,removeTelefono);
		sectionStack.addSection(sezioneTelefono);  
		
		SectionStackSection sezioneCellulare = new SectionStackSection("Cellulare");
		sezioneCellulare.setControls(addCellulare,removeCellulare);
		sectionStack.addSection(sezioneCellulare);  
		
		SectionStackSection sezioneFax = new SectionStackSection("Fax");
		sezioneFax.setControls(addFax,removeFax);
		sectionStack.addSection(sezioneFax);  
		
		SectionStackSection sezioneEmail = new SectionStackSection("e-Mail");
		sezioneEmail.setControls(addEmail,removeEmail);
		sectionStack.addSection(sezioneEmail);  
		
		SectionStackSection sezioneNote = new SectionStackSection("Note");
		sectionStack.addSection(sezioneNote);  
		
		
		
		////////// INDIRIZZI /////////////

		 listGridIndirizzi = new ListGrid();
		 listGridIndirizzi.setCanEdit(true);  
		 listGridIndirizzi.setEditEvent(ListGridEditEvent.CLICK);  
		 listGridIndirizzi.setFields(
				 new ListGridField("etichetta", "Etichetta"),  
				 new ListGridField("via", "Via"),
				 new ListGridField("ncivico", "N.Civico"),
				 new ListGridField("cap", "CAP"),
				 new ListGridField("frazione", "Frazione"),
				 new ListGridField("citta", "Citta"),
				 new ListGridField("provincia", "Provincia"),
				 new ListGridField("regione", "Regione"),
				 new ListGridField("nazione", "Nazione")/*,
				 new ListGridField("predefinito", "Predef")*/
				 
		 );  

		 listGridIndirizzi.setData( DataSourceContattiIndirizzi.getNewRecords(contatto) );
		 
		 sezioneIndirizzo.addItem(listGridIndirizzi);

		 
////////// TELEFONO /////////////

		 listGridTelefono = new ListGrid();
		 listGridTelefono.setCanEdit(true);  
		 listGridTelefono.setEditEvent(ListGridEditEvent.CLICK);  
		 listGridTelefono.setFields(
				 new ListGridField("etichetta", "Etichetta"),  
				 new ListGridField("valore", "Telefono")
		 );  

		 listGridTelefono.setData( DataSourceContattiCampiVari.getNewRecords(contatto,"Telefono") );
		 
		 sezioneTelefono.addItem(listGridTelefono);
		 
		 
////////// CELLULARE /////////////

		 listGridCellulare = new ListGrid();
		 listGridCellulare.setCanEdit(true);  
		 listGridCellulare.setEditEvent(ListGridEditEvent.CLICK);  
		 listGridCellulare.setFields(
				 new ListGridField("etichetta", "Etichetta"),  
				 new ListGridField("valore", "Cellulare")
		 );  

		 listGridCellulare.setData( DataSourceContattiCampiVari.getNewRecords(contatto,"Cellulare") );
		 sezioneCellulare.addItem(listGridCellulare);

		 
////////// FAX /////////////

		 listGridFax = new ListGrid();
		 listGridFax.setCanEdit(true);  
		 listGridFax.setEditEvent(ListGridEditEvent.CLICK);  
		 listGridFax.setFields(
				 new ListGridField("etichetta", "Etichetta"),  
				 new ListGridField("valore", "Fax")
		 );  

		 listGridFax.setData( DataSourceContattiCampiVari.getNewRecords(contatto,"Fax") );
		 sezioneFax.addItem(listGridFax);
		 
////////// eMAIL /////////////

		 listGridEmail = new ListGrid();
		 listGridEmail.setCanEdit(true);  
		 listGridEmail.setEditEvent(ListGridEditEvent.CLICK);  
		 listGridEmail.setFields(
				 new ListGridField("etichetta", "Etichetta"),  
				 new ListGridField("valore", "eMail")
		 );  

		 listGridEmail.setData( DataSourceContattiCampiVari.getNewRecords(contatto,"Email") );
		 sezioneEmail.addItem(listGridEmail);

		 
////////// NOTE /////////////
		richTextEditor = new RichTextEditor();  
		richTextEditor.setHeight100();
		richTextEditor.setOverflow(Overflow.HIDDEN);  
		richTextEditor.setCanDragResize(true);  
		richTextEditor.setShowEdges(true); 
        
		sezioneNote.addItem(richTextEditor);
		
		
		
		if(modifica){
			RagioneSocialeItem.setValue(contatto.getRagioneSociale()); 
			PrecisazioneItem.setValue(contatto.getPrecisazione()); 
			PIVAItem.setValue(contatto.getPIVA()); 
			SitoWebItem.setValue(contatto.getSitoWeb()); 
			TipoSoggettoItem.setValue(contatto.getTipoSoggetto()); 
			TipoSoggettoItem.disable();
			ProvvigioneItem.setValue(contatto.getProvvigione()); 
			
			richTextEditor.setValue(contatto.getNote()); 
			
			
		}
	}
	
	
	private void addForm(){
		
		DynamicForm form = new DynamicForm();
		form.setWidth(250);
		form.setTitleOrientation(TitleOrientation.LEFT);
		
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
	        		  Liste.getVettoreContatti().add(contatto);
	        		  DB db = new DB();
	        		  String query = "INSERT INTO contatti (`RagioneSociale`,`Precisazione`,`PIVA`,`Logo`,`Indirizzo`,`Telefono`,`Cellulare`,`Fax`,`Email`,`SitoWeb`,`TipoSoggetto`,`Provvigione`,`Note`) VALUES ('"+contatto.getRagioneSociale()+"','"+contatto.getPrecisazione()+"','"+contatto.getPIVA()+"','"+contatto.getLogo()+"','"+contatto.getIndirizzo()+"','"+contatto.getTelefono()+"','"+contatto.getCellulare()+"','"+contatto.getFax()+"','"+contatto.geteMail()+"','"+contatto.getSitoWeb()+"','"+contatto.getTipoSoggetto()+"','"+contatto.getProvvigione()+"','"+contatto.getNote()+"')";
	        		  db.eseguiUpdateToDB(query);
	        		  TreeContatti.aggiornaTreeContatti();
	        		  
	        		  //dialog.removeFromParent();
	        		  //Aggiornare albero
	        	  }
	        	  
	        	  TreeContatti.aggiornaTreeContatti();
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
				        		
				        		TreeContatti.aggiornaTreeContatti();
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
        tab.setPane( thisPanel );
        tab.setCanClose(canclose);
        GUIManager.getTopTabset().addTab(tab);
        GUIManager.getTopTabset().selectTab(tab);
		
		return;
		
	}
	 
	 
}
