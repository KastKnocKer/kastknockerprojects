import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Vector;


public class Main {
	
	public static void main(String[] args) {
		
	Vector<SitoDaControllare> vettore = new Vector<SitoDaControllare>();
	
	/* CARICAMENTO SITI DA CONTROLLARE */
	SitoDaControllare sito = new SitoDaControllare();
	sito.setUrl("http://www.dii.unimo.it/zanasi/didattica/contr_A_NOD/Controlli_A_NOD_09.htm");
	sito.setMessaggio("Pagina Controlli Automatici Aggiornata");
	sito.setTipopagina("html");
	vettore.addElement(sito);
	
	String messaggio = new String("");
	boolean flag = true ;

	
	while(flag){
		
		for(int i=0; i<vettore.size(); i++){
		
		sito = vettore.get(i);
			sito.controlloAggiornato();
			if( sito.isAggiornato() ){
			messaggio.concat(sito.getMessaggio());
			messaggio.concat(" - ");
			vettore.remove(i);
			}
		}
		
		if(messaggio.length()>0){
			MMSViaWeb mms = new MMSViaWeb();
			mms.setMittente("ProgPro");
			mms.setDestinatario("3406787668");
			mms.setTestomessaggio(messaggio);
			mms.sendMMS();
			System.exit(0);
		}
		
		try {Thread.currentThread().sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	
		
		
		
		
		
		
		
	/*
		ControlloPagina cp = new ControlloPagina();
		
		while( cp.dataUltimaModificaPagina.equals(cp.scaricaPagina()) ){
			try{
				  //do what you want to do before sleeping
				  Thread.currentThread().sleep(10000);//sleep for 1000 ms
				  //do what you want to do after sleeptig
				}
				catch(Exception ie){
				//If this thread was intrrupted by nother thread 
				}
		}
		*/
		Toolkit.getDefaultToolkit().beep();
		MMSViaWeb mms = new MMSViaWeb();
		mms.setMittente("PrOg");
		mms.setDestinatario("3405706733");
		mms.setTestomessaggio("io venuto qui e padrone del tuo paese");
		//mms.sendMMS();
		
		
		/*while(true){
			Toolkit.getDefaultToolkit().beep();
			try{
				  //do what you want to do before sleeping
				  Thread.currentThread().sleep(10000);//sleep for 1000 ms
				  //do what you want to do after sleeptig
				}
				catch(Exception ie){
				//If this thread was intrrupted by nother thread 
				}
		}*/
		
	}
}
