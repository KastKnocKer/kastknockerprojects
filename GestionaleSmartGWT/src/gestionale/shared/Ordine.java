package gestionale.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Ordine implements IsSerializable{
	
	private String ID;
	private String DataCreazioneOrdine;
	private String DataInvioOrdine;
	private String DataPartenzaMerce;
	private String Note;
	private String IDTrasportatore;
	private String IDFornitore;
	private String Convalidato;
	private String TipoOrdine;
	
	
	public void setID(String iD) {
		ID = iD;
	}
	public String getID() {
		return ID;
	}
	public void setDataCreazioneOrdine(String dataCreazioneOrdine) {
		DataCreazioneOrdine = dataCreazioneOrdine;
	}
	public String getDataCreazioneOrdine() {
		return DataCreazioneOrdine;
	}
	public void setDataInvioOrdine(String dataInvioOrdine) {
		DataInvioOrdine = dataInvioOrdine;
	}
	public String getDataInvioOrdine() {
		return DataInvioOrdine;
	}
	public void setDataPartenzaMerce(String dataPartenzaMerce) {
		DataPartenzaMerce = dataPartenzaMerce;
	}
	public String getDataPartenzaMerce() {
		return DataPartenzaMerce;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getNote() {
		return Note;
	}
	public void setIDTrasportatore(String iDTrasportatore) {
		IDTrasportatore = iDTrasportatore;
	}
	public String getIDTrasportatore() {
		return IDTrasportatore;
	}
	public void setConvalidato(String convalidato) {
		Convalidato = convalidato;
	}
	public String getConvalidato() {
		return Convalidato;
	}
	public void setTipoOrdine(String tipoOrdine) {
		TipoOrdine = tipoOrdine;
	}
	public String getTipoOrdine() {
		return TipoOrdine;
	}
	public void setIDFornitore(String iDFornitore) {
		IDFornitore = iDFornitore;
	}
	public String getIDFornitore() {
		return IDFornitore;
	}
	

}
