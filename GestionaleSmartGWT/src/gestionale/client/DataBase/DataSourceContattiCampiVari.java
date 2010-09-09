package gestionale.client.DataBase;

import gestionale.shared.Contatto;
import com.smartgwt.client.data.DataSource;

public class DataSourceContattiCampiVari extends DataSource{
	
private static RecordContattiCampiVari[] records;
	
	public static RecordContattiCampiVari[] getRecords(Contatto contatto, String campo) {  
		if (records == null) {  
		records = getNewRecords(contatto, campo);  
		}
		return records;  
	}
	

	 public static RecordContattiCampiVari[] getNewRecords(Contatto contatto, String campo) {
		 String stringa = null;
		 int indiceA,indiceB;
		 RecordContattiCampiVari[] records = new RecordContattiCampiVari[20];
		 
		 if( campo.equals("Telefono") ){
			 stringa = contatto.getTelefono();
		 } else if( campo.equals("Cellulare") ){
			 stringa = contatto.getCellulare();
		 } else if( campo.equals("Fax") ){
			 stringa = contatto.getFax();
		 } else if( campo.equals("Email") ){
			 stringa = contatto.geteMail();
		 }
		 int i = 0;
		 while(stringa.length() > 0){
			 indiceA = stringa.indexOf('*');
			 indiceB = stringa.indexOf('*', 1);
			 String temp = stringa.substring(indiceA+1, indiceB);
			 stringa = stringa.substring(indiceB+1);
			 records[i] = new RecordContattiCampiVari(temp);
			 i++;
		 }
		 
		  
		         
		  return records;
	}
	

	
}
