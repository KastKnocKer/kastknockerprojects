package Controllo;

import java.awt.*;
import java.io.*;

/** 
 * Classe Config, serve per memorizzare le impostazioni di sistema
 * 
 * @author Castelli Andrea 
 * */

public class Config implements Serializable{
	
	///Esame Info C - Mp3-Organizer/Dati/Config.dat

	private static int RisoluzioneX;
	private static int RisoluzioneY;
	private static String IndLibreria="Dati/";
	private static String IndImmagini="Immagini/";
	private static String ConfPath="Dati/";
	
	/**
	 * Ritorna la directory della libreria
	 * @return
	 */
	public static String getIndLibreria(){return IndLibreria;}
	/**
	 * Ritorna la directory delle immagini
	 * @return String
	 */
	public static String getIndImmagini(){return IndImmagini;}
	/**
	 * ritorna la larghezza della risoluzione
	 * @return String
	 */
	public static int getRisX(){return RisoluzioneX;}
	/**
	 * ritorna l'altezza della risoluzione
	 * @return int
	 */
	public static int getRisY(){return RisoluzioneY;}
	/**
	 * Setta la risoluzione
	 * @param RisoluzioneX
	 * @param RisoluzioneY
	 */
	public static void setRis(int RisoluzioneX,int RisoluzioneY){ 
		Config.RisoluzioneX=RisoluzioneX;
		Config.RisoluzioneY=RisoluzioneY;
	}
	
	
	
	
	
	///////////////////////////////////////Salva e Carica
	
	public Config(){
		RisoluzioneX=1000;
		RisoluzioneY=750;
	}
	
	/**
	 * Salva le configurazioni su file
	 */
	public void SalvaConfig(){
		FileOutputStream fileConfOut = null;
		try{
			//fileConfOut=new FileOutputStream(ConfPath + "Config.dat");
			fileConfOut=new FileOutputStream(ConfPath+"Config.dat");
		}
		catch(IOException e){
			System.out.println("Errore di apertura file");
			System.exit(998);}
		
		ObjectOutputStream os=null;
		
		try {
			os=new ObjectOutputStream(fileConfOut);
			os.writeObject(new Config());
			os.flush();
			os.close();		
			
		} catch (IOException e1) {
			System.out.println("Errore in scrittura file");
			System.exit(999);
		}
		
	}
	/**
	 * Carica le configurazioni da file
	 * @return Config
	 */
	public Config CaricaConfig(){
		FileInputStream fileConfIn = null;
		ObjectInputStream is = null;
		try{
			fileConfIn=new FileInputStream(ConfPath+"Config.dat");
			is=new ObjectInputStream(fileConfIn);
		}
		catch(IOException e){
			System.out.println("File non trovato");
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			System.exit(998);}
		Config inCarica = null;
		try{
			try {
				inCarica = (Config) (is.readObject());
			} catch (ClassNotFoundException e) {
				System.out.println("Errore nella Deserializzazione Configurazioni");
				System.exit(999);
			}
			is.close();
			
		}catch (IOException e1) {
			System.out.println("Errore in lettura file");
			System.exit(999);
		}
		
		return inCarica;
		
	}
	
	
	


}
