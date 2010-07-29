package gestionale.client.DataBase;

import gestionale.shared.Contatto;

import com.smartgwt.client.data.DataSource;

public class DataSourceContattiIndirizzi extends DataSource{
	
	private static RecordContattiIndirizzi[] records;
	
	public static RecordContattiIndirizzi[] getRecords(Contatto contatto) {  
		if (records == null) {  
		records = getNewRecords(contatto);  
		}
		return records;  
	}
	

	 public static RecordContattiIndirizzi[] getNewRecords(Contatto contatto) {
		 
		 RecordContattiIndirizzi[] records = new RecordContattiIndirizzi[20];
		 
		 	String stringa = contatto.getIndirizzo();
			int indiceA,indiceB;
			String temporanea = null;
			int i=0;
			
			while(stringa.length()>0){
				indiceA=stringa.indexOf('*');
				indiceB=stringa.indexOf('*', 1);
				
				temporanea=stringa.substring(indiceA+1,indiceB);
				stringa = stringa.substring(indiceB+1);

				records[i] = new RecordContattiIndirizzi(temporanea);

				i++;
			}
		 
		  
		 
		 
		 
		         
		  return records;
	}

	
}
