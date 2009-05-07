import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class JPanel_SudPanel extends JPanel implements ActionListener{
	private static JPanel_SudPanel LinkSudPanel=null;
	
	private JTextField Crediti;
    private JLabel EsamiSuperati;
    private JLabel JLVoto;
    private JLabel Media;
    private JLabel Media110;
    private JTextField Nome;
   // private JLabel NomeEsame;
    private JLabel NumeroCrediti;
   // private JButton Salva;
    private JSeparator Separatore;
   // private JTextField Voto;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JLabel jLabel2;
    
    private javax.swing.JLabel JEsamiSup;
    private javax.swing.JLabel JMedia;
    private javax.swing.JLabel JMedia110;
    private javax.swing.JLabel NomeEsame;
    private javax.swing.JLabel NumCrediti;
    private javax.swing.JButton Salva;
    private javax.swing.JLabel TEsamiSup;
    private javax.swing.JLabel TMedia;
    private javax.swing.JLabel TMedia110;
    private javax.swing.JTextField TNomeEsame;
    private javax.swing.JTextField TNumCrediti;
    private javax.swing.JTextField TVoto;
    private javax.swing.JLabel Voto;
    
	public JPanel_SudPanel(){
		super();
		LinkSudPanel=this;
		/*
		Nome = new JTextField();
        Crediti = new JTextField();
        Voto = new JTextField();
        Salva = new JButton();
        NomeEsame = new JLabel();
        NumeroCrediti = new JLabel();
        JLVoto = new JLabel();
        Separatore = new JSeparator();
        jLabel4 = new JLabel();
        EsamiSuperati = new JLabel();
        jLabel1 = new JLabel();
        Media = new JLabel();
        Media110 = new JLabel();
        jLabel2 = new JLabel();


        Salva.setText("Inserisci");

        NomeEsame.setText("Nome esame");

        NumeroCrediti.setText("Crediti");

        JLVoto.setText("Voto");

        jLabel4.setText("Esami superati: ");

        EsamiSuperati.setText("n\\a");

        jLabel1.setText("Media: ");

        Media.setText("n\\a");
        
        jLabel2.setText("Media110: ");

        Media110.setText("n\\a");
        
        Salva.addActionListener(this);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Nome, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomeEsame))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Crediti, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                            .addComponent(NumeroCrediti))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Voto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Salva))
                            .addComponent(JLVoto)))
                    .addComponent(Separatore, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EsamiSuperati)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Media)
                        .addGap(335, 335, 335)))
                .addContainerGap())
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {Crediti, Voto});

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EsamiSuperati)
                    .addComponent(jLabel1)
                    .addComponent(Media))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separatore, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(NomeEsame)
                    .addComponent(NumeroCrediti)
                    .addComponent(JLVoto))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Crediti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Voto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salva))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );*/
		
		
		 TMedia = new javax.swing.JLabel();
	        JMedia = new javax.swing.JLabel();
	        TMedia110 = new javax.swing.JLabel();
	        JMedia110 = new javax.swing.JLabel();
	        TEsamiSup = new javax.swing.JLabel();
	        JEsamiSup = new javax.swing.JLabel();
	        NomeEsame = new javax.swing.JLabel();
	        TNomeEsame = new javax.swing.JTextField();
	        NumCrediti = new javax.swing.JLabel();
	        TNumCrediti = new javax.swing.JTextField();
	        Voto = new javax.swing.JLabel();
	        TVoto = new javax.swing.JTextField();
	        Salva = new javax.swing.JButton();
	        Salva.addActionListener(this);
	        TMedia.setText("Media:");

	        JMedia.setText("N\\A");

	        TMedia110.setText("Media(110):");

	        JMedia110.setText("N\\A");

	        TEsamiSup.setText("Esami superati:");

	        JEsamiSup.setText("N\\A");

	        NomeEsame.setText("Nome esame");

	        TNomeEsame.setText("");

	        NumCrediti.setText("Numero crediti");

	        TNumCrediti.setText("");

	        Voto.setText("Voto");

	        TVoto.setText("");

	        Salva.setText("Aggiungi esame");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(TMedia110)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(JMedia110)
	                        .addGap(237, 237, 237))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(TEsamiSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(JEsamiSup)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(TMedia, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(JMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(Voto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(NumCrediti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(NomeEsame))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(TNomeEsame, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(TVoto, 0, 0, Short.MAX_VALUE)
	                            .addComponent(TNumCrediti, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(65, 65, 65)
	                        .addComponent(Salva)))
	                .addContainerGap())
	        );

	        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TEsamiSup, TMedia, TMedia110});

	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(52, 52, 52)
	                        .addComponent(Voto))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(26, 26, 26)
	                                .addComponent(NumCrediti))
	                            .addComponent(NomeEsame))
	                        .addGap(26, 26, 26))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(TNomeEsame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(6, 6, 6)
	                                .addComponent(TNumCrediti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(TVoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(18, 18, 18)
	                                .addComponent(Salva))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(JEsamiSup)
	                            .addComponent(TEsamiSup))
	                        .addGap(13, 13, 13)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(JMedia, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(TMedia, javax.swing.GroupLayout.Alignment.TRAILING))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(JMedia110, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(TMedia110, javax.swing.GroupLayout.Alignment.TRAILING))))
	                .addContainerGap())
	        );
        
        
     this.setVisible(true);   
	}

	public static JPanel_SudPanel getLinkSudPanel(){
		return LinkSudPanel;
	}
	
	public void Aggiorna(){
		Vettore Vet= Vettore.getLinkVettore();
		JEsamiSup.setText( Integer.toString( Vet.size() ));
		JMedia.setText( Double.toString( Vet.calcolaMedia() ));
		JMedia110.setText( Double.toString( Vet.calcolaMedia110() ));
	}

	public void actionPerformed(ActionEvent E) {

		if( TNomeEsame.getText().length()==0 ) return;
		if( TNumCrediti.getText().length()==0 ) return;
		if( TVoto.getText().length()==0 ) return;
		Vettore.getLinkVettore().insert(new Voto(TNomeEsame.getText(),Integer.parseInt(TNumCrediti.getText()),Integer.parseInt(TVoto.getText())) ); 
		
		
	}
}
