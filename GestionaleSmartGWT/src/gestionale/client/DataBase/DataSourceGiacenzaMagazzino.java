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

public class DataSourceGiacenzaMagazzino extends DataSource{
	
	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static DataSourceGiacenzaMagazzino istance;
	
	public static DataSourceGiacenzaMagazzino getIstance(){
		if (istance == null) {  
            istance = new DataSourceGiacenzaMagazzino();  
        } 
		return istance;
	}
	
	public DataSourceGiacenzaMagazzino(){
		setID(id); 
		DataSourceTextField idField = new DataSourceTextField("id");  
		idField.setHidden(true);  
		idField.setPrimaryKey(true);
		
		DataSourceTextField idprodottoField = new DataSourceTextField("idprodotto","ragionesociale");
		DataSourceTextField idordineField = new DataSourceTextField("idordine","precisazione");
		DataSourceTextField dataarrivomerceField = new DataSourceTextField("dataarrivomerce","piva");
		DataSourceTextField datascadenzamerceField = new DataSourceTextField("datascadenzamerce","logo");
		DataSourceTextField numpedaneField = new DataSourceTextField("numpedane","indirizzo");
		DataSourceTextField quantitaField = new DataSourceTextField("quantita","telefono");
		DataSourceTextField validitamerceField = new DataSourceTextField("validitamerce","cellulare");
		
        setFields(idField, idprodottoField, idordineField, dataarrivomerceField, datascadenzamerceField, numpedaneField, quantitaField, validitamerceField); 
		
		setClientOnly(true);
		newRecords();
	}
	
	
	public void newRecords(){
		String query = "SELECT * FROM magazzino WHERE CURDATE() >= DataArrivoMerce AND CURDATE() <= DataScadenzaMerce";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB contatti");
			}

			@Override
			public void onSuccess(String[][] result) {
				for(int i=0; i<result.length; i++){
					String[] s_record = result[i];
					ListGridRecord record = new ListGridRecord();
					
					record.setAttribute("id", s_record[0]);
					record.setAttribute("idprodotto", s_record[1]);
					record.setAttribute("idordine", s_record[2]);
					record.setAttribute("idimballaggio", s_record[3]);
					record.setAttribute("dataarrivomerce", s_record[4]);
					record.setAttribute("datascadenzamerce", s_record[5]);
					record.setAttribute("numpedane", s_record[6]);
					record.setAttribute("quantita", s_record[7]);
					record.setAttribute("validitamerce", s_record[8]);
					
					istance.addData(record);
				}
			}	
		});
		
	}
	

	
	
}
