package Controllo;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import Archiviazione.*;
import GestioneArtisti.Artist_MusicArtist;
import GestioneArtisti.LibreriaArtisti;
import Grafica.*;

/** 
 * Ascoltatore di eventi ActionListener della barra dei menu della finestra principale
 * 
 * @author Castelli Andrea 
 * */

public class EventsMainFrame implements ActionListener{
	private JFrame mframe;

	/**
	 * Costruttore
	 */
	public EventsMainFrame() {
		
	}


	public void actionPerformed(ActionEvent e){
		String Evento= e.getActionCommand();
		if (Evento.equals("Esci")){System.exit(0);}
		if (Evento.equals("Opzioni")){
			Toolkit.getDefaultToolkit().beep();
		}
		
		
		if (Evento.equals("Importa Mp3")){
			Libreria LibMp3=new Libreria();	//Creo nuova libreria di supporto;
			LibMp3.caricaLibreriaFile();	//Carico tutti gli mp3 da file;
			LibMp3.importFileMp3();		//Apporto le modifiche necessarie;
			LibMp3.salvaLibreriaFile();		//Salva su File le modifiche apportate;
			LibMp3.caricaFiltri();
			Libreria.setForzatoLibreriaGlobale(LibMp3);	//Metto questa libreria come Libreria Globale
			
			Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
			}
		
		
		if (Evento.equals("Importa Cartella Mp3")){
			Libreria LibMp3=new Libreria();	//Creo nuova libreria di supporto;
			LibMp3.caricaLibreriaFile();	//Carico tutti gli mp3 da file;
			LibMp3.importDirMp3();		//Apporto le modifiche necessarie
			LibMp3.salvaLibreriaFile();		//Salva su File le modifiche apportate;
			LibMp3.caricaFiltri();
			Libreria.setForzatoLibreriaGlobale(LibMp3);	//Metto questa libreria come Libreria Globale
			
			Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
			}
		
		if (Evento.equals("Importa Cartella e sottocartelle Mp3")){
			Libreria LibMp3=new Libreria();	//Creo nuova libreria di supporto;
			LibMp3.caricaLibreriaFile();	//Carico tutti gli mp3 da file;
			LibMp3.importDirSubDirMp3();		//Apporto le modifiche necessarie
			LibMp3.salvaLibreriaFile();		//Salva su File le modifiche apportate;
			LibMp3.caricaFiltri();
			Libreria.setForzatoLibreriaGlobale(LibMp3);	//Metto questa libreria come Libreria Globale
			
			Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
			}
		
		
		if (Evento.equals("Svuota Libreria")){
			
			int n = JOptionPane.showConfirmDialog(Ctrl_Componenti.getLinkMainFrame(),
				    "Vuoi veramente svuotare l'intera libreria?",
				    "Avviso.",
				    JOptionPane.YES_NO_OPTION);

					if(n==0){
						
					Libreria LibMp3=new Libreria();
					Libreria.setForzatoLibreriaGlobale(LibMp3);
					LibMp3.salvaLibreriaFile();
					
					Ctrl_Componenti.getLinkJPanelArea_Filtri().resetFiltri();
					Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
					
					}
			
		}
		
			if (Evento.equals("Cerca")){
			
				new JDialogCerca();
				
			}
			
			if (Evento.equals("Libreria Mp3")){
				
				Ctrl_Componenti.getLinkMainPanel().VisualizzaLibreriaMp3();
				Libreria LibMp3=Libreria.getLibreriaGlobale();
				Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
				
			}
		
			if (Evento.equals("Info")) {
			JOptionPane.showMessageDialog(mframe,"Mp3-Organizer 2008\nStudente: Castelli Andrea\nMatricola: 30300","Info", JOptionPane.PLAIN_MESSAGE);
			}
			
			if (Evento.equals("Opzioni")){

				//((MainPanel) Ctrl_Componenti.getLinkMainPanel()).VisualizzaLibreriaMp3();
				//LibreriaArtisti LibArt = LibreriaArtisti.getLibreriaArtistiGlobale();
				//Artist_MusicArtist AMA = (Artist_MusicArtist) LibArt.elementAt(1);
				//((MainPanel) Ctrl_Componenti.getLinkMainPanel()).VisualizzaSezioneArtisti(AMA);
				
			}
			
	
		
	}


	
	

}
