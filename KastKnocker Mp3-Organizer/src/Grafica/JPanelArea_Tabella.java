package Grafica;


import javax.swing.*;


import java.awt.*;
import java.io.*;
import Archiviazione.*;
import Controllo.Config;
import Controllo.Ctrl_Componenti;

/** 
 * JPanel personalizzato che visualizza nella schermata principale la tabella degli mp3 ed eventualmente
 * le schede degli artisti
 * 
 * @author Castelli Andrea 
 **/

public class JPanelArea_Tabella extends JPanel{
	
	private TM_Tabella DataModel = null;
	private JScrollPane scroll=null;
	private JTableTabella Tabella=null;
	
	/**
	 * Costruttore
	 */
	JPanelArea_Tabella(){
		super();
		setLayout(new BorderLayout());
		Ctrl_Componenti.setLinkJPanelArea_Tabella(this);
		DataModel = new TM_Tabella();
		Tabella=new JTableTabella(DataModel);
		Tabella.getColumnModel();
		this.setBorder( BorderFactory.createTitledBorder("Elenco Brani") );
		Tabella.repaint();
		scroll=new JScrollPane(Tabella);
		add(scroll, BorderLayout.CENTER);
		
		this.setPreferredSize(new Dimension(682,547));
		
	}
	
	
}
