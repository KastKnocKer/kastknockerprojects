package gestionale.client.UI;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;

public class LabelOrdinazioneSpeciale extends Label{
	
	
	private static DoubleClickHandler DCH = null;
	private String idordine = null;
	private String idcliente = null;
	private String idprodotto = null;
	private int num = 0;
	
	
	
	public LabelOrdinazioneSpeciale(String idOrdine, String idCliente, String idProdotto){
		setIdordine(idOrdine);
		setIdprodotto(idProdotto);
		setIdcliente(idCliente);
		
		num = 0;
		
		System.out.println("Creata Label: " + idordine +" "+ idcliente +" "+idprodotto);
		
		if(DCH == null){
			DCH = new DoubleClickHandler() {
				
				public void onDoubleClick(DoubleClickEvent event) {
					LabelOrdinazioneSpeciale lo = (LabelOrdinazioneSpeciale) event.getSource();
					new WindowDettaglioOrdiniSpeciale(lo);
				}
			};
		}
		
		
		this.addDoubleClickHandler(DCH);
	}



	public void setIdordine(String idordine) {
		this.idordine = idordine;
	}



	public String getIdordine() {
		return idordine;
	}



	public void setIdprodotto(String idprodotto) {
		this.idprodotto = idprodotto;
	}



	public String getIdprodotto() {
		return idprodotto;
	}



	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}



	public String getIdcliente() {
		return idcliente;
	}
	
	public void aumentaContatore(int valore) {
		num = num + valore;
		this.setContents(Integer.toString(num));
	}

	

}
