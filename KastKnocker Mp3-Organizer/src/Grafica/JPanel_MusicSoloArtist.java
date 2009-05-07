package Grafica;

import javax.swing.*;

import GestioneArtisti.Artist_SoloMusicArtist;

/** 
 * JPanel personalizzato utilizzato nella gestione delle schede degli artisti musicali
 * visualizza i campi degli artisti solisti, utilizzato sia per la visione del singolo artista solista
 * sia per la visualizzazione dei singoli componenti del gruppo musicale
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_MusicSoloArtist extends JPanel{
	
	private JLabel JL_Biografia;
    private JLabel JL_CasaDiscografica;
    private JLabel JL_Cognome;
    private JLabel JL_DataEsordio;
    private JLabel JL_DataMorte;
    private JLabel JL_DataNascita;
    private JLabel JL_Discografia;
    private JLabel JL_GeneriMusicaliPreferiti;
    private JLabel JL_Nazionalità;
    private JLabel JL_Nome;
    private JLabel JL_NomeDArte;
    private JLabel JL_WebUrl;
    private JTextArea JTA_Biografia;
    private JTextField JTF_CasaDiscografica;
    private JTextField JTF_Cognome;
    private JTextField JTF_DataEsordio;
    private JTextField JTF_DataMorte;
    private JTextField JTF_DataNascita;
    private JTextField JTF_Discografia;
    private JTextField JTF_GeneriMusicaliPreferiti;
    private JTextField JTF_Nazionalità;
    private JTextField JTF_Nome;
    private JTextField JTF_NomeDArte;
    private JTextField JTF_WebUrl;
    private JScrollPane jScrollPane1;
    private Artist_SoloMusicArtist Artista;
    
    /**
     * Costruttore
     * @param Artista
     */
    public JPanel_MusicSoloArtist(Artist_SoloMusicArtist Artista){
    	this.Artista=Artista;
    	this.setBorder( BorderFactory.createTitledBorder("Artista Solista"));
    	JTF_NomeDArte = new JTextField();
        JL_NomeDArte = new JLabel();
        JL_GeneriMusicaliPreferiti = new JLabel();
        JTF_GeneriMusicaliPreferiti = new JTextField();
        JL_WebUrl = new JLabel();
        JTF_WebUrl = new JTextField();
        JL_Nome = new JLabel();
        JTF_Nome = new JTextField();
        JL_Cognome = new JLabel();
        JTF_Cognome = new JTextField();
        JL_Nazionalità = new JLabel();
        JTF_Nazionalità = new JTextField();
        JL_CasaDiscografica = new JLabel();
        JTF_CasaDiscografica = new JTextField();
        JL_DataNascita = new JLabel();
        JTF_DataNascita = new JTextField();
        JL_DataMorte = new JLabel();
        JTF_DataMorte = new JTextField();
        JL_DataEsordio = new JLabel();
        JTF_DataEsordio = new JTextField();
        JL_Biografia = new JLabel();
        JL_Discografia = new JLabel();
        JTF_Discografia = new JTextField();
        jScrollPane1 = new JScrollPane();
        
        JTA_Biografia = new JTextArea();
        JTA_Biografia.setLineWrap(true);
        JTA_Biografia.setWrapStyleWord(true);


        JTF_NomeDArte.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_NomeDArte.setText("Nome D'arte");

        JL_GeneriMusicaliPreferiti.setText("Generi musicali preferiti");

        JTF_GeneriMusicaliPreferiti.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_WebUrl.setText("Sito Web");

        JTF_WebUrl.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_Nome.setText("Nome");

        JTF_Nome.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_Cognome.setText("Cognome");

        JTF_Cognome.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_Nazionalità.setText("Nazionalità");

        JTF_Nazionalità.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_CasaDiscografica.setText("Casa discografica");

        JTF_CasaDiscografica.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_DataNascita.setText("Data di nascita");

        JTF_DataNascita.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_DataMorte.setText("Data di morte");

        JTF_DataMorte.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_DataEsordio.setText("Data esordio");

        JTF_DataEsordio.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_Biografia.setText("Biografia");

        JL_Discografia.setText("Discografia");

        JTF_Discografia.setPreferredSize(new java.awt.Dimension(150, 20));

        JTA_Biografia.setColumns(20);
        JTA_Biografia.setRows(5);
        jScrollPane1.setViewportView(JTA_Biografia);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_GeneriMusicaliPreferiti)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_GeneriMusicaliPreferiti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_DataEsordio)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_DataEsordio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_DataNascita)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_DataNascita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_DataMorte)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_DataMorte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Discografia)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Discografia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_CasaDiscografica)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_CasaDiscografica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_WebUrl)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_WebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Nome)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_NomeDArte)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_NomeDArte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Cognome)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Cognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Nazionalità)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Nazionalità, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Biografia)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_NomeDArte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_NomeDArte))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Nome))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_Cognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Cognome))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_Nazionalità, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Nazionalità))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_GeneriMusicaliPreferiti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_GeneriMusicaliPreferiti))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_DataEsordio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_DataEsordio))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_DataNascita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_DataNascita))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_DataMorte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_DataMorte))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_Discografia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Discografia))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_CasaDiscografica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_CasaDiscografica))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_WebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_WebUrl)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(JL_Biografia)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.Aggiorna();
    }

    /**
     * Aggiorna i campi
     */
    public void Aggiorna() {
    	JTF_NomeDArte.setText(Artista.getNomeDArte());
        JTF_GeneriMusicaliPreferiti.setText(Artista.getGeneriMusicaliPreferiti());
        JTF_WebUrl.setText(Artista.getUrlWeb());
        JTF_Nome.setText(Artista.getNome());
        JTF_Cognome.setText(Artista.getCognome());
        JTF_Nazionalità.setText(Artista.getNazionalità());
        JTF_CasaDiscografica.setText(Artista.getCasaDiscografica());
        JTF_DataNascita.setText(Artista.getDataNascita());
        JTF_DataMorte.setText(Artista.getDataMorte());
        JTF_DataEsordio.setText(Artista.getDataEsordio());
        JTF_Discografia.setText(Artista.getDiscografiaAlbum());
        JTA_Biografia.setText(Artista.getBiografia());
	}
    
    /**
     * Salva le modifiche apportate
     */
	public void Salva() {
		Artista.setNomeDArte( JTF_NomeDArte.getText() );   //<- -- - - - Controllo esistenza?
		Artista.setBiografia( JTA_Biografia.getText() );
		Artista.setCasaDiscografica( JTF_CasaDiscografica.getText() );
		Artista.setCognome( JTF_Cognome.getText() );
		Artista.setDataEsordio( JTF_DataEsordio.getText() );
		Artista.setDataMorte( JTF_DataMorte.getText() );
		Artista.setDataNascita( JTF_DataNascita.getText() );
		Artista.setDiscografiaAlbum( JTF_Discografia.getText() );
		Artista.setGeneriMusicaliPreferiti( JTF_GeneriMusicaliPreferiti.getText() );
		Artista.setNazionalità( JTF_Nazionalità.getText() );
		Artista.setNome( JTF_Nome.getText() );
		Artista.setUrlWeb( JTF_WebUrl.getText() );
		
		
	}
	
	/**
	 * Setta al pannello l'artista passato come parametro,
	 * il numero del componente ed il num di componenti max del gruppo
	 * Utilizzato per la gestione dei gruppi musicali
	 * @param Artista
	 * @param NumComponente
	 * @param NumComponenti
	 */
	public void setMusicSoloArtist(Artist_SoloMusicArtist Artista,int NumComponente, int NumComponenti){
		this.setBorder( BorderFactory.createTitledBorder("Componente n°"+NumComponente+" di "+NumComponenti) );
    	this.Artista=Artista;
    	this.Aggiorna();
	}
	
	/**
	 * Indica se sono state apportate modifiche alla scheda
	 * @return
	 */
	public boolean isChanged(){
		boolean Flag = false;
		if(!Artista.getNomeDArte().equals(JTF_NomeDArte.getText())) Flag=true;
		if(!Artista.getGeneriMusicaliPreferiti().equals(JTF_GeneriMusicaliPreferiti.getText())) Flag=true;
		if(!Artista.getUrlWeb().equals(JTF_WebUrl.getText())) Flag=true;
		if(!Artista.getNome().equals(JTF_Nome.getText())) Flag=true;
		if(!Artista.getCognome().equals(JTF_Cognome.getText())) Flag=true;
		if(!Artista.getNazionalità().equals(JTF_Nazionalità.getText())) Flag=true;
		if(!Artista.getCasaDiscografica().equals(JTF_CasaDiscografica.getText())) Flag=true;
		if(!Artista.getDataNascita().equals(JTF_DataNascita.getText())) Flag=true;
		if(!Artista.getDataMorte().equals(JTF_DataMorte.getText())) Flag=true;
		if(!Artista.getDataEsordio().equals(JTF_DataEsordio.getText())) Flag=true;
		if(!Artista.getDiscografiaAlbum().equals(JTF_Discografia.getText())) Flag=true;
		if(!Artista.getBiografia().equals(JTA_Biografia.getText())) Flag=true;
		return Flag;
	}
	

}
