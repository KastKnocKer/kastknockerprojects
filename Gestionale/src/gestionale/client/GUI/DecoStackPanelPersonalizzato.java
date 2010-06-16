package gestionale.client.GUI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class DecoStackPanelPersonalizzato extends DecoratedStackPanel{
	
	public DecoStackPanelPersonalizzato(){
		super();
		
		Image images = (Image) GWT.create(Image.class);
		this.setWidth("200px");
		this.setHeight("650px");
		
		this.add(new FlowPanel(),"Sole",  true);
		this.add(new FlowPanel(),"Cuore", true);
		this.add(new FlowPanel(),"Amore",  true);
		
		
		
	}

}
