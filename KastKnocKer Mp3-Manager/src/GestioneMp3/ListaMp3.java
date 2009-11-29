package GestioneMp3;

import java.io.*;
import java.util.*;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class ListaMp3 extends Vector{
	public static ListaMp3 listastatica = null;
	public ListaMp3(){
		super();
		if(listastatica == null) listastatica = this;
		
	}
	
	public void caricaMp3daDir(String dir){
		//String dir = new String("E:\\eMule\\Incoming");
		
		File file = new File(dir);
		if( file.isDirectory() ){
			File[] listafile = file.listFiles();
			for(int i=0; i<listafile.length ; i++){
				if( listafile[i].isFile() )
					if( listafile[i].getName().endsWith(".mp3") ) Database.eseguiQuery("INSERT INTO Mp3 (Dir) VALUES ('"+listafile[i].getAbsoluteFile()+"');");
			}
			
		}
	}
	
	public void caricaMp3Ricorsivo(String dir){
		File file = new File(dir);
		if( file.isDirectory() ){
			caricaMp3daDir(file.getAbsolutePath());
			File[] listfiles = file.listFiles();
			for(int i=0; i<listfiles.length; i++){
				if(listfiles[i].isDirectory()) caricaMp3Ricorsivo(listfiles[i].getAbsolutePath());
			}
		}
		
		
	}
	
	public void aggiornaVociDB(){
		Vector Vdir = Database.eseguiQuery("SELECT Dir FROM Mp3;");
		Mp3 mp3 = null;
		
		for(int i=0; i < Vdir.size(); i++){
			String [] record = (String[]) Vdir.elementAt(i);
			try {
				mp3 = new Mp3( new File(record[0]) );
				Database.eseguiQuery("UPDATE Mp3 SET Autore='"+ mp3.getArtista()+"',Titolo='"+mp3.getTitolo()+"' WHERE Dir = '"+record[0]+"';");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
