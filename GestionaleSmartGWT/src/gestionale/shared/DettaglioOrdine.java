package gestionale.shared;

public class DettaglioOrdine {
	
	private String Id,Id_Ordine,Id_Prodotto,Id_Cliente,Id_Imballaggio,Id_Fornitore,Id_Trasportatore,quantita,utente;
	
	public DettaglioOrdine(){
		super();
	}

	public void setId_Ordine(String id_Ordine) {
		Id_Ordine = id_Ordine;
	}

	public String getId_Ordine() {
		return Id_Ordine;
	}

	public void setId_Cliente(String id_Cliente) {
		Id_Cliente = id_Cliente;
	}

	public String getId_Cliente() {
		return Id_Cliente;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getId() {
		return Id;
	}

	public void setId_Fornitore(String id_Fornitore) {
		Id_Fornitore = id_Fornitore;
	}

	public String getId_Fornitore() {
		return Id_Fornitore;
	}

	public void setId_Prodotto(String id_Prodotto) {
		Id_Prodotto = id_Prodotto;
	}

	public String getId_Prodotto() {
		return Id_Prodotto;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getUtente() {
		return utente;
	}

	public void setId_Imballaggio(String id_Imballaggio) {
		Id_Imballaggio = id_Imballaggio;
	}

	public String getId_Imballaggio() {
		return Id_Imballaggio;
	}

	public void setId_Trasportatore(String id_Trasportatore) {
		Id_Trasportatore = id_Trasportatore;
	}

	public String getId_Trasportatore() {
		return Id_Trasportatore;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}

	public String getQuantita() {
		return quantita;
	}

}
