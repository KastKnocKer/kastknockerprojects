package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import javax.media.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import Archiviazione.Mp3;
import Controllo.*;

/** 
 * JPanel che gestisce la visualizzazione e la riproduzione degli mp3
 * 
 * @author Castelli Andrea 
 **/

public class JPanelArea_Play extends JPanel implements ActionListener,ChangeListener,MouseListener{
	
	private JButton BPlay=null;
	private JButton BStop=null;
	private JButton BPause=null;
	private JSlider Barra=null;
	
	private JPanelMp3Img PannelloImmagine=null;
	private Image Mp3Image;
	private Mp3 mp3;
	
	private File file;
	private Player player;
	private boolean pausa=false;
	private int i;
	private Time durata;
	private Thread_ControlloPlayer Controller=null;
	private boolean AggiornamentoBarra=true;
	
	
	/**
	 * Costruttore
	 */
	public JPanelArea_Play(){
		super();
		Ctrl_Componenti.setLinkJPanelArea_Play(this);
		GroupLayout ButtonLayout = new GroupLayout(this);
		setLayout(ButtonLayout);
		this.setBorder( BorderFactory.createTitledBorder("Player") );
		
		BPlay=new JButton("Play");
		BPlay.setIcon(new ImageIcon(Config.getIndImmagini()+"Play.png"));
		BPause=new JButton("Pause");
		BPause.setIcon(new ImageIcon(Config.getIndImmagini()+"Pause.png"));
		BStop=new JButton("Stop");
		BStop.setIcon(new ImageIcon(Config.getIndImmagini()+"Stop.png"));
		Barra=new JSlider(0, 1000, 0);
		PannelloImmagine=new JPanelMp3Img();

		BPlay.addActionListener(this);
		BStop.addActionListener(this);
		BPause.addActionListener(this);
		
		Barra.addChangeListener(this);
		Barra.addMouseListener(this);
	
		
		
		/*ZONA LAYOUT CREATA CON NETBEANS****/

        java.awt.GridBagConstraints gridBagConstraints;

        this.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        this.add(BPause, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        this.add(BPlay, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        this.add(BStop, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        this.add(Barra, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        this.add(PannelloImmagine, gridBagConstraints);
        
	}
	
	/**
	 * Procedura che riproduce un mp3 se già impostato
	 */
	public void Play(){
		Barra.setValue(0);
		pausa=false;
		AggiornamentoBarra=true;
		
		if(mp3==null) return;
		if (player!=null){player.close();}
		
		file = new File (mp3.getDir());
	    try {
			player = Manager.createRealizedPlayer(file.toURL());
			player.start();
			durata=player.getDuration();
			
			if(Controller==null){
			Controller = new Thread_ControlloPlayer(this);
			Controller.start();
			}
			
		} 
	    catch (NoPlayerException e1) {System.out.println("NoPlayerException - Player Musicale");}
	    catch (CannotRealizeException e1) {
	    	System.out.println("CannotRealizeException - Player Musicale");
	    	JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
	    		    "Il riproduttore musicale non riesce\na riprodurre il brano.",
	    		    "Errore di riproduzione",
	    		    JOptionPane.ERROR_MESSAGE);
	
	    } 
	    catch (MalformedURLException e1) {System.out.println("MalformedURLException - Player Musicale");}
	    catch (IOException e1) {System.out.println("IOException - Player Musicale");}
	    
		
		}
	/**
	 * Procedura che riproduce l'mp3 passato come parametro
	 * @param mp3
	 */
	public void Play(Mp3 mp3){
		AggiornamentoBarra=true;
		this.mp3=mp3;
		PannelloImmagine.setMp3ToPlay(mp3);
		this.Play();
	}
	/**
	 * Procedura che aggiorna la barra di progressione della riproduzione degli mp3
	 */
	public void AggiornaBarra(){
		if(AggiornamentoBarra){
		int posizionesec=(int) player.getMediaTime().getSeconds();
		int percentuale= (posizionesec*1000)/ (int) durata.getSeconds();
		Barra.setValue(percentuale);
		}
	}
	
	/**
	 * Procedura che effettua il pause\resume
	 */
	public void Pause(){
		if (player == null) return;
		
		if(pausa==false){
				pausa=true;
				player.stop();
				}else{
				pausa=false;
				player.syncStart(player.getMediaTime());
				}
			
			
		}
	/**
	 * Procedura che interrompe la riproduzione dell'mp3
	 */
	public void Stop(){
		
		if (player != null) {
			pausa=false;
	        //player.stop();
	        //player.deallocate();
	        player.close();
	        player=null;
	        AggiornamentoBarra=false;
	        
	    }
		
	}
		
	//////////////////////EVENTI DEI BOTTONI/////////////////////////////////////////
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getActionCommand().equals("Play")){this.Play();}
			if(e.getActionCommand().equals("Stop")){this.Stop();}
			if(e.getActionCommand().equals("Pause")){this.Pause();};
			
	
		}
	//////////////////////////////////////Eventi JSlide/////////////////////////////////
	public void stateChanged(ChangeEvent event) {
		if( event.getSource() == Barra )
			{
			i=Barra.getValue();		
			//System.out.println("Barra: "+i);
			}
	
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {AggiornamentoBarra=false;}
	
	public void mouseReleased(MouseEvent e) {
	
	AggiornamentoBarra=true;
	if (player == null) return;
		
		if(pausa==false){	
			player.stop();
			double tempino=(double) ((durata.getSeconds()*i)/1000);
			player.setMediaTime(new Time(tempino));
			player.syncStart( player.getMediaTime() );
			
		}
		
		}
		
	
		
	
	}
