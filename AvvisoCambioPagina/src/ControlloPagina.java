import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.Date;


public class ControlloPagina {
	
	public String dataUltimaModificaPagina = null;

	public ControlloPagina(){
		dataUltimaModificaPagina = scaricaPagina();
	}
	
	public String scaricaPagina(){
		
	try{
	URL metafeedUrl = new URL("http://kastknocker.no-ip.info/Sito/KastKnocKerRadio.htm");
	HttpURLConnection conn = null;
	conn = (HttpURLConnection) metafeedUrl.openConnection();
	
	String data = null;
	
	for (int j = 1;; j++) {
        String header = conn.getHeaderField(j);
        String headerKey = conn.getHeaderFieldKey(j);
       
        if (header == null) break;
        System.out.println(j +" "+ headerKey + " XXX " + header);
        if (headerKey.equalsIgnoreCase("Last-Modified") ) data = conn.getHeaderField(j);
      }
	
	System.out.println("DATA ULTIMA MODIFICA: " + data);
	
	
	

    
    
    return data;
    /* PARTE PER BUFFERIZZARE INTERNO NON SERVE
	BufferedReader in = new BufferedReader(new InputStreamReader(metafeedUrl.openStream()));
	String inputLine;
	System.out.println( "DATA: " + date.toLocaleString());
	while ((inputLine = in.readLine()) != null) {
		System.out.println(inputLine);
	}*/
	
	}catch(Exception e){};
	return null;
	}
}
