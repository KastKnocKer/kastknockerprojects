package GestioneArtisti;

/** 
 * Classe che definisce l'artista musicale solista ed estende la classe del generico artista musicale
 * ed implementa i metodi dell'interfaccia riguardante i metodi della persona fisica.
 * 
 * @author Castelli Andrea 
 **/

public class Artist_SoloMusicArtist extends Artist_MusicArtist implements Artist_Person{

	private static final String Sconosciuto="";
	private String Nome;
	private String Cognome;
	private String DataMorte;
	private String DataNascita;
	private String NazionalitÓ;
	
	/**
	 * Costruttore dell'artista musicale solista
	 */
	public Artist_SoloMusicArtist(){
		super();
		this.setTipoArtista("SoloMusicArtist");
		this.Nome=Sconosciuto;
		this.Cognome=Sconosciuto;
		this.DataMorte=Sconosciuto;
		this.DataNascita=Sconosciuto;
		this.NazionalitÓ=Sconosciuto;
		
	}
	/**
	 * Ritorna il cognome del musicista
	 * @return Cognome
	 */
	public String getCognome() {return Cognome;}
	/**
	 * Ritorna la data di morte del musicista
	 * @return DataMorte
	 */
	public String getDataMorte() {return DataMorte;}
	/**
	 * Ritorna la data di nascita del musicista
	 * @return DataNascita
	 */
	public String getDataNascita() {return DataNascita;}
	/**
	 * Ritorna la nazionalitÓ del musicista
	 * @return NazionalitÓ
	 */
	public String getNazionalitÓ() {return NazionalitÓ;}
	/**
	 * Ritorna il nome del musicista
	 * @return Nome
	 */
	public String getNome() {return Nome;}

	/**
	 * Setta il cognome del musicista
	 * @param Cognome
	 */
	public void setCognome(String Cognome) {this.Cognome=Cognome;}
	/**
	 * Setta la data di morte del musicista
	 * @param DataMorte
	 */
	public void setDataMorte(String DataMorte) {this.DataMorte=DataMorte;}
	/**
	 * Setta la data di nascita del musicista
	 * @param DataNascita
	 */
	public void setDataNascita(String DataNascita) {this.DataNascita=DataNascita;}
	/**
	 * Setta la nazionalitÓ del musicista
	 * @param NazionalitÓ
	 */
	public void setNazionalitÓ(String NazionalitÓ) {this.NazionalitÓ=NazionalitÓ;}
	/**
	 * Setta il nome del musicista
	 * @param Nome
	 */
	public void setNome(String Nome) {this.Nome=Nome;}



	


}
