package gestionale.client.DataBase;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceProdotti_Selezione extends DataSource{
	
	public static int ready = 0;
	private static int IDMaxTipologia = 0;
	private static int IDMaxVarieta = 0;
	private static int IDMaxSottoVarieta = 0;
	private static int IDMaxCalibro = 0;
	private static int IDMaxImballaggio = 0;
	
	private static DataSourceProdotti_Selezione istance = null;
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	
	public static DataSourceProdotti_Selezione getIstance(){
		if (istance == null) {  
            istance = new DataSourceProdotti_Selezione();  
        } 
		return istance;
	}
	
	public DataSourceProdotti_Selezione(){
		setID(id);  
        setTitleField("Name");
		DataSourceTextField nameField = new DataSourceTextField("Name", "Name");  
		nameField.setRequired(true);
		nameField.setCanEdit(true);
		 
		DataSourceTextField IDField = new DataSourceTextField("ID", "ID");  
		IDField.setRequired(true);
		IDField.setPrimaryKey(true);
		IDField.setHidden(true);
		 
		DataSourceTextField PIDField = new DataSourceTextField("PID", "PID");  
		PIDField.setRequired(true);
		PIDField.setForeignKey(id + ".ID");
		PIDField.setHidden(true);
		PIDField.setRootValue("root");
		
		setFields(nameField, IDField, PIDField);
		setClientOnly(true);
		getNewRecords();
	}
	
	public static void addRecord(ListGridRecord lgr){
		istance.addData(lgr);
	}


	 public static void getNewRecords() {
		 ready = 0;
		 rpc.eseguiQuery("SELECT * FROM prodotto_categoria", new AsyncCallback<String[][]>(){
				
				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Prodotti");
				}
	
				public void onSuccess(String[][] result) {
					ListGridRecord lgr = null;
						for(int i=0; i<result.length; i++){
							lgr = new ListGridRecord();
							lgr.setAttribute("ID", "C"+result[i][0]);
							lgr.setAttribute("Name", result[i][1]);
							lgr.setAttribute("PID", "root");
							lgr.setAttribute("Tipo", "Categoria");
							istance.addData(lgr);
							
						}
					ready++;
				}	
			});
			////////////////////////////////
			rpc.eseguiQuery("SELECT * FROM prodotto_tipologia", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}
			
			public void onSuccess(String[][] result) {
				ListGridRecord lgr = null;
					for(int i=0; i<result.length; i++){
						lgr = new ListGridRecord();
						lgr.setAttribute("ID", "T"+result[i][0]);
						lgr.setAttribute("Name", result[i][1]);
						lgr.setAttribute("PID", "C"+result[i][2]);
						lgr.setAttribute("Tipo", "Tipologia");
						istance.addData(lgr);
						if(IDMaxTipologia < Integer.parseInt(result[i][0])){
							IDMaxTipologia = Integer.parseInt(result[i][0]);
						}
					}
				ready++;
			}	
			});
	 	}	
			
		public static int getIDMaxTipologia(){
			return IDMaxTipologia;
		}
	 
		public static int getIDMaxVarieta(){
			return IDMaxVarieta;
		}
	 
		public static int getIDMaxSottoVarieta(){
			return IDMaxSottoVarieta;
		}
	 
		public static int getIDMaxCalibro(){
			return IDMaxCalibro;
		}
	 
		public static int getIDMaxImballaggio(){
			return IDMaxImballaggio;
		}
		
		public static String getNewIDMaxTipologia(){
			IDMaxTipologia++;
			return Integer.toString(IDMaxTipologia);
		}
	 
		public static String getNewIDMaxVarieta(){
			IDMaxVarieta++;
			return Integer.toString(IDMaxVarieta);
		}
	 
		public static String getNewIDMaxSottoVarieta(){
			IDMaxSottoVarieta++;
			return Integer.toString(IDMaxSottoVarieta);
		}
	 
		public static String getNewIDMaxCalibro(){
			IDMaxCalibro++;
			return Integer.toString(IDMaxCalibro);
		}
	 
		public static String getNewIDMaxImballaggio(){
			IDMaxImballaggio++;
			return Integer.toString(IDMaxImballaggio);
		}
		
		public boolean isReady(){
			if(ready == 2) return true;
			return false;
		}
	 

	
}
