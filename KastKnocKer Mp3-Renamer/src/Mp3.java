import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.org.apache.xml.internal.utils.CharKey;


public class Mp3 {
	boolean isfileSelected=false;
	boolean isdirSelected=false;
	File fileSelected=null;
	Vector VettorefileSelected=null;
	
	
public void importFileMp3(){
		   
	      JFileChooser fileChooser = new JFileChooser();				//Invoco il Fchooser
	      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	//Setto modalità solo per file
	      fileChooser.addChoosableFileFilter (new FileNameExtensionFilter ("File *.mp3", "mp3"));	//Imposto solo mp3
	      int result = fileChooser.showOpenDialog(new JPanel());	
	      if ( result == JFileChooser.CANCEL_OPTION )return;/////Fine Parte Chooser	      
	      
	      fileSelected = fileChooser.getSelectedFile();
	      if(isMp3(fileSelected)){isdirSelected=false; isfileSelected=true;}
}


public void importDirMp3(){
		   
		      JFileChooser fileChooser = new JFileChooser();
		      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		      int result = fileChooser.showOpenDialog(new JPanel());
		      if ( result == JFileChooser.CANCEL_OPTION )return; 
		      /////Fine Parte Chooser	     		   
		      File file = fileChooser.getSelectedFile();
		      File fileDir = new File(file.getAbsolutePath()); 
		         
		      if(fileDir == null) return;
		      if(!fileDir.isDirectory()) return;
		      if(!fileDir.canRead()) return;

		      File[] VettoreFileDir = fileDir.listFiles();
		      Vector <File> VettoreMp3=  new Vector();
for(int i = 0; i < VettoreFileDir.length; i++) {
	if(isMp3(VettoreFileDir[i])==true){ 
		VettoreMp3.add(VettoreFileDir[i]);
		}  
	} 
		  
	if(VettoreMp3.size()>0){isdirSelected=true; isfileSelected=false;}else{isdirSelected=false; isfileSelected=false;}
System.out.println( VettoreMp3.size() );
this.VettorefileSelected=VettoreMp3;
}


public boolean isMp3(File isMp3File){
	String Nome = isMp3File.getName();
	if( Nome.endsWith(".mp3") ) return true;
	return false;
}

public void RinominaMp3(){
	
	if(isfileSelected){
		this.Mp3Renamer(fileSelected);
	}
	
	if(isdirSelected){
		File TmpFile =null;
		for(int i=0;i<VettorefileSelected.size();i++){
			TmpFile=(File) VettorefileSelected.elementAt(i);
			this.Mp3Renamer( TmpFile );
		}
		return;
	}
}

private void Mp3Renamer(File Mp3DaRinominare){
	String NomeFile = Mp3DaRinominare.getName();
	String Autore=null;
	String Titolo=null;
	String Versione=null;
	String NomeUscita=null;
	
	int Lunghezza=NomeFile.length();
	NomeFile = NomeFile.replace("_", " ");
	NomeFile = EliminaNumeriIniziali(NomeFile);
	
	try{
	Autore=Elimina_Spazi_Esterni(GetAutore(NomeFile));
	Titolo=Elimina_Spazi_Esterni(GetTitolo(NomeFile));
	}catch(Exception E){return;}
	Versione=Elimina_Spazi_Esterni(GetVersione(NomeFile));
	
	if(Versione.length()>0) NomeUscita = new String( Autore+ " - " +Titolo+ " " +"("+Versione+").mp3");
	else NomeUscita = new String( Autore+ " - " +Titolo+".mp3");
	
	NomeUscita = NomeUscita.replace("  ", " ");
	NomeUscita = this.Get_Stringa_Nomi_Maiuscoli(NomeUscita);
	
	System.out.println("Path: "+ Mp3DaRinominare.getParent());
	System.out.println(NomeFile + "\n" + NomeUscita);
	System.out.println("Autore: " + Autore+ "\n" + "Titolo: " + Titolo+ "\n" + "Versione: " + Versione);
	Boolean Rinominato;

	Rinominato = Mp3DaRinominare.renameTo(new File( Mp3DaRinominare.getParent()+"/" + NomeUscita ));
	if (Rinominato){
		System.out.println("File rinominato con successo!");
	} else {System.out.println("File NON rinominato!");}
	
	return;
}

private String EliminaNumeriIniziali(String NomeFile){
	int Carattere = NomeFile.charAt(0);
	while( ((Carattere>47)&&(Carattere<58))||(Carattere==40)||(Carattere==41) ){
		NomeFile=NomeFile.substring(1);
		Carattere = NomeFile.charAt(0);
	}
	while( NomeFile.startsWith(" ") || NomeFile.startsWith("-") || NomeFile.startsWith(".")){
		NomeFile=NomeFile.substring(1);
	}
	
	return NomeFile;
}

private String Elimina_Spazi_Esterni(String Stringa){
	while( Stringa.startsWith(" ") ){
		Stringa=Stringa.substring(1);
	}
	
	while( Stringa.endsWith(" ") ){
		Stringa=Stringa.substring(0, Stringa.length()-1);
	}
	return Stringa;
}

private String Get_Stringa_Nomi_Maiuscoli(String Stringa){
	int Lunghezza=Stringa.length();
	int tmp = Stringa.charAt(0);
	String Target=null;
	String Replacement=null;
	if( (tmp>96)&&(tmp<123)){ 
		tmp=tmp-32;
		char Char = (char) tmp;
		Stringa = new String( Char + Stringa.substring(1) );
	}
	boolean TrovatoSpazio=false;
	int tmp2;
	
	for(int i=1;i<Lunghezza;i++){
		tmp = Stringa.charAt(i);
		if( (TrovatoSpazio)&&(tmp>96)&&(tmp<123) ) {
			Target = Stringa.substring(i-1, i+1);
			Replacement=Target.toUpperCase();
			Stringa = Stringa.replace(Target, Replacement);
		}
		if(tmp==32 || tmp==40) TrovatoSpazio=true; else TrovatoSpazio=false;
	}
	
	return Stringa;
}

private String GetAutore(String NomeFile){
	int indiceTrattino = NomeFile.indexOf(45);
	String Autore=NomeFile.substring(0, indiceTrattino);
	return Autore;
}

private String GetTitolo(String NomeFile){
	int indiceTrattino = NomeFile.indexOf(45);  //Inizio
	int indiceParentesiAperta = NomeFile.indexOf(40);
	int indicePunto = NomeFile.indexOf(46);
	int MinIndice;
	if(indiceParentesiAperta >0) {MinIndice=indiceParentesiAperta; }
	else MinIndice=indicePunto;
	
	return NomeFile.substring(indiceTrattino+1, MinIndice);
}

private String GetVersione(String NomeFile){		
	int indiceParentesiAperta = NomeFile.indexOf(40);
	int indiceParentesiChiusa = NomeFile.indexOf(41);
	if ( (indiceParentesiAperta<0)||(indiceParentesiChiusa<0)) return "";
	return NomeFile.substring(indiceParentesiAperta+1, indiceParentesiChiusa);
}

}
