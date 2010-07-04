package gestionale.client.DataBase;

import gestionale.shared.Contatto;

import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeNode;
/*ListGridRecord */
public class RecordContattiIndirizzi extends  ListGridRecord {
	
	// public RecordContattiIndirizzi() {
 //
	 //}
	 
	 public RecordContattiIndirizzi(String stringa){

		super();
		String[] vetString = null;
		int indiceA,indiceB;
		String temporanea;
		System.out.println("Stringhetta: " + stringa);
				
				temporanea=stringa;
				
				vetString = new String[10];
				
				indiceA = temporanea.indexOf('+');
				vetString[0] = temporanea.substring(0, indiceA); // Etichetta
				
				indiceB = temporanea.indexOf('+', indiceA+1);
				vetString[1] = temporanea.substring(indiceA+1, indiceB); // Via
				
				indiceA = temporanea.indexOf('+', indiceB+1);
				vetString[2] = temporanea.substring(indiceB+1, indiceA); // Numero Civico
				
				indiceB = temporanea.indexOf('+', indiceA+1);
				vetString[3] = temporanea.substring(indiceA+1, indiceB); // CAP
				
				indiceA = temporanea.indexOf('+', indiceB+1);
				vetString[4] = temporanea.substring(indiceB+1, indiceA); // Località
				
				indiceB = temporanea.indexOf('+', indiceA+1);
				vetString[5] = temporanea.substring(indiceA+1, indiceB); // Città
				
				indiceA = temporanea.indexOf('+', indiceB+1);
				vetString[6] = temporanea.substring(indiceB+1, indiceA); // Provincia
				
				indiceB = temporanea.indexOf('+', indiceA+1);
				vetString[7] = temporanea.substring(indiceA+1, indiceB); // Regione
				
				indiceA = temporanea.indexOf('+', indiceB+1);
				vetString[8] = temporanea.substring(indiceB+1, indiceA); // Provincia
				
				indiceB = temporanea.indexOf('+', indiceA+1);
				vetString[9] = temporanea.substring(indiceA+1); // Regione
				
		
		 setAttribute("etichetta", 	vetString[0]);
		 setAttribute("via", 		vetString[1]);
		 setAttribute("ncivico", 	vetString[2]);
		 setAttribute("cap", 		vetString[3]);
		 setAttribute("frazione", 	vetString[4]);
		 setAttribute("citta", 		vetString[5]);
		 setAttribute("provincia", 	vetString[6]);
		 setAttribute("regione", 	vetString[7]);
		 setAttribute("nazione", 	vetString[8]);
		 setAttribute("predefinito", vetString[9]);

	 }
		   
		     
		   
		     
		   
	public String getFieldValue(String field) {  
		 return getAttributeAsString(field);  
	}

}
