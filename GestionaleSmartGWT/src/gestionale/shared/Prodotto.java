package gestionale.shared;

public class Prodotto {
	
	private String ID;
	private String Categoria;
	private String Tipologia;
	private String Varieta;
	private String SottoVarieta;
	private String Calibro;
	private String Imballaggio;
	
	public Prodotto(){
		super();
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setTipologia(String tipologia) {
		Tipologia = tipologia;
	}

	public String getTipologia() {
		return Tipologia;
	}

	public void setVarieta(String varieta) {
		Varieta = varieta;
	}

	public String getVarieta() {
		return Varieta;
	}

	public void setSottoVarieta(String sottoVarieta) {
		SottoVarieta = sottoVarieta;
	}

	public String getSottoVarieta() {
		return SottoVarieta;
	}

	public void setCalibro(String calibro) {
		Calibro = calibro;
	}

	public String getCalibro() {
		return Calibro;
	}

	public void setImballaggio(String imballaggio) {
		Imballaggio = imballaggio;
	}

	public String getImballaggio() {
		return Imballaggio;
	}
	

}
