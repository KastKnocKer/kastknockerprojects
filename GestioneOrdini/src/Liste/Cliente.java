package Liste;

public class Cliente {
	private static String StrVuota="";
	private String IDCliente=StrVuota;
	private String Nome=StrVuota;
	private String Cognome=StrVuota;
	private String Telefono=StrVuota;
	private String Cellulare=StrVuota;
	private String Note=StrVuota;
	private String Indirizzo=StrVuota;
	private String Cap=StrVuota;
	private String Citta=StrVuota;
	
	
	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}
	public String getIDCliente() {
		return IDCliente;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getNome() {
		return Nome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setCellulare(String cellulare) {
		Cellulare = cellulare;
	}
	public String getCellulare() {
		return Cellulare;
	}
	public void setCap(String cap) {
		Cap = cap;
	}
	public String getCap() {
		return Cap;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getNote() {
		return Note;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setCitta(String citta) {
		Citta = citta;
	}
	public String getCitta() {
		return Citta;
	}



}
