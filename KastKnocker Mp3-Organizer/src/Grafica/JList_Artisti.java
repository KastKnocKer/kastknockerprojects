package Grafica;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.corba.se.pept.transport.Selector;


import Archiviazione.Mp3;
import Archiviazione.Vettore;
import Controllo.Ctrl_Componenti;
import GestioneArtisti.Artist_MusicArtist;
import GestioneArtisti.Artist_MusicGroup;
import GestioneArtisti.Artist_SoloMusicArtist;
import GestioneArtisti.LibreriaArtisti;

/** 
 * JList personalizzata per la visualizzazione delle schede degli artisti musicali 
 * e l'interazione con l'utente per aggiungere, rimuovere, modificare tali schede.
 * 
 * @author Castelli Andrea 
 **/

public class JList_Artisti extends JList implements MouseListener,ActionListener{
	private Vettore VArtisti=null;
	private int LineaSelezionata;
	private JPopupMenu JPM=null;
	private LibreriaArtisti LibArtist=null;
	
	/**
	 * Costruttore
	 */
	public JList_Artisti(){
		super();
		Ctrl_Componenti.setLinkJList_Artisti(this);
		this.addMouseListener(this);
		
		JPM =new JPopupMenu();	//Creo il popup menu
		
		JMenuItem JMIP_NuovaScheda = new JMenuItem("Nuova Scheda");
		JMenuItem JMIP_RimuoviScheda = new JMenuItem("Rimuovi Scheda");
		JMenuItem JMIP_VisualizzaScheda = new JMenuItem("Visualizza Scheda");
		JMIP_NuovaScheda.addActionListener(this);
		JMIP_RimuoviScheda.addActionListener(this);
		JMIP_VisualizzaScheda.addActionListener(this);
		
		JPM.add(JMIP_VisualizzaScheda);
		JPM.add(JMIP_NuovaScheda);
		JPM.add(JMIP_RimuoviScheda);
		
		LibArtist=new LibreriaArtisti();
		LibArtist.caricaLibreriaFileTxt();
		
		VArtisti=LibArtist.getNameArtistVector();
		
		//VArtisti=new Vettore();
		//for(int i=0;i<100;i++){
		//	VArtisti.addElement( new String("Fakka "+i) );
		//}
		
		this.setListData(VArtisti);
		
		
		
		
	}
	/**
	 * Aggiorna la JList, ricaricando tutti i nomi degli artisti musicali in libreria
	 */
	public void Aggiorna(){
		LibArtist.getLibreriaArtistiGlobale();
		VArtisti=LibArtist.getNameArtistVector();
		this.setListData(VArtisti);
	}

	
	public void mouseClicked(MouseEvent ME) {
		LineaSelezionata=this.locationToIndex(ME.getPoint());
		this.setSelectedIndex(LineaSelezionata);
		
		if( ME.getButton() == MouseEvent.BUTTON1) {
			if( ME.getClickCount()==1 ) return;
			String NomeArtista = (String) VArtisti.elementAt(LineaSelezionata);
			LibArtist.visualizzaSchedaArtista(NomeArtista);
			return;
		}
			
		if( ME.getButton() == MouseEvent.BUTTON2){/*evento per tasto rotella mouse*/return;}
		
		if( ME.getButton() == MouseEvent.BUTTON3){
			JPM.show(this, ME.getX(), ME.getY());	//Mostro il PopupMenu
			return;
			}
		
		}

	
	public void mouseEntered(MouseEvent ME) {}
	public void mouseExited(MouseEvent ME) {}
	public void mousePressed(MouseEvent ME) {}
	public void mouseReleased(MouseEvent ME) {}
	
	public void actionPerformed(ActionEvent AE) {
		String Evento=AE.getActionCommand();
		
		if(Evento.equals("Nuova Scheda")){
			LibArtist.creaSchedaArtista();
			
			this.Aggiorna();
		}
		if(Evento.equals("Rimuovi Scheda")){
			String NomeArtista = (String) VArtisti.elementAt(LineaSelezionata);
			LibArtist=LibreriaArtisti.getLibreriaArtistiGlobale();
			LibArtist.rimuoviSchedaArtista(NomeArtista);
			LibArtist.salvaLibreriaFileTxt();

			this.Aggiorna();
		}
		if(Evento.equals("Visualizza Scheda")){
			String NomeArtista = (String) VArtisti.elementAt(LineaSelezionata);
			LibArtist.visualizzaSchedaArtista(NomeArtista);
			
			
		}
	
	}
	
	
	
}
