package Controllo;

import javax.swing.*;
import Grafica.*;

/** 
 * Classe di controllo di tutti i componenti del programma
 * contiene tutti i riferimenti ai componenti più importanti
 * permettendo di farli comunicare al meglio e più facilmente tra di loro
 * 
 * @author Castelli Andrea 
 * */

public class Ctrl_Componenti {
	
	private static MainFrame LinkMainFrame=null;
	private static MainPanel LinkMainPanel=null;
	private static JPanelArea_Elenco LinkJPanelArea_Elenco=null;
	private static JPanelArea_Play LinkJPanelArea_Play=null;
	private static JPanelArea_Tabella LinkJPanelArea_Tabella=null;
	private static JPanelArea_Filtri LinkJPanelArea_Filtri=null;
	private static JPanel LinkJPanelMp3Img=null;
	private static JTableTabella LinkJTableTabella=null;
	private static TM_Tabella LinkTM_Tabella=null;
	private static JComboBox LinkJCB_Album;
    private static JComboBox LinkJCB_Anno;
    private static JComboBox LinkJCB_Artista;
    private static JComboBox LinkJCB_Genere;
    private static JList_Artisti LinkJList_Artisti;
	
	/////////////////////////////////////////////
    /**
     * Ritorna il collegamento con il frame principale
     */
	public static MainFrame getLinkMainFrame(){
		return LinkMainFrame;
	}
	/**
	 * Setta il collegamento con il main frame
	 * @param MainFrame
	 */
	public static void setLinkMainFrame(MainFrame JF){
		if (LinkMainFrame==null) LinkMainFrame=JF;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il pannello principale
	 */
	public static MainPanel getLinkMainPanel(){
		return LinkMainPanel;
	}
	/**
	 * Setta il collegamento con il pannello principale
	 * @param MainPanel
	 */
	public static void setLinkMainPanel(MainPanel JP){
		if (LinkMainPanel==null) LinkMainPanel=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il pannello contenenti i filtri
	 */
	public static JPanelArea_Filtri getLinkJPanelArea_Filtri(){
		return LinkJPanelArea_Filtri;
	}
	/**
	 * Setta il collegamento con il pannello contenenti i filtri
	 * @param JPanelArea_Filtri
	 */
	public static void setLinkJPanelArea_Filtri(JPanelArea_Filtri JP){
		if (LinkJPanelArea_Filtri==null) LinkJPanelArea_Filtri=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il pannello contenenti l'immagine dell'mp3 riprodotto
	 */
	public static JPanel getLinkJPanelMp3Img(){
		return LinkJPanelMp3Img;
	}
	/**
	 * Setta il collegamento con il pannello contenenti l'immagine dell'mp3 riprodotto
	 * @param JP
	 */
	public static void setLinkJPanelMp3Img(JPanel JP){
		if (LinkJPanelMp3Img==null) LinkJPanelMp3Img=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il pannello contenenti il riproduttore musicale
	 */
	public static JPanelArea_Play getLinkJPanelArea_Play(){
		return LinkJPanelArea_Play;
	}
	/**
	 * Setta il collegamento con il pannello contenenti il riproduttore musicale
	 * @param JP
	 */
	public static void setLinkJPanelArea_Play(JPanelArea_Play JP){
		if (LinkJPanelArea_Play==null) LinkJPanelArea_Play=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il modello della tabella visualizzante gli mp3
	 */
	public static TM_Tabella getLinkTM_Tabella(){
		return LinkTM_Tabella;
	}
	/**
	 * Setta il collegamento con il modello della tabella visualizzante gli mp3
	 * @param JP
	 */
	public static void setLinkTM_Tabella(TM_Tabella JP){
		if (LinkTM_Tabella==null) LinkTM_Tabella=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con la tabella visualizzante gli mp3
	 */
	public static JTableTabella getLinkJTableTabella(){
		return LinkJTableTabella;
	}
	/**
	 * Setta il collegamento con  la tabella visualizzante gli mp3
	 * @param JP
	 */
	public static void setLinkJTableTabella(JTableTabella JP){
		if (LinkJTableTabella==null) LinkJTableTabella=JP;
	}
/////////////////////////////////////////////
	/**
	 * Ritorna il collegamento con il filtro-Album degli mp3
	 */
	public static JComboBox getLinkJCB_Album(){
		return LinkJCB_Album;
	}
	/**
	 * Setta il collegamento con il filtro-Album degli mp3
	 * @param JCB
	 */
	public static void setLinkJCB_Album(JComboBox JCB){
		if (LinkJCB_Album==null) LinkJCB_Album=JCB;
	}
	///
	/**
	 * Ritorna il collegamento con il filtro-Anno degli mp3
	 */
	public static JComboBox getLinkJCB_Anno(){
		return LinkJCB_Anno;
	}
	/**
	 * Setta il collegamento con il filtro-Anno degli mp3
	 * @param JCB
	 */
	public static void setLinkJCB_Anno(JComboBox JCB){
		if (LinkJCB_Anno==null) LinkJCB_Anno=JCB;
	}
	///
	/**
	 * Ritorna il collegamento con il filtro-Artista degli mp3
	 */
	public static JComboBox getLinkJCB_Artista(){
		return LinkJCB_Artista;
	}
	/**
	 * Setta il collegamento con il filtro-Artista degli mp3
	 * @param JCB
	 */
	public static void setLinkJCB_Artista(JComboBox JCB){
		if (LinkJCB_Artista==null) LinkJCB_Artista=JCB;
	}
	////
	/**
	 * Ritorna il collegamento con il filtro-Genere degli mp3
	 */
	public static JComboBox getLinkJCB_Genere(){
		return LinkJCB_Genere;
	}
	/**
	 * Setta il collegamento con il filtro-Genere degli mp3
	 * @param JCB
	 */
	public static void setLinkJCB_Genere(JComboBox JCB){
		if (LinkJCB_Genere==null) LinkJCB_Genere=JCB;
	}
	////
	/**
	 * Ritorna il collegamento con la Jlist visualizzante l'elenco degli artisti
	 */
	public static JList_Artisti getLinkJList_Artisti(){
		return LinkJList_Artisti;
	}
	/**
	 * Setta il collegamento con la Jlist visualizzante l'elenco degli artisti
	 * @param JLA
	 */
	public static void setLinkJList_Artisti(JList_Artisti JLA){
		if (LinkJList_Artisti==null) LinkJList_Artisti=JLA;
	}
	////
	/**
	 * Ritorna il collegamento con il pannello contenente la tabella degli mp3
	 */
	public static JPanelArea_Tabella getLinkJPanelArea_Tabella(){
		return LinkJPanelArea_Tabella;
	}
	/**
	 * Setta il collegamento con il pannello contenente la tabella degli mp3
	 * @param JLA
	 */
	public static void setLinkJPanelArea_Tabella(JPanelArea_Tabella JLA){
		if (LinkJPanelArea_Tabella==null) LinkJPanelArea_Tabella=JLA;
	}
	
	
	
}
