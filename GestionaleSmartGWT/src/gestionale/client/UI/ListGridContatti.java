package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceContatti;

import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ListGridContatti extends ListGrid{
	

	public ListGridContatti(){
		super();
		setCanEdit(true);
		setHeight100();
		setWidth100();
		setShowAllRecords(true);
		//setCellHeight(22);
		setGroupStartOpen(GroupStartOpen.NONE);
		setGroupByField("tiposoggetto");
		setDataSource(DataSourceContatti.getIstance());
		
		ListGridField nameField = new ListGridField("ragionesociale", "Ragione Sociale");
		ListGridField continentField = new ListGridField("tiposoggetto", "Tipo Soggetto");  
		continentField.setHidden(true);
		setFields(nameField, continentField);
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
