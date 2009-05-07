package Grafica;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import Archiviazione.Libreria;
import Archiviazione.Mp3;
import Archiviazione.Mp3Comparator;
import Controllo.Ctrl_Componenti;
import GestioneArtisti.LibreriaArtisti;

/** 
 * JTable personalizzata: Utilizzata per visualizzare gli mp3, permette anche di interagire con la libreria
 * degli mp3 tramite un menù a tendina e con doppioclick permette di riprodurre il brano musicale
 * 
 * @author Castelli Andrea 
 **/

public class JTableTabella extends JTable implements MouseListener,ActionListener{
	
	private JPopupMenu JPM;
	private JTableHeader JTB;
	private Libreria LibMp3;
	public int ClickRiga;
	public int ClickColonna;
	
	private static TM_Tabella LinkDataModel=null;
	private static JTableTabella LinkTabella=null;
	

	/**
	 * Costruttore
	 * @param DataModel
	 */
	public JTableTabella(TM_Tabella DataModel){
		super(DataModel);
		Ctrl_Componenti.setLinkJTableTabella(this);	//Salvo l'indirizzo sul CTRL
		LinkDataModel=DataModel;
		LibMp3=Libreria.getLibreriaGlobale(); //Carico di default la libreria completa non filtrata
		DataModel.setLibreria(LibMp3);			//Setto la libreria al DataModel
		LibMp3.caricaFiltri();
		
		if(LinkTabella==null) LinkTabella=this;
		this.setName("JTableTabella");
		this.addMouseListener(this);
		
		JTB=this.getTableHeader();
		JTB.setName("JTableHeader");
		JTB.addMouseListener(this);

		JPM =new JPopupMenu();	//Creo il popup menu
		JMenuItem JMIP_Play = new JMenuItem("Riproduci");
		JMenuItem JMIP_Scheda = new JMenuItem("Scheda artista");
		JMenuItem JMIP_Aggiorna = new JMenuItem("Aggiorna");
		JMenuItem JMIP_Proprietà = new JMenuItem("Proprietà");
		JMenuItem JMIP_Elimina = new JMenuItem("Elimina");
		JMIP_Play.addActionListener(this);
		JMIP_Scheda.addActionListener(this);
		JMIP_Aggiorna.addActionListener(this);
		JMIP_Proprietà.addActionListener(this);
		JMIP_Elimina.addActionListener(this);
		
			JPM.add(JMIP_Play);
			JPM.add(JMIP_Scheda);
			JPM.add(JMIP_Aggiorna);
			JPM.add(JMIP_Elimina);
			JPM.add(JMIP_Proprietà);
			
		TableColumn colonna = null;
		for(int i=0;i<LinkDataModel.getColumnCount();i++){
				colonna = this.getColumnModel().getColumn(i);
				switch(i){
				case 0: {colonna.setPreferredWidth(50); break;}		//Colonna indice
				case 5: {colonna.setPreferredWidth(60); break;}		//Colonna anno
				case 6: {colonna.setPreferredWidth(30); break;}		//Colonna num traccia cd
				case 7: {colonna.setPreferredWidth(100); break;}		//Colonna num traccia cd
				default: {colonna.setPreferredWidth(200); break;}
				
				}
		}
		
	}
	/**
	 * Aggiorna la tabella
	 */
	public void Aggiorna(){
	LibMp3.caricaFiltri();
	this.updateUI();
	}
	
	/**
	 * Setta la libreria alla tabella
	 * @param LibMp3
	 */
	public void setLibreria(Libreria LibMp3){
		this.LibMp3=LibMp3;
		LinkDataModel.setLibreria(LibMp3);
		this.updateUI();
	}
	
////////////////////////////////////////////////////////////////////////////////////////////

	public void mouseClicked(MouseEvent me) {
		
	ClickRiga = rowAtPoint(me.getPoint());
	ClickColonna = columnAtPoint(me.getPoint());
		
	this.setRowSelectionInterval(ClickRiga, ClickRiga);
	
		if(me.getComponent().getName().equals("JTableTabella") ){
			
			if( me.getButton() == MouseEvent.BUTTON1) {
				if( me.getClickCount()==1 ) return;	
				JPanelArea_Play JPAP=Ctrl_Componenti.getLinkJPanelArea_Play();
				JPAP.Play( (Mp3)LibMp3.getObjPos(ClickRiga) );
				return;
			}
				
			if( me.getButton() == MouseEvent.BUTTON2){/*evento per tasto rotella mouse*/return;}
			
			if( me.getButton() == MouseEvent.BUTTON3){
				JPM.show(this, me.getX(), me.getY());	//Mostro il PopupMenu
				return;
				}
		
			return;
			}
		
		if(me.getComponent().getName().equals("JTableHeader") ){
			Libreria Lib_Supporto=LibMp3;
			switch(ClickColonna){
			case 0: this.Aggiorna(); break;
			case 1: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Artista); break;
			case 2: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Titolo); break;
			case 3: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Album); break;
			case 4: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Genere); break;
			case 5: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Anno); break;
			case 6: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_NTraccia); break;
			case 7: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_TagId); break;
			case 8: LibMp3=new Libreria(); LibMp3.getComparator().setMode(Mp3Comparator.Key_Sort_Dir); break;
			}
			LibMp3.caricaLibreria( Lib_Supporto );
			
			this.setLibreria(LibMp3);
			return;
	}
	
	
	}
	public void mouseEntered(MouseEvent me) {}
	
	public void mouseExited(MouseEvent me) {}

	public void mousePressed(MouseEvent me) {}

	public void mouseReleased(MouseEvent me) {}

///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		String Evento= e.getActionCommand();
		if (Evento.equals("Riproduci")){
			JPanelArea_Play JPAP=Ctrl_Componenti.getLinkJPanelArea_Play();
			JPAP.Play( (Mp3)LibMp3.getObjPos(ClickRiga) );
			}
		
		if (Evento.equals("Scheda artista")){
			Mp3 mp3 = ( (Mp3)LibMp3.getObjPos(ClickRiga) );
			LibreriaArtisti LibArt=new LibreriaArtisti();
			LibArt=LibreriaArtisti.getLibreriaArtistiGlobale();
			
			if( LibArt.contains(mp3.getArtista()) ){
			LibArt.visualizzaSchedaArtista(mp3.getArtista());
			}else{//Crea scheda
			LibArt.creaSchedaArtista(mp3);
			}
			
			}
		
		if (Evento.equals("Aggiorna")){
			Mp3 mp3=(Mp3) LibMp3.getObjPos(ClickRiga);
			mp3.Aggiorna();
			Libreria.getLibreriaGlobale().salvaLibreriaFile();
			this.updateUI();
			}
		
		if (Evento.equals("Elimina")){
			
			Mp3 mp3Riga= ((Mp3) LibMp3.getObjPos(ClickRiga)); 
			
			int n = JOptionPane.showConfirmDialog(new JFrame(),
				    "Vuoi veramente cancellare il brano:\n"+mp3Riga.getArtista()+" - "+mp3Riga.getTitolo()+"?",
				    "Avviso eliminazione brano musicale",
				    JOptionPane.YES_NO_OPTION);

			if(n==0){//////Nel caso in cui si acconsenta 
				
			Libreria LibGlob=Libreria.getLibreriaGlobale();
			LibGlob.remove(mp3Riga);
			LibGlob.salvaLibreriaFile();
			this.setLibreria(LibGlob);
			
			}
			
			}
		
		if (Evento.equals("Proprietà")){
			
			Mp3 mp3Riga= ((Mp3) LibMp3.getObjPos(ClickRiga));
			if ( mp3Riga.hasTagID3v2() ) new JDialogTagModifier(mp3Riga);
			else JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
				    "La modifica dei tag per ora è solo supportata\nper i Tag Id3v2",
				    "Attenzione!",
				    JOptionPane.WARNING_MESSAGE);

			}
		
	}
	

}
