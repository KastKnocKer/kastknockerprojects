package gestionale.client.UI;

import com.smartgwt.client.widgets.tab.Tab;

import gestionale.shared.Ordine;

public class PanelOrdine {
	
	private Tab tab;
	private PanelOrdine thisPanel;
	
	public PanelOrdine(Ordine ordine){
		super();
		if(ordine == null){
	
		}else{
			
		}
		
		thisPanel=this;
		
	}
	
	
	
	
	private void closePanel(){
		GUIManager.getTopTabset().removeTab(tab);
	}

	private void addToTabPanel(String tabName, boolean canclose){

		Tab[] tabArray = GUIManager.getTopTabset().getTabs();
		
        for( int i=0; i<tabArray.length; i++){
        	if( tabArray[i].getTitle().equals( tabName ) ){
        		GUIManager.getTopTabset().selectTab(tabArray[i]);
        		return;
        		}
        }
		
		tab = new Tab(tabName);
		//tab.setPane( thisPanel );
        tab.setCanClose(canclose);
        GUIManager.getTopTabset().addTab(tab);
        GUIManager.getTopTabset().selectTab(tab);
		
		return;
		
	}
	
}
