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

public class DataSourceProdottiCatalogati extends DataSource{
	
	private static DataSourceProdottiCatalogati istance = null;
	
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	
	public static DataSourceProdottiCatalogati getIstance(){
		System.out.println("MA QUANTA CE NERA?");
		if (istance == null) {  
            istance = new DataSourceProdottiCatalogati();  
        } 
		return istance;
	}
	
	public DataSourceProdottiCatalogati(){
		setID(id);  
        setTitleField("Name");
        
        DataSourceTextField IDField = new DataSourceTextField("CodiceProdotto", "codiceprodotto");
        IDField.setRequired(true);
		IDField.setPrimaryKey(true);
		IDField.setHidden(true);
		
        DataSourceTextField categoriaField = new DataSourceTextField("Categoria", "categoria");  
        DataSourceTextField tipologiaField = new DataSourceTextField("Tipologia", "tipologia");  
        DataSourceTextField varietaField = new DataSourceTextField("Variet�", "varieta");  
        DataSourceTextField sottovarietaField = new DataSourceTextField("Sottovarieta", "sottovarieta");  
        DataSourceTextField calibroField = new DataSourceTextField("Calibro", "calibro");  
        
		
		 
		setFields(IDField, categoriaField, tipologiaField, varietaField, sottovarietaField, calibroField);
		 
		setClientOnly(true);
		 
		getNewRecords();
	}
	
	public static void addRecord(ListGridRecord lgr){
		istance.addData(lgr);
	}


	 public static void getNewRecords() {
		 String query = "SELECT cal.ID, cat.Categoria, tip.Tipologia, var.Varieta, svar.Sottovarieta, cal.Calibro " +
		 		"FROM prodotto_categoria cat JOIN prodotto_tipologia tip JOIN prodotto_varieta var JOIN prodotto_sottovarieta svar JOIN prodotto_calibro cal " +
		 		"ON cat.ID = tip.IDCategoria AND tip.ID = var.IDTipologia AND var.ID = svar.IDVarieta AND svar.ID = cal.IDSottovarieta";
		 
		 rpc.eseguiQuery(query, new AsyncCallback<String[][]>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(String[][] result) {
				String[] stringa = null;
				System.out.println("RISULTATO MAGICA QUERY");
				
				for(int i=0; i<result.length; i++){
					stringa = result[i];
					ListGridRecord record = new ListGridRecord();
					
					record.setAttribute("codiceprodotto", stringa[0]);
					record.setAttribute("categoria", stringa[1]);
					record.setAttribute("tipologia", stringa[2]);
					record.setAttribute("varieta", stringa[3]);
					record.setAttribute("sottovarieta", stringa[4]);
					record.setAttribute("calibro", stringa[5]);
					
					addRecord(record);
					
				}
			}
			 
		 });
		 
	}
	 
	 
	 

	
}