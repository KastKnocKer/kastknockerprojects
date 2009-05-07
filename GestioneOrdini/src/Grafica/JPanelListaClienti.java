package Grafica;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JPanelListaClienti extends JPanel{
	
	public static JTable LinkTabellaClienti=null;
	public JTable_ListaClienti TabellaClienti;
	public JScrollPane Scroll;
	public TableModelListaClienti tablemodel;
	public JPanelSottoVisualizzazioneClienti JPSVC = new JPanelSottoVisualizzazioneClienti();
	
	
	public JPanelListaClienti(){
		super();
		this.setLayout(new BorderLayout() );
		tablemodel = new TableModelListaClienti();
		TabellaClienti = new JTable_ListaClienti(tablemodel);
		Scroll = new JScrollPane(TabellaClienti);
		this.add(Scroll, java.awt.BorderLayout.CENTER);
		
		
		this.add(JPSVC, java.awt.BorderLayout.SOUTH); 
		
		
	}

}
