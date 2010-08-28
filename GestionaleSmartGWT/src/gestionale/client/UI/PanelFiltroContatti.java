package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;
import gestionale.shared.Contatto;

import java.util.LinkedHashMap;
import java.util.Vector;

import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.Layout;

public class PanelFiltroContatti extends Layout{

	private Vector<Contatto> v = null;
	
	private DynamicForm form = null;
	
	private TextItem ti_Citta;
	
	
	
	public PanelFiltroContatti(Vector<Contatto> V){
		super();
		v = V;
		
		form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.LEFT);
		  
  
		
		
		//form.setItems(selectCategorie);
		
		this.addMember(form);
		
	}
	
	private void filtra(){
		
	}
	
}
