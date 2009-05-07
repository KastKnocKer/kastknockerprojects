import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


public class Vettore extends Vector{
	private static Vettore LinkVettore=null;
	
	public Vettore(){
		super();
		if(LinkVettore==null) LinkVettore=this;
	}
	
	public static Vettore getLinkVettore(){
		return LinkVettore;
	}
	
	public void remove(String NomeEsame){
		for(int i=0;i<this.size();i++){
			if( ((Voto)this.elementAt(i)).getNomeEsame().equals(NomeEsame) ){
				this.removeElementAt(i);
				return;
			}
		}
		this.AggiornaComponenti();
	}
	
	public void insert(Voto esito){
		this.addElement(esito);
		
		this.salvaLibreriaFileTxt();
		
		this.AggiornaComponenti();
		this.OrdinaVotiNome();
	}
	
	public void AggiornaComponenti(){
		if (JPanel_SudPanel.getLinkSudPanel()!=null) JPanel_SudPanel.getLinkSudPanel().Aggiorna();
		if (JTable_TabellaEsami.getLinkJTable()!=null) JTable_TabellaEsami.getLinkJTable().updateUI();
		
	}
	
	public double calcolaMedia110() {
		return (this.calcolaMedia()*11)/3;
	}

	public double calcolaMedia(){
		int NumEsami = this.size();
		double NumCrediti=0;
		double SommaCredXVoto=0;
		Voto Votarello=null;
		for(int i=0;i<NumEsami;i++){
			Votarello = (Voto) this.elementAt(i);
			if( (Votarello.getVoto() > 17)&&(Votarello.getVoto()<31) ){
			NumCrediti=NumCrediti + Votarello.getCrediti();
			SommaCredXVoto=SommaCredXVoto + ( Votarello.getCrediti()*Votarello.getVoto() );
			}
		}
		double Media = SommaCredXVoto/NumCrediti;
		System.out.println("Media: "+Media);
		return Media;
	}

	public void OrdinaVotiNome(){
	
		String Stringa1,Stringa2;
		Voto Voto1,Voto2;
		Boolean NonOrdinato=true;
		while(NonOrdinato){
			NonOrdinato=false;
		for(int i=0;i<this.size()-1;i++){
			Stringa1=((Voto)this.elementAt(i)).getNomeEsame();
			Stringa2=((Voto)this.elementAt(i+1)).getNomeEsame();
			
			if(   Stringa1.compareToIgnoreCase(Stringa2)>0){
			Voto1=(Voto) this.elementAt(i);
			Voto2=(Voto) this.elementAt(i+1);
			this.setElementAt(Voto2, i);
			this.setElementAt(Voto1, i+1);
			NonOrdinato=true;
			}
			
			
		}}
		
		
		
	}


	public void salvaLibreriaFileTxt(){
		BufferedWriter writer;
		try {
			// apro il buffered writer
			writer = new BufferedWriter(new FileWriter("Libretto.txt", false));
			writer.write("StartLibrary");
			writer.newLine();
			// Scrivo per artista le informazioni 
			for (int i = 0; i < this.size(); i++) {
				Voto Votarello = (Voto) this.elementAt(i);

				writer.write("Voto");
				writer.newLine();
				writer.write(Votarello.getNomeEsame());
				writer.newLine();
				writer.write(Integer.toString(Votarello.getCrediti()));
				writer.newLine();
				writer.write(Integer.toString(Votarello.getVoto()));
				writer.newLine();
			}
			
			writer.write("EOF");
			writer.newLine();
		
			// chiudo il file
			writer.flush();
			writer.close();
			}
		catch (IOException e) {
			System.out.println("Eccezione durante un'operazione di Output artista");
			}
	}
	
	public void caricaLibreriaFileTxt(){
		BufferedReader reader = null; // il reader che userò sul file
		try {
			reader = new BufferedReader(new FileReader ("Libretto.txt"));			
			String Tmp;
			while ((Tmp = reader.readLine()) != null) {	//Finchè non è nulla continua la lettura!
					
				if(Tmp.equals("Voto")){
					String nome = reader.readLine();
					String crediti = reader.readLine();
					String voto = reader.readLine();
					
				Voto Votarello = new Voto(nome,Integer.parseInt(crediti),Integer.parseInt(voto));
				
				this.insert(Votarello);
				}
				
				
			
				}
			}
		catch (FileNotFoundException e) {System.out.println("File non trovato");}
		catch (IOException e) {System.out.println("IO EXCE");}
	}

}
