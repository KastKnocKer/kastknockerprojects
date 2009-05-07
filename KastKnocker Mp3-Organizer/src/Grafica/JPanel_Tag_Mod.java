package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagFieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3v23FieldKey;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;


import Archiviazione.Libreria;
import Archiviazione.Mp3;
import Controllo.Config;
import Controllo.Ctrl_Componenti;

/** 
 * JPanel personalizzato per permettere all'utente di modificare i metadati dell'mp3 scelto
 * 
 * @author Castelli Andrea 
 **/

public class JPanel_Tag_Mod extends JPanel implements ActionListener{
	private JDialogTagModifier JD_Contenitore;
    private JButton JB_Annulla;
    private JButton JB_Salva;
    private JButton JB_Cover;
    private JLabel JL_Album;
    private JLabel JL_Anno;
    private JLabel JL_Artista;
    private JLabel JL_Bitrate;
    private JLabel JL_Channels;
    private JLabel JL_Genere;
    private JLabel JL_Lyrics;
    private JLabel JL_NumTraccia;
    private JLabel JL_SimpleRate;
    private JLabel JL_Titolo;
    private JScrollPane JTAScrollPane;
    private JTextArea JTA_Lyrics;
    private JTextField JTF_Album;
    private JTextField JTF_Anno;
    private JTextField JTF_Artista;
    private JTextField JTF_Bitrate;
    private JTextField JTF_Channels;
    private JTextField JTF_Genere;
    private JTextField JTF_NumTraccia;
    private JTextField JTF_SimpleRate;
    private JTextField JTF_Titolo;
    private Image Mp3Image;
	private Mp3 fileMp3;
	private Mp3 mp3Ricaricato=null;

	/**
	 * Costruttore
	 * @param fileMp3
	 */
    	public JPanel_Tag_Mod(Mp3 fileMp3) {
    		super();
    		this.fileMp3=fileMp3;
			        JL_Titolo = new JLabel();
			        JTF_Titolo = new JTextField();
			        JL_Artista = new JLabel();
			        JTF_Artista = new JTextField();
			        JL_Bitrate = new JLabel();
			        JTF_Bitrate = new JTextField();
			        JL_Album = new JLabel();
			        JTF_Album = new JTextField();
			        JL_Channels = new JLabel();
			        JTF_Channels = new JTextField();
			        JL_SimpleRate = new JLabel();
			        JTF_SimpleRate = new JTextField();
			        JL_NumTraccia = new JLabel();
			        JTF_NumTraccia = new JTextField();
			        JL_Anno = new JLabel();
			        JTF_Anno = new JTextField();
			        JL_Genere = new JLabel();
			        JTF_Genere = new JTextField();
			        JTAScrollPane = new JScrollPane();
			        JTA_Lyrics = new JTextArea();
			        JL_Lyrics = new JLabel();
			        JB_Salva = new JButton();
			        JB_Annulla = new JButton();
			        JB_Cover = new JButton();
			        
			        JL_Titolo.setText("Titolo");
			        JTF_Titolo.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Artista.setText("Artista");
			        JTF_Artista.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Bitrate.setText("Bitrate");
			        JTF_Bitrate.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Album.setText("Album");
			        JTF_Album.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Channels.setText("Channels");
			        JTF_Channels.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_SimpleRate.setText("Sample Rate");
			        JTF_SimpleRate.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_NumTraccia.setText("#");
			        JTF_NumTraccia.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Anno.setText("Anno");
			        JTF_Anno.setPreferredSize(new java.awt.Dimension(180, 20));
			        JL_Genere.setText("Genere");
			        JTF_Genere.setPreferredSize(new java.awt.Dimension(180, 20));
			        
			        JTA_Lyrics.setColumns(20);
			        JTA_Lyrics.setRows(5);
			        JTAScrollPane.setViewportView(JTA_Lyrics);

			        JL_Lyrics.setText("Lyrics");
			        
			        JB_Salva.setText("Salva");
			        JB_Annulla.setText("Annulla");
			        JB_Cover.setText("Carica Cover");

			        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
			        this.setLayout(layout);
			        layout.setHorizontalGroup(
			            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			            .addGroup(layout.createSequentialGroup()
			                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			                    .addGroup(layout.createSequentialGroup()
			                        .addContainerGap()
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Artista)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Artista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Genere)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Genere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_NumTraccia)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_NumTraccia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Anno)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Anno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Channels)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Channels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_SimpleRate)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_SimpleRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Bitrate)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Bitrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Album)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                                .addComponent(JL_Titolo)
			                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                                .addComponent(JTF_Titolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			                            .addComponent(JTAScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
			                            .addComponent(JL_Lyrics)))
			                    .addGroup(layout.createSequentialGroup()
			                        .addGap(179, 179, 179)
			                        .addComponent(JB_Salva)
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addComponent(JB_Annulla)
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addComponent(JB_Cover)))
			                .addContainerGap(39, Short.MAX_VALUE))
			        );
			        layout.setVerticalGroup(
			            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                .addContainerGap(186, Short.MAX_VALUE)
			                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Artista)
			                            .addComponent(JTF_Artista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Titolo)
			                            .addComponent(JTF_Titolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addGap(8, 8, 8)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Album)
			                            .addComponent(JTF_Album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addGap(5, 5, 5)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Genere)
			                            .addComponent(JTF_Genere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Bitrate)
			                            .addComponent(JTF_Bitrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_SimpleRate)
			                            .addComponent(JTF_SimpleRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Channels)
			                            .addComponent(JTF_Channels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_Anno)
			                            .addComponent(JTF_Anno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                            .addComponent(JL_NumTraccia)
			                            .addComponent(JTF_NumTraccia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
			                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			                        .addComponent(JL_Lyrics)
			                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			                        .addComponent(JTAScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
			                        .addGap(1, 1, 1)))
			                .addGap(16, 16, 16)
			                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			                    .addComponent(JB_Salva)
			                    .addComponent(JB_Annulla)
			                    .addComponent(JB_Cover))
			                .addContainerGap())
			        );
			        
		JB_Salva.addActionListener(this);
        JB_Annulla.addActionListener(this);
        JB_Cover.addActionListener(this);
        JTF_Bitrate.setEditable(false);
        JTF_Channels.setEditable(false);
        JTF_SimpleRate.setEditable(false);       
        
        Mp3Image=fileMp3.getImgCopertina();
		JTF_Album.setText(fileMp3.getAlbum());
		JTF_Anno.setText(fileMp3.getAnno());
	    JTF_Artista.setText(fileMp3.getArtista());
	    JTF_Bitrate.setText(Integer.toString(fileMp3.getBitrate()));
	    JTF_Channels.setText(fileMp3.getChannels());
	    JTF_Genere.setText(fileMp3.getGenere());
	    JTF_NumTraccia.setText(fileMp3.getNumTracciaCd());
	    JTF_SimpleRate.setText(Integer.toString(fileMp3.getSampleRate()));
	    JTF_Titolo.setText(fileMp3.getTitolo());
	    JTA_Lyrics.setText(fileMp3.getTestoCanzone());
        

}


    	/**
    	 * Procedura per salvare i tag versione ID3V2
    	 */
		public void salvaTagId3v2() {
			
			AbstractID3v2Tag tag=null;
			
			tag=mp3Ricaricato.getID3v2Tag();
			String Tag_Artista=JTF_Artista.getText();
			String Tag_Titolo=JTF_Titolo.getText();
			String Tag_Album=JTF_Album.getText();
			String Tag_NumTraccia=JTF_NumTraccia.getText();
			String Tag_Genere=JTF_Genere.getText();
			String Tag_Anno=JTF_Anno.getText();
			String TestoCanzone=JTA_Lyrics.getText();
				
			try {
				tag.setArtist(Tag_Artista);
				tag.setTitle(Tag_Titolo);
				tag.setAlbum(Tag_Album);
				tag.setGenre(Tag_Genere);
				tag.setTrack(Tag_NumTraccia);
				tag.setYear(Tag_Anno);
				
				///////////////////////////////
				
				
				if ( tag.hasFrameAndBody(ID3v23FieldKey.LYRICS.getFieldName()) ){
					
					ID3v23Frame lyricframe;
					lyricframe = (ID3v23Frame) ((AbstractID3v2Tag) tag).getFrame(ID3v23FieldKey.LYRICS.getFieldName());
					FrameBodyUSLT tmp = (FrameBodyUSLT) lyricframe.getBody();
					tmp.setLyric(TestoCanzone);
					lyricframe.setBody(tmp);
					((AbstractID3v2Tag) tag).setFrame(lyricframe);
					
				}else{
					
					//Se non ha il campo dei lyrics
					
				}


				///////////////////////////////
				
				} catch (FieldDataInvalidException E) { System.out.println("ERRORE MODIFICA TAG"); }	
				
			try {
				mp3Ricaricato.commit();
			} catch (CannotWriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileMp3.Aggiorna();
			JD_Contenitore.dispose();	
			Ctrl_Componenti.getLinkJTableTabella().updateUI();
				
				
				
			
			
			
		}
		/**
		 * Setta il JDialog in cui è contenuto questo pannello
		 * @param JD
		 */
		public void setJD_Contenitore(JDialogTagModifier JD){ JD_Contenitore=JD; }
		
		private void setCoverArt(){		
			
			JFileChooser fileChooser = new JFileChooser();				//Invoco il Fchooser
		    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	//Setto modalità solo per file
		    int result = fileChooser.showOpenDialog(new JPanel());	
		    if ( result == JFileChooser.CANCEL_OPTION )return;
		    /////Fine Parte Chooser	      
		    File file = fileChooser.getSelectedFile();
		    boolean Flag = false;
		    String ImgExt = null;
		    
		    if(Utils.getExtension(file).equals("jpg")){ ImgExt="image/jpg"; Flag=true; }
		    if(Utils.getExtension(file).equals("png")){ ImgExt="image/png"; Flag=true; }
		    if(Utils.getExtension(file).equals("bmp")){ ImgExt="image/bmp"; Flag=true; }
		    
		    if( Flag ){
		    	
				ID3v23Tag tag = (ID3v23Tag) mp3Ricaricato.getID3v2Tag();
				RandomAccessFile imageFile = null;
				byte[] imagedata = null;
				
				try { imageFile = new RandomAccessFile( file, "r"); } 
				catch (FileNotFoundException e) {return;}
				try { imagedata = new byte[(int) imageFile.length()]; } 
				catch (IOException e) {return;}
				try { imageFile.read(imagedata); } 
				catch (IOException e) {return;}
				try {
					
					if ( tag.getFirst(TagFieldKey.COVER_ART).isEmpty() ){
						
						tag.deleteTagField(TagFieldKey.COVER_ART);
						tag.add(((AbstractID3v2Tag) tag).createArtworkField(imagedata,ImgExt));
					}
					else
						tag.add(((AbstractID3v2Tag) tag).createArtworkField(imagedata,ImgExt));
				} catch (FieldDataInvalidException e) {return;}
				
				mp3Ricaricato.setTag(tag);
				try {
					mp3Ricaricato.commit();
				} catch (CannotWriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileMp3.Aggiorna();
				JD_Contenitore.dispose();	
				Ctrl_Componenti.getLinkJTableTabella().updateUI();
		    }
		    
		}
		
		private boolean ricaricaMp3(){
			
			try {
				mp3Ricaricato = new Mp3(new File(fileMp3.getDir()) );
			} catch (IOException e) {
				System.out.println("IOException - Load Mp3 - TagPanel");
				JD_Contenitore.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
					    "Il brano è attualmente in uso!\nI tag non possono esser al momento modificati!",
					    "Attenzione!",
					    JOptionPane.WARNING_MESSAGE);
				JD_Contenitore.setAlwaysOnTop(true);
				return false; //Mp3 NON Caricato correttamente
				} catch (TagException e) {
				System.out.println("TagException - Load Mp3 - TagPanel");
				JD_Contenitore.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
						"Il brano è attualmente in uso!\nI tag non possono esser al momento modificati!",
						"Attenzione!",
						JOptionPane.WARNING_MESSAGE);
				JD_Contenitore.setAlwaysOnTop(true);
				return false; //Mp3 NON Caricato correttamente
				} catch (ReadOnlyFileException e) {
					System.out.println("ReadOnlyFileException - Load Mp3 - TagPanel");
				return false; //Mp3 NON Caricato correttamente
				}catch (InvalidAudioFrameException e) {
					System.out.println("InvalidAudioFrameException - Load Mp3 - TagPanel");
				return false; //Mp3 NON Caricato correttamente
				}
			return true; //Mp3 Caricato correttamente
		}
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			int dim = 170;
			g.drawImage(Mp3Image, 80, 5, dim, dim, null);
			
			g.drawString(fileMp3.getArtista(), 270, 100);
			g.drawString(fileMp3.getTitolo(), 270, 130);


		}


		public void actionPerformed(ActionEvent e) {
			String Evento= e.getActionCommand();
			
			if (Evento.equals("Salva")){ 
				if ( ricaricaMp3() ){

					if ( fileMp3.hasTagID3v2() )  { AbstractID3v2Tag Tag=fileMp3.getID3v2Tag();
					} else {System.out.println("NO TAG ID3V2"); return;}
					int n = JOptionPane.showConfirmDialog(this,
						    "Vuoi veramente modificare le informazioni del brano?",
						    "Modifica Tag",
						    JOptionPane.YES_NO_OPTION);

					if(n==0){ 
						this.salvaTagId3v2();
						Libreria.getLibreriaGlobale().salvaLibreriaFile();
					} //Salva tag modificati!
					
				}
				
				
				
			}
			
			if (Evento.equals("Annulla")){
				JD_Contenitore.dispose();
			}
			if (Evento.equals("Carica Cover")){
				if ( ricaricaMp3() ){
					JD_Contenitore.setAlwaysOnTop(false);
					this.setCoverArt();	
					JD_Contenitore.setAlwaysOnTop(true);
				}
				
			}
			
			
		}


		
}