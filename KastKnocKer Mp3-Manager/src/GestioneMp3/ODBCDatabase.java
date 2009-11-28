package GestioneMp3;
/*
 * Classe dedicata alla gestione del Database.
 * Gestisce l'apertura e la chiusura della connessione col Database
 * Fornisce i metodi per l'esecuzione delle query sul Database
 */
import java.sql.*;
import java.util.Vector;

public class ODBCDatabase {

   private String nomeDSN;         // Nome DSN dell'origine dati ODBC
   private String[][] attributi;   // Insieme di attributi da usare per la connessione
   private String errore;          // Stringa contenente un eventuale messaggio di errore
   private Connection db;          // Oggetto che rappresenta la connessione col DB
   private boolean connesso;       // Flag che indica se il DB è connesso o meno

   public ODBCDatabase(String nomeDSN) {
      this.nomeDSN = nomeDSN;
      attributi = new String[0][0];
      connesso = false;
   }

   public ODBCDatabase(String nomeDSN, String [][] attributi) {
      this.nomeDSN = nomeDSN;
      this.attributi = attributi;
      connesso = false;
   }

   public boolean connetti() {
      connesso = false;
      try {

         // Carico il bridge JDBC-ODBC per la connessione con il database
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

         String conString = "jdbc:odbc:" + nomeDSN;   // Il nome del DSN può anche essere vuoto!!

         // Controllo se ci sono attributi ausiliari da usare per la connessione
         if (attributi.length > 0) {

            // Uso gli attributi per la connessione
            for (int i=0; i<attributi.length; i++) {
               conString += ";" + attributi[i][0] + "=" + attributi[i][1];
            }
         }

         // Effettuo la connessione
         db = DriverManager.getConnection( conString );

         // La connessione è avvenuta con successo
         connesso = true;
      } catch (Exception e) { errore = e.getMessage(); }

      return connesso;
   }

   // Esegue una query di selezione dati sul Database
   // query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
   // colonne: il numero di colonne di cui sarà composta la tupla del risultato
   // ritorna un Vector contenente tutte le tuple del risultato
   public Vector eseguiQuery(String query) {
      Vector v = null;
      String [] record;
      int colonne = 0;
      try {
         Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
         ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
         v = new Vector();
         ResultSetMetaData rsmd = rs.getMetaData();
         colonne = rsmd.getColumnCount();

         while(rs.next()) {   // Creo il vettore risultato scorrendo tutto il ResultSet
            record = new String[colonne];
            for (int i=0; i<colonne; i++) record[i] = rs.getString(i+1);
            v.add( (String[]) record.clone() );
         }

         rs.close();     // Chiudo il ResultSet
         stmt.close();   // Chiudo lo Statement
      } catch (Exception e) { e.printStackTrace(); errore = e.getMessage(); }

      return v;
   }

   // Esegue una query di aggiornamento sul Database
   // query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
   // ritorna TRUE se l'esecuzione è adata a buon fine, FALSE se c'è stata un'eccezione
   public boolean eseguiAggiornamento(String query) {
      int numero = 0;
      boolean risultato = false;
      try {
         Statement stmt = db.createStatement();
         numero = stmt.executeUpdate(query);
         risultato = true;
         stmt.close();
      } catch (Exception e) {
         e.printStackTrace();
         errore = e.getMessage();
         risultato = false;
      }
      return risultato;
   }

   // Chiude la connessione con il Database
   public void disconnetti() {
      try {
         db.close();
         connesso = false;
      } catch (Exception e) { e.printStackTrace(); }
   }

   public boolean isConnesso() { return connesso; }   // Ritorna TRUE se la connessione con il Database è attiva
   public String getErrore() { return errore; }       // Ritorna il messaggio d'errore dell'ultima eccezione sollevata
}