package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceGiacenzaMagazzino;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class PanelGestioneMagazzino extends VLayout{
	
	private ListGrid lg;
	
	public PanelGestioneMagazzino(){
		super();
		
		lg = new ListGrid();
		lg.setWidth100();
		lg.setHeight100();
		lg.setDataSource(new DataSourceGiacenzaMagazzino());
		
		
		
		this.addMember(lg);
	}

}
