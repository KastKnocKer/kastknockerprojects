package Archiviazione;

import java.util.*;

/** 
 * Libreria specializzata per l'uso con la classe Mp3
 * 
 * @author Castelli Andrea 
 * */

public class Mp3Comparator implements Comparator{
	
	
	private int mode;
	public static final int Key_Sort_Artista =0;
	public static final int Key_Sort_Titolo =1;
	public static final int Key_Sort_Album =2;
	public static final int Key_Sort_Genere =3;
	public static final int Key_Sort_Anno =4;
	public static final int Key_Sort_Dir =5;
	public static final int Key_Sort_NTraccia =6;
	public static final int Key_Sort_TagId = 7;
	
	/**
	 * Costruttore
	 */
	public Mp3Comparator(){
		mode=Key_Sort_Artista;
	}
	
	/**
	 * Setta la modalità di comparazione
	 * @param Int
	 */
	public void setMode(int mode){
		this.mode=mode;
	}
	
	
	/**
	 * Compara gli mp3
	 * @param Object (Mp3)
	 * @return	 1 se o1 minore di o2,
	 * 			 -1 se o1 maggiore di o2,
	 * 			 0 se o1 uguale o2
	 */

	public int compare(Object o1, Object o2) {
		
		switch(mode){
		case 0: return ((Mp3)o1).getArtista().compareTo(((Mp3)o2).getArtista());
		case 1: return ((Mp3)o1).getTitolo().compareTo(((Mp3)o2).getTitolo());
		case 2: return ((Mp3)o1).getAlbum().compareTo(((Mp3)o2).getAlbum());
		case 3: return ((Mp3)o1).getGenere().compareTo(((Mp3)o2).getGenere());
		case 4: return ((Mp3)o1).getAnno().compareTo(((Mp3)o2).getAnno());
		case 5: return ((Mp3)o1).getDir().compareTo(((Mp3)o2).getDir());
		case 6: return ((Mp3)o1).getNumTracciaCd().compareTo(((Mp3)o2).getNumTracciaCd());
		case 7: return ((Mp3)o1).getTagVersion().compareTo(((Mp3)o2).getTagVersion());
		default: return 0;
		
		}

	
		
	}

}
