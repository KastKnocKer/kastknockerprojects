package Liste;
import java.util.Vector;

public class ListaClienti extends Vector<Cliente>{
	
	public static ListaClienti LinkListaClienti;
	
	public ListaClienti(){
		super();
		LinkListaClienti = this;
		this.CaricaDaDatabase();
	}
	
	public void Aggiorna(){
		this.removeAllElements();
		this.CaricaDaDatabase();
	}
	
	public void CaricaDaDatabase(){
		Vector V = Database.eseguiQuery("SELECT * FROM Cliente_Query;");
		Cliente cliente = null;
		
		for(int i=0;i<V.size();i++){
			String [] record = (String[]) V.elementAt(i);
			cliente = new Cliente();
			
			cliente.setIDCliente(record[0]);
			cliente.setNome(record[2]);
			cliente.setCognome(record[1]);
			cliente.setTelefono(record[3]);
			cliente.setCellulare(record[4]);
			
			cliente.setIndirizzo(record[5]);
			cliente.setCap(record[6]);
			cliente.setCitta(record[7]);
			cliente.setNote(record[8]);
			this.add(cliente);
		}
	}
	
	public String getID(String Cognome, String Nome){
		Cliente cliente;
		for(int i=0; i<ListaClienti.LinkListaClienti.size(); i++){
			cliente=ListaClienti.LinkListaClienti.get(i);
			
			if( cliente.getNome().equals(Nome) && cliente.getCognome().equals(Cognome)){
				return cliente.getIDCliente();
			}
			
			
		}
		return "";
		
		
	}
	
	public void Contains(){
		
	}

	public static Cliente getClienteDaIDCliente(String cliente) {
		Cliente clientetmp;
		for(int i=0 ; i<ListaClienti.LinkListaClienti.size() ; i++){
			clientetmp = ListaClienti.LinkListaClienti.get(i);
			if(clientetmp.getIDCliente().equals(cliente)){
				return(clientetmp);
			}
		}
		return null;
	}
	
	public static String getIDClientedaNomeCognome(String cognome, String nome){
		Cliente cliente;
		for(int i=0; i<ListaClienti.LinkListaClienti.size();i++){
			cliente = ListaClienti.LinkListaClienti.get(i);
			if( cliente.getCognome().equals(cognome) && cliente.getNome().equals(nome) ){
				return cliente.getIDCliente();
			}
		}
		return null;
		
	}

}
