import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MMSViaWeb {
	
	private String mittente=null;
	private String destinatario=null;
	private String testomessaggio=null;
	
	
	public MMSViaWeb(){
		
	}

	public boolean sendMMS(){
		
		if(mittente.length()<1) return false;
		if(destinatario.length()<10) return false;
		if(testomessaggio.length()<1) return false;
		
		
		try{
			// Struttura del pacchetto
	        String data = URLEncoder.encode("recipient", "UTF-8") + "=" + URLEncoder.encode("%2B39" + destinatario, "UTF-8");
	        data += "&" + URLEncoder.encode("subjecttosend", "UTF-8") + "=" + URLEncoder.encode("Da: " + mittente, "UTF-8");
	        data += "&" + URLEncoder.encode("TextName", "UTF-8") + "=" + URLEncoder.encode(testomessaggio, "UTF-8");
	        data += "&" + URLEncoder.encode("SmilName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
	        data += "&" + URLEncoder.encode("ImageName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
	        data += "&" + URLEncoder.encode("AudioName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
	        data += "&" + URLEncoder.encode("nextPage", "UTF-8") + "=" + URLEncoder.encode("/web/servletresult.html", "UTF-8");
	       
	        // Invio del pacchetto
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
				return false;
			}
		
		return true;
	}
	
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
	public String getMittente() {
		return mittente;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setTestomessaggio(String testomessaggio) {
		this.testomessaggio = testomessaggio;
	}
	public String getTestomessaggio() {
		return testomessaggio;
	}
	
	
	

}
