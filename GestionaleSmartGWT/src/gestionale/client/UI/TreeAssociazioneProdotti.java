package gestionale.client.UI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceProdotti_Selezione;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class TreeAssociazioneProdotti extends TreeGrid{

	private static  DBConnectionAsync	rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	private TreeAssociazioneProdotti thistree = null;
	
	public TreeAssociazioneProdotti(){
		
		thistree = this;
		
		setDataSource(	DataSourceProdotti_Selezione.getIstance()	);
		setWidth(200);  
        setHeight(240);  
        setNodeIcon("icons/16/person.png");  
        setFolderIcon("icons/16/person.png");  
        setShowOpenIcons(false);  
        setShowDropIcons(false);  
        setClosedIconSuffix("");
        setSelectionAppearance(SelectionAppearance.CHECKBOX);  
        setShowSelectedStyle(false);  
        setShowPartialSelection(false);  
        setCascadeSelection(false);  
		setAutoFetchData(true);
		
		
	}
	
	public void setFornitore(String IDFornitore){
		String query = "SELECT * FROM fornitore_tipologie_trattate";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			
			@Override
			public void onSuccess(String[][] result) {
				if(result == null) return;
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
	}
	
	public String[][] getSelezioni(){
		ListGridRecord[]  lgr = this.getSelection();
		
		for(int i=0; i<lgr.length; i++){
			lgr[i].getAttribute("Name");
		}
		
		return null;
	}
	
}
