package Grafica;

import javax.swing.table.*;

import Liste.ListaProdotti;
import Liste.Prodotto;

public class TableModelListaProdotti extends AbstractTableModel{
	
	public static TableModelListaProdotti LinkTMListaProdotti;
	ListaProdotti listaprodotti;
	Prodotto prodotto;
	
	
	public TableModelListaProdotti(){
		super();
		LinkTMListaProdotti=this;
		listaprodotti = ListaProdotti.LinkListaProdotti;
	}
	
	public String getColumnName(int col){
		switch(col){
		case 0: return "Cod."; 
		case 1: return "Nome"; 
		case 2: return "Prod";
		case 3: return "Tipo";
		case 4: return "Categ";
		case 5: return "ml";
		case 6: return "Prezzo";
		case 7: return "Punti";
		default: return "***";
		}
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	public int getRowCount() {
		return listaprodotti.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		prodotto = (Prodotto) listaprodotti.get(arg0);
		
		switch(arg1){
		case 0: return prodotto.getCodice();
		case 1: return prodotto.getNome();
		case 2: return prodotto.getProduttore();
		case 3: return prodotto.getTipo();
		case 4: return prodotto.getCategoria();
		case 5: return prodotto.getContenuto();
		case 6: return prodotto.getPrezzoCliente()+"€";
		case 7: return prodotto.getPunti();
		default: return "***";
			
		}
		
		
	}

}
