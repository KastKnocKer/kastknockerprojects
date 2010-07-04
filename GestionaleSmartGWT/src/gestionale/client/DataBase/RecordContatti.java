package gestionale.client.DataBase;

import gestionale.shared.Contatto;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeNode;
/*ListGridRecord */
public class RecordContatti extends  TreeNode {
	
	 public RecordContatti() {
 
	 }
	 
	 public RecordContatti(Contatto contatto){

		 setAttribute("id", contatto.getID());
		 setAttribute("ragionesociale", contatto.getRagioneSociale());
		 setAttribute("precisazione", contatto.getPrecisazione());
		 setAttribute("piva", contatto.getPIVA());
		 setAttribute("logo", contatto.getLogo());
		 setAttribute("indirizzo", contatto.getIndirizzo());
		 setAttribute("telefono", contatto.getTelefono());
		 setAttribute("cellulare", contatto.getCellulare());
		 setAttribute("fax", contatto.getFax());
		 setAttribute("email", contatto.geteMail());
		 setAttribute("sitoweb", contatto.getSitoWeb());
		 setAttribute("tiposoggetto", contatto.getTipoSoggetto());
		 setAttribute("provvigione", contatto.getProvvigione());
		 setAttribute("note", contatto.getNote());
		 	
	/*	 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);
		 setAttribute("continent", continent);*/
		 
		 
	 }
		   
		     
		   
		     
		   
	public String getFieldValue(String field) {  
		 return getAttributeAsString(field);  
	}

}
