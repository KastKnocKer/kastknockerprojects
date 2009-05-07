package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** 
 * JPanel personalizzato utilizzato nella gestione delle schede degli artisti musicali
 * contiene bottoni, gestiti dal pannello di visione principale, che permettono di salvare
 * ricaricare i campi direttamente dalla scheda, ritornare alla visione della libreria
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_Music_Bar extends JPanel{
	
	private JButton JB_Salva;
    private JButton JB_Reset;
    private JButton JB_Annulla;
    private JPanel_ViewMusicArtist Viewer;
    /**
     * costruttore
     * @param JPanel di visione principale
     */
	public JPanel_Music_Bar(JPanel_ViewMusicArtist JP){
		this.Viewer=JP;
		JB_Salva = new JButton();
        JB_Reset = new JButton();
        JB_Annulla = new JButton();

        JB_Salva.setText("Salva");
        JB_Reset.setText("Reset");
        JB_Annulla.setText("Libreria");
        
        JB_Salva.addActionListener(Viewer);
        JB_Reset.addActionListener(Viewer);
        JB_Annulla.addActionListener(Viewer);
        
        add(JB_Salva);
        add(JB_Reset);
        add(JB_Annulla);

        
       
        this.setVisible(true);
	}
	


	
}
