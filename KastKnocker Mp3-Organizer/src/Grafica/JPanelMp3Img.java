package Grafica;

import java.awt.*;

import javax.swing.*;


import Archiviazione.Mp3;
import Controllo.Config;

/** 
 * JPanel personalizzato per la visualizzazione della copertina (Artwork) dell'mp3
 * 
 * @author Castelli Andrea 
 **/

public class JPanelMp3Img extends JPanel{
	Mp3 mp3;
	Image Mp3Image;
	String Artista;
	String Titolo;
	
	/**
	 * Costruttore
	 */
	public JPanelMp3Img(){
		super();
		Toolkit t=Toolkit.getDefaultToolkit();
		Mp3Image=t.getImage( Config.getIndImmagini()+"ImgDefaultMp3.jpg" );	
		setPreferredSize(new Dimension(120,160));
		setBackground(Color.black);
		Artista=new String(" ");
		Titolo=new String(" ");	
		
	}
	/**
	 * Setta l'mp3 di cui visualizzare la copertina
	 * @param mp3
	 */
	public void setMp3ToPlay(Mp3 mp3){
		this.mp3=mp3;
		Mp3Image=mp3.getImgCopertina();
		Artista=mp3.getArtista();
		Titolo=mp3.getTitolo();
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		int dim = 130;
		g.drawImage(Mp3Image, (this.getWidth()-dim)/2, 3, dim, dim, null);
		
		//g.setColor(Color.white);
		g.setColor(Color.white);
		g.drawString(Artista, 3, 145);
		g.drawString(Titolo, 3, 158);


	}
	
	
}
