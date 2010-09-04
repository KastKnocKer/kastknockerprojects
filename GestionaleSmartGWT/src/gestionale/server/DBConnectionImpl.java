package gestionale.server;

import gestionale.client.DBConnection;
import gestionale.shared.Contatto;
import gestionale.shared.DettaglioOrdine;
import gestionale.shared.Imballaggio;
import gestionale.shared.Ordine;
import gestionale.shared.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.itextpdf.text.DocumentException;

public class DBConnectionImpl extends RemoteServiceServlet implements DBConnection {

private MySQLAccess db = null;
private String status;
private String url = "jdbc:mysql://localhost:3306/db_gestionale";
private String user = "programma";
private String pass = "programma";


public DBConnectionImpl() {
	
	//Imposta Connessione al database
	db = new MySQLAccess("db_gestionale", "programma", "programma");
	db.setPublicHost("localhost");
	db.connetti();
	System.out.println(db.getErrore());
	System.out.println("Connesso: " + db.isConnesso());
	
}


public User authenticateUser(User utente) {
	db.connetti();
	String query = "SELECT COUNT(*) FROM utente WHERE Username='"+ utente.getUsername() +"' AND Password='"+ utente.getPassword()+"'";
	Vector v = db.eseguiQuery(query);
	
	String[] record = (String[]) v.get(0);

	User user = null;
	
	if(Integer.parseInt(record[0])==1) user = new User();
	db.disconnetti();
	return user;
	}


public Contatto[] eseguiQueryContatto(String query) {
	db.connetti();
	Contatto[] contattoarray = null;
	
	Vector v = db.eseguiQuery(query);
	
	String[] record = null;
	
	try{
		contattoarray = new Contatto[v.size()];
		
		for(int i=0; i<v.size();i++){
			record = (String[]) v.get(i);
			
			contattoarray[i] = new Contatto();
			
			contattoarray[i].setID(record[0]);
			contattoarray[i].setRagioneSociale(record[1]);
			contattoarray[i].setPrecisazione(record[2]);
			contattoarray[i].setPIVA(record[3]);
			contattoarray[i].setLogo(record[4]);
			contattoarray[i].setIndirizzo(record[5]);
			contattoarray[i].setTelefono(record[6]);
			contattoarray[i].setCellulare(record[7]);
			contattoarray[i].setFax(record[8]);
			contattoarray[i].seteMail(record[9]);
			contattoarray[i].setSitoWeb(record[10]);
			contattoarray[i].setTipoSoggetto(record[11]);
			contattoarray[i].setProvvigione(record[12]);
			contattoarray[i].setNote(record[13]);
			
		}
		
	}catch(Exception e){
		
	}
	
	

	db.disconnetti();
	return contattoarray;
}


public Ordine[] eseguiQueryOrdine(String query) {
	db.connetti();
	Ordine[] ordinearray = null;
	
	Vector v = db.eseguiQuery(query);
	
	String[] record = null;
	
	try{
		ordinearray = new Ordine[v.size()];
		
		for(int i=0; i<v.size();i++){
			record = (String[]) v.get(i);
			
			ordinearray[i] = new Ordine();
			
			ordinearray[i].setID(record[0]);
			ordinearray[i].setDataCreazioneOrdine(record[1]);
			ordinearray[i].setDataInvioOrdine(record[2]);
			ordinearray[i].setDataPartenzaMerce(record[3]);
			ordinearray[i].setNote(record[4]);
			ordinearray[i].setIDTrasportatore(record[5]);
			ordinearray[i].setConvalidato(record[6]);
			ordinearray[i].setTipoOrdine(record[7]);
			ordinearray[i].setIDFornitore(record[8]);
			
			
		}
		
	}catch(Exception e){
		
	}
	
	db.disconnetti();
	return ordinearray;
}

public DettaglioOrdine[] eseguiQueryDettaglioOrdine(String query) {
	db.connetti();
	DettaglioOrdine[] dettaglioordinearray = null;
	
	Vector<String[]> v = db.eseguiQuery(query);
	
	String[] record = null;
	
	try{
		dettaglioordinearray = new DettaglioOrdine[v.size()];
		
		for(int i=0; i<v.size();i++){
			record = v.get(i);
			
			dettaglioordinearray[i] = new DettaglioOrdine();
			
			dettaglioordinearray[i].setId(record[0]);
			dettaglioordinearray[i].setId_Ordine(record[1]);
			dettaglioordinearray[i].setId_Prodotto(record[2]);
			dettaglioordinearray[i].setId_Cliente(record[3]);
			dettaglioordinearray[i].setId_Fornitore(record[4]);
			dettaglioordinearray[i].setId_Trasportatore(record[5]);
			dettaglioordinearray[i].setId_Imballaggio(record[6]);
			dettaglioordinearray[i].setUtente(record[7]);
			dettaglioordinearray[i].setQuantita(record[8]);
		}
		
	}catch(Exception e){
		
	}
	db.disconnetti();
	return dettaglioordinearray;
}

@Override
public Imballaggio[] eseguiQueryImballaggio(String query) {
	db.connetti();
	System.out.println("Eseguo query: " + query);
	Imballaggio[] imballaggioarray = null;
	
	Vector<String[]> v = db.eseguiQuery(query);
	
	String[] record = null;
	
	try{
		imballaggioarray = new Imballaggio[v.size()];
		
		for(int i=0; i<v.size();i++){
			record = v.get(i);
			
			imballaggioarray[i] = new Imballaggio();
			
			imballaggioarray[i].setID(record[0]);
			imballaggioarray[i].setDescrizione(record[1]);
			imballaggioarray[i].setLunghezza(record[2]);
			imballaggioarray[i].setLarghezza(record[3]);
			imballaggioarray[i].setAltezza(record[4]);
			imballaggioarray[i].setTara(record[5]);
			imballaggioarray[i].setMateriale(record[6]);
			imballaggioarray[i].setMarchio(record[7]);
			imballaggioarray[i].setIsSelezionato(record[8]);
			
		}
		
	}catch(Exception e){
		
	}
	db.disconnetti();
	return imballaggioarray;
}


//////////////////////////////////////////////////////////////////////////////////////
// Funzioni generiche per eseguire le query direttamente dall'applicazione client  //
////////////////////////////////////////////////////////////////////////////////////

public String[][] eseguiQuery(String query) {
	db.connetti();
	System.out.println("SERVER Query:  "+query);
	return db.eseguiQueryStringArray(query);
}

public boolean eseguiUpdate(String query) {
	db.connetti();
	System.out.println("SERVER QueryUpdate:  "+query);
	return db.eseguiAggiornamento(query);
}


//Chiamata per la creazione dei pdf
public boolean eseguiCreazioneDocumentiOrdine(String IDOrdine) {


	return false;
}





















}