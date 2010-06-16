package gestionale.server;

import gestionale.client.DBConnection;
import gestionale.shared.Contatto;
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
	
	String query = "SELECT COUNT(*) FROM utente WHERE Username='"+ utente.getUsername() +"' AND Password='"+ utente.getPassword()+"'";
	Vector v = db.eseguiQuery(query);
	
	String[] record = (String[]) v.get(0);

	User user = null;
	
	if(Integer.parseInt(record[0])==1) user = new User();
	
	return user;
	}

@Override
public Contatto[] getContatto(String query) {
	
	Contatto[] contattoarray = null;
	
	Vector v = db.eseguiQuery(query);
	String[] record = null;
	
	try{
		contattoarray = new Contatto[v.size()];
		
		for(int i=0; i<v.size();i++){
			record = (String[]) v.get(i);
			
			contattoarray[i] = new Contatto();			
			contattoarray[i].setCitta(record[0]);
			contattoarray[i].setRagioneSociale(record[1]);
		}
		
	}catch(Exception e){
		
	}
	
	


	return contattoarray;
}






}