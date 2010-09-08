package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.SessioneUtente;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.Layout;

public class PanelFiltroProdotti extends Layout{

	private static Vector<String[]> V = null;
	private DynamicForm form = null;
	private Vector<String[]> v = null;
	
	private SelectItem selectCategorie;
	private SelectItem selectTipologie;
	private CheckboxItem setSelezionePreferita;
	
	public PanelFiltroProdotti(){
		super();
		v = new Vector<String[]>();
	
		form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.LEFT);
		
		selectCategorie = new SelectItem();  
		selectCategorie.setTitle("Seleziona categorie");  
		selectCategorie.setMultiple(true);  
		selectCategorie.setMultipleAppearance(MultipleAppearance.PICKLIST);
		selectCategorie.setValueMap("Frutta","Frutta Secca","Verdura");
		
		selectTipologie = new SelectItem();
		selectTipologie.setTitle("Seleziona tipologie");  
		selectTipologie.setMultiple(true);  
		selectTipologie.setMultipleAppearance(MultipleAppearance.PICKLIST);

		
		selectCategorie.addBlurHandler(new BlurHandler() {
			
			public void onBlur(BlurEvent event) {
				String[] selezione = selectCategorie.getValues();
				Vector<Prodotto> vTemp = DataSourceProdottiCatalogati.getvProdottiCatalogati();
				Prodotto prodotto = null;
				
				String[] map = new String[vTemp.size()];
				int indicemap = 0;
				
				
					
				for(int i=0; i<vTemp.size(); i++){
						prodotto = vTemp.get(i);
						
					for(int k=0; k<selezione.length; k++){
						if(prodotto.getCategoria().equals( selezione[k] )){
							
							if(indicemap == 0){
								map[indicemap] = prodotto.getTipologia();
								indicemap++;
								break;
							}
							if(map[indicemap-1].equals(prodotto.getTipologia())){
								
							}else{
								map[indicemap] = prodotto.getTipologia();
								indicemap++;
								break;
							}
						}
					}
					
				}
				
				
				
				String selezionato = "";
				for(int i=0; i<selezione.length; i++){
					selezionato = selezionato + " " + selezione[i];
				}

				
				
				
				
				
				selectTipologie.setValueMap(map);
			}
		});
		/*
		selectTipologie.addBlurHandler(new BlurHandler() {
			
			public void onBlur(BlurEvent event) {
				String[] selezione = selectTipologie.getValues();
				String selezionato = "";
				for(int i=0; i<selezione.length; i++){
					selezionato = selezionato + " " + selezione[i];
				}
				
				v.removeAllElements();
				
				Vector<Prodotto> vTemp = DataSourceProdottiCatalogati.getvProdottiCatalogati();
				Prodotto prodotto = null;
				String[] map = new String[vTemp.size()];
				for(int i=0; i<vTemp.size(); i++){
					prodotto = vTemp.get(i);
					if(selezionato.contains((String) prodotto.getTipologia())){
						String[] stringa =  new String[] {prodotto.getCategoria(), prodotto.getTipologia()};
						if(v.size() == 0){ v.add(stringa); continue;}
						if( v.get(v.size()-1)[0].equals(stringa[0]) && v.get(v.size()-1)[1].equals(stringa[1])){
							
						}else{
						System.out.println("Filtrare: " + stringa[0] + stringa[1]);
						v.add(stringa);
						}
						
					}
				}
				
			}
		});
		*/
		
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
		
		form.setItems(selectCategorie,selectTipologie,setSelezionePreferita,confermabutton);
		this.addMember(form);
	}
	
	private void filtra(){
		Vector<String[]> newVector = new Vector<String[]>();
		Vector<Prodotto> temp = DataSourceProdottiCatalogati.getvProdottiCatalogati();
		
		String[] selezioneCategorie = selectCategorie.getValues();
		String[] selezioneTipologie = selectTipologie.getValues();
		Prodotto prod = null;
		
		for(int i=0; i<temp.size(); i++){
			prod = temp.get(i);
			
			//Scarto per categoria
			boolean check = false;
			for(int k=0; k<selezioneCategorie.length; k++){
				if( prod.getCategoria().equals(selezioneCategorie[k]) ){
					check = true; break;
				}
			}
			if(!check) continue;
			
			//Scarto per tipologia
			check = false;
			for(int k=0; k<selezioneTipologie.length; k++){
				if( prod.getTipologia().equals(selezioneTipologie[k]) ){
					check = true; break;
				}
			}
			if(!check) continue;
			
			String[] tmp = new String[]{ prod.getCategoria(), prod.getTipologia() };
			if(newVector.size() > 0 ){
			String[] last = newVector.get( newVector.size()-1 );
				if( tmp[0].equals(last[0]) && tmp[1].equals(last[1])){
					//Non aggiungo niente
				}else{
					newVector.add(tmp);
				}
			}else{
				newVector.add(tmp);
			}
			
		}
		
		v = newVector;
		
		if( form.getValue("preferito") != null ){
			System.out.println("SALVO? :" + form.getValue("preferito"));
			V = newVector;
			salvaRisultatoNeiPreferiti(v);
		}
	}

	public static void CaricaVettoreProdottiPreferito(){
		DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		String query = "SELECT * FROM lista_preferiti_prodotti WHERE Username = '"+SessioneUtente.getUsername()+"';";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			public void onSuccess(String[][] result) {
				V = new Vector<String[]>();
				for(int i=0; i<result.length; i++){
					String[] record = new String[2];
					record[0] = result[i][1];
					record[1] = result[i][2];
					V.add(record);
				}
				
			}
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	private void salvaRisultatoNeiPreferiti(final Vector<String[]> v){
		DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		String query = "DELETE FROM lista_preferiti_prodotti WHERE Username = '"+SessioneUtente.getUsername()+"';";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				
			}
			public void onSuccess(Boolean result) {
				for(int i=0; i<v.size(); i++){
					String[] tmp = v.get(i);
					String query = "INSERT INTO lista_preferiti_prodotti VALUES ('"+SessioneUtente.getUsername()+"','"+ tmp[0] +"','"+ tmp[1] +"');";
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
	
	public Vector<String[]> getVettoreProdottiFiltrato() {
		if(v == null) return V;
		if(v.size() == 0) return V;
		return v;
	}
	
}
