package Grafica;

import javax.swing.JDialog;


public class JDialog_Mod extends JDialog{
	
	public JDialog_Mod(JPanel_Mod_Dialog pannello, String title){
		super();
		this.setModal(false); 
		//this.setSize(800,500);
		this.setLocation(250,250); 
		this.setTitle(title);
		this.getContentPane().add(pannello);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		pannello.setJdialog(this);
		this.pack();
	}
	
	

}


