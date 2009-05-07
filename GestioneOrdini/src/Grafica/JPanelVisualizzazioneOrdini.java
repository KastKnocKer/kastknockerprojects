package Grafica;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Liste.Prodotto;

public class JPanelVisualizzazioneOrdini extends JPanel{
	
	public static String fileordine;
	JPanelSottoVisualizzazioneOrdini JPSVO = new JPanelSottoVisualizzazioneOrdini();
	private TableModelListaOrdinazioni tablemodel;
	private JTable_ListaOrdinazioni TabellaOrdinazioni;
	private JScrollPane Scroll;

	public JPanelVisualizzazioneOrdini(){
		super();
		
		JPSVO = new JPanelSottoVisualizzazioneOrdini();
		
		this.setLayout(new BorderLayout() );
		tablemodel = new TableModelListaOrdinazioni();
		TabellaOrdinazioni = new JTable_ListaOrdinazioni(tablemodel);
		Scroll = new JScrollPane(TabellaOrdinazioni);
		this.add(Scroll, java.awt.BorderLayout.CENTER);
		
		
		this.add(JPSVO, java.awt.BorderLayout.SOUTH); 
		
		
		
		
	}



	
}
