package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Liste.Database;
import Liste.ListaClienti;

public class JPanelInserimentoClienti extends JPanel_Mod_Dialog implements ActionListener{
	
	private javax.swing.JLabel JL_Cap;
    private javax.swing.JLabel JL_Cellulare;
    private javax.swing.JLabel JL_Citta;
    private javax.swing.JLabel JL_Cognome;
    private javax.swing.JLabel JL_Indirizzo;
    private javax.swing.JLabel JL_Nome;
    private javax.swing.JLabel JL_Note;
    private javax.swing.JLabel JL_Telefono;
    private javax.swing.JTextField JT_Cap;
    private javax.swing.JTextField JT_Cellulare;
    private javax.swing.JTextField JT_Citta;
    private javax.swing.JTextField JT_Cognome;
    private javax.swing.JTextField JT_Indirizzo;
    private javax.swing.JTextField JT_Nome;
    private javax.swing.JTextField JT_Note;
    private javax.swing.JTextField JT_Telefono;
    private javax.swing.JButton PulsanteNuovo;
    private javax.swing.JButton PulsanteAnnulla;
	
	public JPanelInserimentoClienti(){
		
		JL_Nome = new javax.swing.JLabel();
        JL_Cognome = new javax.swing.JLabel();
        JL_Telefono = new javax.swing.JLabel();
        JL_Cellulare = new javax.swing.JLabel();
        JL_Note = new javax.swing.JLabel();
        JL_Indirizzo = new javax.swing.JLabel();
        JL_Cap = new javax.swing.JLabel();
        JL_Citta = new javax.swing.JLabel();
        JT_Nome = new javax.swing.JTextField();
        JT_Cognome = new javax.swing.JTextField();
        JT_Cellulare = new javax.swing.JTextField();
        JT_Telefono = new javax.swing.JTextField();
        JT_Citta = new javax.swing.JTextField();
        JT_Cap = new javax.swing.JTextField();
        JT_Indirizzo = new javax.swing.JTextField();
        JT_Note = new javax.swing.JTextField();
        PulsanteNuovo = new javax.swing.JButton();
        PulsanteAnnulla = new javax.swing.JButton();

        JL_Nome.setText("Nome");

        JL_Cognome.setText("Cognome");

        JL_Telefono.setText("Telefono");

        JL_Cellulare.setText("Cellulare");

        JL_Note.setText("Note");

        JL_Indirizzo.setText("Indirizzo");

        JL_Cap.setText("Cap");

        JL_Citta.setText("Città");


        PulsanteNuovo.setText("Inserisci Cliente");
        PulsanteAnnulla.setText("Annulla");
        PulsanteNuovo.addActionListener(this);
        PulsanteAnnulla.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_Cap, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Citta, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Indirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Note, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Cellulare, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Cognome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JT_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Cognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Cellulare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Citta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Cap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_Indirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PulsanteAnnulla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PulsanteNuovo)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JT_Cap, JT_Cellulare, JT_Citta, JT_Cognome, JT_Indirizzo, JT_Nome, JT_Note, JT_Telefono});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Nome)
                    .addComponent(JT_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Cognome)
                    .addComponent(JT_Cognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Telefono)
                    .addComponent(JT_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Cellulare)
                    .addComponent(JT_Cellulare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Note)
                    .addComponent(JT_Note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Indirizzo)
                    .addComponent(JT_Indirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Cap)
                    .addComponent(JT_Cap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Citta)
                    .addComponent(JT_Citta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PulsanteNuovo)
                    .addComponent(PulsanteAnnulla))
                .addGap(28, 28, 28))
        );
		
		
	}

	public void actionPerformed(ActionEvent AE) {
		String tmp = AE.getActionCommand();
		if(tmp.equals("Annulla")){
			this.getJdialog().dispose();
		}
		
		if(tmp.equals("Inserisci Cliente")){
			String Campi = "Nome, Cognome, Telefono, Cellulare, Indirizzo, Cap, Citta";
			String Valori = "'"+ JT_Nome.getText() +"', '"+ JT_Cognome.getText() +"', '"+ JT_Telefono.getText() +"', '"+ JT_Cellulare.getText() +"', '"+ JT_Indirizzo.getText() +"', '"+ JT_Cap.getText() +"', '"+ JT_Citta.getText() +"'";
			
			int n = JOptionPane.showConfirmDialog(this,
				    "Vuoi veramente inserire questo cliente?",
				    "Inserisci Cliente",
				    JOptionPane.YES_NO_OPTION);

			if(n==0){ 
				Database.eseguiQuery( "INSERT INTO Cliente ("+ Campi +") VALUES ("+ Valori +");" );
				this.getJdialog().dispose();
				ListaClienti.LinkListaClienti.Aggiorna();
				TableModelListaClienti.LinkTMListaClienti.fireTableDataChanged();
				JPanelSottoVisualizzazioneOrdini.LinkJPSVO.AggiornaJCB();
			}
			
		}
		
		
		
		
	}

}
