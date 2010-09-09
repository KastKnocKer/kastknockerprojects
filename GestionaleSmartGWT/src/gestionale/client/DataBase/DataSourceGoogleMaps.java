package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceGoogleMaps extends DataSource{
	

	
	public DataSourceGoogleMaps(){
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id");  
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		
		DataSourceTextField ragionesocialeField = new DataSourceTextField("ragionesociale","ragionesociale");
		DataSourceTextField precisazioneField = new DataSourceTextField("precisazione","precisazione");
		DataSourceTextField pivaField = new DataSourceTextField("piva","piva");
		DataSourceTextField logoField = new DataSourceTextField("logo","logo");
		DataSourceTextField indirizzoField = new DataSourceTextField("indirizzo","indirizzo");
		DataSourceTextField telefonoField = new DataSourceTextField("telefono","telefono");
		DataSourceTextField cellulareField = new DataSourceTextField("cellulare","cellulare");
		DataSourceTextField faxField = new DataSourceTextField("fax","fax");
		DataSourceTextField emailField = new DataSourceTextField("email","email");
		DataSourceTextField sitowebField = new DataSourceTextField("sitoweb","sitoweb");
		DataSourceTextField tiposoggettoField = new DataSourceTextField("tiposoggetto","tiposoggetto");
		DataSourceTextField provvigioneField = new DataSourceTextField("provvigione","provvigione");
		DataSourceTextField noteField = new DataSourceTextField("note","note");
		  
        setFields(idField, ragionesocialeField, precisazioneField, pivaField, logoField, indirizzoField, telefonoField, cellulareField,faxField,emailField,sitowebField,tiposoggettoField,provvigioneField,noteField); 
		
        String url = "http://maps.google.com/maps/api/directions/json?origin=Adelaide,SA&destination=Adelaide,SA&waypoints=optimize:true|Barossa+Valley,SA|Clare,SA|Connawarra,SA|McLaren+Vale,SA&sensor=false";
        setDataURL(url);
		setClientOnly(true);
	}
	
	
	
	
}
