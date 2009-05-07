package GestioneArtisti;

/** 
 * Interfaccia che definisce i metodi che agiscono sui dati dei gruppi musicali
 * 
 * @author Castelli Andrea 
 * */

public interface Artist_Group {
	/**
	 * Restituisce il numero dei componenti del gruppo
	 * @return NumComponenti
	 */
	public String getNumComponenti();
	/**
	 * Setta il numero dei componenti del gruppo musicale
	 * @param NumComponenti
	 */ 
	public void setNumComponenti(String NumComponenti);
	/**
	 * Restituisce la data della formazione del gruppo musicale
	 * @return DataFormazione
	 */
	public String getDataFormazione();
	/**
	 * Setta la data di formazione al gruppo musicale
	 * @param DataFormazione
	 */
	public void setDataFormazione(String DataFormazione);
	/**
	 * Restituisce la data dello scioglimento del gruppo musicale
	 * @return DataScioglimento
	 */
	public String getDataScioglimento();
	/**
	 * Setta la data di scioglimento del gruppo musicale
	 * @param DataScioglimento
	 */
	public void setDataScioglimento(String DataScioglimento);

}
