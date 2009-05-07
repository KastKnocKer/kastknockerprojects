package Grafica;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class JPanelListaProdotti extends JPanel{
	
	public static JTable LinkTabellaProdotti=null;
	public JTable_ListaProdotti TabellaProdotti;
	public JScrollPane Scroll;
	public TableModelListaProdotti tablemodel;
	
	
	public JPanelListaProdotti(){
		super();
		this.setLayout(new BorderLayout() );
		tablemodel = new TableModelListaProdotti();
		TabellaProdotti = new JTable_ListaProdotti(tablemodel);
		Scroll = new JScrollPane(TabellaProdotti);
		
		
		
		this.add(Scroll, java.awt.BorderLayout.CENTER);
		// this.add(JPanelListaOrdini, java.awt.BorderLayout.WEST); Pannello Pulsanti
	}
	

}
