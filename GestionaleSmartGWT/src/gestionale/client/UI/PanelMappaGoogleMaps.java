package gestionale.client.UI;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.shared.Contatto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PanelMappaGoogleMaps extends VLayout{

	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private MapWidget map;
	private DockLayoutPanel dock;
	private Geocoder geo;
	
	private PanelFiltroContatti panelFiltroContatti;
	
	public PanelMappaGoogleMaps(){
		
		Maps.loadMapsApi("ABQIAAAACnqVpi16nfPl4-V9TbU06RR6ecWJkAiEahMAWnoQLm2OMrnSyhRGBhHercUQqnX6AxAAKbNvJPgiYA", "2", false, new Runnable() {
		      public void run() {
		        buildUi();
		      }
		});
	}
		
	
	private void buildUi(){
		Geocoder geo = new Geocoder();
		LatLng vignola = LatLng.newInstance(44.478068,11.007976);
		panelFiltroContatti = new PanelFiltroContatti();
		map = new MapWidget(vignola, 2);
		
		map.setSize("100%", "100%");
	    map.addControl(new LargeMapControl());
	    map.setScrollWheelZoomEnabled(true);
	    
	    //map.getInfoWindow().open(map.getCenter(),new InfoWindowContent("Home sweet home!!"));
		
	    dock = new DockLayoutPanel(Unit.PX);
	    dock.addNorth(map, 500);
	    dock.setSize("100%", "100%");
		
		this.addMember(dock);
		Button but = new Button();
		but.setTitle("Aggiorna LatLng Contatti");
		but.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				aggiornaCoordinateContattiGeocoder();
			}
		});
		this.addMember(but);
		
		
		
		Button but2= new Button();
		but2.setTitle("Visualizza Markers");
		but2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				map.clearOverlays();
				Vector<Contatto> v = panelFiltroContatti.getVettoreContattiFiltrato();
				for(int i=0; i<v.size(); i++){
					addMarkerContatto(v.get(i));
				}
			}
		});
		this.addMember(but2);
		panelFiltroContatti = new PanelFiltroContatti();
		panelFiltroContatti.setHeight(150);
		this.addMember(panelFiltroContatti);
	}
	
	private void addMarkerContatto(final Contatto contatto){
		
		String lat = contatto.getLatitudine();
		String lng = contatto.getLongitudine();
		if(lat.length() == 0 || lng.length() == 0) return;
		
		
		InfoWindowContent content;
		
		final Marker marker = new Marker( LatLng.newInstance(Double.parseDouble(lat),Double.parseDouble(lng)));
		marker.addMarkerClickHandler(new MarkerClickHandler() {
		      public void onClick(MarkerClickEvent event) {
		        InfoWindow info = map.getInfoWindow();
		        info.open(marker,new InfoWindowContent( contatto.getHtmlText() ));
		      }
		    });
		
		map.addOverlay(marker);
		
	}
	
	private boolean aggiornaCoordinateContattiGeocoder(){
		geo = new Geocoder();
		
		for(int i=0; i<DataSourceContatti.getVettoreContatti().size(); i++){
			final Contatto contatto = DataSourceContatti.getVettoreContatti().get(i);
			Vector<String[]> vInd = contatto.getVectorIndirizzi();
			if(vInd.size() == 0) continue;
			String[] indirizzo = vInd.get(0);
			geo.getLatLng(indirizzo[1]+" "+indirizzo[3]+" "+indirizzo[5], new LatLngCallback() {
				
				public void onSuccess(LatLng point) {
					if(point == null) return;
					contatto.setLatitudine(Double.toString(point.getLatitude()));
					contatto.setLongitudine(Double.toString(point.getLongitude()));
					String query = "UPDATE contatti SET Latitudine = "+point.getLatitude()+", Longitudine = "+point.getLongitude()+" WHERE ID = '"+contatto.getID()+"'";
					rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
						
						public void onSuccess(Boolean result) {
							System.out.println("Riuscito: "+result);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							
						}
					});
				}
				public void onFailure() {
					
				}
			});
		}
		
		return false;
	}
	
	  

	
	
}
