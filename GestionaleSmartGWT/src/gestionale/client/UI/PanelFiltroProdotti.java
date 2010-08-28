package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Prodotto;

import java.util.Vector;

import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.layout.Layout;

public class PanelFiltroProdotti extends Layout{

	private DynamicForm form = null;
	private Vector<String[]> v = null;
	
	private SelectItem selectCategorie;
	private SelectItem selectTipologie;
	
	public PanelFiltroProdotti(Vector<String[]> vettoreProdottiDaVisualizzare){
		super();
		v = vettoreProdottiDaVisualizzare;
	
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
				String selezionato = "";
				for(int i=0; i<selezione.length; i++){
					selezionato = selezionato + " " + selezione[i];
				}

				Vector<Prodotto> vTemp = DataSourceProdottiCatalogati.getvProdottiCatalogati();
				Prodotto prodotto = null;
				String[] map = new String[vTemp.size()];
				int indicemap = 0;
				for(int i=0; i<vTemp.size(); i++){
					prodotto = vTemp.get(i);
					if(selezionato.contains((String) prodotto.getCategoria())){
						
						if(indicemap == 0){
							map[indicemap] = prodotto.getTipologia();
							indicemap++;
						}
						if(map[indicemap-1].equals(prodotto.getTipologia())){
							
						}else{
						map[indicemap] = prodotto.getTipologia();
						indicemap++;
						}
					}
				}
				selectTipologie.setValueMap(map);
			}
		});
		
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
		
		
		form.setItems(selectCategorie,selectTipologie);
		this.addMember(form);
	}
	
}
