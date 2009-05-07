package Grafica;

import java.awt.*;
import javax.swing.*;

import Controllo.*;
import GestioneArtisti.*;

/** 
 * JPanel personalizzato: Pannello principale che contiene e gestisce i propri sottopannelli
 * per l'interazione dell'utente con il programma
 * 
 * @author Castelli Andrea 
 **/

public class MainPanel extends JPanel{

	
	private JPanel JPArea1=null;
	private JPanel JPArea2=null;
	private JPanel JPArea3=null;

	private JPanelArea_Elenco 			JPArea_Elenco=null;
	private JPanelArea_Tabella 			JPArea_Tabella=null;
	private JPanelArea_Play 			JPArea_Play=null;
	private JPanelArea_Filtri 			JPArea_Filtri=null;
	private JPanelArea_ComandiRapidi 	JPArea_ComandiRapidi=null;
	private JPanel_ViewMusicArtist 		JPVMA=null;
	private JScrollPane 				Scroll=null;
	
	/**
	 * Costruttore
	 */
	public MainPanel(){
		Ctrl_Componenti.setLinkMainPanel(this);

		JPArea1=new JPanel();
		JPArea2=new JPanel();
		JPArea3=new JPanel();
	
		
		JPArea_Filtri=new JPanelArea_Filtri();
		JPArea_Elenco=new JPanelArea_Elenco();
		JPArea_Tabella=new JPanelArea_Tabella();
		JPArea_Play=new JPanelArea_Play();
		JPArea_ComandiRapidi=new JPanelArea_ComandiRapidi();
		
		this.setLayout(new BorderLayout());
		JPArea1.setLayout(new BorderLayout());
		JPArea2.setLayout(new BorderLayout());
		JPArea3.setLayout(new BorderLayout());
		
		add(JPArea1, BorderLayout.WEST);
		add(JPArea2, BorderLayout.CENTER);
		
		JPArea1.add(JPArea_Elenco, BorderLayout.CENTER);
		JPArea1.add(JPArea_Play, BorderLayout.SOUTH);
		
		JPArea2.add(JPArea_Tabella, BorderLayout.CENTER);
		JPArea2.add(JPArea3, BorderLayout.SOUTH);
		
		JPArea3.add(JPArea_ComandiRapidi, BorderLayout.SOUTH);
		JPArea3.add(JPArea_Filtri, BorderLayout.NORTH);
		
	}
	/**
	 * Visualizza la libreria mp3
	 */
	public void VisualizzaLibreriaMp3(){
		JPArea2.removeAll();
		JPArea2.add(JPArea_Tabella, BorderLayout.CENTER);
		JPArea2.add(JPArea3, BorderLayout.SOUTH);
		this.updateUI();
	}
	/**
	 * Visualizza al posto della tabella degli mp3 la scheda dell'artista passato come parametro
	 * @param Artista
	 */
	public void VisualizzaSezioneArtisti(Artist_MusicArtist Artista){
		JPVMA = new JPanel_ViewMusicArtist( Artista );
		Scroll=new JScrollPane(JPVMA);
		
		JPArea2.removeAll();
		JPArea2.add(Scroll, BorderLayout.CENTER);
		JPArea2.add(JPArea3, BorderLayout.SOUTH);
		this.updateUI();
	}
	


}
