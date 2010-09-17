package gestionale.client.UI;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import gestionale.client.DataBase.DataSourceDettaglioOrdini;

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

	private String IDOrdine;
	private DataSourceDettaglioOrdini dsdo; 
	private PanelFiltroProdotti panelfiltroprodotti;
	private PanelFiltroContatti panelfiltrocontatti;
	
	
	
	public PanelOrdine(ListGridRecord ordine){
		super();
		IDOrdine = ordine.getAttribute("id");
		thisTabSet = this;
		if(IDOrdine == null){
			this.destroy();
		}
		
		dsdo = new DataSourceDettaglioOrdini(IDOrdine,null,null,DataSourceDettaglioOrdini.MOD_TabellaComposizione);
		

		this.setHeight100();
		this.setWidth100();
		panelfiltrocontatti = new PanelFiltroContatti();
		panelfiltroprodotti = new PanelFiltroProdotti();
		
		flextableOrdineOrdinario = new FlexTableOrdineOrdinario(IDOrdine);
		
		flextableOrdineOrdinario.setPanelFiltroContatti(panelfiltrocontatti);
		flextableOrdineOrdinario.setPanelFiltroProdotti(panelfiltroprodotti);
		
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
		tabFiltroClienti.setPane(panelfiltrocontatti);
		tabFiltroProdotti.setPane(panelfiltroprodotti);
		
		if( ordine.getAttribute("convalidato").equals("0") ){
		this.addTab(tabFiltroClienti);
		this.addTab(tabTabella);
		this.addTab(tabFiltroProdotti);
		}
		this.addTab(tabTabellaComplessiva);
		
		
		this.addToTabPanel("Visualizzazione Ordine: " + ordine.getAttribute("datacreazioneordine")+" - ["+ordine.getAttribute("tipoordine")+"]", true);
		
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
