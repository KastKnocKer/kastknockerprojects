package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DB;
import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Contatto;
import gestionale.shared.Imballaggio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceImballaggi extends DataSource{
	
	private static 	DataSourceImballaggi 	istance = null;
	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static  Vector<Imballaggio>	vettoreImballaggi = null;
	
	public static DataSourceImballaggi getIstance(){
		if (istance == null) {  
            istance = new DataSourceImballaggi();  
        } 
		return istance;
	}
	
	public DataSourceImballaggi(){
		setID(id);
		vettoreImballaggi = new Vector<Imballaggio>();
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
			//rpc.eseguiQueryContatto("SELECT * FROM contatti", new AsyncCallback<Contatto[]>()});
		String query = "SELECT * FROM imballaggio";
		rpc.eseguiQueryImballaggio(query, new AsyncCallback<Imballaggio[]>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Imballaggio[] result) {
				vettoreImballaggi.removeAllElements();
				Imballaggio imballaggio = null;
				for(int i=0; i<result.length; i++){
					imballaggio = result[i];
					vettoreImballaggi.add( imballaggio );
					
					ListGridRecord lgr = new ListGridRecord();
					
					lgr.setAttribute("id", imballaggio.getID());
					lgr.setAttribute("descrizione", imballaggio.getDescrizione());
					lgr.setAttribute("lunghezza", imballaggio.getLunghezza());
					lgr.setAttribute("larghezza", imballaggio.getLarghezza());
					lgr.setAttribute("altezza", imballaggio.getAltezza());
					lgr.setAttribute("tara", imballaggio.getTara());
					lgr.setAttribute("materiale", imballaggio.getMateriale());
					lgr.setAttribute("marchio", imballaggio.getMarchio());
					
					addData(lgr);
				}
				
			}
			
		});
	}
	
	
	
	public static void aggiungiImballaggio(Imballaggio imballaggio){
		//Aggiungo al database
		DB db = new DB();
		//String query = "INSERT INTO contatti (`RagioneSociale`,`Precisazione`,`PIVA`,`Logo`,`Indirizzo`,`Telefono`,`Cellulare`,`Fax`,`Email`,`SitoWeb`,`TipoSoggetto`,`Provvigione`,`Note`) VALUES ('"+contatto.getRagioneSociale()+"','"+contatto.getPrecisazione()+"','"+contatto.getPIVA()+"','"+contatto.getLogo()+"','"+contatto.getIndirizzo()+"','"+contatto.getTelefono()+"','"+contatto.getCellulare()+"','"+contatto.getFax()+"','"+contatto.geteMail()+"','"+contatto.getSitoWeb()+"','"+contatto.getTipoSoggetto()+"','"+contatto.getProvvigione()+"','"+contatto.getNote()+"')";
		//db.eseguiUpdateToDB(query);
		//Aggiungo al Vettore

		
		//Aggiungo ai listgridrecords
		ListGridRecord record = new ListGridRecord();
		/*
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
		*/
		istance.addData(record);
	}
	
	public static void rimuoviImballaggio(ListGridRecord record){/*
		Contatto contatto = null;
		for(int i=0; i<vettoreContatti.size(); i++){
			contatto = vettoreContatti.get(i);
			if(contatto.getRagioneSociale().equals(record.getAttribute("ragionesociale"))){
				DB db = new DB();
				String query = "DELETE FROM contatti WHERE ID='" + contatto.getID()+ "'";
        		db.eseguiUpdateToDB(query);
        		vettoreContatti.remove(i);
				istance.removeData(record);
				break;
			}
		}
		*/
	}
	
	
	


	
}
