package Grafica;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class JPanel_Mod_Dialog extends JPanel{
	
	private JDialog jdialog;
	
	public JPanel_Mod_Dialog(){
		super();
	}

	public void setJdialog(JDialog jdialog) {
		this.jdialog = jdialog;
	}

	public JDialog getJdialog() {
		return jdialog;
	}

}
