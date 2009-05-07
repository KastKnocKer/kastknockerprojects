package Grafica;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Liste.Database;


public class MainFrame extends JFrame implements WindowListener{
	
	public MainFrame(){
		super("Ordini Profumi");
		this.addWindowListener(this);
		
		/*Barra dei menu*/
		JMenuBar BarraDeiMenu = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		
        JMenuItem itemEsci = new JMenuItem("Esci");	
        menuFile.add(itemEsci);
        
        BarraDeiMenu.add(menuFile);
		this.setJMenuBar(BarraDeiMenu);
		/*Fine Barra dei menu*/
		
		MainPanel mp = new MainPanel();
		this.add(mp);
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setVisible(true);
	}

	public void windowClosed(WindowEvent e){Database.Disconnetti(); System.exit(0);}
	public void windowClosing(WindowEvent e){Database.Disconnetti(); System.exit(0);}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}

