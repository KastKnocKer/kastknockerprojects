package GestioneArtisti;

/** 
 * Classe che rappresenta un generico artista musicale e che implementa i metodi di un genico artista
 * 
 * @author Castelli Andrea 
 * */

public class Artist_MusicArtist implements Artist{
	
	public static final String Sconosciuto="";
	private String DataEsordio=null;
	private String Biografia=null;
	private String NomeDArte=null;
	
	private String GeneriMusicaliPreferiti=null;
	private String DiscografiaAlbum=null;
	private String UrlWeb=null;
	private String CasaDiscografica=null;
	
	private String TipoArtista=null;
	
	/**
	 * Costruttore
	 */
	public Artist_MusicArtist(){
		this.DataEsordio=Sconosciuto;
		this.Biografia=Sconosciuto;
		this.NomeDArte=Sconosciuto;
		this.GeneriMusicaliPreferiti=Sconosciuto;
		this.DiscografiaAlbum=Sconosciuto;
		this.UrlWeb=Sconosciuto;
		this.CasaDiscografica=Sconosciuto;
		this.TipoArtista="MusicArtist";
	}
	/**
	 * Ritorna il tipo di artista, per riconoscere la classe di artista utilizzata
	 * @return TipoArtista
	 */
	public String getTipoArtista() {return TipoArtista;}
	/**
	 * Setta il tipo di artista
	 * @param TipoArtista
	 */
	public void setTipoArtista(String TipoArtista) {this.TipoArtista=TipoArtista;}
	/**
	 * Ritorna la biografia
	 * @return Biografia
	 */
	public String getBiografia() {return Biografia;}
	/**
	 * Ritorna la data d'esordio
	 * @return DataEsordio
	 */
	public String getDataEsordio() {return DataEsordio;}
	/**
	 * Ritorna il nome d'arte
	 * @return NomeDArte
	 */
	public String getNomeDArte() {return NomeDArte;}
	/**
	 * Ritorna i generi musicali preferiti
	 * @return GeneriMusicaliPreferiti
	 */
	public String getGeneriMusicaliPreferiti() {return GeneriMusicaliPreferiti;}
	/**
	 * Ritorna la discografia
	 * @return DiscografiaAlbum
	 */
	public String getDiscografiaAlbum() {return DiscografiaAlbum;}
	/**
	 * Ritorna l'indirizzo web
	 * @return UrlWeb
	 */
	public String getUrlWeb() {return UrlWeb;}
	/**
	 * Ritorna la casa discografica
	 * @return CasaDiscografica
	 */
	public String getCasaDiscografica() {return CasaDiscografica;}
	/**
	 * Setta la biografia
	 * @param Biografia
	 */
	public void setBiografia(String Biografia) {this.Biografia=Biografia;}
	/**
	 * Setta la data d'esordio
	 * @param DataEsordio
	 */
	public void setDataEsordio(String DataEsordio) {this.DataEsordio=DataEsordio;}
	/**
	 * Setta il nome d'arte
	 * @param
	 */
	public void setNomeDArte(String NomeDArte) {this.NomeDArte=NomeDArte;}
	/**
	 * Setta i generi musicali preferiti
	 * @param GeneriMusicaliPreferiti
	 */
	public void setGeneriMusicaliPreferiti(String GeneriMusicaliPreferiti) {this.GeneriMusicaliPreferiti=GeneriMusicaliPreferiti;}
	/**
	 * Setta la discografia
	 * @param DiscografiaAlbum
	 */
	public void setDiscografiaAlbum(String DiscografiaAlbum) {this.DiscografiaAlbum=DiscografiaAlbum;}
	/**
	 * Setta l'indirizzo web
	 * @param UrlWeb
	 */
	public void setUrlWeb(String UrlWeb) {this.UrlWeb=UrlWeb;}
	/**
	 * Setta la casa discografica
	 * @param CasaDiscografica
	 */
	public void setCasaDiscografica(String CasaDiscografica) {this.CasaDiscografica=CasaDiscografica;}
	
	
}
