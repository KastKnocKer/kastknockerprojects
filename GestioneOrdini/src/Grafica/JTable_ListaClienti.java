package Grafica;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTable_ListaClienti extends JTable{
	
	private TableColumn colonna;

	public JTable_ListaClienti(TableModelListaClienti TM){
		super(TM);
		
		for(int i=0;i<TableModelListaClienti.LinkTMListaClienti.getColumnCount();i++){
			colonna = this.getColumnModel().getColumn(i);
			switch(i){
			case 0: {colonna.setMaxWidth(40); break;}//Colonna indice
			case 1: {colonna.setMaxWidth(140); break;}	
			case 2: {colonna.setMaxWidth(140); break;}	
			case 3: {colonna.setMaxWidth(80); break;}	
			case 4: {colonna.setMaxWidth(80); break;}	
			default: {/*colonna.setPreferredWidth(200);*/ break;}
			
			}
	}
		
		
		
		
	}

}
