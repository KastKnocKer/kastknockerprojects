package Grafica;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTable_ListaOrdinazioni extends JTable{
	
	private TableColumn colonna;

	public JTable_ListaOrdinazioni(TableModelListaOrdinazioni TM){
		
		super(TM);
		for(int i=0;i<TableModelListaOrdinazioni.TMListaOrdinazioni.getColumnCount();i++){
			colonna = this.getColumnModel().getColumn(i);
			switch(i){
			case 0: {colonna.setMaxWidth(40); break;}		//Colonna indice
			case 1: {colonna.setMaxWidth(40); break;}	
			case 2: {colonna.setMinWidth(270); colonna.setMaxWidth(300); break;}
			case 4: {colonna.setMaxWidth(60); break;}
			default: {/*colonna.setPreferredWidth(200);*/ break;}
			
			}
		}
		
		
		
		
	}

}
