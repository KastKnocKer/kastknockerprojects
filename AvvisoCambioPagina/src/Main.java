import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class Main {
	
	public static void main(String[] args) {
		
	
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
		Toolkit.getDefaultToolkit().beep();
		MMSViaWeb mms = new MMSViaWeb();
		mms.setMittente("PrOg");
		mms.setDestinatario("3406787668");
		mms.setTestomessaggio("PaginaAggiornata");
		mms.sendMMS();
		
		
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
