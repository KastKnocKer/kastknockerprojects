package gestionale.client.UI;


import gestionale.client.DataBase.DataSourceProdottiCatalogati;


import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ListGridProdotti extends ListGrid{
	

	public ListGridProdotti(){
		super();
		setCanEdit(true);
		setHeight100();
		setWidth100();
		setShowAllRecords(true);
		//setCellHeight(22);
		//setGroupStartOpen(GroupStartOpen.NONE);
		//setGroupByField("tiposoggetto");
		setDataSource(DataSourceProdottiCatalogati.getIstance());
		
		
		ListGridField categoriaField = new ListGridField("categoria", "Categoria");  
        ListGridField tipologiaField = new ListGridField("tipologia", "Tipologia");  
        ListGridField varietaField = new ListGridField("varieta", "Varieta");  
        ListGridField sottovarietaField = new ListGridField("sottovarieta", "Sottovarieta");  
        ListGridField calibroField = new ListGridField("calibro", "Calibro");  
        
		
		//ListGridField nameField = new ListGridField("ragionesociale", "Ragione Sociale");
		//ListGridField continentField = new ListGridField("tiposoggetto", "Tipo Soggetto");  
		//continentField.setHidden(true);
		setFields(categoriaField, tipologiaField, varietaField, sottovarietaField, calibroField);
		setAutoFetchData(false);
		fetchData();
		/*
		 
  
        IButton unGroupButton = new IButton("Ungroup");  
        unGroupButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                countryGrid.ungroup();  
            }  
        });  
  
  
        IButton groupByButton = new IButton("Group By Continent");  
        groupByButton.setWidth(140);  
        groupByButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                countryGrid.groupBy("continent");  
            }  
        });  
  
        buttonLayout.addMember(unGroupButton);  
        buttonLayout.addMember(groupByButton);  
          
        layout.addMember(countryGrid);  
        layout.addMember(buttonLayout);  
		 */
	}
}
