package Grafica;

import javax.swing.JDialog;
import Archiviazione.Mp3;

/** 
 * JDialog personalizzato per la ricerca degli mp3 dettagliata
 * 
 * @author Castelli Andrea 
 **/

public class JDialogCerca extends JDialog {
	

	/**
	 * Costruttore
	 */
	public JDialogCerca() {
		  super();
		  
		  this.setModal(false);
		  this.setLocation(250,250); 
		  this.setTitle("Cerca"); 
		  JPanelCerca JPC = new JPanelCerca();
		  JPC.setJD_Contenitore(this);
		  //JPTM.setMp3(fileMp3);
		  this.getContentPane().add(JPC);
		  this.setVisible(true);
		  this.setAlwaysOnTop(true);
		  this.pack();
 
	  }

}
