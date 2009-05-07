import javax.swing.UIManager;


public class MainEasyMedia {

	/**
	 * @author KastKnocKer
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
		new JFrame_MainFrame();

	}

}
