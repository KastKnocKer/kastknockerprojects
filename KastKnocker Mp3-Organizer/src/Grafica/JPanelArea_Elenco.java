package Grafica;

import java.awt.BorderLayout;

import javax.swing.*;

import Archiviazione.Vettore;

/** 
 * JPanel personalizzato che mostra all'utente la lista degli artisti in libreria nella schermata principale
 * 
 * @author Castelli Andrea 
 **/

public class JPanelArea_Elenco extends JPanel{
	JList_Artisti ListaArtisti;
	JScrollPane ScrollP;
	/**
	 * Costruttore
	 */
	JPanelArea_Elenco(){
		super();
		
		this.setBorder( BorderFactory.createTitledBorder("Schede Artisti") );
		this.setLayout(new BorderLayout());
		
		ListaArtisti=new JList_Artisti();
		ScrollP=new JScrollPane(ListaArtisti);
		add(ScrollP, BorderLayout.CENTER);
	
		
	}

}
