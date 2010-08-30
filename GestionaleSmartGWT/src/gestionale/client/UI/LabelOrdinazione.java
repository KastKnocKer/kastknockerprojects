package gestionale.client.UI;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;

public class LabelOrdinazione extends Label{
	
	private String idCliente,idProdotto,idOrdine;
	private static DoubleClickHandler DCH = null;
	
	public LabelOrdinazione(String idCliente, String idProdotto, String idOrdine){
		this.idCliente = idCliente;
		this.idProdotto = idProdotto;
		this.idOrdine = idOrdine;
		
		if(DCH == null){
			DCH = new DoubleClickHandler() {
				
				public void onDoubleClick(DoubleClickEvent event) {
					LabelOrdinazione lo = (LabelOrdinazione) event.getSource();
					lo.setContents("123");
				}
			};
		}
		
		
		this.addDoubleClickHandler(DCH);
	}

}
