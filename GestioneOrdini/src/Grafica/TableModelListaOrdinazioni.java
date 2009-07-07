package Grafica;

import java.util.Vector;

import javax.swing.table.*;

import Liste.Cliente;
import Liste.ListaClienti;
import Liste.ListaOrdinazioni;
import Liste.ListaOrdini;
import Liste.ListaProdotti;
import Liste.Ordinazione;
import Liste.Prodotto;

public class TableModelListaOrdinazioni extends AbstractTableModel{
	
	public static TableModelListaOrdinazioni TMListaOrdinazioni;
	ListaOrdinazioni listaordinazioni;
	Ordinazione ordinazione;
	Cliente cliente;
	Prodotto prodotto;
	
	
	public TableModelListaOrdinazioni(){
		super();
		TMListaOrdinazioni=this;
		listaordinazioni = ListaOrdinazioni.LinkListaOrdinazioni;
		
		String stringa = (String) ListaOrdini.LinkVectorOrdini.get(0);
		this.AggiornaVisualizzazioneOrdini(stringa);
	}
	
	
	public String getColumnName(int col){
		switch(col){
		case 0: return "Cod."; 
		case 1: return "Qnt."; 
		case 2: return "Prodotto (Tipo, Fragranza, Produttore)";
		case 3: return "Cliente";
		case 4: return "Prezzo";
		case 5: return "Note";
		default: return "***";
		}
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	public int getRowCount() {
		return ListaOrdinazioni.LinkListaOrdinazioni.size();
	}
	
	public Object getValueAt(int arg0, int arg1) {
		ordinazione = (Ordinazione) ListaOrdinazioni.LinkListaOrdinazioni.get(arg0);
		cliente = ListaClienti.getClienteDaIDCliente( ordinazione.getIDCliente() );
		prodotto = ListaProdotti.getProdottoDaCodice( ordinazione.getCodiceProdotto() );
		
		Float prezzo = Float.parseFloat(prodotto.getPrezzoFornitore())*Float.parseFloat(ordinazione.getQuantita());
		switch(arg1){
		case 0: return ordinazione.getCodiceProdotto();
		case 1: return ordinazione.getQuantita();
		case 2: return prodotto.getTipo()+" - "+prodotto.getNome()+" - "+prodotto.getProduttore();
		case 3: return cliente.getNome()+" "+cliente.getCognome();
		case 4: return prezzo;
		case 5: return ordinazione.getAnnotazioni();
		default: return "***";
		
			
		}
		
		
	}


	public void AggiornaVisualizzazioneOrdini(String stringa) {
		
		String idordine=ListaOrdini.LinkListaOrdini.getIdOrdineDaData(stringa+" 00:00:00");
		String query = "SELECT * FROM Ordinazione WHERE IDOrdine="+idordine+";";
		ListaOrdinazioni.LinkListaOrdinazioni.caricaOrdinazioni(query);
		Vector<String> vettore = ListaOrdinazioni.LinkListaOrdinazioni.getInfoOrdinazioni();
		
		JPanelSottoVisualizzazioneOrdini.LinkJPSVO.setInfoOrdini(vettore.get(0), vettore.get(1), vettore.get(2), vettore.get(3));
		this.fireTableDataChanged();
	}

}
