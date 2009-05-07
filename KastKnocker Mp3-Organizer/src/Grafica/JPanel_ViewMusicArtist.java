package Grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import Controllo.Ctrl_Componenti;
import GestioneArtisti.*;

/** 
 * JPanel personalizzato che gestisce la visualizzazione delle schede degli artisti musicali.
 * Permette sia la visualizzazione delle schede dei gruppi musicali, sia quella degli artisti solisti
 * controllando semplicemente la tipologia di artista in entrata.
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_ViewMusicArtist extends JPanel implements ActionListener,ChangeListener{

	private Artist_MusicArtist Artista;
	private JPanel_Music_Bar JPMB;
	private JPanel_MusicSoloArtist JPMSA;
	private JPanel_MusicGroup JPMG;
	private boolean MusicSoloMode;
	private int NumCompMax;
	
	/**
	 * Costruttore
	 * @param Artista
	 */
	public JPanel_ViewMusicArtist(Artist_MusicArtist Artista){
		super();
		this.Artista=Artista;
		BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        JPMB = new JPanel_Music_Bar(this);
		this.add(JPMB, java.awt.BorderLayout.SOUTH);
		
        if(Artista.getTipoArtista().equals( "SoloMusicArtist" )) setModeMusicSoloArtist();
        if(Artista.getTipoArtista().equals( "MusicGroup" )) setModeMusicGroup();
        this.setVisible(true);
        
	}
	
	private void setModeMusicSoloArtist(){
		MusicSoloMode=true;
		JPMSA = new JPanel_MusicSoloArtist( (Artist_SoloMusicArtist) Artista);
		this.add(JPMSA, java.awt.BorderLayout.CENTER);
		JPMSA.setVisible(true);
	}
	
	private void setModeMusicGroup(){
		MusicSoloMode=false;
		NumCompMax=Integer.parseInt( ( (Artist_MusicGroup) Artista ).getNumComponenti() );
		JPMG = new JPanel_MusicGroup((Artist_MusicGroup) Artista,this);
		JPMSA = new JPanel_MusicSoloArtist( ( (Artist_MusicGroup) Artista ).getComponenteAtPos(0) );
		JPMSA.setMusicSoloArtist(( (Artist_MusicGroup) Artista ).getComponenteAtPos(0), 1, NumCompMax);
		
		this.add(JPMG, java.awt.BorderLayout.NORTH);
		this.add(JPMSA, java.awt.BorderLayout.CENTER);
		JPMSA.setVisible(true);
		JPMG.setVisible(true);
	}

	
	
	

	public void actionPerformed(ActionEvent e) {
		String Evento = e.getActionCommand();
		
		if( Evento.equals("Salva")){
			
			if( MusicSoloMode ){
			JPMSA.Salva();
			}else{
			JPMSA.Salva();
			JPMG.Salva();
			}
			
			LibreriaArtisti LibArt=new LibreriaArtisti();
			LibArt=LibreriaArtisti.getLibreriaArtistiGlobale();
			LibArt.salvaLibreriaFileTxt();
		}
		
		if( Evento.equals("Reset")){
			
			if( MusicSoloMode ){
				JPMSA.Aggiorna();
				}else{
				JPMSA.Aggiorna();
				JPMG.Aggiorna();
				}
		}
		
		if( Evento.equals("Libreria")){
			if( MusicSoloMode ){
				if(JPMSA.isChanged()){
					
					int n = JOptionPane.showConfirmDialog(Ctrl_Componenti.getLinkMainFrame(),
							    "La scheda dell'artista musicale\nè stata modificata\nsalvare le modifiche?",
							    "Avviso.",
							    JOptionPane.YES_NO_OPTION);

								if(n==0){JPMSA.Salva();}
					}
				
				}else{
					
					if( (JPMSA.isChanged())||(JPMG.isChanged()) ){
						
						int n = JOptionPane.showConfirmDialog(Ctrl_Componenti.getLinkMainFrame(),
								    "La scheda del gruppo musicale\nè stata modificata\nsalvare le modifiche?",
								    "Avviso.",
								    JOptionPane.YES_NO_OPTION);

									if(n==0){
										JPMSA.Salva();
										JPMG.Salva();
									}
						}
				}
			
			( (MainPanel) Ctrl_Componenti.getLinkMainPanel()).VisualizzaLibreriaMp3();
			
		}
		
		
	}

	
	public void stateChanged(ChangeEvent CE) {
		JSpinner Spinner = (JSpinner) CE.getSource();
		//((Integer)Spinner.getValue())
		if( ((Integer)Spinner.getValue())<1 ) {Spinner.setValue(NumCompMax);}
		if( ((Integer)Spinner.getValue())>NumCompMax ) {Spinner.setValue(1);}
		int Num = ((Integer)Spinner.getValue());
		if(JPMSA.isChanged()){
			
		int n = JOptionPane.showConfirmDialog(Ctrl_Componenti.getLinkMainFrame(),
				    "La scheda del componente del gruppo\nè stata modificata\nsalvare le modifiche?",
				    "Avviso.",
				    JOptionPane.YES_NO_OPTION);

					if(n==0){
						JPMSA.Salva();
					}
		}
		JPMSA.setMusicSoloArtist(( (Artist_MusicGroup) Artista ).getComponenteAtPos(Num-1), Num, NumCompMax);
		
	}
	
}
