import java.awt.*;
import javax.swing.*;
import Grafica.*;
import Liste.*;


public class Main {

	/**
	 * @author KastKnocKer
	 */
	public static void main(String[] args) {
		
		Toolkit.getDefaultToolkit().beep();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
			
		Database.Connetti();
		
		ListaClienti.LinkListaClienti = new ListaClienti();
		ListaProdotti.LinkListaProdotti = new ListaProdotti();
		ListaOrdini.LinkListaOrdini = new ListaOrdini();
		ListaOrdinazioni.LinkListaOrdinazioni = new ListaOrdinazioni();
		
		
		MainFrame MF = new MainFrame();
		ListaOrdinazioni.riassuntoOrdinazione();
	}

}
