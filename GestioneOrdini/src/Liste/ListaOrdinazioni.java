package Liste;
import java.io.File;
import java.util.Vector;

import Grafica.TableModelListaOrdinazioni;

public class ListaOrdinazioni extends Vector{
	
	public static ListaOrdinazioni LinkListaOrdinazioni;

	
	
	public ListaOrdinazioni(){
		super();
		LinkListaOrdinazioni=this;
	}
	
	

	
	public void caricaOrdinazioni(){
		this.removeAllElements();
		Vector V = Database.eseguiQuery("SELECT * FROM Ordinazione_Query;");
		for(int i=0; i < V.size(); i++){
			String [] record = (String[]) V.elementAt(i);
			Ordinazione ordinazione = new Ordinazione();
			
			ordinazione.setIDOrdinazione( record[0] );
			ordinazione.setOrdineData( record[1] );
			ordinazione.setQuantita( record[2] );
			ordinazione.setCodiceProdotto( record[3] );
			ordinazione.setIDCliente( record[4] );
			ordinazione.setAnnotazioni( record[5] );
			
			this.add( ordinazione );
		}
	
	}
	
	public void caricaOrdinazioni(String query){
		this.removeAllElements();
		Vector V = Database.eseguiQuery(query);
		for(int i=0; i < V.size(); i++){
			String [] record = (String[]) V.elementAt(i);
			Ordinazione ordinazione = new Ordinazione();
			
			ordinazione.setIDOrdinazione( record[0] );
			ordinazione.setOrdineData( record[1] );
			ordinazione.setQuantita( record[2] );
			ordinazione.setCodiceProdotto( record[3] );
			ordinazione.setIDCliente( record[4] );
			ordinazione.setAnnotazioni( record[5] );
			
			this.add( ordinazione );
		}
		
	
	}

	public Vector<String> getInfoOrdinazioni(){
		Vector<String> vettore = new Vector<String>();
		float punti=0;
		float totprezzoclienti=0;
		float totprezzodistrib=0;
		float guadagno;
		Prodotto prodotto;
		Ordinazione ordinazione;
		for(int i=0; i<ListaOrdinazioni.LinkListaOrdinazioni.size(); i++){
			ordinazione = (Ordinazione) ListaOrdinazioni.LinkListaOrdinazioni.get(i);
			prodotto = ListaProdotti.LinkListaProdotti.getProdottoDaCodice(ordinazione.getCodiceProdotto());
			punti=punti + Float.parseFloat( prodotto.getPunti() )*Float.parseFloat( ordinazione.getQuantita() );
			totprezzoclienti=totprezzoclienti + Float.parseFloat( prodotto.getPrezzoCliente() )*Float.parseFloat( ordinazione.getQuantita() );
			totprezzodistrib=totprezzodistrib + Float.parseFloat( prodotto.getPrezzoFornitore() )*Float.parseFloat( ordinazione.getQuantita() );
		}
		guadagno = totprezzoclienti - totprezzodistrib;
		vettore.add( Float.toString( punti ));
		vettore.add( Float.toString( totprezzoclienti ));
		vettore.add( Float.toString( totprezzodistrib ));
		vettore.add( Float.toString( guadagno ));
		return vettore;
	}

	public static void riassuntoOrdinazione(){
		/*
		Ordinazione ordinazione;
		Vector V = new Vector();
		String[] stringa,tmp;
		boolean trovato=false;
		for(int i=0; i<LinkListaOrdinazioni.size();i++){
			trovato=false;
			ordinazione = (Ordinazione) LinkListaOrdinazioni.get(i);
			if(ordinazione.getOrdineData().equals(ListaOrdini.IDUltimoOrdine)){
				stringa = new String[2];
				stringa[0]=ordinazione.getCodiceProdotto();
				stringa[1]=ordinazione.getQuantita();
				
				for(int j=0; j<V.size();j++){
					tmp = (String[]) V.get(j);
					if(tmp.equals(stringa[0])) {
						trovato = true;
						int tot = Integer.parseInt(stringa[1])+Integer.parseInt(tmp[1]);
						tmp[1]=Integer.toString(tot);
						System.out.println("WAAAAAAAA" + tmp[1]);
						break;
					}
				}
				if(!trovato){
					V.add(stringa);
				}
				
			}
		}*/
		
		Vector<Ordinazione> vettore = new Vector<Ordinazione>();
		boolean Trovato = false;
		Ordinazione ordinazione,tmpord;
		for(int i=0; i<LinkListaOrdinazioni.size();i++){
			Trovato = false;
			ordinazione = (Ordinazione) LinkListaOrdinazioni.get(i);
			if(ordinazione.getOrdineData().equals(ListaOrdini.IDUltimoOrdine)){
				tmpord = new Ordinazione();
				tmpord.setCodiceProdotto( ordinazione.getCodiceProdotto() );
				tmpord.setQuantita( ordinazione.getQuantita() );
				/*Cerco se c'è prodotto già inserito*/
				for(int j=0;j<vettore.size();j++){
					Ordinazione TMP = vettore.get(j);
					if(TMP.getCodiceProdotto().equals(tmpord.getCodiceProdotto())){
						int numquantita = Integer.parseInt(TMP.getQuantita()) + Integer.parseInt(tmpord.getQuantita());
						TMP.setQuantita(Integer.toString(numquantita));
						Trovato = true;
						break;
					}
					
				}
				if(!Trovato){
					vettore.add(tmpord);
				}
				
				
				
				
				
			}
		}
		
		/* Inserisco dati nel database */
		//Database.eseguiQuery("DROP TABLE RiepilogoOrdine;");
		//Database.eseguiQuery("CREATE TABLE RiepilogoOrdine;");
		
		Database.eseguiQuery("DELETE FROM RiepilogoOrdine;");
		for(int i=0;i<vettore.size();i++){
			tmpord = (Ordinazione) vettore.get(i);
			Database.eseguiQuery("INSERT INTO RiepilogoOrdine (CodProdotto, Quantita) VALUES ('"+tmpord.getCodiceProdotto()+"', '"+tmpord.getQuantita()+"');");
			System.out.println(tmpord.getCodiceProdotto()+" - "+tmpord.getQuantita());
		}
		
	}


}
