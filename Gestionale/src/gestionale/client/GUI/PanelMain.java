package gestionale.client.GUI;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class PanelMain extends DockPanel{
	

	public PanelMain(){
		
		this.add(new DecoStackPanelPersonalizzato(),DockPanel.CENTER);
		/*
		flexTable = new FlexTable();
		
	    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
	    flexTable.addStyleName("cw-FlexTable");
	    flexTable.setWidth("32em");
	    flexTable.setCellSpacing(5);
	    flexTable.setCellPadding(3);
	    
	    
	    DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	    ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		String query = "";
		

		
		this.add(flexTable,DockLayoutPanelPanel.CENTER);*/
	}



}
