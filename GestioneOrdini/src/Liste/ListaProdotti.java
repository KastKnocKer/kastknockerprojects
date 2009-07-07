package Liste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;




public class ListaProdotti extends Vector<Prodotto>{
	
	public static ListaProdotti LinkListaProdotti;
	
	public ListaProdotti(){
		super();
		LinkListaProdotti=this;
		this.caricaProdottiDaDatabase();
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
					this.add(Oggetto);
				}
		
	this.caricaPromozioni();
				
	}
	
	private void caricaPromozioni(){
		Prodotto Oggetto = null;
		Vector v = Database.eseguiQuery("SELECT * FROM Promozione;");
			for(int i=0; i < v.size(); i++){
			String [] record = (String[]) v.elementAt(i);
			Oggetto = this.getProdottoDaCodice(record[1]);

			
			if(record[2].equals("Sconto")){
				
				Float prezzo = Float.parseFloat( Oggetto.getPrezzoFornitore() );
				Float punti = Float.parseFloat( Oggetto.getPunti() );
				prezzo=prezzo*(100-Integer.parseInt(record[3]))/100;
				punti=punti*(100-Integer.parseInt(record[4]))/100;
				Oggetto.setPrezzoFornitore( prezzo.toString() );
				Oggetto.setPunti( punti.toString() );
			}
			
			if(record[2].equals("Ribasso")){
				Oggetto.setPrezzoFornitore( record[3] );
				Oggetto.setPunti( record[4] );
			}
		
				
			
			}
	}
	
	
	public static boolean contains (String codiceProdotto) {
		Prodotto prodotto;
		for(int i=0 ; i<ListaProdotti.LinkListaProdotti.size(); i++){
			prodotto = (Prodotto) ListaProdotti.LinkListaProdotti.get(i);
			if(prodotto.getCodice().toLowerCase().equals(codiceProdotto.toLowerCase())){
				return true;
			}
		}
		return false;
	}

	public static Prodotto getProdottoDaCodice(String codiceProdotto) {
		Prodotto prodotto;
		for(int i=0 ; i<ListaProdotti.LinkListaProdotti.size(); i++){
			prodotto = (Prodotto) ListaProdotti.LinkListaProdotti.get(i);
			if(prodotto.getCodice().toLowerCase().equals(codiceProdotto.toLowerCase())){
				return prodotto;
			}
		}
		return null;
	}

}
