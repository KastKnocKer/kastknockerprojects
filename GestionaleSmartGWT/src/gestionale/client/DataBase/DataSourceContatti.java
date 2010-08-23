package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.Liste;
import gestionale.shared.Contatto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceContatti extends DataSource{
	
	private static DataSourceContatti istance = null;
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public static DataSourceContatti getIstance(){
		if (istance == null) {  
            istance = new DataSourceContatti();  
        } 
		return istance;
	}
	
	public DataSourceContatti(){
		/*
		super();
		setID(id);  
        setTitleField("Name");
		DataSourceTextField nameField = new DataSourceTextField("Name", "Name");  
		nameField.setRequired(true);
		 
		DataSourceTextField IDField = new DataSourceTextField("ID", "ID");  
		IDField.setRequired(true);
		IDField.setPrimaryKey(true);
		IDField.setHidden(true);
		 
		DataSourceTextField PIDField = new DataSourceTextField("PID", "PID");
		PIDField.setRequired(true);
		//PIDField.setForeignKey(id + ".ID");
		PIDField.setHidden(true);
		//PIDField.setRootValue("root");
		nameField.setCanEdit(true);
		
		//PIDField.setGroup("PID");
		
		*/
		
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
		newRecords();
		
	}
	
	
	public void newRecords(){
	/*
		rpc.eseguiQuery("SELECT * FROM tiposoggetto", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}

			public void onSuccess(String[][] result) {
				for(int i=0 ; i<result.length; i++){
					Record record = new Record();
					System.out.println("AMAMELO" + result[i][0]);
					record.setAttribute("ID", result[i][0]);
					record.setAttribute("Name", result[i][0]);
					record.setAttribute("PID", "root");
					istance.addData(record);
				}
			}	
		}); */
		
		rpc.eseguiQueryContatto("SELECT * FROM contatti", new AsyncCallback<Contatto[]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB contatti");
			}

			public void onSuccess(Contatto[] result) {

				for(int i=0; i<result.length; i++){
					ListGridRecord record = new ListGridRecord();
					Contatto contatto = result[i];
					
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
	
	
	
	
private static RecordContatti[] records;
	
	public static RecordContatti[] getRecords() {  
		if (records == null) {  
		records = getNewRecords();  
		}
		return records;  
	}
	

	 public static RecordContatti[] getNewRecords() {
		 
		 Vector<Contatto> v = Liste.getVettoreContatti();
		 RecordContatti[] records = new RecordContatti[v.size()];
		 
		 for(int i=0; i<v.size(); i++){
			 records[i] = new RecordContatti( v.get(i) );
		 }
		  
		         
		  return records;
	}

	
}
