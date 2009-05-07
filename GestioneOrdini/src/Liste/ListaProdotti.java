package Liste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;




public class ListaProdotti extends ListNode{
	
	public static ListaProdotti LinkListaProdotti;
	
	public ListaProdotti(){
		super();
		LinkListaProdotti=this;
		this.caricaProdottiDaDatabase();
	}
	
	public void insert(Prodotto o){
		if( this.contains(o) ) return;
		setNode(new Node(o,getNode()));
	}
	
	public boolean contains(Prodotto o){
		Node tmp=getNode();
		while(tmp!=null){
			if(  ((Prodotto) tmp.info).getCodice().equals(o.getCodice())) return true;
			tmp=tmp.next;
		}
		return false;
	}
	
	public void caricaProdottiDaDatabase(){
	
		Vector v = Database.eseguiQuery("SELECT * FROM CatalogoProdotti_Query;");
		Prodotto Oggetto = null;
				
				for(int i=0; i < v.size(); i++){
					String [] record = (String[]) v.elementAt(i);
					Oggetto = new Prodotto();
					
					Oggetto.setCodice(record[0]);
					Oggetto.setNome(record[1]);
					Oggetto.setProduttore(record[2]);
					Oggetto.setTipo(record[3]);
					Oggetto.setCategoria(record[4]);
					Oggetto.setPrezzoCliente(record[5]);
					Oggetto.setPrezzoFornitore(record[6]);
					Oggetto.setPunti(record[7]);
					Oggetto.setContenuto(record[8]);
					this.insert(Oggetto);
				}
	}
	
	public void caricaProdottiDaTXT(){
		BufferedReader reader  = null; // il reader che userò sul file
		try {
			reader = new BufferedReader(new FileReader ("DataBaseProdotti.txt"));			
			String Tmp;
			Prodotto Oggetto = null;
			while ((Tmp = reader.readLine()) != null) {	//Finchè non è nulla continua la lettura!
			Oggetto = new Prodotto();
			
			Oggetto.setCategoria(Tmp);
			Oggetto.setTipo(reader.readLine());
			Oggetto.setCodice(reader.readLine());
			Oggetto.setProduttore(reader.readLine());
			Oggetto.setNome(reader.readLine());
			Oggetto.setPunti(reader.readLine());
			Oggetto.setContenuto(reader.readLine());
			Oggetto.setPrezzoCliente(reader.readLine());
			Oggetto.setPrezzoFornitore(reader.readLine());
			
			this.insert(Oggetto);
			}
			
			
				
			}
		catch (FileNotFoundException e) {System.out.println("File non trovato");}
		catch (IOException e) {System.out.println("IO EXCE");}
	}
	
	public void salvaProdottiSuTXT(){
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("DataBaseProdotti.txt", false));
			//writer.newLine();
			int LunghezzaLista = this.length();
			Prodotto Oggetto = null;
			for (int i=0 ; i < LunghezzaLista ; i++){
			Oggetto = (Prodotto) this.getObjPos(i);
			
			writer.write( Oggetto.getCategoria() ); writer.newLine();
			writer.write( Oggetto.getTipo() ); writer.newLine();
			writer.write( Oggetto.getCodice() ); writer.newLine();
			writer.write( Oggetto.getProduttore() ); writer.newLine();
			writer.write( Oggetto.getNome() ); writer.newLine();
			writer.write( Oggetto.getPunti() ); writer.newLine();
			writer.write( Oggetto.getContenuto() ); writer.newLine();
			writer.write( Oggetto.getPrezzoCliente() ); writer.newLine();
			writer.write( Oggetto.getPrezzoFornitore() ); writer.newLine();
			
			}
			
			//writer.write("EOF");
			//writer.newLine();
		
			// chiudo il file
			writer.flush();
			writer.close();
			}
		catch (IOException e) {
			System.out.println("Eccezione durante un'operazione di Output artista");
			}
	}
	
	public static boolean contains (String codiceProdotto) {
		Prodotto prodotto;
		for(int i=0 ; i<ListaProdotti.LinkListaProdotti.length(); i++){
			prodotto = (Prodotto) ListaProdotti.LinkListaProdotti.getObjPos(i);
			if(prodotto.getCodice().equals(codiceProdotto)){
				return true;
			}
		}
		return false;
	}

	public static Prodotto getProdottoDaCodice(String codiceProdotto) {
		Prodotto prodotto;
		for(int i=0 ; i<ListaProdotti.LinkListaProdotti.length(); i++){
			prodotto = (Prodotto) ListaProdotti.LinkListaProdotti.getObjPos(i);
			if(prodotto.getCodice().equals(codiceProdotto)){
				return prodotto;
			}
		}
		return null;
	}

}
