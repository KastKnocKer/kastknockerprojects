import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class Main {
	
	public static void main(String[] args) {
		
		try{
		// Construct data
		String destinatario = "3406787668";
        String data = URLEncoder.encode("recipient", "UTF-8") + "=" + URLEncoder.encode("%2B39" + destinatario, "UTF-8");
        data += "&" + URLEncoder.encode("subjecttosend", "UTF-8") + "=" + URLEncoder.encode("Da+Marco", "UTF-8");
        data += "&" + URLEncoder.encode("TextName", "UTF-8") + "=" + URLEncoder.encode("Completamento+dell+amore", "UTF-8");
        data += "&" + URLEncoder.encode("SmilName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
        data += "&" + URLEncoder.encode("ImageName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
        data += "&" + URLEncoder.encode("AudioName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
        data += "&" + URLEncoder.encode("nextPage", "UTF-8") + "=" + URLEncoder.encode("/web/servletresult.html", "UTF-8");
       
        // Send data
        URL url = new URL("http://mmsviaweb.net.vodafone.it/WebComposer/web/elaborapop.jsp");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            
        	//System.out.println(line);
        }
        wr.close();
        rd.close();
        
		}catch(Exception e){
			System.out.println("Errore invio MMS");
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
		
		while(true){
			Toolkit.getDefaultToolkit().beep();
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
		
	}

}
