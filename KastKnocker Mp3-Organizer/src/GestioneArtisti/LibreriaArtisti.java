package GestioneArtisti;

import java.awt.Toolkit;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.TagException;


import Archiviazione.Mp3;
import Archiviazione.Vettore;
import Controllo.Config;
import Controllo.Ctrl_Componenti;
import Grafica.JPanelArea_Filtri;
import Grafica.JPanelCerca;
import Grafica.JPanel_MusicGroup;
import Grafica.JPanel_MusicSoloArtist;
import Grafica.JPanel_SelezioneSchedaArtista;
import Grafica.JPanel_ViewMusicArtist;
import Grafica.JTableTabella;
import Grafica.MainPanel;
import Grafica.TM_Tabella;

/** 
 * Libreria specializzata per l'uso con la classe Mp3
 * 
 * @author Castelli Andrea 
 **/

public class LibreriaArtisti extends Vettore{
	
	private static LibreriaArtisti LinkLibreriaArtistiGlobale=null;
	
	/**
	 * Costruttore. Nel caso in cui non sia stata impostata la libreria
	 * globale, viene settata questa libreria come libreria globale.
	 */
	public LibreriaArtisti(){
		super();
		if(LinkLibreriaArtistiGlobale==null) LinkLibreriaArtistiGlobale=this;
	}
	/**
	 * Ritorna la libreria globale degli artisti
	 * @return LibreriaArtistiGlobale
	 */
	public static LibreriaArtisti getLibreriaArtistiGlobale(){return LinkLibreriaArtistiGlobale;}

	/**
	 * Inserisce nella libreria l'artista dato come parametro controllando che non sia già stato inserito
	 * @param Artista
	 */
	public void insert(Artist_MusicArtist Artista){
		if(contains(Artista.getNomeDArte()) ) return;
		this.addElement(Artista);
	}

	/**
	 * Indica se l'artista dato come parametro è contenuto nella libreria degli artisti
	 * @param NomeDArte
	 * @return
	 */
	public boolean contains(String NomeDArte){
		int elementi=this.size();
		for(int i=0;i<elementi;i++){
			if(((Artist_MusicArtist) this.elementAt(i)).getNomeDArte().toLowerCase().equals( NomeDArte.toLowerCase() ) ){
				return true;
			}
		}
		return false;
	}
	/**
	 * Dato come parametro il nome dell'artista, ritorna l'artista corrispondente
	 * @param NomeDArte
	 * @return Artista
	 */
	public Artist_MusicArtist getArtistFromName(String NomeDArte){
		int elementi=this.size();
		for(int i=0;i<elementi;i++){
			if(((Artist_MusicArtist) this.elementAt(i)).getNomeDArte().toLowerCase().equals( NomeDArte.toLowerCase() ) ){
				return (Artist_MusicArtist) this.elementAt(i);
			}
		}
		return null;
	}
	/**
	 * Procedura che permette all'utente di creare una scheda artista personalizzata
	 * potendo scegliere la tipologia di artista.
	 * Apre una finestra di dialogo che guida l'utente nella creazione della scheda dell'artista.
	 */
	public void creaSchedaArtista(){
		JDialog JD = new JDialog();
		JPanel_SelezioneSchedaArtista JPSSA = new JPanel_SelezioneSchedaArtista(JD,null);
		
		JD.setModal(false);
		JD.setLocation(250,250); 
		JD.setTitle("Creazione scheda artista");
		JD.getContentPane().add(JPSSA);
		JD.setVisible(true);
		JD.setAlwaysOnTop(true);
		JD.pack();
		
	}
	/**
	 * Procedura che permette all'utente di creare una scheda artista personalizzata
	 * potendo scegliere la tipologia di artista.
	 * Apre una finestra di dialogo che guida l'utente nella creazione della scheda dell'artista
	 * scelto tramite la selezione diretta da mp3.
	 * @param Mp3
	 */
	public void creaSchedaArtista(Mp3 mp3){
		JDialog JD = new JDialog();
		JPanel_SelezioneSchedaArtista JPSSA = new JPanel_SelezioneSchedaArtista(JD,mp3);
		
		JD.setModal(false);
		JD.setLocation(250,250); 
		JD.setTitle("Creazione scheda artista");
		JD.getContentPane().add(JPSSA);
		JD.setVisible(true);
		JD.setAlwaysOnTop(true);
		JD.pack();
		
	}
	/**
	 * Aggiunge l'artista dato come parametro nella libreria degli artisti
	 * @param Artista
	 */
	public void aggiungiArtista(Artist_MusicArtist Artista){
		this.insert(Artista);
		this.salvaLibreriaFileTxt();
		( (MainPanel) Ctrl_Componenti.getLinkMainPanel()).VisualizzaSezioneArtisti(Artista);

		Ctrl_Componenti.getLinkJList_Artisti().Aggiorna();
		
		
	}
	/**
	 * Rimuove l'artista corrispondente al nome artista dato come parametro
	 * @param NomeDArte
	 */
	public void rimuoviSchedaArtista(String NomeDArte){
		int elementi=this.size();
		for(int i=0;i<elementi;i++){
			if(((Artist_MusicArtist) this.elementAt(i)).getNomeDArte().toLowerCase().equals( NomeDArte.toLowerCase() ) ){
				this.remove(i);
				return;
			}
		}
		
	}
	/**
	 * Procedura che permette di visualizzare al posto della tabella che visualizza l'elenco degli mp3
	 * la scheda dell'artista corrispondente al nome artista dato come parametro
	 * @param NomeDArte
	 */
	public void visualizzaSchedaArtista(String NomeDArte){
		
		Artist_MusicArtist AMA = this.getArtistFromName(NomeDArte);
		if( AMA!=null ){
			( (MainPanel) Ctrl_Componenti.getLinkMainPanel()).VisualizzaSezioneArtisti(AMA);
			}
	}
	/**
	 * Ritorna un vettore ordinato contenente come elementi tutti i nomi d'arte degli artisti in libreria
	 * @return Vettore
	 */
	public Vettore getNameArtistVector(){
		Vettore NuovoVettore=new Vettore();
		int Grandezza=this.size();
		
		for(int i=0;i<Grandezza;i++){
			String Tmp=((Artist_MusicArtist) this.elementAt(i)).getNomeDArte();
			NuovoVettore.addElement( Tmp  );
		}
		NuovoVettore.Ordina();
		return NuovoVettore;
	}
	/**
	 * Procedura che salva la libreria artisti su file di testo
	 */
	public void salvaLibreriaFileTxt(){
		BufferedWriter writer;
		try {
			// apro il buffered writer
			writer = new BufferedWriter(new FileWriter(Config.getIndLibreria()+"LibreriaArtisti.txt", false));
			writer.write("StartArtistLibrary");
			writer.newLine();
			// Scrivo per artista le informazioni 
			for (int i = 0; i < this.size(); i++) {
				Artist_MusicArtist Artista = (Artist_MusicArtist) this.elementAt(i);
				String TipoArtista=Artista.getTipoArtista();
				if( TipoArtista.equals("SoloMusicArtist") ){
					writer.write("SoloMusicArtist");	writer.newLine();
					
					writer.write( ((Artist_SoloMusicArtist) Artista).getNomeDArte() ); 						writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getNome() ); 							writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getCognome() ); 						writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getNazionalità() ); 					writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getUrlWeb() ); 						writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getDataEsordio() ); 					writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getDataNascita() ); 					writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getDataMorte() ); 						writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getCasaDiscografica() ); 				writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getDiscografiaAlbum() ); 				writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getGeneriMusicaliPreferiti() ); 		writer.newLine();
					writer.write("BOB");	writer.newLine();
					writer.write( ((Artist_SoloMusicArtist) Artista).getBiografia() ); 						writer.newLine();
					writer.write("EOB");	writer.newLine();
					
				}
				if( TipoArtista.equals("MusicGroup") ){
					writer.write("MusicGroup");
					writer.newLine();
					
					writer.write( ((Artist_MusicGroup) Artista).getNumComponenti() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getNomeDArte() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getDataFormazione() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getDataScioglimento() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getUrlWeb() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getDiscografiaAlbum() );
					writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getCasaDiscografica() );
					writer.newLine();
					writer.write("BOB");	writer.newLine();
					writer.write( ((Artist_MusicGroup) Artista).getBiografia() );		 writer.newLine();
					writer.write("EOB");	writer.newLine();
					
					int NumComp=Integer.parseInt(((Artist_MusicGroup) Artista).getNumComponenti());
					
					for(int j=0;j<NumComp;j++){
						Artist_SoloMusicArtist ASMA = ((Artist_MusicGroup) Artista).getComponenteAtPos(j);
						writer.write( ASMA.getNomeDArte() );
						writer.newLine();
						writer.write( ASMA.getNome() );
						writer.newLine();
						writer.write( ASMA.getCognome() );
						writer.newLine();
						writer.write( ASMA.getNazionalità() );
						writer.newLine();
						writer.write( ASMA.getUrlWeb() );
						writer.newLine();
						writer.write( ASMA.getDataEsordio() );
						writer.newLine();
						writer.write( ASMA.getDataNascita() );
						writer.newLine();
						writer.write( ASMA.getDataMorte() );
						writer.newLine();
						writer.write( ASMA.getCasaDiscografica() );
						writer.newLine();
						writer.write( ASMA.getDiscografiaAlbum() );
						writer.newLine();
						writer.write( ASMA.getGeneriMusicaliPreferiti() );
						writer.newLine();
						writer.write("BOB");	writer.newLine();
						writer.write( ASMA.getBiografia() ); 						writer.newLine();
						writer.write("EOB");	writer.newLine();
						
					}
					
					
				}
				
				
				//writer.write(Artista.elementAt(i).getCasaDisco());
				//writer.newLine();
			}
			writer.write("EOF");
			writer.newLine();
		
			// chiudo il file
			writer.flush();
			writer.close();
			}
		catch (IOException e) {
			System.out.println("Eccezione durante un'operazione di Output artista");
			}
	}
	/**
	 * Procedura che carica da file di testo la libreria degli artisti
	 */
	public void caricaLibreriaFileTxt(){
		BufferedReader reader = null; // il reader che userò sul file
		try {
			reader = new BufferedReader(new FileReader (Config.getIndLibreria()+"LibreriaArtisti.txt"));			
			String Tmp;
			Artist_MusicArtist Artista = null;
			while ((Tmp = reader.readLine()) != null) {	//Finchè non è nulla continua la lettura!
					
				if(Tmp.equals("SoloMusicArtist")){
				Artista=new Artist_SoloMusicArtist();
				
				((Artist_SoloMusicArtist) Artista).setNomeDArte(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setNome(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setCognome(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setNazionalità(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setUrlWeb(reader.readLine());	
				((Artist_SoloMusicArtist) Artista).setDataEsordio(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setDataNascita(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setDataMorte(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setCasaDiscografica(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setDiscografiaAlbum(reader.readLine());
				((Artist_SoloMusicArtist) Artista).setGeneriMusicaliPreferiti(reader.readLine());
				String S_Bio = "";
				String S_Tmp = reader.readLine();
				if( S_Tmp.equals("BOB") ){
					S_Tmp = reader.readLine();
					while( !(S_Tmp.equals("EOB")) ){
						S_Bio=new String(S_Bio + S_Tmp+"\n");
						S_Tmp = reader.readLine();
					}
				}
				((Artist_SoloMusicArtist) Artista).setBiografia( S_Bio );
				
				this.addElement(Artista);
				} else {
				
				if(Tmp.equals("MusicGroup")){
				String NumCompStr= reader.readLine();
				int NumeroComponenti = Integer.parseInt(NumCompStr);
				Artista=new Artist_MusicGroup(NumeroComponenti);
				
				((Artist_MusicGroup) Artista).setNomeDArte(reader.readLine());
				((Artist_MusicGroup) Artista).setDataFormazione(reader.readLine());
				((Artist_MusicGroup) Artista).setDataScioglimento(reader.readLine());
				((Artist_MusicGroup) Artista).setUrlWeb(reader.readLine());
				((Artist_MusicGroup) Artista).setDiscografiaAlbum(reader.readLine());
				((Artist_MusicGroup) Artista).setCasaDiscografica(reader.readLine());
				((Artist_MusicGroup) Artista).setNumComponenti(NumCompStr);
				
				String S_BioG = "";
				String S_TmpG = reader.readLine();
				if( S_TmpG.equals("BOB") ){
					S_TmpG = reader.readLine();
					while( !(S_TmpG.equals("EOB")) ){
						S_BioG=new String(S_BioG + S_TmpG+"\n");
						S_TmpG = reader.readLine();
					}
				}
				((Artist_MusicGroup) Artista).setBiografia( S_BioG );
				
				
				for(int i=0;i<NumeroComponenti;i++){
					Artist_SoloMusicArtist ASMA = ((Artist_MusicGroup) Artista).getComponenteAtPos(i);
					ASMA.setNomeDArte(reader.readLine());
					ASMA.setNome(reader.readLine());
					ASMA.setCognome(reader.readLine());
					ASMA.setNazionalità(reader.readLine());
					ASMA.setUrlWeb(reader.readLine());	
					ASMA.setDataEsordio(reader.readLine());
					ASMA.setDataNascita(reader.readLine());
					ASMA.setDataMorte(reader.readLine());
					ASMA.setCasaDiscografica(reader.readLine());
					ASMA.setDiscografiaAlbum(reader.readLine());
					ASMA.setGeneriMusicaliPreferiti(reader.readLine());
					
					String S_Bio = "";
					String S_Tmp = reader.readLine();
					if( S_Tmp.equals("BOB") ){
						S_Tmp = reader.readLine();
						while( !(S_Tmp.equals("EOB")) ){
							S_Bio=new String(S_Bio + S_Tmp+"\n");
							S_Tmp = reader.readLine();
						}
					}
					ASMA.setBiografia( S_Bio );
				}
				

				this.addElement(Artista);
				}}
				
				
			
				}
			}
		catch (FileNotFoundException e) {System.out.println("File non trovato");}
		catch (IOException e) {System.out.println("IO EXCE");}
	}
		
		
}


