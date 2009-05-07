import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


public class JPanel_MainPanel extends JPanel{
	
	JPanel_SudPanel SudPanel=null;
	JTable_TabellaEsami Tabella=null;
	
	public JPanel_MainPanel(){
		this.setLayout(new BorderLayout());
		SudPanel = new JPanel_SudPanel();
		TableModel TM = new TableModel();
		Tabella = new JTable_TabellaEsami(TM);
		JScrollPane Scroll = new JScrollPane(Tabella);
		
		
		this.add(Scroll,BorderLayout.CENTER);
		this.add(SudPanel,BorderLayout.SOUTH);
		
	}

}
