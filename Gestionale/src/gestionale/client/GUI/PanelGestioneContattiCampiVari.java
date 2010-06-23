package gestionale.client.GUI;

import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PanelGestioneContattiCampiVari extends VerticalPanel{

	private Vector<String[]> v;
	private PanelGestioneContattiCampiVari thispanel;
	private String modalita;
	private Contatto contatto;
	private String[] campi = {"Etichetta","Via"};
	private String[] dimensione = {"200px","350px"};
	
	private TextBox[] tbarray;
	
	public static String mod_Telefono = "Telefono";
	public static String mod_Cellulare = "Cellulare";
	public static String mod_Fax = "Fax";
	public static String mod_Email = "eMail";
	
	
	public PanelGestioneContattiCampiVari(Contatto c, String mod){
		super();
		modalita=mod;
		thispanel=this;
		contatto=c;
		
		if(mod.equals(mod_Telefono)){
			v = contatto.getVectorCampo(mod_Telefono);
			campi[1] = "Numero di Telefono Fisso";
		}
		if(mod.equals(mod_Cellulare)){
			v = contatto.getVectorCampo(mod_Cellulare);
			campi[1] = "Numero di Cellulare";
		}
		if(mod.equals(mod_Fax)){
			v = contatto.getVectorCampo(mod_Fax);
			campi[1] = "Numero di Fax";
		}
		if(mod.equals(mod_Email)){
			v = contatto.getVectorCampo(mod_Email);
			campi[1] = "Indirizzo e-Mail";
		}
		
		aggiornaPannello();
	}
	
	public void aggiornaPannello(){
		this.clear();
		
		ClickHandler CH = new ClickHandler() {//HANDLER RIMOZIONE INDIRIZZO
				          public void onClick(ClickEvent event) {
				        	  int indice = ((PushButton) event.getSource()).getTabIndex();
				        	  
				        	  if( Window.confirm( "Sei sicuro di voler cancellare: " + v.get(indice)[0]+ " ?") ){
				        		  v.remove(indice);
				        		  thispanel.aggiornaPannello();
				        	  }
				          }
			};
		
		Grid grid = new Grid(v.size()+1,3);
		Grid grid2 = new Grid(1,2);
		
		for(int i=0; i<campi.length; i++){
			Label lb = new Label(campi[i]);
			lb.setWidth(dimensione[i]);
			grid.setWidget(0, i, lb);
		}
		
		for(int i=0; i<v.size(); i++){
				for(int j=0; j<v.get(i).length;j++){
					TextBox lb = new TextBox();
					lb.setText(v.get(i)[j]);
					lb.setWidth(dimensione[j]);
					grid.setWidget(i+1, j, lb);
				}
				
				PushButton pb = new PushButton(new Image("Icone/remove.png"));
				pb.setTabIndex(i);
				pb.addClickHandler(CH);
				grid.setWidget(i+1, campi.length, pb);
		}
		
		
		tbarray = new TextBox[campi.length];
		
		for(int j=0; j<campi.length;j++){
			TextBox lb = new TextBox();
			tbarray[j] = lb;
			lb.setWidth(dimensione[j]);
			grid2.setWidget(0, j, lb);
		}
		
		Button addIndirizzoButton = new Button("Aggiungi",new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  boolean isnotnull = false;
			        	  String[] arrayString = new String[campi.length];
			        	  for(int i=0;i<campi.length; i++){
			        		  arrayString[i]=tbarray[i].getText();
			        		  if( arrayString[i].length()>0 ) isnotnull=true;
			        	  }
			        	 
			        	  if(isnotnull){
			        		  v.add(arrayString);
			        		  thispanel.aggiornaPannello();
			        	  }
			          }
		});
		
		this.add(grid);
		this.add( new Label("Record aggiuntivo:") );
		this.add(grid2);
		this.add(addIndirizzoButton);
	}

	public DecoratorPanel getPannello(){
		DecoratorPanel dp = new DecoratorPanel();
		dp.add(this);
		return dp;
	}

	public String getStringaValori() {
		String stringa = new String("");
		
		for(int i=0; i<v.size();i++){
			String[] array = v.get(i);
			stringa=stringa+"*"+array[0]+"?"+array[1]+"*";
		}
		
		return stringa;
	}
	
}
