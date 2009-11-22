import java.net.HttpURLConnection;
import java.net.URL;


public class SitoDaControllare {
 
	private String url = null;
	private String messaggio = null;
	private String tipopagina = null;
	private boolean aggiornato = false;
	private String dataUltimoAggiornamento = null;
	
	public SitoDaControllare(String url, String tipopagina){
		this.url = url;
		this.tipopagina = tipopagina;
		
		if(tipopagina.equals("html")){
		dataUltimoAggiornamento = getHtmlDataUltimaModifica();	
		}
	}
	
	
	
	
	public String getHtmlDataUltimaModifica(){
		
		try{
		URL metafeedUrl = new URL(url);
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
		}catch(Exception e){};
		return null;
		}
	
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setTipopagina(String tipopagina) {
		this.tipopagina = tipopagina;
	}
	public String getTipopagina() {
		return tipopagina;
	}
	
	public void setAggiornato(boolean aggiornato) {
		this.aggiornato = aggiornato;
	}
	public boolean isAggiornato() {
		return aggiornato;
	}
	
	public void controlloAggiornato() {
		
		if(tipopagina.equals("html")){
			if(dataUltimoAggiornamento.equals(getHtmlDataUltimaModifica())){
				setAggiornato(false);
				}else{
				setAggiornato(true);	
				}
		}
		
		
	}
	
}
