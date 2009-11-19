
public class SitoDaControllare {
 
	private String url = null;
	private String messaggio = null;
	private String tipopagina = null;
	private boolean aggiornato = false;
	
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
	
}
