package Grafica;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Liste.Database;
import Liste.ListaOrdinazioni;


public class MainFrame extends JFrame implements WindowListener, ActionListener{
	
	public MainFrame(){
		super("Ordini Profumi");
		this.addWindowListener(this);
		
		/*Barra dei menu*/
		JMenuBar BarraDeiMenu = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		
        JMenuItem itemEsci = new JMenuItem("Esci");	
        itemEsci.addActionListener(this);
        menuFile.add(itemEsci);
        
        BarraDeiMenu.add(menuFile);
		this.setJMenuBar(BarraDeiMenu);
		/*Fine Barra dei menu*/
		
		MainPanel mp = new MainPanel();
		this.add(mp);
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setExtendedState( JFrame.MAXIMIZED_BOTH );
		setVisible(true);
	}

	public void windowClosed(WindowEvent e){ListaOrdinazioni.riassuntoOrdinazione(); Database.Disconnetti(); System.exit(0);}
	public void windowClosing(WindowEvent e){ListaOrdinazioni.riassuntoOrdinazione(); Database.Disconnetti(); System.exit(0);}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
	public void actionPerformed(ActionEvent AE) {
		String evento = AE.getActionCommand();
		
		if( evento.equals("Esci") ){
			ListaOrdinazioni.riassuntoOrdinazione(); Database.Disconnetti(); System.exit(0);
		}
	}
	
}

