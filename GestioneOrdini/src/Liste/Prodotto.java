package Liste;

public class Prodotto {
	public static String nullString = new String("");
	public static String Donna = new String("Donna");
	public static String Uomo = new String("Uomo");
	public static String Bambino = new String("Bambino");
	public static String Casa = new String("Casa");
	public static String Varie = new String("Varie");
	
	
	private String Nome;
	private String Produttore;
	private String Codice;
	private String Categoria;
	private String Tipo;
	private String Punti;
	private String PrezzoCliente;
	private String PrezzoFornitore;
	private String Contenuto;
	
	public Prodotto(){
		super();
		Nome=nullString;
		Produttore=nullString;
		Codice=nullString;
		Categoria=nullString;
		Tipo=nullString;
		Punti=nullString;
		PrezzoCliente=nullString;
		PrezzoFornitore=nullString;
		Contenuto=nullString;
	}
	
	/*
	 * Funzioni di ritorno
	 */
	public String getNome(){
		return this.Nome;
	}
	
	public String getProduttore(){
		return this.Produttore;
	}
	
	public String getCodice(){
		return this.Codice;
	}
	
	public String getCategoria(){
		return this.Categoria;
	}
	
	public String getTipo(){
		return this.Tipo;
	}
	
	public String getPunti(){
		return this.Punti;
	}
	
	public String getPrezzoCliente(){
		return this.PrezzoCliente;
	}
	
	public String getPrezzoFornitore(){
		return this.PrezzoFornitore;
	}
	
	public String getContenuto(){
		return this.Contenuto;
	}
	
	public int getIntegerPrezzoCliente(){
		return Integer.parseInt(this.PrezzoCliente);
	}
	
	public int getIntegerPrezzoFornitore(){
		return Integer.parseInt(this.PrezzoFornitore);
	}
	
	public int getIntegerContenuto(){
		return Integer.parseInt(this.Contenuto);
	}
	
	/*
	 * Funzioni di settaggio
	 */
	
	public void setNome(String Nome){
		this.Nome = Nome;
	}
	
	public void setProduttore(String Produttore){
		this.Produttore = Produttore;
	}
	
	public void setCodice(String Codice){
		this.Codice = Codice;
	}
	
	public void setCategoria(String Categoria){
		this.Categoria = Categoria;
	}
	
	public void setTipo(String Tipo){
		this.Tipo = Tipo;
	}
	
	public void setPunti(String Punti){
		this.Punti = Punti;
	}
	
	public void setPrezzoCliente(String PrezzoCliente){
		this.PrezzoCliente = PrezzoCliente;
	}
	
	public void setPrezzoFornitore(String PrezzoFornitore){
		this.PrezzoFornitore = PrezzoFornitore;
	}
	
	public void setContenuto(String Contenuto){
		this.Contenuto = Contenuto;
	}
	
	

}
