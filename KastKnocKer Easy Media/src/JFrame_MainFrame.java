import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class JFrame_MainFrame extends JFrame implements ActionListener,WindowListener{
	
	public JFrame_MainFrame(){
		super();
		this.addWindowListener(this);
		this.setTitle("KastKnocKer - Easy Media");
		JMenuBar BarraDeiMenu=new JMenuBar();
		JMenu menuFile=new JMenu("File");
		
		JMenuItem MnuovoEsame=new JMenuItem("Nuovo Esame");
		JMenuItem MSalva=new JMenuItem("Salva");
		JMenuItem MfileEsci=new JMenuItem("Esci");
		MnuovoEsame.addActionListener(this);
		MSalva.addActionListener(this);
		MfileEsci.addActionListener(this);
		menuFile.add(MnuovoEsame);
		menuFile.add(MSalva);
		menuFile.add(MfileEsci);
		BarraDeiMenu.add(menuFile);
		this.setJMenuBar(BarraDeiMenu);
		
		Container cmf=getContentPane();
		JPanel_MainPanel mp=new JPanel_MainPanel();
		cmf.add(mp);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String Evento = e.getActionCommand();
		if(Evento.equals("Nuovo Esame")){}
		if(Evento.equals("Salva")){
			Vettore.getLinkVettore().salvaLibreriaFileTxt();
			System.out.println("Voti Salvati");
		}
		if(Evento.equals("Esci")){System.exit(0);}
		
	}

	public void windowClosed(WindowEvent e){System.exit(0);}
	public void windowClosing(WindowEvent e){System.exit(0);}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	

}
