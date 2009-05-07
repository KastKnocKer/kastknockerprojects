package Liste;

import java.util.Vector;

public class Database {
	
	private static ODBCDatabase db = null;
	
	public Database(){
		
	}
	
	public static void Connetti(){
		String [][] parametri = {{"DRIVER", "Microsoft Access Driver (*.mdb)"},
                {"DBQ", "DataBaseProdotti.mdb"}
               };
		db = new ODBCDatabase("", parametri);  // Nota: il nome DSN è vuoto!
		
		if ( !db.connetti() ) {
			System.out.println("Errore durante la connessione!");
			System.out.println( db.getErrore() );
			System.exit(0);
		}
		
	}
	
	public static void Disconnetti(){
		db.disconnetti();
	}
	
	public static Vector eseguiQuery(String query){
		return db.eseguiQuery(query);
	}
	
	public static boolean eseguiAggiornamento(String query){
		return db.eseguiAggiornamento(query);
	}
	
	
	
	

}
