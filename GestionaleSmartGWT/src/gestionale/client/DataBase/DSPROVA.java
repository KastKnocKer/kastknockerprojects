package gestionale.client.DataBase;

import java.util.Vector;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

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

public class DSPROVA extends DataSource{
	
	private static DSPROVA istance = null;
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	public static DSPROVA getIstance(){
		if (istance == null) {  
            istance = new DSPROVA();  
        } 
		return istance;
	}
	
	public DSPROVA(){
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
		PIDField.setForeignKey(id + ".ID");
		PIDField.setHidden(true);
		PIDField.setRootValue("root");
		nameField.setCanEdit(true);
		
		PIDField.setGroup("PID");
		 
		 
		setFields(nameField, IDField, PIDField);
		 
		setClientOnly(true);
		 
		newRecords();
		
	}
	
	
	public void newRecords(){
	
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
		});
		
		rpc.eseguiQueryContatto("SELECT * FROM contatti", new AsyncCallback<Contatto[]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Prodotti");
			}

			public void onSuccess(Contatto[] result) {

				for(int i=0; i<result.length; i++){
					Record record = new Record();
					Contatto contatto = result[i];
					
					record.setAttribute("ID", contatto.getID());
					record.setAttribute("Name", contatto.getRagioneSociale());
					record.setAttribute("PID", contatto.getTipoSoggetto());
					
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
