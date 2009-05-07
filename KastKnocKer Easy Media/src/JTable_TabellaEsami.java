import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class JTable_TabellaEsami extends JTable implements MouseListener,ActionListener{
	private static JTable_TabellaEsami LinkJTable=null;
	private int ClickRiga;
	private int ClickColonna;
	private JPopupMenu JPM;
	
	public JTable_TabellaEsami(TableModel TM){
		super(TM);
		this.addMouseListener(this);
		if(LinkJTable==null) LinkJTable=this;
		JPM=new JPopupMenu();
		JMenuItem JMIP_Elimina = new JMenuItem("Elimina");
		JMIP_Elimina.addActionListener(this);
		JPM.add(JMIP_Elimina);
	}
	
	public static JTable_TabellaEsami getLinkJTable(){
		return LinkJTable;
	}

	
	public void mouseClicked(MouseEvent me) {
		
		ClickRiga = rowAtPoint(me.getPoint());
		ClickColonna = columnAtPoint(me.getPoint());
		System.out.println("Riga: " + ClickRiga);
		this.setRowSelectionInterval(ClickRiga, ClickRiga);
		
		if( me.getButton() == MouseEvent.BUTTON1) {	}
					
		if( me.getButton() == MouseEvent.BUTTON2){/*evento per tasto rotella mouse*/return;}
				
		if( me.getButton() == MouseEvent.BUTTON3){
					JPM.show(this, me.getX(), me.getY());	//Mostro il PopupMenu
					}
			
}
			
		


public void mouseEntered(MouseEvent ME) {}
public void mouseExited(MouseEvent ME) {}
public void mousePressed(MouseEvent ME) {}
public void mouseReleased(MouseEvent ME) {}
public void actionPerformed(MouseEvent ME) {}


public void actionPerformed(ActionEvent e) {
	
	Vettore.getLinkVettore().removeElementAt(ClickRiga);
	JTable_TabellaEsami.getLinkJTable().updateUI();
	JPanel_SudPanel.getLinkSudPanel().Aggiorna();
	
}

}
