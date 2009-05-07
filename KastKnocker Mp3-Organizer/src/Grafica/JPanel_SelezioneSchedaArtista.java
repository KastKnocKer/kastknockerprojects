package Grafica;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import Archiviazione.Mp3;
import Controllo.Ctrl_Componenti;
import GestioneArtisti.Artist_MusicArtist;
import GestioneArtisti.Artist_MusicGroup;
import GestioneArtisti.Artist_SoloMusicArtist;
import GestioneArtisti.LibreriaArtisti;

/** 
 * JPanel personalizzato per permettere all'utente di decidere la tipologia
 * di scheda artista da assegnare.
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_SelezioneSchedaArtista extends JPanel implements ActionListener,ChangeListener{
	
	private ButtonGroup ButtonGroup;
    private JButton JB_Annulla;
    private JButton JB_Crea;
    private JLabel JL_Didascalia;
    private JLabel JL_Nome;
    private JLabel JL_NumComp;
    private JRadioButton JRB_MusicGroup;
    private JRadioButton JRB_SoloMusicArtist;
    private JTextField JTF_Nome;
    private JSpinner Spinner;
    private JDialog JD_Contenitore;
    private Mp3 mp3;
    
    /**
     * Costruttore
     * @param JD_Contenitore
     * @param mp3
     */
    public JPanel_SelezioneSchedaArtista(JDialog JD_Contenitore,Mp3 mp3){
    	this.JD_Contenitore=JD_Contenitore;
    	this.mp3=mp3;
    	ButtonGroup = new ButtonGroup();
        JRB_SoloMusicArtist = new JRadioButton();
        JRB_MusicGroup = new JRadioButton();
        Spinner = new JSpinner();
        JL_NumComp = new JLabel();
        JL_Didascalia = new JLabel();
        JL_Nome = new JLabel();
        JTF_Nome = new JTextField();
        JB_Crea = new JButton();
        JB_Annulla = new JButton();
        
        ButtonGroup.add(JRB_SoloMusicArtist);
        ButtonGroup.add(JRB_MusicGroup);
        
        JRB_SoloMusicArtist.addActionListener(this);
        JRB_MusicGroup.addActionListener(this);
        JB_Crea.addActionListener(this);
        JB_Annulla.addActionListener(this);
        Spinner.addChangeListener(this);

        JRB_SoloMusicArtist.setText("Artista solista");

        JRB_MusicGroup.setText("Gruppo musicale");

        JL_NumComp.setText("Numero componenti");

        JL_Didascalia.setText("Seleziona la scheda artista più appropriata");

        JL_Nome.setText("Nome");

        JB_Crea.setText("Crea Scheda");

        JB_Annulla.setText("Annulla");
        
        Spinner.setEnabled(false);
        Spinner.setValue(2);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(JB_Crea)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Annulla))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Didascalia)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JRB_SoloMusicArtist)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(JL_Nome)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JTF_Nome))
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(JRB_MusicGroup)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JL_NumComp)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {JB_Annulla, JB_Crea});

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Nome)
                    .addComponent(JTF_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_Didascalia)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JRB_SoloMusicArtist)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(JRB_MusicGroup)
                    .addComponent(Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_NumComp))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Crea)
                    .addComponent(JB_Annulla))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        if(mp3!=null){
        	JTF_Nome.setText(mp3.getArtista());
        	JTF_Nome.setEditable(false); 
        	}
    	
    }


	public void actionPerformed(ActionEvent e) {
	String Evento = e.getActionCommand();
	
	if(Evento.equals("Artista solista")){
		Spinner.setEnabled(false);
	}
	if(Evento.equals("Gruppo musicale")){
		Spinner.setEnabled(true);
	}
	if(Evento.equals("Crea Scheda")){
		if( JTF_Nome.getText().length()==0 ) {
			JD_Contenitore.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
					"Nessun nome inserito!",
					"Attenzione!",
					JOptionPane.WARNING_MESSAGE);
			JD_Contenitore.setAlwaysOnTop(true);
		return;
		}
		
		LibreriaArtisti LibArt = LibreriaArtisti.getLibreriaArtistiGlobale();
		if( LibArt.contains( JTF_Nome.getText() ) ) {
			JD_Contenitore.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
					"L'artista è già inserito in libreria!",
					"Attenzione!",
					JOptionPane.WARNING_MESSAGE);
			JD_Contenitore.setAlwaysOnTop(true);
		return;
		}
		
		Artist_MusicArtist Artista=null;
		if( JRB_SoloMusicArtist.isSelected() ){
			Artista= new Artist_SoloMusicArtist();
			Artista.setNomeDArte(JTF_Nome.getText());
			LibArt.aggiungiArtista(Artista);
			JD_Contenitore.dispose();
		}
		if( JRB_MusicGroup.isSelected() ){
			int NumComp = ((Integer)Spinner.getValue());
			Artista= new Artist_MusicGroup( NumComp );
			Artista.setNomeDArte(JTF_Nome.getText());
			LibArt.aggiungiArtista(Artista);
			JD_Contenitore.dispose();
		}
		
		
		
	}
	if(Evento.equals("Annulla")){ JD_Contenitore.dispose(); }

		
	}


	public void stateChanged(ChangeEvent CE) {
		if( ((Integer)Spinner.getValue())<2 ) Spinner.setValue(2);
		if( ((Integer)Spinner.getValue())>9 ) Spinner.setValue(9);
		
	}

}
