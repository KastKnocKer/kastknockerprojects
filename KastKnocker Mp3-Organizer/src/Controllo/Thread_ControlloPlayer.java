package Controllo;

/** 
 * Thread che aggiorna la barra del riproduttore musicale
 * 
 * @author Castelli Andrea 
 * */

import javax.swing.*;
import Grafica.*;

public class Thread_ControlloPlayer extends Thread{
	
	private JPanelArea_Play JPAP;
	
	/**
	 * Costruttore
	 * @param JPanelArea_Play
	 */
	public Thread_ControlloPlayer(JPanelArea_Play JPAP) {
		super();
		this.JPAP=JPAP;
		
	}
	


	/**
	 * Avvia il thread
	 */
	public void run(){
		while(true){
			JPAP.AggiornaBarra();
			try {
				Thread.sleep( new Long(1000) );
			} catch (InterruptedException e) {}

		}
	}

}
