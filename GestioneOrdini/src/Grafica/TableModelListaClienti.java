package Grafica;

import javax.swing.table.*;

import Liste.Cliente;
import Liste.Database;
import Liste.ListaClienti;
import Liste.ListaProdotti;
import Liste.Prodotto;

public class TableModelListaClienti extends AbstractTableModel{
	
	private Cliente cliente;
	public static TableModelListaClienti LinkTMListaClienti;
	
	public TableModelListaClienti(){
		super();
		LinkTMListaClienti=this;
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
		return 7;
	}

	public int getRowCount() {
		return ListaClienti.LinkListaClienti.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		cliente = ListaClienti.LinkListaClienti.get(arg0);
		
		switch(arg1){
		case 0: return cliente.getIDCliente();
		case 1: return cliente.getNome();
		case 2: return cliente.getCognome();
		case 3: return cliente.getTelefono();
		case 4: return cliente.getCellulare();
		case 5: return cliente.getIndirizzo();
		case 6: return cliente.getCitta() +" - "+cliente.getCap();
		default: return "***";
			
		}
		
		
	}

}
