package gestionale.client.UI;

import java.util.Vector;

import gestionale.client.DB;
import gestionale.client.Liste;
import gestionale.shared.Contatto;
import gestionale.shared.User;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;

public class GUIManager {
	
	private static VLayout vLayoutMain;
	private static VLayout vLayoutSx;
	private static TextItem usernameItem;
	private static PasswordItem passwordItem;
	private static TabSet topTabSet;
	
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
								DB db = new DB();
								db.eseguiAutentificazione(user);

								 
							}  
        });  
        
        clearGUI();
        //vLayoutMain.addMember(form, 300);
        //vLayoutMain.addMember(swapButton);  
        //vLayoutMain.draw(); 
        
        Image img = new Image("MIAMilano.jpg");
        
        RootPanel.get("centro0").add(img);
        RootPanel.get("centro1").add(form);
        RootPanel.get("centro2").add(swapButton);
		
	}
	
	public static void visualizzaSchermataPrincipale(){
		clearGUI();
		
		Menubar MB = new Menubar();
		
		///Pannello principale
		vLayoutMain.addMember(MB);
		vLayoutMain.setWidth100();  
		vLayoutMain.setHeight100();
		
		///Pannello secondario
		HLayout hLayout = new HLayout();
		//hLayout.setShowEdges(true);
		hLayout.setWidth100();  
		hLayout.setHeight100();
		
		///Pannello laterale sinistro
		vLayoutSx = new VLayout();
		vLayoutSx.setWidth(150);
		vLayoutSx.setHeight100();
        //vLayout.setMembersMargin(0);  
        //vLayout.setLayoutMargin(10);
        //vLayout.setWidth100();  
		vLayoutSx.setHeight100();
		
		final SectionStack sectionStack = new SectionStack();
		
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);  
		sectionStack.setWidth(250);  
		sectionStack.setHeight100();
		
		SectionStackSection sezioneContatti = new SectionStackSection("Contatti");  
		sezioneContatti.setExpanded(true);
		sectionStack.addSection(sezioneContatti);  
		
		TreeContatti tc = new TreeContatti();
		sezioneContatti.addItem( tc.getTreeGrid() );
		
		
		vLayoutSx.addChild(sectionStack);
        
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
        
        
        hLayout.addMember(vLayoutSx);
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
	}

	public static TabSet getTopTabset(){
		return topTabSet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}