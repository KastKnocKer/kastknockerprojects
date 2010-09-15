package gestionale.client.UI;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.shared.Contatto;

public class LabelOrdinazioneCliente extends Label{
	
	private Contatto contatto;
	
	public LabelOrdinazioneCliente(final Contatto contatto){
		super();
		this.contatto = contatto;
		
		addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				Finestra window = new Finestra();  
				window.setTitle(contatto.getRagioneSociale());
				window.addItem(new Label(contatto.getHtmlText()));
				window.setWidth(300);  
				window.setHeight(230);
				window.setCanDragReposition(true);  
				window.setCanDragResize(true);  
				window.centerInPage();
				window.draw();
			}
		});
		
	}

}
