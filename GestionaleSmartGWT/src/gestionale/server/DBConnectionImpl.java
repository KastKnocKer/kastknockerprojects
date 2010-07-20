package gestionale.server;

import gestionale.client.DBConnection;
import gestionale.shared.Contatto;
import gestionale.shared.Ordine;
import gestionale.shared.User;
import java.sql.*;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBConnectionImpl extends RemoteServiceServlet implements DBConnection {

private MySQLAccess db = null;
private String status;
private String url = "jdbc:mysql://localhost:3306/db_gestionale";
private String user = "programma";
private String pass = "programma";


public DBConnectionImpl() {
	
	//Connessione al database
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
			
			
		}
		
	}catch(Exception e){
		
	}
	
	return ordinearray;
}


////////////////////////////////////////////////////////////////////////////////////
// Funzione generica per eseguire le query
//////////////////////////////

public String[][] eseguiQuery(String query) {
	db.connetti();
	return db.eseguiQueryStringArray(query);
}

public boolean eseguiUpdate(String query) {
	db.connetti();
	System.out.println("SERVER:  "+query);
	
	return db.eseguiAggiornamento(query);
}












}