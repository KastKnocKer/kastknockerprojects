package gestionale.client.GUI;

import java.util.Vector;
import gestionale.client.Liste;
import gestionale.shared.Contatto;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class PanelContatti extends DockPanel{

	public PanelContatti(){
		super();

		FlexTable flexTable = new FlexTable();
	    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
	    flexTable.addStyleName("cw-FlexTable");
	    flexTable.setWidth("32em");
	    flexTable.setCellSpacing(5);
	    flexTable.setCellPadding(3);
	    
	    this.add(flexTable,DockPanel.CENTER);
		
	}
	
	
}
