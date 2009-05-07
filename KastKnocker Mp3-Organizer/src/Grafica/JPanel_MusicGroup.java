package Grafica;

import javax.swing.*;

import GestioneArtisti.Artist_MusicGroup;

/** 
 * JPanel personalizzato utilizzato nella gestione delle schede degli artisti musicali
 * visualizza i campi generali della scheda di un gruppo musicale.
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_MusicGroup extends JPanel{
	
	private JLabel JL_BiografiaGruppo;
    private JLabel JL_CasaDiscografica;
    private JLabel JL_DataFormazione;
    private JLabel JL_DataScioglimento;
    private JLabel JL_Discografia;
    private JLabel JL_NomeGruppo;
    private JLabel JL_WebUrl;
    private JTextArea JTA_BiografiaGruppo;
    private JTextField JTF_CasaDiscografica;
    private JTextField JTF_DataFormazione;
    private JTextField JTF_Discografia;
    private JTextField JTF_NomeDArte;
    private JTextField JTF_WebUrl;
    private JSpinner Spinner;
    private JScrollPane jScrollPane1;
    private JTextField JTF_DataScioglimento;
    private Artist_MusicGroup Artista;
    private JPanel_ViewMusicArtist Viewer;
    
    /**
     * costruttore
     * @param Artista
     * @param Jpanel di visione principale
     */
    public JPanel_MusicGroup(Artist_MusicGroup Artista, JPanel_ViewMusicArtist Viewer){
    	this.Artista=Artista;
    	this.Viewer=Viewer;
    	this.setBorder( BorderFactory.createTitledBorder("Informazioni sul gruppo musicale"));
        JL_DataFormazione = new JLabel();
        JTF_DataScioglimento = new JTextField();
        JL_DataScioglimento = new JLabel();
        JTF_DataFormazione = new JTextField();
        JL_Discografia = new JLabel();
        JTF_Discografia = new JTextField();
        JL_CasaDiscografica = new JLabel();
        JTF_CasaDiscografica = new JTextField();
        JL_WebUrl = new JLabel();
        JTF_WebUrl = new JTextField();
        JL_NomeGruppo = new JLabel();
        JTF_NomeDArte = new JTextField();
        jScrollPane1 = new JScrollPane();
        JTA_BiografiaGruppo = new JTextArea();
        JL_BiografiaGruppo = new JLabel();
        Spinner = new JSpinner();
        Spinner.setValue(1);
        Spinner.addChangeListener(Viewer);
        
        JL_DataFormazione.setText("Data di formazione");

        JTF_DataScioglimento.setPreferredSize(new java.awt.Dimension(150, 20));
        

        JL_DataScioglimento.setText("Data di scioglimento");

        JTF_DataFormazione.setPreferredSize(new java.awt.Dimension(150, 20));
        

        JL_Discografia.setText("Discografia");

        JTF_Discografia.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_CasaDiscografica.setText("Casa discografica");

        JTF_CasaDiscografica.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_WebUrl.setText("Sito Web");

        JTF_WebUrl.setPreferredSize(new java.awt.Dimension(150, 20));

        JL_NomeGruppo.setText("Nome Gruppo");

        JTF_NomeDArte.setPreferredSize(new java.awt.Dimension(150, 20));

        JTA_BiografiaGruppo.setColumns(20);
        JTA_BiografiaGruppo.setRows(5);
        JTA_BiografiaGruppo.setLineWrap(true);
        JTA_BiografiaGruppo.setWrapStyleWord(true);

        jScrollPane1.setViewportView(JTA_BiografiaGruppo);

        JL_BiografiaGruppo.setText("Biografia Gruppo");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(JL_WebUrl)
                            .addComponent(JL_NomeGruppo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(JTF_WebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTF_NomeDArte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_CasaDiscografica)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_CasaDiscografica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_DataFormazione)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_DataScioglimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Discografia)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Discografia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_DataScioglimento)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_DataFormazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_BiografiaGruppo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                        .addComponent(Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_BiografiaGruppo)
                            .addComponent(Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_NomeDArte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_NomeGruppo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_WebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_WebUrl))
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
                            .addComponent(JTF_DataScioglimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_DataFormazione))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(JTF_DataFormazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_DataScioglimento))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    	
        this.Aggiorna();
    }

    /**
     * Aggiorna i campi
     */
    public void Aggiorna(){
    	
    	JTA_BiografiaGruppo.setText(Artista.getBiografia());
    	JTF_NomeDArte.setText(Artista.getNomeDArte());
    	JTF_WebUrl.setText(Artista.getUrlWeb());
    	JTF_Discografia.setText(Artista.getDiscografiaAlbum());
    	JTF_CasaDiscografica.setText(Artista.getCasaDiscografica());
        JTF_DataFormazione.setText(Artista.getDataFormazione());
        JTF_DataScioglimento.setText(Artista.getDataScioglimento());
        
        
    }
    /**
     * Salva le modifiche apportate al gruppo musicale
     */
    public void Salva(){
    	Artista.setBiografia( JTA_BiografiaGruppo.getText() );
    	Artista.setNomeDArte( JTF_NomeDArte.getText() );
    	Artista.setUrlWeb( JTF_WebUrl.getText() );
    	Artista.setDiscografiaAlbum( JTF_Discografia.getText() );
    	Artista.setCasaDiscografica( JTF_CasaDiscografica.getText() );
    	Artista.setDataFormazione( JTF_DataFormazione.getText() );
    	Artista.setDataScioglimento( JTF_DataScioglimento.getText() );
    	
        
    }
    /**
     * Indica se sono state apportate modifiche
     * @return
     */
    public boolean isChanged(){
		boolean Flag = false;
		if(!Artista.getBiografia().equals(JTA_BiografiaGruppo.getText())) Flag=true;
		if(!Artista.getNomeDArte().equals(JTF_NomeDArte.getText())) Flag=true;
		if(!Artista.getUrlWeb().equals(JTF_WebUrl.getText())) Flag=true;
		if(!Artista.getDiscografiaAlbum().equals(JTF_Discografia.getText())) Flag=true;
		if(!Artista.getCasaDiscografica().equals(JTF_CasaDiscografica.getText())) Flag=true;
		if(!Artista.getDataFormazione().equals(JTF_DataFormazione.getText())) Flag=true;
		if(!Artista.getDataScioglimento().equals(JTF_DataScioglimento.getText())) Flag=true;

		return Flag;
	}
    
}
