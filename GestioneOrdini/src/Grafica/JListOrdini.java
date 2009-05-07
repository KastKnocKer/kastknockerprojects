package Grafica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import Liste.ListaOrdini;

public class JListOrdini extends JList implements MouseListener{

	private int Linea;
	
	
	public JListOrdini(){
		super();
		this.addMouseListener(this);
		this.setModel(new javax.swing.AbstractListModel() {
            public int getSize() { return ListaOrdini.LinkVectorOrdini.size(); }
            public Object getElementAt(int i) { return ListaOrdini.LinkVectorOrdini.get(i); }
			});
		
	}

	
	public void mouseClicked(MouseEvent ME) {
		Linea=this.locationToIndex(ME.getPoint());
		this.setSelectedIndex(Linea);
		
		if( ME.getButton() == MouseEvent.BUTTON1) {
			if( ME.getClickCount()==1 ) return;
			String stringa = (String) ListaOrdini.LinkVectorOrdini.get(Linea);
			TableModelListaOrdinazioni.TMListaOrdinazioni.AggiornaVisualizzazioneOrdini(stringa);
			//String NomeArtista = (String) VArtisti.elementAt(LineaSelezionata);
			//LibArtist.visualizzaSchedaArtista(NomeArtista);
			return;
		}
			
		if( ME.getButton() == MouseEvent.BUTTON2){return;}
		
		if( ME.getButton() == MouseEvent.BUTTON3){return;}
		
		}

	
	public void mouseEntered(MouseEvent ME) {}
	public void mouseExited(MouseEvent ME) {}
	public void mousePressed(MouseEvent ME) {}
	public void mouseReleased(MouseEvent ME) {}
	
}
