package gestionale.client.UI;

import java.util.Vector;


import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Scrollbar;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Ordine;
import gestionale.shared.Prodotto;

public class PanelOrdine extends TabSet{
	
	private Tab tab;
	private Tab tabTabella;
	private Tab tabTabellaComplessiva;
	private Tab tabFiltroClienti;
	private Tab tabFiltroProdotti;
	private TabSet thisTabSet;
	
	private Layout panelTabella;
	private Layout panelTabellaComplessiva;
	private Layout panelFiltroProdotti = new Layout();
	
	private ListGridDettaglioOrdini lgdettaglioordini;
	private FlexTableOrdineOrdinario flextableOrdineOrdinario;
	
	
	private Vector<Contatto> vettoreContattiFiltrato = null;
	private Vector<String[]> vettoreProdottiDaVisualizzare = null;

	
	private String IDOrdine;
	
	private Vector<LabelOrdinazione> vLabel = null;
	private DataSourceDettaglioOrdini dsdo = null;
	
	
	public PanelOrdine(String idOrdine){
		super();
		thisTabSet = this;
		if(idOrdine == null){
			this.destroy();
		}else{
			
		}
		
		IDOrdine = idOrdine;
		dsdo = new DataSourceDettaglioOrdini(idOrdine,null,null,DataSourceDettaglioOrdini.MOD_TabellaComposizione);
		

		this.setHeight100();
		this.setWidth100();
		
		flextableOrdineOrdinario = new FlexTableOrdineOrdinario(IDOrdine);
		
		tabTabella				= new Tab("Composizione Ordine");
		tabTabellaComplessiva	= new Tab("Visualizzazione Ordine");
		tabFiltroClienti		= new Tab("Filtro Clienti");
		tabFiltroProdotti		= new Tab("Filtro Prodotti");
		
		panelTabella = new Layout();
		panelTabella.addMember(flextableOrdineOrdinario);
		
		panelTabellaComplessiva = new Layout();

		panelFiltroProdotti = new Layout();
		panelFiltroProdotti.addMember(new ListGridContatti());
		
		
		tabTabella.setPane(panelTabella);
		tabTabellaComplessiva.setPane(panelTabellaComplessiva);
		tabFiltroClienti.setPane(new PanelFiltroContatti(vettoreContattiFiltrato));
		tabFiltroProdotti.setPane(new PanelFiltroProdotti(vettoreProdottiDaVisualizzare));
		
		
		
		this.addTab(tabTabella);
		this.addTab(tabTabellaComplessiva);
		this.addTab(tabFiltroClienti);
		this.addTab(tabFiltroProdotti);
		
		
		
		this.addToTabPanel("Visualizzazione Ordine: " + idOrdine, true);
		
		this.addTabSelectedHandler(new TabSelectedHandler() {
			
			public void onTabSelected(TabSelectedEvent event) {
				if(event.getTab() == tabTabella){
					panelTabella.destroy();
					panelTabella = new Layout();
					panelTabella.addMember(flextableOrdineOrdinario.creaTabella());
					tabTabella.setPane(panelTabella);
					
				}else if(event.getTab() == tabTabellaComplessiva){
					if(lgdettaglioordini != null)lgdettaglioordini.destroy();
					lgdettaglioordini = new ListGridDettaglioOrdini(IDOrdine);
					panelTabellaComplessiva.addMember(lgdettaglioordini);
				}
				
			}
		});
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
