package GestioneMp3;

import java.io.*;
import javax.swing.*;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.*;


/** 
 * Classe Mp3 serializzabile che estende la classe MP3File della libreria JAudioTagger
 * 
 * @author Castelli Andrea 
 * */

@SuppressWarnings("serial")
public class Mp3 extends MP3File implements Serializable {
	
	private static final String Sconosciuto = "Sconosciuto";
	private static final String StringID3v1Tag = "ID3v1";
	private static final String StringID3v2Tag = "ID3v2";
	private static final String StringNoTag = "NoTag";
	
	private int Bitrate;
	private int SampleRate;
	private String Artista=null;
	private String Titolo=null;
	private String Album=null;
	private String Genere=null;
	private String TestoCanzone=null;
	private String NumTracciaCd=null;
	private String Anno=null;
	private String Channels=null;
	private String indirizzoDir=null;
	private ImageIcon IconCopertina=null;
	
	private String TagVersion=null;
	private boolean ID3v1Tag=false;
	private boolean ID3v2Tag=false;
	private boolean Copertina=false;
	private boolean Lyric=false;
	
	transient MP3AudioHeader mp3AudioHeader=null;

	
	public Mp3(File file) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		
		super(file,LOAD_ALL);
		indirizzoDir=file.getAbsolutePath();
		
	///Leggo i metadati
		
		mp3AudioHeader =  (MP3AudioHeader) this.getAudioHeader(); 
		Bitrate	= (int) mp3AudioHeader.getBitRateAsNumber();
		SampleRate =	mp3AudioHeader.getSampleRateAsNumber();
		Channels =		mp3AudioHeader.getChannels();
		
		if( this.hasID3v1Tag() ) {ID3v1Tag=true; TagVersion=StringID3v1Tag;}
		if( this.hasID3v2Tag() ) {ID3v2Tag=true; TagVersion=StringID3v2Tag; }
		if( !ID3v1Tag && !ID3v2Tag ) TagVersion=StringNoTag;
		
		
		if( ID3v2Tag ){
			AbstractID3v2Tag tag= this.getID3v2Tag(); 
			
			Artista=tag.getFirst(FieldKey.ARTIST).toString();
			Titolo=tag.getFirst(FieldKey.TITLE).toString();
			//Album=tag.getFirst(FieldKey.ALBUM).toString();
			//Genere=tag.getFirst(FieldKey.GENRE).toString();
			//Genere=this.GenereDaNumero();
			//NumTracciaCd=tag.getFirst(FieldKey.TRACK).toString();
			//Anno=tag.getFirst(FieldKey.YEAR).toString();
			
			/*
			Artista=tag.getFirstArtist();
			Titolo=tag.getFirstTitle();
			Album=tag.getFirstAlbum();
			Genere=tag.getFirstGenre();
			Genere=this.GenereDaNumero();
			NumTracciaCd=tag.getFirstTrack();
			Anno=tag.getFirstYear();
			*/
		//	AggiornaLyric();
		//	AggiornaCopertina();
			return;
		}
		
		if( ID3v1Tag ){
			ID3v1Tag tag=this.getID3v1Tag();
			
	    	Artista=tag.getArtist().toString();
			Titolo=tag.getTitle().toString();
			Album=tag.getAlbum().toString();
			TestoCanzone=tag.getFirstComment();
			//NumTracciaCd=tag.getFirstTrack();
			Anno=tag.getFirstYear();
			Genere=tag.getFirstGenre();
			Genere=this.GenereDaNumero();
			TestoCanzone=null;
			return;
		}
		
		

	}
	/**
	 * Aggiorna i dati dell'mp3
	 */
	public void Aggiorna(){
		
	Mp3 mp3;
	try {
		
		mp3 = new Mp3( new File( this.getDir() ) );
		Artista=mp3.getArtista();
		Titolo=mp3.getTitolo();
		Album=mp3.getAlbum();
		Genere=mp3.getGenere();
		TestoCanzone=mp3.getTestoCanzone();
		NumTracciaCd=mp3.getNumTracciaCd();
		Bitrate=mp3.getBitrate();
		SampleRate=mp3.getSampleRate();
		Anno=mp3.getAnno();
		Channels=mp3.getChannels();
		IconCopertina=mp3.getIconCopertina();
		Copertina=mp3.hasCopertina();
		Lyric=mp3.hasLyric();
		
	} catch (IOException e) {
		System.out.println("IOException - Creazione Mp3 da Dir");
		/*
		JOptionPane.showMessageDialog(Ctrl_Componenti.getLinkMainFrame(),
			    "Il brano potrebbe non esser stato aggiornato perchè già in uso!",
			    "Attenzione!",
			    JOptionPane.WARNING_MESSAGE);*/

	} catch (TagException e) {
		System.out.println("TagException - Creazione Mp3 da Dir");
	} catch (ReadOnlyFileException e) {
		System.out.println("ReadOnlyFileException - Creazione Mp3 da Dir");
	} catch (InvalidAudioFrameException e) {
		System.out.println("InvalidAudioFrameException - Creazione Mp3 da Dir");
	}
	
	
	
	}
	
	
	
	
	
	
	
	
	/**
	 * Ritorna l'immagine della copertina dell'mp3
	 * @return Image
	 */
	/*
	public Image getImgCopertina(){
		if(IconCopertina==null){
		Toolkit t=Toolkit.getDefaultToolkit();
		return t.getImage( Config.getIndImmagini()+"ImgDefaultMp3.jpg" );	
		}
		return IconCopertina.getImage();
	}
	*/
	/**
	 * Ritorna l'immagine della copertina in formato icona
	 * @return ImageIcon
	 */
	public ImageIcon getIconCopertina(){
		return IconCopertina;
		}
	/**
	 * Ritorna il nome dell'artista dell'mp3
	 * @return String Artista
	 */
	public String getArtista(){
		if ( (Artista!=null)&&(Artista.length()>0) ) return Artista;
		return Sconosciuto;
	}
	/**
	 * Ritorna il titolo dell'mp3
	 * @return String Artista
	 */
	public String getTitolo(){
		if ( (Titolo!=null)&&(Titolo.length()>0) ) return Titolo;
		return Sconosciuto;
	}
	/**
	 * Ritorna l'album di appartenenza dell'mp3
	 * @return String Album
	 */
	public String getAlbum(){
		if ( (Album!=null)&&(Album.length()>0) ) return Album;
		return Sconosciuto;
	}
	/**
	 * Ritorna il genere dell'mp3
	 * @return String genere
	 */
	public String getGenere(){
		if ( (Genere!=null)&&(Genere.length()>0) ) return Genere;
		return Sconosciuto;
	}
	/**
	 * Ritorna il testo della canzone
	 * @return String Testo canzone
	 */
	public String getTestoCanzone(){
		if ( (TestoCanzone!=null)&&(TestoCanzone.length()>0) ) return TestoCanzone;
		return Sconosciuto;
	}
	/**
	 * Ritorna la directory dell'mp3
	 * @return String Directory
	 */
	public String getDir(){
		if ( (indirizzoDir!=null)&&(indirizzoDir.length()>0) ) return indirizzoDir;
		return Sconosciuto;
	}
	/**
	 * Ritorna il numero della traccia del cd
	 * @return String Numero traccia del cd
	 */
	public String getNumTracciaCd(){
		if ( (NumTracciaCd!=null)&&(NumTracciaCd.length()>0) ) return NumTracciaCd;
		return Sconosciuto;
	}
	/**
	 * Ritorna i bitrate dell'mp3
	 * @return int Bitrate
	 */
	public int getBitrate(){
		return Bitrate;
	}
	/**
	 * Ritorna i Samplerate dell'mp3
	 * @return int Samplerate
	 */
	public int getSampleRate(){
		return SampleRate;
	}
	/**
	 * Ritorna l'anno dell'mp3
	 * @return String Anno
	 */
	public String getAnno(){
		if ( (Anno!=null)&&(Anno.length()>0) ) return Anno;
		return Sconosciuto;
	}
	/**
	 * Ritorna i canali dell'mp3
	 * @return String Channels
	 */
	public String getChannels(){
		if (Channels!=null) return Channels;
		return Sconosciuto;
	}
	/**
	 * Ritorna la versione dei tag
	 * @return String Tagversion
	 */
	public String getTagVersion(){
		if (TagVersion!=null) return TagVersion;
		return Sconosciuto;
	}
	/**
	 * Ritorna come stringa l'autore ed il titolo dell'mp3
	 * @return String
	 */
	public String toString(){
		System.out.println(getArtista()+" - "+getTitolo());
		return getArtista()+" - "+getTitolo();
	}
	/**
	 * Indica se l'mp3 è uguale ad un mp3 dato
	 * @param Mp3
	 * @return boolean
	 */
	public boolean equals(Mp3 inMp3){
		return inMp3.getDir().equals(this.indirizzoDir);
	}
	/**
	 * Indica se l'mp3 possiede i tag ID3v1
	 * @return boolean
	 */
	public boolean hasTagID3v1(){
		return ID3v1Tag;
	}
	/**
	 * Indica se l'mp3 possiede i tag ID3v2
	 * @return boolean
	 */
	public boolean hasTagID3v2(){
		return ID3v2Tag;
	}
	/**
	 * Indica se l'mp3 possiede la copertina
	 * @return boolean
	 */
	public boolean hasCopertina(){return Copertina;}
	/**
	 * Indica se l'mp3 possiede il testo della canzone
	 * @return boolean
	 */
	public boolean hasLyric(){return Lyric;}

	/**
	 * Costruttore
	 * @param file
	 * @throws IOException
	 * @throws TagException
	 * @throws ReadOnlyFileException
	 * @throws InvalidAudioFrameException
	 */

	/**
	 * Aggiorna la copertina dell'mp3
	 */
	/*
	public void AggiornaCopertina(){
		
		try{
            ID3v23Tag tag = (ID3v23Tag) this.getTag();
            if (tag.getFirst(TagFieldKey.COVER_ART).isEmpty()) return;
            try {
                    byte[] artwork = null;
                    ID3v23Frame apicframe = (ID3v23Frame) tag.getFrame("APIC");
                    FrameBodyAPIC cc = (FrameBodyAPIC) apicframe.getBody();
                    artwork = (byte[]) cc.getObjectValue(org.jaudiotagger.tag.datatype.DataTypes.OBJ_PICTURE_DATA);
                    IconCopertina = new ImageIcon(artwork);
                    Copertina=true;
            }
            catch (Exception e) {
            	IconCopertina=null;
            }
    }
    catch (Exception e) {
    		IconCopertina=null;
    }
		
	}*/
	/**
	 * Aggiorna il testo della canzone dell'mp3
	 */
	/*
	public void AggiornaLyric(){
		try{
			   ID3v23Tag tag = (ID3v23Tag) this.getTag();
			   if (tag.getFirst(TagFieldKey.LYRICS).isEmpty()) return;
			   ID3v23Frame lyricframe = (ID3v23Frame) tag.getFrame(org.jaudiotagger.tag.id3.ID3v23FieldKey.LYRICS.getFieldName());
			   FrameBodyUSLT FBU = (FrameBodyUSLT) lyricframe.getBody();
			   TestoCanzone = FBU.getFirstTextValue();
			   if( (TestoCanzone.length()>0)&&(!TestoCanzone.equals(Sconosciuto)) )Lyric=true;
			   
			}
			catch(Exception e){
				TestoCanzone = null;
			}
	}
	*/
	/**
	 * Traduce i metadati del genere dei tag ID3v1 in stringhe
	 * @return String
	 */
	public String GenereDaNumero(){
		try{
		if (Genere.startsWith("(")) {
			 int index = Genere.lastIndexOf(")");
			 Genere = Genere.substring(0, index);
			 Genere = Genere.replace("(", "");
			 int i = new Integer(Genere);
			 switch (i) {
				case   0 : return "Blues"; 
				case   1 : return "Classic Rock";
				case   2 : return "Country";
				case   3 : return "Dance";
				case   4 : return "Disco";
				case   5 : return "Funk";
				case   6 : return "Grunge";
				case   7 : return "Hip-Hop";
				case   8 : return "Jazz";
				case   9 : return "Metal";
				case  10 : return "New Age";
				case  11 : return "Oldies";
				case  12 : return "Other";
				case  13 : return "Pop";
				case  14 : return "R&B";
				case  15 : return "Rap";
				case  16 : return "Reggae";
				case  17 : return "Rock";
				case  18 : return "Techno";
				case  19 : return "Industrial";
				case  20 : return "Alternative";
				case  21 : return "Ska";
				case  22 : return "Death Metal";
				case  23 : return "Pranks";
				case  24 : return "Soundtrack";
				case  25 : return "Euro-Techno";
				case  26 : return "Ambient";
				case  27 : return "Trip-Hop";
				case  28 : return "Vocal";
				case  29 : return "Jazz Funk";
				case  30 : return "Fusion";
				case  31 : return "Trance";
				case  32 : return "Classical";
				case  33 : return "Instrumental";
				case  34 : return "Acid";
				case  35 : return "House";
				case  36 : return "Game";
				case  37 : return "Sound Clip";
				case  38 : return "Gospel";
				case  39 : return "Noise";
				case  40 : return "AlternRock";
				case  41 : return "Bass";
				case  42 : return "Soul";
				case  43 : return "Punk";
				case  44 : return "Space";
				case  45 : return "Meditative";
				case  46 : return "Instrumental Pop";
				case  47 : return "Instrumental Rock";
				case  48 : return "Ethnic";
				case  49 : return "Gothic";
				case  50 : return "Darkwave";
				case  51 : return "Techno-Industrial";
				case  52 : return "Electronic";
				case  53 : return "Pop-Folk";
				case  54 : return "Eurodance";
				case  55 : return "Dream";
				case  56 : return "Southern Rock";
				case  57 : return "Comedy";
				case  58 : return "Cult";
				case  59 : return "Gangsta";
				case  60 : return "Top 40";
				case  61 : return "Christian Rap";
				case  62 : return "Pop/Funk";
				case  63 : return "Jungle";
				case  64 : return "Native American";
				case  65 : return "Cabaret";
				case  66 : return "New Wave";
				case  67 : return "Psychadelic";
				case  68 : return "Rave";
				case  69 : return "Showtunes";
				case  70 : return "Trailer";
				case  71 : return "Lo-Fi";
				case  72 : return "Tribal";
				case  73 : return "Acid Punk";
				case  74 : return "Acid Jazz";
				case  75 : return "Polka";
				case  76 : return "Retro";
				case  77 : return "Musical";
				case  78 : return "Rock & Roll";
				case  79 : return "Hard Rock";
				case  80 : return "Folk";
				case  81 : return "Folk-Rock";
				case  82 : return "National Folk";
				case  83 : return "Swing";
				case  84 : return "Fast Fusion";
				case  85 : return "Bebob";
				case  86 : return "Latin";
				case  87 : return "Revival";
				case  88 : return "Celtic";
				case  89 : return "Bluegrass";
				case  90 : return "Avantgarde";
				case  91 : return "Gothic Rock";
				case  92 : return "Progressive Rock";
				case  93 : return "Psychedelic Rock";
				case  94 : return "Symphonic Rock";
				case  95 : return "Slow Rock";
				case  96 : return "Big Band";
				case  97 : return "Chorus";
				case  98 : return "Easy Listening";
				case  99 : return "Acoustic";
				case 100 : return "Humour";
				case 101 : return "Speech";
				case 102 : return "Chanson";
				case 103 : return "Opera";
				case 104 : return "Chamber Music";
				case 105 : return "Sonata";
				case 106 : return "Symphony";
				case 107 : return "Booty Bass";
				case 108 : return "Primus";
				case 109 : return "Porn Groove";
				case 110 : return "Satire";
				case 111 : return "Slow Jam";
				case 112 : return "Club";
				case 113 : return "Tango";
				case 114 : return "Samba";
				case 115 : return "Folklore";
				case 116 : return "Ballad";
				case 117 : return "Power Ballad";
				case 118 : return "Rhythmic Soul";
				case 119 : return "Freestyle";
				case 120 : return "Duet";
				case 121 : return "Punk Rock";
				case 122 : return "Drum Solo";
				case 123 : return "A capella";
				case 124 : return "Euro-House";
				case 125 : return "Dance Hall";
				
				default: return Genere;
				}
		 }
		 return Genere;
		}catch(Exception E){return Genere;}
	}
	
}
