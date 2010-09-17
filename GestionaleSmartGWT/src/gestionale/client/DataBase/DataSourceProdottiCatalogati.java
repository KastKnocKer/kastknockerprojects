package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.Prodotto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DataSourceProdottiCatalogati extends DataSource{
	
	private static DataSourceProdottiCatalogati istance = null;
	
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	private static Vector<Prodotto> vProdottiCatalogati = null;
	
	private boolean ready = false;
	
	public static DataSourceProdottiCatalogati getIstance(){
		if (istance == null) {  
            istance = new DataSourceProdottiCatalogati();  
        } 
		return istance;
	}
	
	
	public DataSourceProdottiCatalogati(){
		setID(id);  
        setTitleField("Name");
        
        DataSourceTextField IDField = new DataSourceTextField("codiceprodotto", "CodiceProdotto");
        IDField.setRequired(true);
		IDField.setPrimaryKey(true);
		IDField.setHidden(true);
		
        DataSourceTextField categoriaField = new DataSourceTextField("categoria", "Categoria");  
        DataSourceTextField tipologiaField = new DataSourceTextField("tipologia", "Tipologia");  
        DataSourceTextField varietaField = new DataSourceTextField("varietà", "Varieta");  
        DataSourceTextField sottovarietaField = new DataSourceTextField("sottovarieta", "Sottovarieta");  
        DataSourceTextField calibroField = new DataSourceTextField("calibro", "Calibro");  
        DataSourceTextField descrizioneField = new DataSourceTextField("descrizione", "Descrizione");  
        
		setFields(IDField, categoriaField, tipologiaField, varietaField, sottovarietaField, calibroField, descrizioneField);
		 
		setClientOnly(true);
		 
		getNewRecords();
	}
	
	public static void addRecord(ListGridRecord lgr){
		istance.addData(lgr);
	}


	 public void getNewRecords() {
		 ready = false;
		 String query = "SELECT cal.ID, cat.Categoria, tip.Tipologia, var.Varieta, svar.Sottovarieta, cal.Calibro " +
		 		"FROM prodotto_categoria cat JOIN prodotto_tipologia tip JOIN prodotto_varieta var JOIN prodotto_sottovarieta svar JOIN prodotto_calibro cal " +
		 		"ON cat.ID = tip.IDCategoria AND tip.ID = var.IDTipologia AND var.ID = svar.IDVarieta AND svar.ID = cal.IDSottovarieta " +
		 		"ORDER BY cat.Categoria, tip.Tipologia, var.Varieta, svar.Sottovarieta, cal.Calibro";
		 
		 rpc.eseguiQuery(query, new AsyncCallback<String[][]>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(String[][] result) {
				String[] stringa = null;
				Vector<Prodotto> v = new Vector<Prodotto>();
				
				for(int i=0; i<result.length; i++){
					stringa = result[i];
					ListGridRecord record = new ListGridRecord();
					
					record.setAttribute("codiceprodotto", stringa[0]);
					record.setAttribute("id", stringa[0]);
					record.setAttribute("categoria", stringa[1]);
					record.setAttribute("tipologia", stringa[2]);
					record.setAttribute("varieta", stringa[3]);
					record.setAttribute("sottovarieta", stringa[4]);
					record.setAttribute("calibro", stringa[5]);
					record.setAttribute("descrizione", stringa[1]+" "+stringa[2]+" "+stringa[3]+" "+stringa[4]+" "+stringa[5]);
					
					Prodotto prodotto = new Prodotto();
					prodotto.setID(stringa[0]);
					prodotto.setCategoria(stringa[1]);
					prodotto.setTipologia(stringa[2]);
					prodotto.setVarieta(stringa[3]);
					prodotto.setSottoVarieta(stringa[4]);
					prodotto.setCalibro(stringa[5]);
					
					v.add(prodotto);
					
					addRecord(record);
					
				}
				vProdottiCatalogati = v;
				ready = true;
			}
			 
		 });
		 
	}


	public static Vector<Prodotto> getvProdottiCatalogati() {
		return vProdottiCatalogati;
	}

	public boolean isReady() {
		return ready;
	}
	 
	 
	 

	
}
