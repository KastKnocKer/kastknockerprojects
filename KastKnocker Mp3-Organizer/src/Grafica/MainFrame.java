package Grafica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Controllo.*;


/** 
 * JFrame personalizzato: Frame principale che contiene il pannello principale e la barra dei menù
 * 
 * @author Castelli Andrea 
 **/

public class MainFrame extends JFrame implements WindowListener{
	
	EventsMainFrame EMF = new EventsMainFrame();
	
	JMenuBar BarraDeiMenu;
	JMenu menuFile,menuArchivio,menuVisualizza,menuStrumenti,menuAiuto;
	JMenuItem MfileEsci;
	JMenuItem MarchivioImportaMP3,MarchivioImportaCartellaMP3;
	JMenuItem MvisualizzaOpzioni;
	
	/**
	 * Costruttore
	 */
	public MainFrame(){
		super("Mp3-Organizer 2008");

		
		addWindowListener(this);
		setBounds(100,100,Config.getRisX(),Config.getRisY());
        JMenuBar BarraDeiMenu = new JMenuBar();		//Creo barra dei menu
        
        JMenu menuFile = new JMenu("File");			//Menu FILE
        JMenuItem MfileEsci = new JMenuItem("Esci");
        MfileEsci.addActionListener(EMF);
        menuFile.add(MfileEsci);
        
        JMenu menuArchivio = new JMenu("Archivio");	//Menu ARCHIVIO
        JMenuItem MarchivioImportaMP3 = new JMenuItem("Importa Mp3");
        JMenuItem MarchivioImportaCartellaMP3 = new JMenuItem("Importa Cartella Mp3");
        JMenuItem MarchivioImportaCartellaSubDirMP3 = new JMenuItem("Importa Cartella e sottocartelle Mp3");
        JMenuItem MarchivioSvuotaLibreria = new JMenuItem("Svuota Libreria");
        
        MarchivioImportaMP3.addActionListener(EMF);
        MarchivioImportaCartellaMP3.addActionListener(EMF);
        MarchivioImportaCartellaSubDirMP3.addActionListener(EMF);
        MarchivioSvuotaLibreria.addActionListener(EMF);
        
        menuArchivio.add(MarchivioImportaMP3);
        menuArchivio.add(MarchivioImportaCartellaMP3);
        menuArchivio.add(MarchivioImportaCartellaSubDirMP3);
        menuArchivio.add(MarchivioSvuotaLibreria);
        
        JMenu menuVisualizza = new JMenu("Visualizza"); //Menu VISUALIZZA
        
        JMenuItem MvisualizzaLibMp3= new JMenuItem("Libreria Mp3");
        MvisualizzaLibMp3.addActionListener(EMF);
        //JMenuItem MvisualizzaListaArtisti= new JMenuItemAL("Lista Artisti",EMF);
        menuVisualizza.add(MvisualizzaLibMp3);
        //menuVisualizza.add(MvisualizzaListaArtisti);
        
        JMenu menuStrumenti = new JMenu("Strumenti"); //Menu VISUALIZZA
        JMenuItem MstrumentiCerca= new JMenuItem("Cerca");
        MstrumentiCerca.addActionListener(EMF);
        //JMenuItem MstrumentiOpzioni= new JMenuItemAL("Opzioni",EMF);
        menuStrumenti.add(MstrumentiCerca);
        //menuStrumenti.add(MstrumentiOpzioni);
        
        JMenu menuAiuto = new JMenu("?");			//Menu ?
        JMenuItem MaiutoInfo = new JMenuItem("Info");
        MaiutoInfo.addActionListener(EMF);
        menuAiuto.add(MaiutoInfo);
        
        //Aggiungo i Menù alla barra dei menù
        BarraDeiMenu.add(menuFile);  
        BarraDeiMenu.add(menuArchivio);
        BarraDeiMenu.add(menuVisualizza);
        BarraDeiMenu.add(menuStrumenti);
        BarraDeiMenu.add(menuAiuto);
        

        
        setJMenuBar(BarraDeiMenu);
	
		Container cmf=getContentPane();
		MainPanel mp=new MainPanel();
		cmf.add(mp);
		setVisible(true);
		
	}
	

	
	/**Gestione eventi della finestra*/
	
	public void windowClosed(WindowEvent e){System.exit(0);}
	public void windowClosing(WindowEvent e){System.exit(0);}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	

}
