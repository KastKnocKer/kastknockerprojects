package gestionale.client.UI;

import java.util.Vector;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.shared.Contatto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class WindowGestionePrezzi extends Finestra{
	
	private HLayout hlayout;
	private ListGrid lg;
	private String IDFornitore;
	private String CurrentDate = null;
	private static  DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);

	
	public WindowGestionePrezzi(String idfornitore){
		super();
		IDFornitore = idfornitore;
		
	    Vector<Contatto> v = DataSourceContatti.getVettoreFornitori();
	    Contatto contatto = null;
	    for(int i=0; i<v.size();i++){
	    	contatto = v.get(i);
	    	if( contatto.getID().equals(idfornitore) ){
	    		this.setTitle("Aggiornamento prezzi: "+contatto.getRagioneSociale());
	    		break;
	    	}
	    }
	    
		hlayout = new HLayout();
		lg = new ListGrid();
		
		ListGridField prodotto = new ListGridField("descprodotto","Prodotto");
		ListGridField imballaggio = new ListGridField("descimballaggio","Imballaggio");
		ListGridField prezzo = new ListGridField("prezzo","Prezzo");
		prezzo.setType(ListGridFieldType.FLOAT);
		lg.setAutoSaveEdits(false);
		prezzo.setCanEdit(true);
		
		prezzo.addEditorExitHandler(new EditorExitHandler() {
			
			@Override
			public void onEditorExit(EditorExitEvent event) {
				if(event.getNewValue() == null) return;
				Record rec = event.getRecord();
				System.out.println(rec.getAttribute("descprodotto")+" "+rec.getAttribute("descimballaggio")+" "+rec.getAttribute("datainserimento")+" "+rec.getAttribute("prezzo")+" "+event.getNewValue());
				String query = null;
				System.out.println("DATA: "+rec.getAttribute("datainserimento"));
				if( CurrentDate.equals( rec.getAttribute("datainserimento") ) ){
					query = "UPDATE assoc_fornitore_prodotto_prezzo SET Prezzo = '"+event.getNewValue()+"' WHERE ID = "+rec.getAttribute("id");
				}else{
					query = "INSERT INTO assoc_fornitore_prodotto_prezzo (IDFornitore,IDProdotto,IDImballaggio,Prezzo,DataInserimento) VALUES ('"+IDFornitore+"','"+rec.getAttribute("idprodotto")+"','"+rec.getAttribute("idimballaggio")+"','"+event.getNewValue()+"',CURDATE())";
					//rec.setAttribute("datainserimento", CurrentDate);
				}
				
				rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						}

					@Override
					public void onSuccess(Boolean result) {
					if(!result){
						Window.alert("Aggiornamento non avvenuto");
					}else{
						caricaProdotti();
					}
					}
				});
			
			}
		});
		
		lg.setFields(prodotto,imballaggio,prezzo);

		hlayout.addMember(lg);
		
		caricaProdotti();
		this.addItem(hlayout);
		this.setWidth(700);
		this.setHeight(400);
		this.centerInPage();
		this.draw();
	}
	
	private void caricaProdotti(){
		String query = null;
		
		query = "SELECT CURDATE()";
		rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(String[][] result) {
				CurrentDate = result[0][0];
			}
		});
		
		query = "INSERT INTO assoc_fornitore_prodotto_prezzo (IDFornitore,IDProdotto,IDImballaggio) SELECT f.IDFornitore,apip.IDProdotto,apip.IDImballaggio FROM fornitore_tipologie_trattate f JOIN prodotti_catalogati pc JOIN assoc_prodotto_imballaggio_peso apip ON pc.Categoria = f.Categoria AND pc.Tipologia = f.Tipologia AND apip.IDProdotto = pc.ID WHERE f.IDFornitore = '"+IDFornitore+"' AND NOT EXISTS (SELECT * FROM fornitore_tipologie_trattate f2 JOIN prodotti_catalogati pc2 JOIN assoc_prodotto_imballaggio_peso apip2 JOIN assoc_fornitore_prodotto_prezzo afpp2 ON pc2.Categoria = f2.Categoria AND pc2.Tipologia = f2.Tipologia AND apip2.IDProdotto = pc2.ID AND afpp2.IDFornitore = f2.IDFornitore AND afpp2.IDProdotto = pc2.ID AND afpp2.IDImballaggio = apip2.IDImballaggio WHERE f.IDFornitore = f2.IDFornitore AND apip.IDProdotto = apip2.IDProdotto AND apip.IDImballaggio = apip2.IDImballaggio )";
		rpc.eseguiUpdate(query, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				String query = "SELECT afpp.*,pc.Categoria,pc.Tipologia,pc.Varieta,pc.Sottovarieta,i.Descrizione FROM fornitore_tipologie_trattate f JOIN prodotti_catalogati pc JOIN assoc_prodotto_imballaggio_peso apip JOIN assoc_fornitore_prodotto_prezzo afpp JOIN imballaggio i ON pc.Categoria = f.Categoria AND pc.Tipologia = f.Tipologia AND apip.IDProdotto = pc.ID AND i.ID = apip.IDImballaggio AND afpp.IDFornitore = f.IDFornitore AND afpp.IDProdotto = pc.ID AND afpp.IDImballaggio = apip.IDImballaggio WHERE f.IDFornitore = '"+IDFornitore+"' AND afpp.DataInserimento = ( SELECT MAX(DataInserimento) FROM assoc_fornitore_prodotto_prezzo afpp2 WHERE afpp2.IDFornitore = afpp.IDFornitore AND afpp2.IDProdotto = afpp.IDProdotto AND afpp2.IDImballaggio = afpp.IDImballaggio);";
				rpc.eseguiQuery(query, new AsyncCallback<String[][]>() {

					@Override
					public void onFailure(Throwable caught) {Window.alert(caught.getMessage());}

					@Override
					public void onSuccess(String[][] result) {
						ListGridRecord[] records = new ListGridRecord[result.length];
						String[] rec = null;
						for(int i=0; i<result.length; i++){
							rec = result[i];
							records[i] = new ListGridRecord();
							records[i].setAttribute("id", rec[0]);
							records[i].setAttribute("idfornitore", rec[1]);
							records[i].setAttribute("idprodotto", rec[2]);
							records[i].setAttribute("idimballaggio", rec[3]);
							records[i].setAttribute("prezzo", rec[4]);
							records[i].setAttribute("datainserimento", rec[5]);
							records[i].setAttribute("descprodotto", rec[6]+" "+rec[7]+" "+rec[8]+" "+rec[9]);
							records[i].setAttribute("descimballaggio", rec[10]);
						}
						lg.setData(records);
					}
					
				});
			}
			
			@Override
			public void onFailure(Throwable caught) {Window.alert(caught.getMessage()+"\n"+caught.getLocalizedMessage());}
		});
		
		
	}

}
