package gestionale.client.GUI;

import java.util.Vector;

import gestionale.shared.Contatto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class PanelGestioneContattiMain extends DockPanel{

	private PanelGestioneContattiMain dialog;
	private Contatto contatto;
	private Boolean modifica;
	private TextBox tb_RagioneSociale;
	private TextBox tb_Precisazione;
	private TextBox tb_PIVA;
	private TextBox tb_SitoWeb;
	private TextBox tb_Provvigione;
	private ListBox lb_TipoSoggetto;
	
	private DisclosurePanel dp_Indirizzi;
	private DisclosurePanel dp_Telefono;
	private DisclosurePanel dp_Cellulare;
	private DisclosurePanel dp_Fax;
	private DisclosurePanel dp_eMail;
	private DisclosurePanel dp_Note;

	
	
	public PanelGestioneContattiMain(Contatto contattoIn){
		super();
		contatto=contattoIn;
		if( contatto == null){	//// Ricordo se il contatto deve esser creato o modificato
			contatto = new Contatto();
			modifica=false;
		}else{
			modifica=true;
		}
		
		this.setSize("1000px", "800px");
		tb_RagioneSociale=new TextBox(  );
		tb_Precisazione=new TextBox(  );
		tb_PIVA=new TextBox(  );
		tb_SitoWeb=new TextBox(  );
		tb_Provvigione=new TextBox(  );
		lb_TipoSoggetto = new ListBox(false);
					lb_TipoSoggetto.addItem("");
					lb_TipoSoggetto.addItem("Cliente");
					lb_TipoSoggetto.addItem("Fornitore");
					lb_TipoSoggetto.addItem("Trasportatore");
		
		if(modifica){
		tb_RagioneSociale.setText( contatto.getRagioneSociale() );
		tb_Precisazione.setText( contatto.getPrecisazione() );
		tb_PIVA.setText( contatto.getPIVA() );
		tb_SitoWeb.setText( contatto.getSitoWeb() );
		tb_Provvigione.setText( contatto.getProvvigione() );
		int k=0;
		String ts = contattoIn.getTipoSoggetto();
		if(ts.equals("Cliente"))k=1;
		
		lb_TipoSoggetto.setSelectedIndex(k);
		}
		
		dialog = this;
		VerticalPanel vp = new VerticalPanel();
		
		dp_Indirizzi = new DisclosurePanel("Indirizzi");
		dp_Indirizzi.setAnimationEnabled(true);
		vp.add(dp_Indirizzi);
		
		dp_Telefono = new DisclosurePanel("Contatti telefonici");
		dp_Telefono.setAnimationEnabled(true);
		vp.add(dp_Telefono);
		
		dp_Cellulare = new DisclosurePanel("Cellulari");
		dp_Cellulare.setAnimationEnabled(true);
		vp.add(dp_Cellulare);
		
		dp_Fax = new DisclosurePanel("Fax");
		dp_Fax.setAnimationEnabled(true);
		vp.add(dp_Fax);
		
		dp_eMail = new DisclosurePanel("e-Mail");
		dp_eMail.setAnimationEnabled(true);
		vp.add(dp_eMail);
		
		dp_Note = new DisclosurePanel("Note");
		dp_Note.setAnimationEnabled(true);
		vp.add(dp_Note);
		
		
		
		DecoratorPanel dp;
		
		dp = new DecoratorPanel();
		dp.add( this.getPannelloGenerale() );
		this.add(dp,DockPanel.NORTH);
		
		this.add(vp,DockPanel.CENTER);


		PanelGestioneContattiIndirizzi pgcInd = new PanelGestioneContattiIndirizzi(contatto);
		dp_Indirizzi.add( pgcInd.getPannello() );
		
		
		
		Button closeButton = new Button("Annulla",new ClickHandler() {
		          public void onClick(ClickEvent event) {
		            //dialog.hide();
		            //dialog.setGlassEnabled(false);
		        	  dialog.removeFromParent();
		          }
		});
		vp.add(closeButton);
		//this.add(vp);
		
		
		this.add(vp,DockPanel.SOUTH);
		
		//this.setGlassEnabled(true);
	    //this.setAnimationEnabled(true);
		//this.center();
		//this.setText("Gestione guidata dei contatti");
		this.setTitle("Gestione guidata dei contatti");
		//this.show();
	}

	
	private Widget getPannelloTelefono() {
		// TODO Auto-generated method stub
		return null;
	}


	private Widget getPannelloGenerale() {
		Grid grid = new Grid(6,2);
		
		grid.setWidget(0, 0, new Label("Ragione Sociale") );
		grid.setWidget(1, 0, new Label("Precisazione") );
		grid.setWidget(2, 0, new Label("C.Fisc / P.IVA") );
		grid.setWidget(3, 0, new Label("Sito Web") );
		grid.setWidget(4, 0, new Label("Provvigione") );
		grid.setWidget(5, 0, new Label("Tipo Contatto") );
		
		grid.setWidget(0, 1, tb_RagioneSociale );
		grid.setWidget(1, 1, tb_Precisazione );
		grid.setWidget(2, 1, tb_PIVA );
		grid.setWidget(3, 1, tb_SitoWeb );
		grid.setWidget(4, 1, tb_Provvigione );
		grid.setWidget(5, 1, lb_TipoSoggetto );
		
		return grid;
	}
	
	
}
