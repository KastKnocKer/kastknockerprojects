package gestionale.client.DataBase;

import java.util.Vector;

import gestionale.client.Liste;
import gestionale.shared.Contatto;

import com.smartgwt.client.data.DataSource;

public class DataSourceContatti extends DataSource{
	
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
