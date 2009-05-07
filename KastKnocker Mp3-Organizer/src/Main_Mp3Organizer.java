import java.awt.Toolkit;
import javax.swing.UIManager;
import Controllo.*;
import Grafica.*;

/** 
 * Mp3 Organizer - Esame di Fondamenti di informatica C
 * 
 * @author Castelli Andrea 
 * */

public class Main_Mp3Organizer {


	public static void main(String[] args) {
		
		Toolkit.getDefaultToolkit().beep();
		
		/*Config Configurazione=*/new Config();
		//Configurazione.SalvaConfig();
		//Configurazione=Configurazione.CaricaConfig();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
			
		
		MainFrame FinestraPrincipale= new MainFrame();
		Ctrl_Componenti.setLinkMainFrame(FinestraPrincipale);
		
		
		
	}
	

}