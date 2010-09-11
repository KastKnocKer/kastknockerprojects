package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceGoogleMaps;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.DirectionsPanel;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Route;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class PanelOrdineSpeciale extends TabSet{
	
	private String IDOrdine;
	private PanelOrdineSpeciale thisTabSet;
	private Tab tab;
	private Tab tabTabella;
	private Tab tabFiltroProdotti;
	
	private Layout panelTabella;
	
	private FlexTableOrdineSpeciale flextable;
	
	private PanelFiltroProdotti panelFiltroProdotti;
	
	
	public PanelOrdineSpeciale(ListGridRecord ordine){
		super();
		IDOrdine = ordine.getAttribute("id");
		thisTabSet = this;
		if(IDOrdine == null){
			this.destroy();
		}
		this.addToTabPanel("Visualizzazione Ordine: " + ordine.getAttribute("datacreazioneordine")+" - ["+ordine.getAttribute("tipoordine")+"]", true);
		
		tabFiltroProdotti = new Tab("Filtro Prodotti");
		panelFiltroProdotti = new PanelFiltroProdotti();
		tabFiltroProdotti.setPane(panelFiltroProdotti);
		
		flextable = new FlexTableOrdineSpeciale(IDOrdine);
		flextable.setPanelFiltroProdotti(panelFiltroProdotti);
		
		tabTabella  = new Tab("Composizione Ordine");
		panelTabella = new Layout();
		panelTabella.addMember(flextable);
		tabTabella.setPane(panelTabella);
		
		this.addTab(tabTabella);
		this.addTab(tabFiltroProdotti);
		
		
		this.addTabSelectedHandler(new TabSelectedHandler() {
			
			public void onTabSelected(TabSelectedEvent event) {
				if(event.getTab() == tabTabella){
					panelTabella.destroy();
					panelTabella = new Layout();
					panelTabella.addMember(flextable.creaTabella());
					tabTabella.setPane(panelTabella);
					
				}/*else if(event.getTab() == tabTabellaComplessiva){
					if(lgdettaglioordini != null)lgdettaglioordini.destroy();
					lgdettaglioordini = new ListGridDettaglioOrdini(IDOrdine);
					panelTabellaComplessiva.addMember(lgdettaglioordini);
				}*/
				
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
