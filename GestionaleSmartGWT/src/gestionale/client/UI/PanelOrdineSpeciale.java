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

public class PanelOrdineSpeciale extends TabSet{
	
	private String IDOrdine;
	private PanelOrdineSpeciale thisTabSet;
	private Tab tab;
	
	
	public PanelOrdineSpeciale(ListGridRecord ordine){
		super();
		IDOrdine = ordine.getAttribute("id");
		thisTabSet = this;
		if(IDOrdine == null){
			this.destroy();
		}
		
		this.addToTabPanel("Visualizzazione Ordine: " + ordine.getAttribute("datacreazioneordine")+" - ["+ordine.getAttribute("tipoordine")+"]", true);
		
		
		Maps.loadMapsApi("ABQIAAAACnqVpi16nfPl4-V9TbU06RR6ecWJkAiEahMAWnoQLm2OMrnSyhRGBhHercUQqnX6AxAAKbNvJPgiYA", "2", false, new Runnable() {
		      public void run() {
		        buildUi();
		      }
		    });
		  }
		
	

	private void buildUi() {
		VLayout vLayout = new VLayout();
	    // Open a map centered on Cawker City, KS USA
		Geocoder geo = new Geocoder();
		LatLng cawkerCity = LatLng.newInstance(39.509, -98.434);
		final MapWidget map = new MapWidget(cawkerCity, 2);
		
		Waypoint waypoints[] = {
					new Waypoint( LatLng.newInstance(40.509, -99.434) ),
					new Waypoint( LatLng.newInstance(39.509, -98.434) ),
					new Waypoint( LatLng.newInstance(41.509, -100.434) )
			  };
		
		
		DirectionsPanel directionsPanel = new DirectionsPanel();
	    directionsPanel.setSize("100%", "100%");
	   

	    DirectionQueryOptions opts = new DirectionQueryOptions(map,directionsPanel);
	    
	    String query = "from: 500 Memorial Dr, Cambridge, MA to: 4 Yawkey Way, Boston, MA by: vignola modena";
	   
	    Directions.load(query, opts, new DirectionsCallback() {

	        public void onFailure(int statusCode) {
	          Window.alert("Failed to load directions: Status "
	              + StatusCodes.getName(statusCode) + " " + statusCode);
	        }

	        public void onSuccess(DirectionResults result) {
	          GWT.log("Successfully loaded directions.", null);

	          // A little exercise of the route API
	          List<Route> routes = result.getRoutes();
	          for (Route r : routes) {
	            Placemark start = r.getStartGeocode();
	            GWT.log("start of route: " + start.getAddress(), null);
	            Placemark end = r.getEndGeocode();
	            GWT.log("end of route: " + end.getAddress(), null);
	          }
	        }

			
	      });


		
		
		/*
		geo.getLatLng("Vignola 41058", new LatLngCallback() {
			
			public void onSuccess(LatLng point) {
				if(point == null) return;
				LatLng vignola = point;
				map.addOverlay(new Marker(point));
			}
			public void onFailure() {
				// TODO Auto-generated method stub
				
			}
		});
		*/
		
	    
		
	    
	    
	    map.setSize("100%", "100%");
	    // Add some controls for the zoom level
	    map.addControl(new LargeMapControl());
	
	    // Add a marker
	    map.addOverlay(new Marker(cawkerCity));
	
	    // Add an info window to highlight a point of interest
	    map.getInfoWindow().open(map.getCenter(),
	        new InfoWindowContent("World's Largest Ball of Sisal Twine"));
	
	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
	    dock.addNorth(map, 500);
	    dock.setSize("100%", "100%");
	
	    // Add the map to the HTML host page
	    Tab tabGM = new Tab();

	    vLayout.addMember(dock);
	    vLayout.addMember(directionsPanel);
	    
	    ListGrid lg = new ListGrid();
	    lg.setSize("500px", "500px");
	    lg.setDataSource( new DataSourceGoogleMaps() );
	    vLayout.addMember(lg);
	    
	    vLayout.setWidth100();
	    vLayout.setHeight100();
	    
	    tabGM.setPane(vLayout);
	    thisTabSet.addTab(tabGM);
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
