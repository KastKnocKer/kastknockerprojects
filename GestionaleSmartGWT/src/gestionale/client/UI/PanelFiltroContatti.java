package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceMercati;
import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.Layout;



public class PanelFiltroContatti extends Layout{

	private static Vector<Contatto> V = null;
	private Vector<Contatto> v = null;
	private DynamicForm form = null;
	
	private SelectItem ti_TipoMercatoDiRiferimento;
	private SelectItem ti_MercatoDiRiferimento;
	private CheckboxItem setSelezionePreferita;
	private static DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
	private String[][] risultatoQueryFiltroPerMercato = null;
	
	public PanelFiltroContatti(){
		super();
		
		form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.LEFT);
		  
		ti_TipoMercatoDiRiferimento = new SelectItem();
		ti_TipoMercatoDiRiferimento.setName("tipomercato");
		ti_TipoMercatoDiRiferimento.setTitle("Tipo Mercato");
		ti_TipoMercatoDiRiferimento.setValueMap( DataSourceMercati.getTipoMercato() );
		ti_TipoMercatoDiRiferimento.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				ti_MercatoDiRiferimento.setValueMap( DataSourceMercati.getMercatiMap((String) event.getValue()));
				ti_MercatoDiRiferimento.setValue("");
				filtraPerMercato();
			}
		});
		
		ti_MercatoDiRiferimento = new SelectItem();
		ti_MercatoDiRiferimento.setName("mercato");
		ti_MercatoDiRiferimento.setTitle("Mercato di riferimento");
		ti_MercatoDiRiferimento.setMultiple(true);
		ti_MercatoDiRiferimento.setMultipleAppearance(MultipleAppearance.PICKLIST);
		
		ti_MercatoDiRiferimento.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				filtraPerMercato();
			}
		});
		
		setSelezionePreferita = new CheckboxItem();  
		setSelezionePreferita.setName("preferito");  
		setSelezionePreferita.setTitle("Salvare risultato come preferito?"); 
		
		ButtonItem confermabutton = new ButtonItem();
		confermabutton.setTitle("Applica filtro");
		confermabutton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				filtra();
			}
		});
		
		
		form.setItems(ti_TipoMercatoDiRiferimento, ti_MercatoDiRiferimento, setSelezionePreferita, confermabutton);
		this.addMember(form);
		
	}
	
	private void filtra(){
		Vector<Contatto> newVector = new Vector<Contatto>();
		Vector<Contatto> temp = DataSourceContatti.getVettoreContatti();
		Contatto contatto = null;
		boolean mercato = false;
		String valueMercato = null;
		if( form.getValue("tipomercato") != null){
			valueMercato = (String) form.getValue("tipomercato");
			valueMercato.toLowerCase();
			if( valueMercato.length() > 0 ) mercato = true;
		}
		
		
		for(int i=0; i<temp.size(); i++){
			contatto = temp.get(i);
			System.out.println("ANALIZZO	1	: " + contatto.getRagioneSociale());
			//Filtro clienti e intermediari
			if(contatto.getTipoSoggetto().equals("Trasportatore") || contatto.getTipoSoggetto().equals("Fornitore")){continue;}
			System.out.println("ANALIZZO	2	: " + contatto.getRagioneSociale()+"	"+valueMercato+"	"+contatto.getIndirizzo().toLowerCase().contains("valueMercato"));
			
			if(mercato){
				boolean trovato = false;
				for(int k=0; k<risultatoQueryFiltroPerMercato.length;k++){
					if( contatto.getID().equals(risultatoQueryFiltroPerMercato[k][0]) ){
						trovato = true;
						break;
					}
				}
				if(!trovato) continue;	
			}
			
			newVector.add(contatto);
			System.out.println("Aggiungo alla lista: " + contatto.getRagioneSociale());
		}
		
		v = newVector;
		
		if( form.getValue("preferito") != null ){
			System.out.println("SALVO? :" + form.getValue("preferito"));
			salvaRisultatoNeiPreferiti(v);
		}
		
		setSelezionePreferita.setValue(false);
	}
	
	private void filtraPerMercato(){
		String querybase = "SELECT C.ID, C.RagioneSociale, m.Mercato, m.TipoMercato FROM contatti c JOIN mercati m ON c.IDMercato = m.ID";
		String query = "";
		
		String[] mercatiSelezionati = ti_MercatoDiRiferimento.getValues();
		if( (mercatiSelezionati.length == 0)||(mercatiSelezionati.length ==1 && mercatiSelezionati[0].equals("")) ){
			query = querybase +" WHERE m.TipoMercato = '"+ti_TipoMercatoDiRiferimento.getValue()+"'";
		}else{
			query = querybase +" WHERE";
			for(int i=0; i<mercatiSelezionati.length;i++){
				if(i==0){
					query = query +" m.Mercato = '"+mercatiSelezionati[i]+"'";
				}else{
					query = query +" OR m.Mercato = '"+mercatiSelezionati[i]+"'";
				}
			}
		}
		
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			@Override
			public void onSuccess(String[][] result) {
				risultatoQueryFiltroPerMercato = result;
			}
			
			@Override
			public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}
		});
	}
	
	
	
	private void salvaRisultatoNeiPreferiti(final Vector<Contatto> v){
		DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		String query = "DELETE FROM lista_preferiti_clienti WHERE Username = '"+SessioneUtente.getUsername()+"';";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				
			}
			public void onSuccess(Boolean result) {
				for(int i=0; i<v.size(); i++){
					String query = "INSERT INTO lista_preferiti_clienti VALUES ('"+SessioneUtente.getUsername()+"','"+ v.get(i).getID() +"');";
					DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
					rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {}
						public void onSuccess(Boolean result) {}
						
					});
				}
			}
			
		});
		
		
		V = v;
	}

	public void setV(Vector<Contatto> v) {
		this.v = v;
	}

	public Vector<Contatto> getV() {
		return v;
	}

	public Vector<Contatto> getVettoreContattiFiltrato() {
		if(v == null) return V;
		if(v.size() == 0) return V;
		return v;
	}
	
	public static void CaricaVettoreContattiPreferito(){
		DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		String query = "SELECT * FROM lista_preferiti_clienti WHERE Username = '"+SessioneUtente.getUsername()+"';";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			public void onSuccess(final String[][] result) {
				V = new Vector<Contatto>();
				
				new Timer(){
					public void run() {
						Vector<Contatto> vett = DataSourceContatti.getVettoreContatti();
						if(vett == null){
							schedule(500);
						}else{
							Contatto contatto = null;
							for(int i=0; i<result.length; i++){
								String record = result[i][1];
								for(int j=0; j<vett.size(); j++){
									contatto = vett.get(j);
									if(contatto.getID().equals(record)){
										V.add(contatto);
										break;
									}
								}
								
								
								
							}
							
						}
						
					}
					
				}.schedule(500);
				
				
				
			}
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
}
