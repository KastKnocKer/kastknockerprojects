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

public class DataSourceContatti extends DataSource{
	
	private static 	DataSourceContatti 	istance = null;
	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private static  Vector<Contatto>	vettoreContatti = null;
	private static  Vector<Contatto>	vettoreFornitori = null;
	private static  Vector<Contatto>	vettoreTrasportatori = null;
	
	private boolean ready = false;
	
	public static DataSourceContatti getIstance(){
		if (istance == null) {  
            istance = new DataSourceContatti();  
        } 
		return istance;
	}
	
	public DataSourceContatti(){
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
			ready = false;
			rpc.eseguiQueryContatto("SELECT * FROM contatti", new AsyncCallback<Contatto[]>(){
			
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
				
				DataSourceContatti.vettoreContatti = vettoreContatti;
				DataSourceContatti.vettoreFornitori = vettoreFornitori;
				DataSourceContatti.vettoreTrasportatori = vettoreTrasportatori;
				ready = true;
			}	
		});
		
	}
	
	public static Vector<Contatto> getVettoreContatti(){
		return vettoreContatti;
	}
	
	public static Vector<Contatto> getVettoreFornitori(){
		return vettoreFornitori;
	}
	
	public static Vector<Contatto> getVettoreTrasportatori(){
		return vettoreTrasportatori;
	}
	
	public static void aggiungiContatto(final Contatto contatto){
		//Aggiungo al database
		String query = "INSERT INTO contatti (`RagioneSociale`,`Precisazione`,`PIVA`,`Logo`,`Indirizzo`,`Telefono`,`Cellulare`,`Fax`,`Email`,`SitoWeb`,`TipoSoggetto`,`Provvigione`,`Note`,`IDMercato`) VALUES ('"+contatto.getRagioneSociale()+"','"+contatto.getPrecisazione()+"','"+contatto.getPIVA()+"','"+contatto.getLogo()+"','"+contatto.getIndirizzo()+"','"+contatto.getTelefono()+"','"+contatto.getCellulare()+"','"+contatto.getFax()+"','"+contatto.geteMail()+"','"+contatto.getSitoWeb()+"','"+contatto.getTipoSoggetto()+"','"+contatto.getProvvigione()+"','"+contatto.getNote()+"','"+contatto.getIDMercato()+"')";
		
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
			
			public void onSuccess(Boolean result) {
				if(!result){
					Window.alert("Non è stato possibile aggiungere il contatto.");
					return;
				}
				
				//Aggiungo al Vettore
				vettoreContatti.add(contatto);
				if(contatto.getTipoSoggetto().equals("Trasportatore")) vettoreTrasportatori.add(contatto);
				if(contatto.getTipoSoggetto().equals("Fornitore")) vettoreFornitori.add(contatto);
				
				//Aggiungo ai listgridrecords
				ListGridRecord record = new ListGridRecord();
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
		});
	}
	
	public static void rimuoviContatto(final ListGridRecord record){
		String query = "DELETE FROM contatti WHERE ID='" + record.getAttribute("id")+ "'";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
		
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(Boolean result) {
				if(!result){
					Window.alert("Non è stato possibile rimuovere il contatto");
					return;
				}else{
					Contatto contatto = null;
					for(int i=0; i<vettoreContatti.size(); i++){
						contatto = vettoreContatti.get(i);
						if(contatto.getRagioneSociale().equals(record.getAttribute("ragionesociale"))){
							vettoreContatti.remove(i);
							istance.removeData(record);
							break;
						}
					}
				}
			}
		});
	}
	
	public String[] getCitta(){
		String[] strings = new String[vettoreContatti.size()];
		for(int i=0; i<vettoreContatti.size();i++){
			
		}
		return null;
	}

	public boolean isReady() {
		return ready;
	}
	
	
}
