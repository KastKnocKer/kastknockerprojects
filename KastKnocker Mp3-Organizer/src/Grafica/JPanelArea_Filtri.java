package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


import Archiviazione.Libreria;
import Archiviazione.Mp3;
import Archiviazione.Vettore;
import Controllo.Ctrl_Componenti;

/** 
 * JPanel personalizzato che contiene i filtri per una ricerca filtrata veloce degli mp3
 * 
 * @author Castelli Andrea 
 **/

public class JPanelArea_Filtri extends JPanel implements ActionListener{
	
	private JComboBox JCB_Album;
    private JComboBox JCB_Anno;
    private JComboBox JCB_Artista;
    private JComboBox JCB_Genere;
    
    private Vettore V_Album = new Vettore();
    private Vettore V_Anno = new Vettore();
    private Vettore V_Artista = new Vettore();
    private Vettore V_Genere = new Vettore();
    
	
	/**
	 * Costruttore
	 */
	public JPanelArea_Filtri(){
		super();
		Ctrl_Componenti.setLinkJPanelArea_Filtri(this);
		
		Libreria LibGlobale=new Libreria();	//Inizializzo la libreria Globale
		LibGlobale.caricaLibreriaFile();
		Libreria.setLibreriaGlobale(LibGlobale);
		
		this.setBorder( BorderFactory.createTitledBorder("Filtri") );
		
		JCB_Genere = new JComboBox();
        JCB_Artista = new JComboBox();
        JCB_Album = new JComboBox();
        JCB_Anno = new JComboBox();
        
        
        Ctrl_Componenti.setLinkJCB_Album(JCB_Album);
        Ctrl_Componenti.setLinkJCB_Anno(JCB_Anno);
        Ctrl_Componenti.setLinkJCB_Artista(JCB_Artista);
        Ctrl_Componenti.setLinkJCB_Genere(JCB_Genere);
        
        setLayout(new java.awt.GridLayout());
        
        JCB_Anno.setModel(new javax.swing.DefaultComboBoxModel( V_Anno ));
        JCB_Genere.setModel(new javax.swing.DefaultComboBoxModel( V_Genere ));
        JCB_Artista.setModel(new javax.swing.DefaultComboBoxModel( V_Artista ));
        JCB_Album.setModel(new javax.swing.DefaultComboBoxModel( V_Album ));
        
        JCB_Anno.addActionListener(this);
        JCB_Genere.addActionListener(this);
        JCB_Artista.addActionListener(this);
        JCB_Album.addActionListener(this);
        
        add(JCB_Genere);
        add(JCB_Artista);
        add(JCB_Album);
        add(JCB_Anno);
        
        
	}
	
	
	
	/**
	 * Procedura per inserire nei filtri i dati dell'mp3 passato come parametro
	 * @param mp3
	 */
	public void insertInfoMp3(Mp3 mp3){
		String Tmp=null;
		Tmp=mp3.getAlbum();
		if(!V_Album.contains(Tmp)) V_Album.addElement(Tmp);
		Tmp=mp3.getAnno();
		if(!V_Anno.contains(Tmp)) V_Anno.addElement(Tmp);
		Tmp=mp3.getArtista();
		if(!V_Artista.contains(Tmp)) V_Artista.addElement(Tmp);
		Tmp=mp3.getGenere();
		if(!V_Genere.contains(Tmp)) V_Genere.addElement(Tmp);

	}
	
	/**
	 * Ordina alfabeticamente i filtri
	 */
	public void ordinaFiltri(){
		
		//while(V_Album.contains(new String("Seleziona Album"))) 
		V_Album.remove(new String("Seleziona Album"));
        V_Anno.remove(new String("Seleziona Anno"));
        V_Artista.remove(new String("Seleziona Artista"));
        V_Genere.remove(new String("Seleziona Genere"));
		
		V_Album.Ordina();
        V_Anno.Ordina();
        V_Artista.Ordina();
        V_Genere.Ordina();
        
        V_Album.insertElementAt(new String("Seleziona Album"), 0);
        V_Anno.insertElementAt(new String("Seleziona Anno"), 0);
        V_Artista.insertElementAt(new String("Seleziona Artista"), 0);
        V_Genere.insertElementAt(new String("Seleziona Genere"), 0);
        
        JCB_Anno.setModel(new javax.swing.DefaultComboBoxModel( V_Anno ));
        JCB_Genere.setModel(new javax.swing.DefaultComboBoxModel( V_Genere ));
        JCB_Artista.setModel(new javax.swing.DefaultComboBoxModel( V_Artista ));
        JCB_Album.setModel(new javax.swing.DefaultComboBoxModel( V_Album ));
	}

	/**
	 * Svuota i filtri
	 */
	public void resetFiltri(){
		V_Album.clear();
        V_Anno.clear();
        V_Artista.clear();
        V_Genere.clear();
		V_Album.insertElementAt(new String("Seleziona Album"), 0);
        V_Anno.insertElementAt(new String("Seleziona Anno"), 0);
        V_Artista.insertElementAt(new String("Seleziona Artista"), 0);
        V_Genere.insertElementAt(new String("Seleziona Genere"), 0);
	}
	/**
	 * Azzera i filtri annullando le selezioni
	 */
	public void AzzeraFiltri(){
		JCB_Anno.setSelectedIndex(0);
        JCB_Genere.setSelectedIndex(0);
        JCB_Artista.setSelectedIndex(0);
        JCB_Album.setSelectedIndex(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String Album,Anno,Genere,Artista;
		Album = (String) JCB_Album.getSelectedItem();
		Anno = (String) JCB_Anno.getSelectedItem();
		Genere = (String) JCB_Genere.getSelectedItem();
		Artista = (String) JCB_Artista.getSelectedItem();
		
		if( Album.equals("Seleziona Album") ) Album=null;
		if( Anno.equals("Seleziona Anno") ) Anno=null;
		if( Genere.equals("Seleziona Genere") ) Genere=null;
		if( Artista.equals("Seleziona Artista") ) Artista=null;
					     
		Libreria LibMp3=new Libreria();
		LibMp3.caricaLibreriaGlobaleFiltrata(Artista, Album, Genere, Anno);
		
		
	
		Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
		
		
		
	}
	
	
	

}
