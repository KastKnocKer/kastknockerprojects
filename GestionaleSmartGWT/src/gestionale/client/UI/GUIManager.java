package gestionale.client.UI;


import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceImballaggi;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class GUIManager {
	
	private static VLayout vLayoutMain;
	
	private static TextItem usernameItem;
	private static PasswordItem passwordItem;
	private static TabSet topTabSet;
	
	private static SectionStack sectionStack;
	private static SectionStackSection sezioneContatti;
	private static SectionStackSection sezioneOrdini;
	private static SectionStackSection sezioneRisultatoRicerca;
	
	
	
	private static ListGridContatti listgridcontattiricercati;
	
	public GUIManager(){
		vLayoutMain = new VLayout();
		vLayoutMain.setShowEdges(true);
		topTabSet = new TabSet();
		//topTabSet.setAutoWidth();
		//topTabSet.setAutoHeight();
	}
	
	public static void visualizzaLogin(){
		
		final DynamicForm form = new DynamicForm();
		form.setWidth(250);
		form.setTitleOrientation(TitleOrientation.LEFT);
		
        usernameItem = new TextItem();
        usernameItem.setWidth(220);
        usernameItem.setTitle("Username");
        usernameItem.setRequired(true);
        usernameItem.setDefaultValue("a");
  
        passwordItem = new PasswordItem();
        passwordItem.setWidth(220);
        passwordItem.setTitle("Password");  
        passwordItem.setRequired(true);
        passwordItem.setDefaultValue("a");
        
       
        form.setFields( new FormItem[] {usernameItem, passwordItem} );
        
        Button swapButton = new Button("Login");
        swapButton.setLeft(90);
        swapButton.addClickHandler(new ClickHandler() {  
				            
							public void onClick(ClickEvent event) {
								User user = new User();
								user.setUsername( (String) usernameItem.getValue() );
								user.setPassword( (String) passwordItem.getValue() );
								usernameItem.setValue("a");
								usernameItem.setValue("a");
								
								SessioneUtente.setUsername( (String) usernameItem.getValue() );

								DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
								
								rpc.authenticateUser(user, new AsyncCallback<User>() {

									public void onFailure(Throwable ex) {
										Window.alert(ex.getMessage());
									}
									
									public void onSuccess(User result) {
										if(result == null){
											Window.alert("Parametri di autentificazione errati!!/nRiprova!");
										}else{
											GUIManager.enterAfterLogin();
										}
										
									}
									
								});
								 
							}  
        });  
        
        clearGUI();
        
        Image img = new Image("MIAMilano.jpg");
        
        RootPanel.get("centro0").add(img);
        RootPanel.get("centro1").add(form);
        RootPanel.get("centro2").add(swapButton);
        
		
	}
	
	public static void visualizzaSchermataPrincipale(){
		clearGUI();
		
		new WindowLoadData();
		
		Menubar MB = new Menubar();
		
		///Pannello principale
		vLayoutMain.addMember(MB);
		vLayoutMain.setWidth100();  
		vLayoutMain.setHeight100();
		
		///Pannello secondario
		HLayout hLayout = new HLayout();
		hLayout.setWidth100();  
		hLayout.setHeight100();
		
		///Pannello laterale sinistro
		//vLayoutSx = new VLayout();
		//vLayoutSx.setWidth(150);
		//vLayoutSx.setHeight100();
        //vLayout.setMembersMargin(0);  
        //vLayout.setLayoutMargin(10);
        //vLayout.setWidth100();  
		//vLayoutSx.setHeight100();
		
		sectionStack = new SectionStack();
		
		sectionStack.setCanReorderSections(true); 	//
		sectionStack.setCanResizeSections(true);	//
		
		sectionStack.setOverflow(Overflow.HIDDEN);  
		sectionStack.setShowResizeBar(true);
		
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);  
		sectionStack.setWidth(250); 
		sectionStack.setHeight100();
		
		sezioneContatti = new SectionStackSection("Contatti");  
		sezioneContatti.setExpanded(false);
		sectionStack.addSection(sezioneContatti);
		
		sezioneOrdini = new SectionStackSection("Ordini");  
		sezioneOrdini.setExpanded(false);
		sectionStack.addSection(sezioneOrdini);
		
		sezioneRisultatoRicerca = new SectionStackSection("Risultato Ricerca");  
		sezioneRisultatoRicerca.setExpanded(false);
		sectionStack.addSection(sezioneRisultatoRicerca);
		/////////////
		final ListGridContatti listgridcontatti = new ListGridContatti();
		sezioneContatti.addItem( listgridcontatti );
		
		
		////////////
		////////////
		listgridcontattiricercati = new ListGridContatti();
		listgridcontattiricercati.setData(new ListGridRecord[]{null});
		sezioneRisultatoRicerca.addItem(listgridcontattiricercati);
		
		/////////////
		
		//Tasto Cerca
		DynamicForm fp = new DynamicForm();
		
		final TextItem ti_cerca = new TextItem();
		ti_cerca.setTitle("Cerca");

		ti_cerca.addKeyPressHandler(new KeyPressHandler() {
			
			public void onKeyPress(KeyPressEvent event) {
				System.out.println("PREMUTO: " + event.getKeyName());
				if(event.getKeyName().equals("Enter")){
					System.out.println("Cerca: " + ti_cerca.getValue());
					String cerca = ((String) ti_cerca.getValue());
					if(cerca == null){
						return;
					}else{
						cerca.toLowerCase();
					}
					
					ListGridRecord[] records = listgridcontatti.getRecords();
					ListGridRecord[] recordsSelected = new ListGridRecord[ records.length ];
					int indice = 0;
					for(int i=0; i<records.length; i++){
						if( records[i].getAttribute("ragionesociale").toLowerCase().contains( (cerca)  ) ){
							recordsSelected[indice] = records[i];
							indice++;
						}
					}
					listgridcontattiricercati.setData(recordsSelected);
				

				}
				
			}
		});

		fp.setFields(new FormItem[]{ti_cerca});
		sezioneContatti.addItem(fp);
		//Tasto Cerca
		
		ListGridOrdini lgo = new ListGridOrdini();
		sezioneOrdini.addItem(lgo);
		
		//vLayoutSx.addChild(sectionStack);
        
      ///Pannello laterale destro
        //topTabSet = new TabSet();
        
        
        /*
         final ListGrid countryGrid = new ListGrid();  
                 countryGrid.setWidth(500);  
                 countryGrid.setHeight(224);  
                 countryGrid.setShowAllRecords(true);  
                 countryGrid.setCanEdit(true);  
                 countryGrid.setEditEvent(ListGridEditEvent.CLICK);  
                 countryGrid.setModalEditing(true);  
           
                 ListGridField nameField = new ListGridField("countryName", "Country");  
                 ListGridField capitalField = new ListGridField("capital", "Capital");  
                 ListGridField continentField = new ListGridField("continent", "Continent");  
                 countryGrid.setFields(new ListGridField[] {nameField, capitalField, continentField});  
                 countryGrid.setData(CountryData.getRecords()); 
        
        vLayout.addChild(countryGrid);
        */
        
        
        //hLayout.addMember(vLayoutSx);
		hLayout.addMember(sectionStack);
        hLayout.addMember(topTabSet);
        vLayoutMain.addMember(hLayout);
        vLayoutMain.draw();
        

       
	}
	
	public static void addToTabPanel(String tabName, Canvas pannello, boolean canclose){
		//Controllo che non ci siano altre tabs gi� presenti con lo stesso nome
		
		Tab[] tabArray = topTabSet.getTabs();
		
        for( int i=0; i<tabArray.length; i++){
        	if( tabArray[i].getTitle().equals( tabName ) ){
        		topTabSet.selectTab(tabArray[i]);
        		return;
        		}
        }
		
		Tab tab = new Tab(tabName);
        tab.setPane( pannello );
        tab.setCanClose(canclose);
        topTabSet.addTab(tab);
        topTabSet.selectTab(tab);
		
		return;
	}
	
	public static void enterAfterLogin(){
		visualizzaSchermataPrincipale();
		//GUIManager.addToTabPanel("Contatti", new PanelContatti(null), true );
		return;
	}
	
	public static void clearGUI(){
		RootPanel.get("centro0").clear();
        RootPanel.get("centro1").clear();
        RootPanel.get("centro2").clear();
        vLayoutMain.clear();
		vLayoutMain = new VLayout();
	}

	public static TabSet getTopTabset(){
		return topTabSet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
