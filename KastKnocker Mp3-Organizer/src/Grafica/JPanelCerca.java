package Grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import Archiviazione.Libreria;
import Controllo.Ctrl_Componenti;

/** 
 * JPanel personalizzato che permette all'utente di effettuare ricerche personalizzate
 * 
 * @author Castelli Andrea 
 **/

public class JPanelCerca extends JPanel implements ActionListener{
	
    private JLabel JL_Album;
    private JLabel JL_Anno;
    private JLabel JL_Artista;
    private JLabel JL_Genere;
    private JLabel JL_Titolo;
    private JTextField JTF_Album;
    private JTextField JTF_Anno;
    private JTextField JTF_Artista;
    private JTextField JTF_Genere;
    private JTextField JTF_Titolo;
    private JButton JB_Annulla;
    private JButton JB_Cerca;
    private JDialog JD_Contenitore;
    private ButtonGroup BG_Lyric;
    private ButtonGroup BG_Copertina;
    private JRadioButton JRB_ConCopertina;
    private JRadioButton JRB_ConLyric;
    private JRadioButton JRB_None;
    private JRadioButton JRB_NoneLyric;
    private JRadioButton JRB_SenzaCopertina;
    private JRadioButton JRB_SenzaLyric;
    private JPanel JPCopertina;
    private JPanel JPLyric;
    private int Copertina;
    private int Lyric;
    
    private static final int SelezioneConSenza = -1;
    private static final int SelezioneCon = 1;
    private static final int SelezioneSenza = 0;

    
	/**
	 * Costruttore
	 */
	public JPanelCerca(){
		super();
		this.setBorder( BorderFactory.createTitledBorder("Ricerca Brani Filtrata") );
	        JL_Artista = new JLabel();
	        JTF_Artista = new JTextField();
	        JL_Genere = new JLabel();
	        JTF_Genere = new JTextField();
	        JL_Anno = new JLabel();
	        JTF_Anno = new JTextField();
	        JL_Album = new JLabel();
	        JTF_Album = new JTextField();
	        JL_Titolo = new JLabel();
	        JTF_Titolo = new JTextField();
	        JB_Cerca = new JButton();
	        JB_Annulla = new JButton();
	        BG_Copertina = new ButtonGroup();
	        BG_Lyric = new ButtonGroup();
	        JPCopertina = new JPanel();
	        JRB_None = new JRadioButton();
	        JRB_ConCopertina = new JRadioButton();
	        JRB_SenzaCopertina = new JRadioButton();
	        JPLyric = new JPanel();
	        JRB_ConLyric = new JRadioButton(); 
	        JRB_NoneLyric = new JRadioButton();
	        JRB_SenzaLyric = new JRadioButton();

	        JL_Artista.setText("Artista");
	        JL_Genere.setText("Genere");
	        JL_Anno.setText("Anno");
	        JL_Album.setText("Album");
	        JL_Titolo.setText("Titolo");
	        JB_Cerca.setText("Cerca");
	        JB_Annulla.setText("Annulla");
	        
	        JTF_Artista.setPreferredSize(new java.awt.Dimension(180, 20));
	        JTF_Genere.setPreferredSize(new java.awt.Dimension(180, 20));
	        JTF_Anno.setPreferredSize(new java.awt.Dimension(180, 20));
	        JTF_Album.setPreferredSize(new java.awt.Dimension(180, 20));
	        JTF_Titolo.setPreferredSize(new java.awt.Dimension(180, 20));

	        JRB_None.setText("Con e senza copertina");
			JRB_ConCopertina.setText("Con copertina");
			JRB_SenzaCopertina.setText("Senza copertina");

			JRB_ConLyric.setText("Con Testo");
	        JRB_NoneLyric.setText("Con e senza Testo");
	        JRB_SenzaLyric.setText("Senza Testo");

	        javax.swing.GroupLayout JPCopertinaLayout = new javax.swing.GroupLayout(JPCopertina);
	        JPCopertina.setLayout(JPCopertinaLayout);
	        JPCopertinaLayout.setHorizontalGroup(
	            JPCopertinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(JPCopertinaLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(JPCopertinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(JRB_None)
	                    .addComponent(JRB_ConCopertina)
	                    .addComponent(JRB_SenzaCopertina))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        JPCopertinaLayout.setVerticalGroup(
	            JPCopertinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(JPCopertinaLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(JRB_None)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(JRB_ConCopertina)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(JRB_SenzaCopertina)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        
	      

	        javax.swing.GroupLayout JPLyricLayout = new javax.swing.GroupLayout(JPLyric);
	        JPLyric.setLayout(JPLyricLayout);
	        JPLyricLayout.setHorizontalGroup(
	            JPLyricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(JPLyricLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(JPLyricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(JRB_NoneLyric)
	                    .addComponent(JRB_ConLyric)
	                    .addComponent(JRB_SenzaLyric))
	                .addContainerGap(18, Short.MAX_VALUE))
	        );
	        JPLyricLayout.setVerticalGroup(
	            JPLyricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(JPLyricLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(JRB_NoneLyric)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(JRB_ConLyric)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(JRB_SenzaLyric)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(JL_Artista)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(JTF_Artista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(JL_Genere)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(JTF_Genere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(JL_Album)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(JTF_Album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(JL_Titolo)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(JTF_Titolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(JL_Anno)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(JTF_Anno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(JPCopertina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(JPLyric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(65, 65, 65)
	                        .addComponent(JB_Cerca)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(JB_Annulla)))
	                .addContainerGap(23, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JL_Artista)
	                    .addComponent(JTF_Artista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JL_Titolo)
	                    .addComponent(JTF_Titolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(8, 8, 8)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JL_Album)
	                    .addComponent(JTF_Album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(5, 5, 5)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JL_Genere)
	                    .addComponent(JTF_Genere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JL_Anno)
	                    .addComponent(JTF_Anno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(JPLyric, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(JPCopertina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(JB_Cerca)
	                    .addComponent(JB_Annulla))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        
	        ////////////////////////////////////////////////////
	        
	        BG_Copertina.add(JRB_None);
	        BG_Copertina.add(JRB_ConCopertina);
	        BG_Copertina.add(JRB_SenzaCopertina);
	        
	        BG_Lyric.add(JRB_NoneLyric);
	        BG_Lyric.add(JRB_ConLyric);
	        BG_Lyric.add(JRB_SenzaLyric);
	        
	        JRB_None.addActionListener(this);
	        JRB_ConCopertina.addActionListener(this);
	        JRB_SenzaCopertina.addActionListener(this);
	        JRB_ConLyric.addActionListener(this);
	        JRB_NoneLyric.addActionListener(this);
	        JRB_SenzaLyric.addActionListener(this);
	        
	        JRB_NoneLyric.setSelected(true);
	        JRB_None.setSelected(true);
	          
	        JB_Annulla.addActionListener(this);
	        JB_Cerca.addActionListener(this);
	        
	        Copertina=JPanelCerca.SelezioneConSenza;
	        Lyric=JPanelCerca.SelezioneConSenza;
	    }

	/**
	 * Setta il JDialog in cui il pannello è contenuto
	 * @param JD
	 */
	public void setJD_Contenitore(JDialogCerca JD){
		JD_Contenitore=JD;
	}

	
	

	public void actionPerformed(ActionEvent AE) {
		String Evento = AE.getActionCommand();
		
		if(Evento.equals("Con e senza Testo")){		Lyric=JPanelCerca.SelezioneConSenza;}
		if(Evento.equals("Con Testo")){				Lyric=JPanelCerca.SelezioneCon;}
		if(Evento.equals("Senza Testo")){			Lyric=JPanelCerca.SelezioneSenza;}
		if(Evento.equals("Con e senza copertina")){	Copertina=JPanelCerca.SelezioneConSenza;}
		if(Evento.equals("Con copertina")){			Copertina=JPanelCerca.SelezioneCon;}
		if(Evento.equals("Senza copertina")){		Copertina=JPanelCerca.SelezioneSenza;}
		
		if(Evento.equals("Cerca")){
			
			Libreria LibMp3=new Libreria();
			
			String Artista=JTF_Artista.getText();
			String Album=JTF_Album.getText();
			String Genere=JTF_Genere.getText();
			String Anno=JTF_Anno.getText();
			String Titolo=JTF_Titolo.getText();
			
			if(Artista.length()==0) Artista=null;
			if(Album.length()==0) Album=null;
			if(Genere.length()==0) Genere=null;
			if(Anno.length()==0) Anno=null;
			if(Titolo.length()==0) Titolo=null;
			
			
			LibMp3.caricaLibreriaGlobaleFiltrata(Artista,Album,Genere,Anno,Titolo,Copertina,Lyric);
			Ctrl_Componenti.getLinkJTableTabella().setLibreria(LibMp3);
			
		}
		
		if(Evento.equals("Annulla")){ JD_Contenitore.dispose(); }
		
	}
	
}
