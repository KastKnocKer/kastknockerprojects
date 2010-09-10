package gestionale.client.DataBase;

import com.smartgwt.client.widgets.grid.ListGridRecord;


public class RecordContattiCampiVari extends ListGridRecord {
	
	 public RecordContattiCampiVari() {
 
	 }
	 
	 public RecordContattiCampiVari(String stringa){

		 int indice = stringa.indexOf('?');
		 String etichetta = stringa.substring(0, indice);
		 String valore = stringa.substring(indice+1);
		 if( etichetta.length() > 0 ) setAttribute("etichetta", etichetta);
		 if( valore.length() > 0 ) setAttribute("valore", valore);
		 
	 }
 

	public String getFieldValue(String field) {  
		 return getAttributeAsString(field);  
	}

}
