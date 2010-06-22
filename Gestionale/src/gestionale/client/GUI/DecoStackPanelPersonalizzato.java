package gestionale.client.GUI;

import java.util.Vector;

import gestionale.client.DB;
import gestionale.client.Liste;
import gestionale.shared.Contatto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;


public class DecoStackPanelPersonalizzato extends DecoratedStackPanel{
	
	private VerticalPanel vp;
	private Tree treeRoot;
	
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
		vp = new VerticalPanel();
		Vector<Contatto> vc = Liste.getVettoreContatti();
		
		treeRoot = new Tree();
		TreeItem treePanelClienti = treeRoot.addItem("Clienti");
		TreeItem treePanelFornitori = treeRoot.addItem("Fornitori");
		TreeItem treePanelTrasportatori = treeRoot.addItem("Trasportatori");
		
		
		// Create a popup to show the contact info when a contact is clicked
	    HorizontalPanel contactPopupContainer = new HorizontalPanel();
	    contactPopupContainer.setSpacing(5);
	    contactPopupContainer.add(new Image("Icone/Contatti.png"));
	    final HTML contactInfo = new HTML();
	    contactPopupContainer.add(contactInfo);
	    final PopupPanel contactPopup = new PopupPanel(true, false);
	    contactPopup.setWidget(contactPopupContainer);

		VerticalPanel contactsPanel = new VerticalPanel();
	    contactsPanel.setSpacing(4);
		
		////////////////////////////////////////
		
		for(int i=0; i<vc.size(); i++){
			c=vc.get(i);
			
			///////////////////////////
			//final HTML contactLink = new HTML("<a href=\"javascript:undefined;\">" + vc.get(i).getRagioneSociale()+" - "+ vc.get(i).getTipoSoggetto() + "</a>");
			final HTML contactLink = new HTML("<a href=\"javascript:undefined;\">" + vc.get(i).getRagioneSociale() + "</a>");
			
			contactLink.addClickHandler(new ClickHandler() {public void onClick(ClickEvent event) {
											Contatto c = null;
									        System.out.println("ECCO LA RISORSA: "+event.getSource().toString().substring(54,event.getSource().toString().indexOf("</a>")));
									        
									        Vector<Contatto> vc = Liste.getVettoreContatti();
									        String ragionesociale = event.getSource().toString().substring(54,event.getSource().toString().indexOf("</a>"));
									        
									        
									        for(int i=0; i<vc.size(); i++){
									        	if( vc.get(i).getRagioneSociale().equals(ragionesociale)){ c=vc.get(i); break;}
									        }
									        
									        contactInfo.setHTML(c.getHtmlText()); //INFORMAZIONI SUL POPUP
							
									          // Show the popup of contact info
									          int left = contactLink.getAbsoluteLeft() + 14;
									          int top = contactLink.getAbsoluteTop() + 14;
									          contactPopup.setPopupPosition(left, top);
									          contactPopup.show();
									        }
									      });
			
			/////INSERIMENTO NELL'ALBERO/////
			if(c.getTipoSoggetto().equals("Cliente")){
				treePanelClienti.addItem(contactLink);
			}else if(c.getTipoSoggetto().equals("Fornitore")){
				treePanelFornitori.addItem(contactLink);
			}else if(c.getTipoSoggetto().equals("Trasportatore")){
				treePanelTrasportatori.addItem(contactLink);
			}
			
		}
		
		
		
		


		//treePanelClienti.setState(true);
		//treePanelFornitori.setState(true);
		//treePanelTrasportatori.setState(true);
		
		vp.add(treeRoot);
	    this.add(vp, getHeaderString("Contatti", "Icone/Contatti.png"), true);

	}
	
	
/**
* Get a string representation of the header that includes an image and some text.
*/
	private String getHeaderString(String text, String image) {
	    // Add the image and text to a horizontal panel
	    HorizontalPanel hPanel = new HorizontalPanel();
	    hPanel.setSpacing(0);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    Image img = new Image(image);
	    img.setPixelSize(25, 25);
	    hPanel.add(img);
	    HTML headerText = new HTML(text);
	    headerText.setStyleName("cw-StackPanelHeader");
	    hPanel.add(headerText);

	    // Return the HTML string for the panel
	    return hPanel.getElement().getString();
	  }

	
}
