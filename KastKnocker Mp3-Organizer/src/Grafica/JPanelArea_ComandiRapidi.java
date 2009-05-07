package Grafica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import Archiviazione.Libreria;
import Controllo.Ctrl_Componenti;

/** 
 * JPanel personalizzato visualizzato nella schermata principale,
 * che contiene bottoni per i comandi rapidi
 * 
 * @author Castelli Andrea 
 **/

public class JPanelArea_ComandiRapidi extends JPanel implements ActionListener{
	
	JButton JB_LibreriaGlob;
	JButton JB_Filtri;
	JButton JB_Cerca;
	/**
	 * Costruttore
	 */
	public JPanelArea_ComandiRapidi(){
		super();
		this.setBorder( BorderFactory.createTitledBorder("Comandi Rapidi") );
		//this.setPreferredSize( new Dimension(150,100) );
		JB_LibreriaGlob=new JButton("Libreria");
		JB_Filtri=new JButton("Azzera Filtri");
		JB_Cerca=new JButton("Cerca");
		this.add(JB_LibreriaGlob);
		this.add(JB_Filtri);
		this.add(JB_Cerca);
		
		JB_LibreriaGlob.addActionListener(this);
		JB_Filtri.addActionListener(this);
		JB_Cerca.addActionListener(this);
	}

	public void actionPerformed(ActionEvent AE) {
		String Evento=AE.getActionCommand();
		
		if( Evento.equals("Libreria") ){
			Ctrl_Componenti.getLinkMainPanel().VisualizzaLibreriaMp3();
			Libreria LibMp3=Libreria.getLibreriaGlobale();
			Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
		}
		if( Evento.equals("Azzera Filtri") ){Ctrl_Componenti.getLinkJPanelArea_Filtri().AzzeraFiltri();}
		if( Evento.equals("Cerca") ){new JDialogCerca();}
		
	}

}
