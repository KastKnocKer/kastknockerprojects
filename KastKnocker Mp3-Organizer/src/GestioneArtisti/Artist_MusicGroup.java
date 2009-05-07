package GestioneArtisti;

import java.util.Date;
import java.util.Vector;

import Archiviazione.Vettore;

/** 
 * Classe che definisce il gruppo musicale, estende il generico artista musicale ed implementa i metodi
 * dei gruppi musicali.
 * 
 * @author Castelli Andrea 
 * */

public class Artist_MusicGroup extends Artist_MusicArtist implements Artist_Group{
	
	public static final String Sconosciuto="";
	private String DataFormazione;
	private String DataScioglimento;
	private String NumComponenti;
	private Vettore Componenti;
	/**
	 * Costruttore del gruppo musicale
	 * @param NumComponenti
	 */
	public Artist_MusicGroup(int NumComponenti){
		super();
		this.setTipoArtista("MusicGroup");
		this.DataFormazione=Sconosciuto;
		this.DataScioglimento=Sconosciuto;
		this.NumComponenti=Integer.toString(NumComponenti);
		
		Componenti=new Vettore();
		for(int i=0;i<NumComponenti;i++){
			Artist_SoloMusicArtist NuovoArtista=new Artist_SoloMusicArtist();
			Componenti.add( NuovoArtista );
		}
		
		
	}
	/**
	 * Ritorna il componente (Artist_SoloMusicArtist) corrispondente al numero inserito (da 0 a numcomp-1)
	 * @param pos
	 * @return Artist_SoloMusicArtist
	 */
	public Artist_SoloMusicArtist getComponenteAtPos(int pos){return (Artist_SoloMusicArtist) Componenti.elementAt(pos);}
	/**
	 * Ritorna la Data di formazione del gruppo musicale
	 * @return DataFormazione
	 */
	public String getDataFormazione() {return DataFormazione;}
	/**
	 * Ritorna la data di scioglimento del gruppo musicale
	 * @return DataScioglimento
	 */
	public String getDataScioglimento() {return DataScioglimento;}
	/**
	 * Ritorna il numero di componenti del gruppo
	 * @return NumComponenti
	 */
	public String getNumComponenti() {return NumComponenti;}
	/**
	 * Setta la data di formazione del gruppo musicale
	 * @param DataFormazione
	 */
	public void setDataFormazione(String DataFormazione) {this.DataFormazione=DataFormazione;}
	/**
	 * Setta la data di scioglimento del gruppo musicale
	 * @param DataScioglimento
	 */
	public void setDataScioglimento(String DataScioglimento) {this.DataScioglimento=DataScioglimento;}
	/**
	 * Setta il numero di componenti (string)
	 * @param NumComponenti
	 */
	public void setNumComponenti(String NumComponenti) {this.NumComponenti=NumComponenti;}

}
