package gestionale.client.GUI;

import gestionale.shared.Contatto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;


public class DecoStackPanelPersonalizzato extends DecoratedStackPanel{
	
	public DecoStackPanelPersonalizzato(){
		super();
		
		Image images = (Image) GWT.create(Image.class);
		this.setWidth("200px");
		this.setHeight("650px");
		
		addPanelContatti();
		
		this.add(new FlowPanel(),"Sole",  true);
		this.add(new FlowPanel(),"Cuore", true);
		this.add(new FlowPanel(),"Amore",  true);
		
		
	}
	
	
	private void addPanelContatti(){
		Contatto c = null;
		
		VerticalPanel vp = new VerticalPanel();
		
		//DB db = new DB();
		
		
		//System.out.println("Client: Dimensione vettore:"+v.size());
		//Button b = new Button();
		//b.setText(Integer.toString(v.size()));
		//vp.add(b);
		
	/*	for(int i=0;i<v.size();i++){
			vp.add(new Button( ((String[])v.get(i))[0] ));
		}
		*/
		
		
		this.add(vp,"Contatti",true);
	}
	
	

}
