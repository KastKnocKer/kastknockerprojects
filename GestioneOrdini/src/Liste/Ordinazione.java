package Liste;

public class Ordinazione {
	
	private String IDOrdinazione;
	private String OrdineData;
	private String Quantita;
	private String CodiceProdotto;
	private String IDCliente;
	private String Annotazioni;
	
	public Ordinazione(){
		super();
	}

	public void setOrdineData(String ordineData) {
		OrdineData = ordineData;
	}

	public String getOrdineData() {
		return OrdineData;
	}

	public void setQuantita(String quantita) {
		Quantita = quantita;
	}

	public String getQuantita() {
		return Quantita;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		CodiceProdotto = codiceProdotto;
	}

	public String getCodiceProdotto() {
		return CodiceProdotto;
	}

	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}

	public String getIDCliente() {
		return IDCliente;
	}

	public void setIDOrdinazione(String iDOrdinazione) {
		IDOrdinazione = iDOrdinazione;
	}

	public String getIDOrdinazione() {
		return IDOrdinazione;
	}

	public void setAnnotazioni(String annotazioni) {
		Annotazioni = annotazioni;
	}

	public String getAnnotazioni() {
		return Annotazioni;
	}
	
	

}
