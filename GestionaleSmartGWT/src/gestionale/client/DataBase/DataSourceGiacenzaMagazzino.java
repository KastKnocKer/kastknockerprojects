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
		
		setClientOnly(true);
		//newRecords();
	}
	
	
	public void newRecords(){
		String query = "";
		rpc.eseguiQueryContatto(query, new AsyncCallback<Contatto[]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB contatti");
			}

			public void onSuccess(Contatto[] result) {
				
				Vector<Contatto> vettoreContatti = new Vector<Contatto>();
				Vector<Contatto> vettoreFornitori = new Vector<Contatto>();
				Vector<Contatto> vettoreTrasportatori = new Vector<Contatto>();
				
				for(int i=0; i<result.length; i++){
					ListGridRecord record = new ListGridRecord();
					Contatto contatto = result[i];
					
					vettoreContatti.add(contatto);
					if(contatto.getTipoSoggetto().equals("Trasportatore")) vettoreTrasportatori.add(contatto);
					if(contatto.getTipoSoggetto().equals("Fornitore")) vettoreFornitori.add(contatto);
					
					record.setAttribute("id", contatto.getID());
					record.setAttribute("ragionesociale", contatto.getRagioneSociale());
					record.setAttribute("precisazione", contatto.getPrecisazione());
					record.setAttribute("piva", contatto.getPIVA());
					record.setAttribute("logo", contatto.getLogo());
					record.setAttribute("indirizzo", contatto.getIndirizzo());
					record.setAttribute("telefono", contatto.getTelefono());
					record.setAttribute("cellulare", contatto.getCellulare());
					record.setAttribute("fax", contatto.getFax());
					record.setAttribute("email", contatto.geteMail());
					record.setAttribute("sitoweb", contatto.getSitoWeb());
					record.setAttribute("tiposoggetto", contatto.getTipoSoggetto());
					record.setAttribute("provvigione", contatto.getProvvigione());
					record.setAttribute("note", contatto.getNote());
					
					istance.addData(record);
				}
				
				
			}	
		});
		
	}
	

	
	
}
