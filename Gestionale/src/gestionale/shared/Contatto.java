package gestionale.shared;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class Contatto implements IsSerializable {
	
	private String ID;
	private String RagioneSociale;
	private String Citta;
	private String CAP;
	private String Indirizzo;
	private String Provincia;
	private String Nazione;
	private String TipoSoggetto;
	
	
	
	
	public Contatto(){
		super();	
	}




	public void setID(String iD) {
		ID = iD;
	}




	public String getID() {
		return ID;
	}




	public void setRagioneSociale(String ragioneSociale) {
		RagioneSociale = ragioneSociale;
	}




	public String getRagioneSociale() {
		return RagioneSociale;
	}
	
	
	
	
	
}
