package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceProdotti extends DataSource{
	private static int IDMaxTipologia = 0;
	private static int IDMaxVarieta = 0;
	private static int IDMaxSottoVarieta = 0;
	private static int IDMaxCalibro = 0;
	private static int IDMaxImballaggio = 0;
	
	private static DataSourceProdotti istance = null;
	
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	
	public static DataSourceProdotti getIstance(){
		if (istance == null) {  
            istance = new DataSourceProdotti();  
        } 
		return istance;
	}
	
	public DataSourceProdotti(){
		setID(id);  
        setTitleField("Name"); 
		DataSourceTextField nameField = new DataSourceTextField("RagioneSociale", "ragionesociale");  
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
		 
		 
		setFields(nameField, IDField, PIDField);
		 
		setClientOnly(true);
		 
		getNewRecords();
	}
	
	public static void addRecord(ListGridRecord lgr){
		istance.addData(lgr);
	}


	 public static void getNewRecords() {
		 
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
							System.out.println("Categoria: "+result[i][1]);
							
						}
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
			}	
			});
			////////////////////////////////
			rpc.eseguiQuery("SELECT * FROM prodotto_varieta", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}
			
			public void onSuccess(String[][] result) {
				ListGridRecord lgr = null;
					for(int i=0; i<result.length; i++){
						lgr = new ListGridRecord();
						lgr.setAttribute("ID", "V"+result[i][0]);
						lgr.setAttribute("Name", result[i][1]);
						lgr.setAttribute("PID", "T"+result[i][2]);
						lgr.setAttribute("Tipo", "Varieta");
						istance.addData(lgr);
						if(IDMaxVarieta < Integer.parseInt(result[i][0])){
							IDMaxVarieta = Integer.parseInt(result[i][0]);
						}
					}
			}	
			});
			////////////////////////////////
			rpc.eseguiQuery("SELECT * FROM prodotto_sottovarieta", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}
			
			public void onSuccess(String[][] result) {
				ListGridRecord lgr = null;
					for(int i=0; i<result.length; i++){
						lgr = new ListGridRecord();
						lgr.setAttribute("ID", "SV"+result[i][0]);
						lgr.setAttribute("Name", result[i][1]);
						lgr.setAttribute("PID", "V"+result[i][2]);
						lgr.setAttribute("Tipo", "SottoVarieta");
						istance.addData(lgr);
						if(IDMaxSottoVarieta < Integer.parseInt(result[i][0])){
							IDMaxSottoVarieta = Integer.parseInt(result[i][0]);
						}
					}
			}	
			});
			////////////////////////////////
			rpc.eseguiQuery("SELECT * FROM prodotto_calibro", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}
			
			public void onSuccess(String[][] result) {
				ListGridRecord lgr = null;
					for(int i=0; i<result.length; i++){
						lgr = new ListGridRecord();
						lgr.setAttribute("ID", "CA"+result[i][0]);
						lgr.setAttribute("Name", result[i][1]);
						lgr.setAttribute("PID", "SV"+result[i][2]);
						lgr.setAttribute("Tipo", "Calibro");
						istance.addData(lgr);
						if(IDMaxCalibro < Integer.parseInt(result[i][0])){
							IDMaxCalibro = Integer.parseInt(result[i][0]);
						}
					}
			}	
			});/*
			////////////////////////////////
			/*
			rpc.eseguiQuery("SELECT * FROM prodotto_imballaggio", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}
			
			public void onSuccess(String[][] result) {
					for(int i=0; i<result.length; i++){
						//tabImballaggio.add( result[i] );
					}
			}	
			});*/
		 
		 /*
		 String query = "SELECT * FROM ordini";
		 
			rpc.eseguiQueryOrdine(query,new AsyncCallback<Ordine[]>(){
				
				public void onFailure(Throwable caught) {
					Window.alert("Errore: Caricamento da DB Ordini");
				}

				public void onSuccess(Ordine[] result) {
					records = new RecordOrdini[result.length];
					
					for(int i=0; i<result.length; i++){
						records[i] = new RecordOrdini( result[i] );
					}
					
					if(lg!=null) lg.setData(records);
					
				}
				
			});*/
		 
		 
		
	}
	 
	 
	 /*
	   public static RecordOrdini[] getNewRecords() {
		 Vector<Ordine> v = Liste.getVettoreOrdini();
		 RecordOrdini[] records = new RecordOrdini[v.size()];
		        
		 for(int i=0; i<v.size(); i++){
			 records[i] = new RecordOrdini( v.get(i) );
		 }
		 
		  return records;
	}
	  */

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
	 

	
}
