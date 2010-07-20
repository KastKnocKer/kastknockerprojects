package gestionale.client.DataBase;

import gestionale.shared.Contatto;
import gestionale.shared.Ordine;

import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeNode;
/*ListGridRecord */
public class RecordOrdini extends  ListGridRecord {
	
	 
	 public RecordOrdini(Ordine ordine){

		 setAttribute("id", ordine.getID());
		 setAttribute("datacreazioneordine", ordine.getDataCreazioneOrdine());
		 setAttribute("datainvioordine", ordine.getDataInvioOrdine());
		 setAttribute("datapartenzamerce", ordine.getDataPartenzaMerce());
		 setAttribute("note", ordine.getNote());
		 setAttribute("idtrasportatore", ordine.getIDTrasportatore());
		 setAttribute("convalidato", ordine.getConvalidato());
		 setAttribute("tipoordine", ordine.getTipoOrdine());
		
	 }


	public String getFieldValue(String field) {  
		 return getAttributeAsString(field);  
	}

}
