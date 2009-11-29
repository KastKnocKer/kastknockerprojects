package InterfacciaGrafica;

import java.util.*;

import javax.swing.table.*;

import GestioneMp3.Database;


public class TM_Mp3 extends AbstractTableModel{
	
	public static Vector V = null;
	
	public TM_Mp3(){
		super();
		V = Database.eseguiQuery("SELECT * FROM Mp3;");
	}
	
	public String getColumnName(int col){
		switch(col){
		case 0: return "ID"; 
		case 1: return "Nome"; 
		case 2: return "Cognome";
		case 3: return "Telefono";
		case 4: return "Cellulare";
		case 5: return "Indirizzo";
		case 6: return "Città";
		default: return "***";
		}
		
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	public int getRowCount() {
		return V.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		
		String[] record = (String[]) V.elementAt(arg0);
		return record[arg1];
		
		/*cliente = ListaClienti.LinkListaClienti.get(arg0);
		
		switch(arg1){
		case 0: return cliente.getIDCliente();
		case 1: return cliente.getNome();
		case 2: return cliente.getCognome();
		case 3: return cliente.getTelefono();
		case 4: return cliente.getCellulare();
		case 5: return cliente.getIndirizzo();
		case 6: return cliente.getCitta() +" - "+cliente.getCap();
		default: return "***";
			
		}*/
		
		
	}

}
