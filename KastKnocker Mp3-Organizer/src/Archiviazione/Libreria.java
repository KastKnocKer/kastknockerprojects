package Archiviazione;

import java.awt.Toolkit;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.TagException;


import Controllo.*;
import Grafica.*;

/** 
 * Libreria specializzata per l'uso con la classe Mp3
 * 
 * @author Castelli Andrea 
 * */

public class Libreria extends ListNode{
	
	@SuppressWarnings("unchecked")
	private Comparator Test;
	private static Libreria LinkLibreriaGlobale=null;
	/**
	 * Funzione statica che setta la libreria come libreria globale se già non settata.
	 * @param Libreria
	 */
	public static void setLibreriaGlobale(Libreria Lib){ if(LinkLibreriaGlobale==null) LinkLibreriaGlobale=Lib; }
	/**
	 * Funzione statica che setta la libreria come libreria globale.
	 * @param Libreria
	 */
	public static void setForzatoLibreriaGlobale(Libreria Lib){ LinkLibreriaGlobale=Lib; }
	/**
	 * Funzione statica che ritorna la libreria globale
	 * @return Libreria Globale
	 */
	public static Libreria getLibreriaGlobale(){ return LinkLibreriaGlobale; }
	/**
	 * Costruttore: crea lista vuota.
	 */
	public Libreria(){super(); Test=new Mp3Comparator();}
	/**
	 * Costruttore: crea lista con il solo mp3 dato
	 * @param Mp3
	 */
	public Libreria(Object o){super(o); Test=new Mp3Comparator();}
	
	/**
	 * Costruttore: crea lista vuota con il comparatore di mp3
	 * @param Mp3Comparator
	 */
	public Libreria(Comparator c){super(); Test=c;}
	/**
	 * Costruttore: crea lista con il solo mp3 dato e con il comparatore di mp3
	 * @param Mp3
	 * @param Mp3Comparator
	 */
	public Libreria(Object o, Comparator c){super(o); Test=c;}
	/**
	 * Costruttore: crea lista ampliando la lista data con l'mp3 dato
	 * @param Mp3
	 * @param Libreria
	 */
	public Libreria(Object o, List l){super(); Test=new Mp3Comparator(); insert(o); 
	while(l.isEmpty()){insert(l.head()); l=l.tail();}}
	/**
	 * Costruttore: crea lista ampliando la lista data con l'mp3 dato
	 * @param Mp3
	 * @param Mp3Comparator
	 * @param Libreria
	 */
	public Libreria(Object o, Comparator c, List l){super(); Test=c; insert(o); 
	while(l.isEmpty()){insert(l.head()); l=l.tail();}}
	/**
	 * Inserisce controllando che non ci siano mp3 già caricati uguali.
	 * @param Mp3
	 */	
	public void insert(Object o){
		if(o==null) return;
		if(isEmpty()){ super.insert(o); return;}
		if(contains(o)){ return; }
		if(Test.compare(o, head())<0) { super.insert(o); return;}
		Node pred=getNode();
		Node tmp=pred.next;
		while((tmp!=null)&&(Test.compare(o, tmp.info) >0 )){
			pred=tmp;
			tmp=tmp.next;
		}
		pred.next=new Node(o,tmp);
		
	}
	

	/**
	 * Dice se la lista contiene l'mp3 richiesto.
	 * @param Mp3
	 * @return true se contiene l'mp3
	 * @return false se non contiene l'mp3
	 */
	public boolean contains(Object o){
		Node tmp=getNode();
		String Dir = new String( ((Mp3)o).getDir() );
		while(tmp!=null){
			String DirTmp = new String( ((Mp3)tmp.info).getDir() );
			if(Dir.equals(DirTmp)) return true;
			tmp=tmp.next;
		}
		return false;
	}
	
	/**
	 * Rimuove un mp3 dalla libreria.
	 * @param Mp3
	 */
	public void remove(Object o){
		if(!isEmpty()){
			String mp3Dir=((Mp3)o).getDir();
			Node pred=getNode();
			Node tmp=getNode().next;
			if( ((Mp3)getNode().info).getDir().equals(mp3Dir)) setNode(getNode().next);
			else
				while(tmp!=null)
					if(((Mp3)tmp.info).getDir().equals(mp3Dir)){
						pred.next=tmp.next;
						return;
					}
					else
					{
						pred=pred.next;
						tmp=tmp.next;
					}
		}
	}
	/**
	 * Rimuove l'Mp3 corrispondente alla directory immessa
	 * @param Mp3
	 */
	public void removeDir(String Dir){
		if(!isEmpty()){
			Node pred=getNode();
			Node tmp=getNode().next;
			if( ((Mp3)getNode().info).getDir().equals(Dir)) setNode(getNode().next);
			else
				while(tmp!=null)
					if(((Mp3)tmp.info).getDir().equals(Dir)){
						pred.next=tmp.next;
						return;
					}
					else
					{
						pred=pred.next;
						tmp=tmp.next;
					}
		}
	}

	
/**
* Ritorna il comparatore
* @return Mp3Comparator
*/

	public Mp3Comparator getComparator(){
		return (Mp3Comparator) Test;
	}
	
/**
* Svuota la lista
*/
	public void setEmptyList(){
			setNode(null);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////Funzioni per l'import///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Controlla tramite l'estensione del file se è mp3
	 * @param File
	 * @return true se è un mp3
	 * @return false se non è un mp3
	 */
	public boolean isMp3(File isMp3File){
		String Estensione = Utils.getExtension(isMp3File);
		if(Estensione.equals("mp3")) return true;
		return false;
	}
	
	File file=null;
	/**
	 * Importa il solo mp3 selezionato nella libreria
	 */
	public void importFileMp3(){
		   
		      JFileChooser fileChooser = new JFileChooser();				//Invoco il Fchooser
		      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	//Setto modalità solo per file
		      fileChooser.addChoosableFileFilter (new FileNameExtensionFilter ("File *.mp3", "mp3"));	//Imposto solo mp3
		      int result = fileChooser.showOpenDialog(new JPanel());	
		      if ( result == JFileChooser.CANCEL_OPTION )return;
	/////Fine Parte Chooser	      
		      file = fileChooser.getSelectedFile();
		      if(isMp3(file)==true){ this.insertMp3(file.getAbsoluteFile()); }
		     System.out.println("File Singolo Mp3 Importato!");
	}
	/**
	 * Importa tutti gli mp3 contenuti nella directory selezionata
	 */
	public void importDirMp3(){
			   
			      JFileChooser fileChooser = new JFileChooser();
			      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			      int result = fileChooser.showOpenDialog(new JPanel());
			      if ( result == JFileChooser.CANCEL_OPTION )return; 
	/////Fine Parte Chooser	     		   
			      file = fileChooser.getSelectedFile();
			      File fileDir = new File(file.getAbsolutePath()); 
			         
			      if(fileDir == null) return;
			      if(!fileDir.isDirectory()) return;
			      if(!fileDir.canRead()) return;

			      File[] VettoreFileDir = fileDir.listFiles();
			        
	for(int i = 0; i < VettoreFileDir.length; i++) {
		if(isMp3(VettoreFileDir[i])==true){ this.insertMp3(VettoreFileDir[i].getAbsoluteFile());}  } 
			          
	System.out.println("Directory Mp3 Importata!");
	}
	
	/**
	 * Importa tutti gli mp3 contenuti nella directory selezionata e nelle sue sottocartelle
	 */
	public void importDirSubDirMp3(){
		   
		      JFileChooser fileChooser = new JFileChooser();
		      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		      int result = fileChooser.showOpenDialog(new JPanel());
		      if ( result == JFileChooser.CANCEL_OPTION ) return;
	/////Fine Parte Chooser		      
		      file = fileChooser.getSelectedFile();
	          File fileDir = new File(file.getAbsolutePath()); 

	          if(fileDir == null) return;
		      if(!fileDir.isDirectory()) return;
		      if(!fileDir.canRead()) return;

	          File[] VettoreFileDir = fileDir.listFiles();
	          CaricamentoRicorsivoDirSubDir(VettoreFileDir);
 
	         System.out.println("Directory e SottoDirectory Mp3 Importate!");
	}
	
	private void CaricamentoRicorsivoDirSubDir(File[] VettoreTemp){
		
		for(int i = 0; i < VettoreTemp.length; i++) {
			if( VettoreTemp[i].isDirectory() ){CaricamentoRicorsivoDirSubDir(VettoreTemp[i].listFiles()); }
			if(isMp3(VettoreTemp[i])){ this.insertMp3( VettoreTemp[i].getAbsoluteFile() ); }
		}
		return;
	}

	private void insertMp3(File FileMp3){

		Mp3 mp3 = null;
				try {
					mp3 = new Mp3(FileMp3);
					
					
				} catch (IOException e) {
					System.out.println("IOException - Insert Mp3 nella lista");
					return;
				} catch (TagException e) {
					System.out.println("TagException - Insert Mp3 nella lista");
					return;
				} catch (ReadOnlyFileException e) {
					System.out.println("ReadOnlyFileException - Insert Mp3 nella lista");
					return;
				} catch (InvalidAudioFrameException e) {
					System.out.println("InvalidAudioFrameException - Insert Mp3 nella lista");
					return;
				}
				
				
				System.out.println("Inserendo: "+FileMp3.getAbsolutePath());
				this.insert(mp3);
		
	}
	/**
	 * Carica nella libreria su cui è chiamata tutti gli mp3 salvati da disco fisso
	 */
	public void caricaLibreriaFile(){
	
	int num;
	ObjectInputStream ois = null;
	Mp3 inCarica;
		
	try {
		ois = new ObjectInputStream(new FileInputStream(Config.getIndLibreria() + "Libreria.dat"));
	} catch (FileNotFoundException e) {
	
		System.out.println("File Non Trovato!!");
		Toolkit.getDefaultToolkit().beep();
		ObjectOutputStream oos;
	try {
		oos = new ObjectOutputStream(new FileOutputStream(Config.getIndLibreria() + "Libreria.dat"));
		oos.close();
		} catch (IOException e1) {System.out.println("Io Exception1 - Caricamento Libreria da File");}
	
		e.printStackTrace();
		System.exit(0);
	
		} catch (IOException e) { System.out.println("Io Exception2 - Caricamento Libreria da File"); }

			num=0;
			try {
				num=ois.readInt(); //Leggo il num di mp3
			} catch (IOException e1) {System.out.println("Io Exception3 (readInt numero mp3) - Caricamento Libreria da File");}	
		
			
			try {				
			for(int i=0;i<num;i++){
					inCarica = (Mp3) ois.readObject();	//Leggo il primo oggetto
					insert( (Mp3) inCarica );		//Inserisco in lista l'oggetto
					}

			ois.close();	//Chiudo il file
					     
			} catch (IOException e) { System.out.println("Io Exception3 (readObject) - Caricamento Libreria da File"); } 
			  catch (ClassNotFoundException e) { System.out.println("Class not found Exception - Caricamento Libreria da File"); }
					

			System.out.println("Status - Libreria Caricata!");
			}
	/**
	 * Salva su disco fisso tutti gli oggetti di tipo Mp3 della libreria corrente
	 */
	public void salvaLibreriaFile(){
		Node tmp=getNode();
		
			try {
			//Apro il file
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Config.getIndLibreria() + "Libreria.dat"));
						oos.writeInt(this.length());
							while(tmp!=null){
								oos.writeObject( ((Mp3) tmp.info) );
								tmp=tmp.next;
								}	
			oos.close();
						   
			} catch (Exception e) {System.out.println("Exception - Salvataggio Libreria su File");}
			
	System.out.println("Status - Libreria Salvata!");
	}
	/**
	 * Carica la libreria corrente leggendo gli elementi della libreria globale già caricata in memoria
	 */
	public void caricaLibreriaGlobale() {
		int NMp3 = LinkLibreriaGlobale.length();
		for (int i=0;i<NMp3;i++){
			this.insert( LinkLibreriaGlobale.getObjPos(i) );
		}
		System.out.println("Caricamento da Libreria Globale, Terminato.");
		this.caricaFiltri();
	}
                /* boolean Copertina, boolean Lyric*/
	/**
	 * Carica la libreria corrente leggendo gli elementi della libreria globale già caricata in memoria
	 * filtrando gli elementi in entrata
	 * @param String Artista
	 * @param String Album
	 * @param String Genere
	 * @param String Anno
	 * @param String Titolo
	 * @param int Copertina
	 * @param int Lyric
	 */
	public void caricaLibreriaGlobaleFiltrata(String Artista,String Album,String Genere,String Anno,String Titolo,int Copertina, int Lyric) {
		int NMp3 = LinkLibreriaGlobale.length();
		for (int i=0;i<NMp3;i++){
			
		    boolean Flag=true;
		    /*
		    if (Album!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getAlbum().toLowerCase().equals(Album.toLowerCase()) ) Flag=false;}
		    if (Artista!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getArtista().toLowerCase().equals(Artista.toLowerCase()) ) Flag=false;}
		    if (Genere!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getGenere().toLowerCase().equals(Genere.toLowerCase()) ) Flag=false;}
		    if (Anno!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getAnno().toLowerCase().equals(Anno.toLowerCase()) ) Flag=false;}
		    if (Titolo!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getTitolo().toLowerCase().equals(Titolo.toLowerCase()) ) Flag=false;}		
		    */
		    
		    if (Album!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getAlbum().toLowerCase().startsWith(Album.toLowerCase()) ) Flag=false;}
		    if (Artista!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getArtista().toLowerCase().startsWith(Artista.toLowerCase()) ) Flag=false;}
		    if (Genere!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getGenere().toLowerCase().startsWith(Genere.toLowerCase()) ) Flag=false;}
		    if (Anno!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getAnno().toLowerCase().startsWith(Anno.toLowerCase()) ) Flag=false;}
		    if (Titolo!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).getTitolo().toLowerCase().startsWith(Titolo.toLowerCase()) ) Flag=false;}		
		    
		    
		    if (Copertina>=0){
		    	if( (Copertina==0)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasCopertina()==true ) ) Flag=false;
		    	if( (Copertina==1)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasCopertina()==false ) ) Flag=false;
		    }
		    if (Lyric>=0){
		    	if( (Lyric==0)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasLyric()==true ) ) Flag=false;
		    	if( (Lyric==1)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasLyric()==false ) ) Flag=false;
		    }
		    	
		    
			if (Flag==true)	this.insert( LinkLibreriaGlobale.getObjPos(i) );	
			
		}
		
		System.out.println("Caricamento filtrato da Libreria Globale, Terminato.");
		System.out.println("Elementi Trovati: " + this.length() );
		
	}
	/**
	 * Carica la libreria corrente leggendo gli elementi della libreria globale già caricata in memoria
	 * filtrando gli elementi in entrata
	 * @param String Artista
	 * @param String Album
	 * @param String Genere
	 * @param String Anno
	 */
	public void caricaLibreriaGlobaleFiltrata(String Artista,String Album,String Genere,String Anno) {
		this.caricaLibreriaGlobaleFiltrata(Artista,Album,Genere,Anno,null,-1,-1);
	}
	/**
	 * Aggiorna i filtri dinamici di catalogazione degli mp3
	 */
	public void caricaFiltri(){
		
	JPanelArea_Filtri JPAF = Ctrl_Componenti.getLinkJPanelArea_Filtri();
	JPAF.resetFiltri();
	int NMp3 = this.length();
	for (int i=0;i<NMp3;i++){
		JPAF.insertInfoMp3( (Mp3) this.getObjPos(i) );
	}
	JPAF.ordinaFiltri();
	System.out.println("Caricamento Filtri, Terminato.");
	
	}
	/**
	 * Carica la libreria corrente leggendo gli elementi della libreria in entrata già caricata in memoria
	 * @param Libreria
	 */
	public void caricaLibreria(Libreria LibMp3){
		
		int NMp3 = LibMp3.length();
		for (int i=0;i<NMp3;i++){
			this.insert( LibMp3.getObjPos(i) );
		}
		System.out.println("Caricamento da Libreria non Globale, Terminato.");
		
	}
	/**
	 * Ritorna, se presente in libreria, l'mp3 corrispondente alla directory inserita
	 * @param String Directory
	 * @return Mp3
	 */
	public Mp3 getMp3(String dir){
		
		int NMp3 = this.length();
		for (int i=0;i<NMp3;i++){
			if( ((Mp3) this.getObjPos(i)).getDir().equals(dir) ) return (Mp3) this.getObjPos(i);
		}
		return null;
	}
	/**
	 * Aggiorna i dati di tutti gli mp3 presenti in libreria
	 */
	public void AggiornaLibreriaMp3(){
		
		int NMp3 = this.length();
		for (int i=0;i<NMp3;i++){
			((Mp3) this.getObjPos(i)).Aggiorna();
		}
	}
}
	
	
