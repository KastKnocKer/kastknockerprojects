package gestionale.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Imballaggio implements IsSerializable{
	
	private String ID;
	private String Descrizione;
	private String Larghezza;
	private String Lunghezza;
	private String Altezza;
	private String Tara;
	private String Materiale;
	private String Marchio;
	private String isSelezionato;
	
	public Imballaggio(){
		
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setLarghezza(String larghezza) {
		Larghezza = larghezza;
	}

	public String getLarghezza() {
		return Larghezza;
	}

	public void setLunghezza(String lunghezza) {
		Lunghezza = lunghezza;
	}

	public String getLunghezza() {
		return Lunghezza;
	}

	public void setAltezza(String altezza) {
		Altezza = altezza;
	}

	public String getAltezza() {
		return Altezza;
	}

	public void setMateriale(String materiale) {
		Materiale = materiale;
	}

	public String getMateriale() {
		return Materiale;
	}

	public void setMarchio(String marchio) {
		Marchio = marchio;
	}

	public String getMarchio() {
		return Marchio;
	}

	public void setIsSelezionato(String isSelezionato) {
		this.isSelezionato = isSelezionato;
	}

	public String getIsSelezionato() {
		return isSelezionato;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setTara(String tara) {
		Tara = tara;
	}

	public String getTara() {
		return Tara;
	}

}
