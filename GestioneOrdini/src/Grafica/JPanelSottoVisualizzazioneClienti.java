package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelSottoVisualizzazioneClienti extends JPanel implements ActionListener{
	
	private JButton JB_AggiungiCliente;

	public JPanelSottoVisualizzazioneClienti(){
		
		JB_AggiungiCliente = new javax.swing.JButton();

        JB_AggiungiCliente.setText("Aggiungi Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(JB_AggiungiCliente)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JB_AggiungiCliente)
                .addContainerGap())
        );
        
        /*Salvare*/
        JB_AggiungiCliente.addActionListener(this);
	}

	public void actionPerformed(ActionEvent AE) {
		String action = AE.getActionCommand();
		
		if(action.equals("Aggiungi Cliente")){
			JPanelInserimentoClienti jpic = new JPanelInserimentoClienti();
			new JDialog_Mod(jpic, "Inserimento Clienti");
		}
		
	}

}
