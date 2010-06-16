package gestionale.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Contatto implements IsSerializable {
	
	private String RagioneSociale;
	private String Citta;
	
	public Contatto(){
		super();
		
		
	}

	public void setRagioneSociale(String ragioneSociale) {
		RagioneSociale = ragioneSociale;
	}

	public String getRagioneSociale() {
		return RagioneSociale;
	}

	public void setCitta(String citta) {
		Citta = citta;
	}

	public String getCitta() {
		return Citta;
	}

}
