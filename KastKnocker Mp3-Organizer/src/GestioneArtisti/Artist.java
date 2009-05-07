package GestioneArtisti;

/** 
 * Interfaccia che definisce i metodi del generico artista
 * 
 * @author Castelli Andrea 
 **/

public interface Artist {
	
	/**
	 * Ritorna il nome d'arte
	 * @return NomeDArte
	 */
	public String getNomeDArte();
	/**
	 * Setta il nome d'arte
	 * @param NomeDArte
	 */
	public void setNomeDArte(String NomeDArte);
	/**
	 * Ritorna la biografia
	 * @return Biografia
	 */
	public String getBiografia();
	/**
	 * Setta la biografia
	 * @param Biografia
	 */
	public void setBiografia(String Biografia);
	/**
	 * Ritorna la data d'esordio
	 * @return DataEsordio
	 */
	public String getDataEsordio();
	/**
	 * Setta la data d'esordio
	 * @param DataEsordio
	 */
	public void setDataEsordio(String DataEsordio);


}
