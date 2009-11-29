package InterfacciaGrafica;

import java.awt.BorderLayout;

import javax.swing.*;


public class JPanel_TabellaMp3 extends JPanel{

	public JPanel_TabellaMp3(){
		super();
		this.setLayout(new BorderLayout() );
		
		
		JTable jt = new JTable(new TM_Mp3());
	
		JScrollPane jsp = new JScrollPane(jt);
		this.add(jsp, java.awt.BorderLayout.CENTER);
		
	}
}


/*

JPSVO = new JPanelSottoVisualizzazioneOrdini();
		
		
		tablemodel = new TableModelListaOrdinazioni();
		TabellaOrdinazioni = new JTable_ListaOrdinazioni(tablemodel);
		Scroll = new JScrollPane(TabellaOrdinazioni);
		this.add(Scroll, java.awt.BorderLayout.CENTER);
		
		
		this.add(JPSVO, java.awt.BorderLayout.SOUTH); 

*/