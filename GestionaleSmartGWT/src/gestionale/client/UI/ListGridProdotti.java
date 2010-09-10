package gestionale.client.UI;


import gestionale.client.DataBase.DataSourceProdottiCatalogati;


import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ListGridProdotti extends ListGrid{
	

	public ListGridProdotti(){
		super();
		setCanEdit(false);
		setHeight100();
		setWidth100();
		setShowAllRecords(true);
		setAutoFetchData(true);
		setDataSource( DataSourceProdottiCatalogati.getIstance() );
		
		ListGridField categoriaField = new ListGridField("categoria", "Categoria");  
        ListGridField tipologiaField = new ListGridField("tipologia", "Tipologia");  
        ListGridField varietaField = new ListGridField("varieta", "Varieta");  
        ListGridField sottovarietaField = new ListGridField("sottovarieta", "Sottovarieta");  
        ListGridField calibroField = new ListGridField("calibro", "Calibro");  
        
		setFields(categoriaField, tipologiaField, varietaField, sottovarietaField, calibroField);
		
		fetchData();
		
	}
}
