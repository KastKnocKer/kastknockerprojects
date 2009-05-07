package Grafica;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTable_ListaProdotti extends JTable{
	
	private TableColumn colonna;

	public JTable_ListaProdotti(TableModelListaProdotti TM){
		super(TM);
		
		for(int i=0;i<TableModelListaProdotti.LinkTMListaProdotti.getColumnCount();i++){
			colonna = this.getColumnModel().getColumn(i);
			switch(i){
			case 0: {colonna.setMaxWidth(40); break;}		//Colonna indice
			case 1: {colonna.setPreferredWidth(30); break;}	
			case 2: {colonna.setPreferredWidth(30); break;}	/*
			case 3: {colonna.setPreferredWidth(20); break;}	
			case 4: {colonna.setPreferredWidth(20); break;}*/	
			case 5: {colonna.setMaxWidth(60); break;}	
			case 6: {colonna.setMaxWidth(60); break;}	
			case 7: {colonna.setMaxWidth(60); break;}
			default: {/*colonna.setPreferredWidth(200);*/ break;}
			
			}
		}
		
		
	}

}
