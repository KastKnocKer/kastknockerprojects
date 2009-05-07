package Grafica;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Archiviazione.*;
import Controllo.*;

/** 
 * Modello che definisce i dati visualizzabili da una JTable abbinata ad esso
 * 
 * @author Castelli Andrea 
 **/

public class TM_Tabella extends AbstractTableModel{
	
	private Libreria LibMp3;
	private Object obj;
	
	/**
	 * Costruttore
	 */
	public TM_Tabella(){
		super();
		Ctrl_Componenti.setLinkTM_Tabella(this);
	}
	/**
	 * Ritorna il numero di colonne
	 * @return Num Colonne
	 */
	public int getColumnCount(){ return 9; }
	/**
	 * Ritorna il numero di Righe
	 * @return Num righe
	 */
	public int getRowCount(){return LibMp3.length();}
	/**
	 * Ritorna l'oggetto da visualizzare nella posizione passata come parametro
	 */
	public Object getValueAt(int row,int col){
		obj= LibMp3.getObjPos(row);
		
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) obj).getArtista();
		case 2: return ((Mp3) obj).getTitolo();
		case 3: return ((Mp3) obj).getAlbum();
		case 4: return ((Mp3) obj).getGenere();
		case 5: return ((Mp3) obj).getAnno();
		case 6: return ((Mp3) obj).getNumTracciaCd();
		case 7: return ((Mp3) obj).getTagVersion();
		case 8: return ((Mp3) obj).getDir();
		default: return "***";
		}
	}
	/**
	 * Dato l'indice di colonna ritorna il nome corrispondente
	 * @return nome colonna
	 */
	public String getColumnName(int col){
		switch(col){
		case 0: return "N."; 
		case 1: return "Artista"; 
		case 2: return "Titolo";
		case 3: return "Album";
		case 4: return "Genere";
		case 5: return "Anno";
		case 6: return "#";
		case 7: return "Tag";
		case 8: return "Dir";	
		default: return "***";
		}
	}
	/**
	 * Indica se la cella nella posizione passata come parametro è editabile
	 */
	public boolean isCellEditable(int row,int col){
		return false;
	}
	/**
	 * Setta a questo modello la libreria mp3
	 * @param LibMp3
	 */
	public void setLibreria(Libreria LibMp3){
		this.LibMp3=LibMp3;
	}
	
}