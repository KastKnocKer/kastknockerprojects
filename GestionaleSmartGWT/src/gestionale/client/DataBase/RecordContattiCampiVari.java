package gestionale.client.DataBase;

import com.smartgwt.client.widgets.grid.ListGridRecord;


public class RecordContattiCampiVari extends ListGridRecord {
	
	 public RecordContattiCampiVari() {
 
	 }
	 
	 public RecordContattiCampiVari(String stringa){

		 int indice = stringa.indexOf('?');
		 String etichetta = stringa.substring(0, indice);
		 String valore = stringa.substring(indice+1);
		 //  System.out.println(etichetta + "  " + valore);
		 setAttribute("etichetta", etichetta);
		 setAttribute("valore", valore);
		 
	 }
 

	public String getFieldValue(String field) {  
		 return getAttributeAsString(field);  
	}

}
