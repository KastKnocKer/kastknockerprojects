package GestioneArtisti;

	/**
	 * Interfaccia che definisce i metodi che gestiscono i dati anagrafici di
	 * una persona
	 */
	public interface Artist_Person{

		/**
		 * Restituisce il nome della Persona
		 * 
		 * @return Nome
		 */
		public String getNome();
		
		/**
		 * Restituisce il cognome della Persona
		 * 
		 * @return Cognome
		 */
		public String getCognome();
		
		/**
		 * Restituisce la data di nascita della Persona
		 * 
		 * @return DataNascita
		 */
		public String getDataNascita();
		
		/**
		 * Restituisce la data di morte della Persona
		 * 
		 * @return DataMorte
		 */
		public String getDataMorte();
		
		/**
		 * Restituisce la nazionalit�della Persona
		 * 
		 * @return Nazionalit�
		 */
		public String getNazionalit�();	
		

		
		/**
		 * Imposta il nome della persona secondo il parametro passato
		 * 
		 * @param  Nome
		 */
		public void setNome(String nome);
		
		/**
		 * Imposta il cognome della persona secondo il parametro passato
		 * 
		 * @param Cognome
		 */
		public void setCognome(String cognome);
		
		/**
		 * Imposta la data di nascita della persona secondo il parametro passato
		 * 
		 * @param DataNascita
		 */
		public void setDataNascita(String dataNascita);
		
		/**
		 * Imposta la data di morte della persona secondo il parametro passato
		 * 
		 * @param DataMorte
		 */
		public void setDataMorte(String dataMorte);
		
		/**
		 * Imposta la nazionalit� della persona secondo il parametro passato
		 * 
		 * @param  Nazionalit�
		 */
		public void setNazionalit�(String Nazionalit�);	
}
	
