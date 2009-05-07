package Grafica;

import java.awt.*;
import javax.swing.*;
import Archiviazione.Mp3;

/** 
 * JDialog personalizzato per la modifica dei tag degli mp3
 * 
 * @author Castelli Andrea 
 **/

public class JDialogTagModifier extends JDialog {
	
	Mp3 fileMp3;
		/**
		 * Costruttore
		 * @param fileMp3
		 */
	  JDialogTagModifier(Mp3 fileMp3) {
		  super();
		  this.fileMp3=fileMp3;
		  this.setModal(false); 
		  //this.setSize(800,500); 
		  this.setLocation(250,250); 
		  this.setTitle("Proprietà "+ fileMp3.getArtista()+" - "+ fileMp3.getTitolo() ); 
		  JPanel_Tag_Mod JPTM = new JPanel_Tag_Mod(fileMp3);
		  JPTM.setJD_Contenitore(this);
		  //JPTM.setMp3(fileMp3);
		  this.getContentPane().add(JPTM);
		  this.setVisible(true);
		  this.setAlwaysOnTop(true);
		  this.pack();
		  
	  }

	  





}
