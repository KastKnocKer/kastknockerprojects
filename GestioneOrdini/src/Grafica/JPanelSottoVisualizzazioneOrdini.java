package Grafica;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import Liste.Cliente;
import Liste.Database;
import Liste.Distributore;
import Liste.ListaClienti;
import Liste.ListaDistributori;
import Liste.ListaOrdinazioni;
import Liste.ListaOrdini;
import Liste.ListaProdotti;

public class JPanelSottoVisualizzazioneOrdini extends JPanel implements ActionListener{
	
	public static JPanelSottoVisualizzazioneOrdini LinkJPSVO;
	public static Vector<String> STR_Nome;
	public static Vector<String> STR_Cognome;
	public static Vector<String> STR_Distributore;
	
	private javax.swing.JButton JB_AggiungiOrdinazione;
    private javax.swing.JComboBox JCB_Cognome;
    private javax.swing.JComboBox JCB_Distributore;
    private javax.swing.JComboBox JCB_Nome;
    private javax.swing.JLabel JL_Cliente;
    private javax.swing.JLabel JL_Clienti;
    private javax.swing.JLabel JL_ClientiDato;
    private javax.swing.JLabel JL_CodiceProdotto;
    private javax.swing.JLabel JL_DistrScelta;
    private javax.swing.JLabel JL_Distributore;
    private javax.swing.JLabel JL_DistributoreDato;
    private javax.swing.JLabel JL_Guadagno;
    private javax.swing.JLabel JL_GuadagnoDato;
    private javax.swing.JLabel JL_Note;
    private javax.swing.JLabel JL_Punti;
    private javax.swing.JLabel JL_PuntiDato;
    private javax.swing.JLabel JL_Quantita;
    private javax.swing.JTextField JT_CodiceProdotto;
    private javax.swing.JTextField JT_Note;
    private javax.swing.JTextField JT_Quantita;
    private javax.swing.JSeparator Separatore;
    private String CognomeSelezionato;

	
	public JPanelSottoVisualizzazioneOrdini(){
		super();
		LinkJPSVO=this; 
		/*                        */
		
		Separatore = new javax.swing.JSeparator();
        JL_Punti = new javax.swing.JLabel();
        JL_Distributore = new javax.swing.JLabel();
        JL_Clienti = new javax.swing.JLabel();
        JL_Guadagno = new javax.swing.JLabel();
        JL_PuntiDato = new javax.swing.JLabel();
        JL_GuadagnoDato = new javax.swing.JLabel();
        JL_ClientiDato = new javax.swing.JLabel();
        JL_DistributoreDato = new javax.swing.JLabel();
        JT_Quantita = new javax.swing.JTextField();
        JL_CodiceProdotto = new javax.swing.JLabel();
        JT_CodiceProdotto = new javax.swing.JTextField();
        JL_Quantita = new javax.swing.JLabel();
        JL_Cliente = new javax.swing.JLabel();
        JCB_Cognome = new javax.swing.JComboBox();
        JCB_Nome = new javax.swing.JComboBox();
        JB_AggiungiOrdinazione = new javax.swing.JButton();
        JL_Note = new javax.swing.JLabel();
        JT_Note = new javax.swing.JTextField();
        JL_DistrScelta = new javax.swing.JLabel();
        JCB_Distributore = new javax.swing.JComboBox();

        JL_Punti.setText("Punti:");

        JL_Distributore.setText("Distributore:");

        JL_Clienti.setText("Clienti:");

        JL_Guadagno.setText("Guadagno:");


        JT_Quantita.setText("1");

        JL_CodiceProdotto.setText("Codice Prodotto");

        JT_CodiceProdotto.setText("");

        JL_Quantita.setText("Quantità");

        JL_Cliente.setText("Cliente");

        JB_AggiungiOrdinazione.setText("Aggiungi Ordinazione");

        JL_Note.setText("Note");

        JT_Note.setText("");

        JL_DistrScelta.setText("Distributore");

        JCB_Distributore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Punti)
                    .addComponent(JL_Guadagno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(JL_PuntiDato))
                    .addComponent(JL_GuadagnoDato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Clienti)
                    .addComponent(JL_Distributore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_ClientiDato)
                    .addComponent(JL_DistributoreDato))
                .addGap(162, 162, 162))
            .addComponent(Separatore, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_CodiceProdotto)
                    .addComponent(JL_Quantita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JT_Quantita)
                    .addComponent(JT_CodiceProdotto, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Note)
                    .addComponent(JL_DistrScelta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JCB_Distributore, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(JL_Cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCB_Cognome, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCB_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JT_Note, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_AggiungiOrdinazione)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JCB_Cognome, JCB_Nome});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(JL_Punti)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JL_Guadagno))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(JL_Clienti)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JL_Distributore)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_PuntiDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_GuadagnoDato))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_ClientiDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_DistributoreDato)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separatore, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_CodiceProdotto)
                    .addComponent(JT_CodiceProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Cliente)
                    .addComponent(JCB_Cognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCB_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_DistrScelta)
                    .addComponent(JCB_Distributore, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Quantita)
                    .addComponent(JT_Quantita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_AggiungiOrdinazione)
                    .addComponent(JL_Note)
                    .addComponent(JT_Note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
		
        
		/*                        */
		JB_AggiungiOrdinazione.addActionListener(this);
        CognomeSelezionato=new String("");
        JCB_Cognome.addActionListener(this);
        JCB_Nome.addActionListener(this);
        JCB_Distributore.addActionListener(this);
        JCB_Cognome.setName("JCBCognome");
        JCB_Nome.setName("JCBNome");
        JCB_Distributore.setName("JCBDistributore");
        this.AggiornaJCB();
       
	}

	public void AggiornaJCB(){
		STR_Nome = new Vector();
		STR_Cognome = new Vector();
		STR_Distributore = new Vector();
		
		Distributore distributore;
		for(int i=0; i<ListaDistributori.LinkListaDistributori.size(); i++){
			distributore = ListaDistributori.LinkListaDistributori.get(i);
			STR_Distributore.add( distributore.getNome() );
		}
		
		Cliente cliente;
		for(int i=0; i<ListaClienti.LinkListaClienti.size(); i++){
			cliente = ListaClienti.LinkListaClienti.get(i);
			String NOME = cliente.getNome();
			String COGNOME = cliente.getCognome();
			boolean NonTrovatoNome = true;
			boolean NonTrovatoCognome = true;
			
			for(int j=0; j<STR_Nome.size(); j++){
				if(STR_Nome.get(j).equals(NOME)){
					NonTrovatoNome=false;
					break;
				}
				
			}
			for(int j=0; j<STR_Cognome.size(); j++){
				if(STR_Cognome.get(j).equals(COGNOME)){
					NonTrovatoCognome=false;
					break;
				}
			}
			
			if(NonTrovatoNome) STR_Nome.add( cliente.getNome() );
			if(NonTrovatoCognome) STR_Cognome.add( cliente.getCognome() );
			
		}
		
		
		 JCB_Cognome.setModel(new javax.swing.DefaultComboBoxModel( STR_Cognome ));
		 JCB_Nome.setModel(new javax.swing.DefaultComboBoxModel( STR_Nome ));
		 JCB_Distributore.setModel(new javax.swing.DefaultComboBoxModel( STR_Distributore ));
	}
	
	public void filtroCognome(String filtrocognome){
		Vector<String> Tmp_NomiClienti = new Vector();
		Cliente cliente;
		for(int i=0; i<ListaClienti.LinkListaClienti.size(); i++){
			cliente = ListaClienti.LinkListaClienti.get(i);
			if( cliente.getCognome().equals(filtrocognome)){
				Tmp_NomiClienti.add(cliente.getNome());
			}

			
		}
		JCB_Nome.setModel(new javax.swing.DefaultComboBoxModel( Tmp_NomiClienti ));
	}
	
	public void RipristinaCampi(){
		JCB_Cognome.setModel(new javax.swing.DefaultComboBoxModel( STR_Cognome ));
		JCB_Nome.setModel(new javax.swing.DefaultComboBoxModel( STR_Nome ));
	}

	
	public void actionPerformed(ActionEvent AE) {
		String action = AE.getActionCommand();
		
		if( action.equals("comboBoxChanged") ){
		String Cognome = (String) JCB_Cognome.getSelectedItem();
			if( !Cognome.equals(CognomeSelezionato) ){
				CognomeSelezionato=Cognome;
				this.filtroCognome(Cognome);
			}
		}
		
		if( action.equals("Aggiungi Ordinazione")){
			
			if( JT_CodiceProdotto.getText().length()==0 ){
				System.out.println("ERROREEEE");
				return;
			}
			if( JT_Quantita.getText().length()==0){
				System.out.println("ERROREEEE");
				return;
			}
			String codiceprodotto = JT_CodiceProdotto.getText().toUpperCase().replace(" ", "");
			if(!ListaProdotti.LinkListaProdotti.contains( codiceprodotto )){
				System.out.println("NO CODICE");
				return;
			}
			
			try{
			int numero= Integer.parseInt(JT_Quantita.getText());
			System.out.println(numero);
			}catch(Exception e){
				System.out.println("It's not love");
			}

			/*Se passa tutti i controlli inserisce l'ordine*/
			String nome=(String) JCB_Nome.getSelectedItem();
			String cognome=(String) JCB_Cognome.getSelectedItem();
			String coddistr= (String) ListaDistributori.getIDDistributoreDaNome( (String)JCB_Distributore.getSelectedItem() );
			String Campi = "IDOrdine, Quantita, CodiceProdotto, IDCliente, Annotazioni, Cod_Distributore";
			String Valori = "'"+ ListaOrdini.IDUltimoOrdine+"', '"+ JT_Quantita.getText() +"', '"+ codiceprodotto +"', '"+ ListaClienti.getIDClientedaNomeCognome(cognome, nome) +"', '"+ JT_Note.getText()  +"', '"+ coddistr +"'";
			Database.eseguiQuery("INSERT INTO Ordinazione ("+ Campi +") VALUES ("+ Valori +");" );
			JT_CodiceProdotto.setText("");
			JT_Note.setText("");
			String stringa = (String) ListaOrdini.LinkVectorOrdini.get(0);
			TableModelListaOrdinazioni.TMListaOrdinazioni.AggiornaVisualizzazioneOrdini(stringa);
		}
		
		
	}
	
	public void setInfoOrdini(String a, String b, String c, String d){
		JL_ClientiDato.setText(b);
	    JL_DistributoreDato.setText(c);
	    JL_GuadagnoDato.setText(d);
	    JL_PuntiDato.setText(a);
	}
}
