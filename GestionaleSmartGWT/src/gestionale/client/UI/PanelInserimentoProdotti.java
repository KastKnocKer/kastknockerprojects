package gestionale.client.UI;

import com.smartgwt.client.widgets.layout.VLayout;

public class PanelInserimentoProdotti extends VLayout{

	public PanelInserimentoProdotti(){
		super();
		TreeInserimentoProdotti TIP = new TreeInserimentoProdotti();
		this.addMember(TIP.getTreeGrid());
		
	}
	
	
}
