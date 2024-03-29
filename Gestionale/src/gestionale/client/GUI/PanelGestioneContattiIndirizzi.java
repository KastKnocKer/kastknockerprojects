package gestionale.client.GUI;

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

import gestionale.shared.Contatto;

public class PanelGestioneContattiIndirizzi extends VerticalPanel{

	private Contatto contatto;
	private TextBox[] tbarray;
	private Vector<String[]> v;
	private static String[] campi = {"Etichetta","Via","N.Civico","CAP","Frazione","Citta'","Provincia","Regione","Nazione"};
	private static String[] dimensione = {"200px","250px","60px","60px","110px","110px","110px","110px","110px"};
	private PanelGestioneContattiIndirizzi thispanel;
	
	public PanelGestioneContattiIndirizzi(Contatto c){
		super();
		thispanel=this;
		contatto=c;
		v = contatto.getVectorIndirizzi();
		aggiornaPannello();
	}
	
	public DecoratorPanel getPannello(){
		DecoratorPanel dp = new DecoratorPanel();
		dp.add(this);
		return dp;
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
		
		Grid grid = new Grid(v.size()+1,campi.length+1);
		Grid grid2 = new Grid(1,campi.length);
		
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
		
		Button addIndirizzoButton = new Button("Aggiungi Indirizzo",new ClickHandler() {
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
		this.add( new Label("Indirizzo aggiuntivo:") );
		this.add(grid2);
		this.add(addIndirizzoButton);
	}
	
	public String getStringaIndirizzi(){
		String stringa = new String("");
		for(int i=0; i<v.size();i++){
			String[] array = v.get(i);
			stringa=stringa+"*"+array[0]+"+"+array[1]+"+"+array[2]+"+"+array[3]+"+"+array[4]+"+"+array[5]+"+"+array[6]+"+"+array[7]+"+"+array[8]+"*";
		}
		return stringa;
	}
	
}
