import GestioneMp3.Database;
import GestioneMp3.ListaMp3;
import InterfacciaGrafica.JFrame_Main;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Database.Connetti();
		ListaMp3 listamp3 = new ListaMp3();
		listamp3.caricaMp3daDir();
		listamp3.aggiornaVociDB();
		JFrame_Main jfm = new JFrame_Main();
		

	}

}
