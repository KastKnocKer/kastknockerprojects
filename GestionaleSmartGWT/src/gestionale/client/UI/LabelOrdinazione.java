package gestionale.client.UI;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;

public class LabelOrdinazione extends Label{
	
	private String idCliente,idProdotto,idOrdine;
	private static DoubleClickHandler DCH = null;
	
	public LabelOrdinazione(String idCliente, String idProdotto, String idOrdine){
		this.setIdCliente(idCliente);
		this.setIdProdotto(idProdotto);
		this.setIdOrdine(idOrdine);
		
		if(DCH == null){
			DCH = new DoubleClickHandler() {
				
				public void onDoubleClick(DoubleClickEvent event) {
					LabelOrdinazione lo = (LabelOrdinazione) event.getSource();
					new WindowDettaglioOrdini(lo);
				}
			};
		}
		
		
		this.addDoubleClickHandler(DCH);
	}

	public void setIdOrdine(String idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getIdOrdine() {
		return idOrdine;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getIdProdotto() {
		return idProdotto;
	}

}
