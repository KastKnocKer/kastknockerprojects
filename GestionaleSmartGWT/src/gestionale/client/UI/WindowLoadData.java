package gestionale.client.UI;

import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceImballaggi;
import gestionale.client.DataBase.DataSourceOrdini;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.client.DataBase.DataSourceProdottiCatalogati;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

public class WindowLoadData extends Window{

	private WindowLoadData thisWin = null;
	
	public WindowLoadData(){
		
		super();
		thisWin = this;
		
		//Carico i dati
		DataSourceProdotti.getIstance();
		DataSourceProdottiCatalogati.getIstance();
		DataSourceContatti.getIstance();
		DataSourceOrdini.getIstance();
		DataSourceImballaggi.getIstance();
		
		PanelFiltroProdotti.CaricaVettoreProdottiPreferito();
		PanelFiltroContatti.CaricaVettoreContattiPreferito();
		
		
		
		VLayout horizontalBars = new VLayout();
		horizontalBars.setHeight100();
		horizontalBars.setWidth100();
        
        final Label etichettaBarra = new Label("Current File Progress");  
        etichettaBarra.setHeight(16);
        
        
        final Progressbar barra = new Progressbar();  
        barra.setHeight(24);  
        barra.setWidth100(); 
        barra.setVertical(false);  
        
        horizontalBars.addMember(etichettaBarra);  
        horizontalBars.addMember(barra);
		
        
        this.setTitle("Caricamento dati");
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.setCanDragReposition(true);  
		this.setCanDragResize(true);
		this.setShowCloseButton(false);
		this.setWidth(300);
		this.setHeight(90);
		this.centerInPage();
		this.addItem(horizontalBars);
		this.draw();
		
		new Timer() {  
			int percentuale = 0;
            public void run() {
            	int contatore = 0;
            	
            	if( DataSourceProdotti.getIstance().isReady() )	contatore++;
            	if( DataSourceProdottiCatalogati.getIstance().isReady() )	contatore++;
            	if( DataSourceContatti.getIstance().isReady() )	contatore++;
            	if( DataSourceOrdini.getIstance().isReady() )	contatore++;
            	if( DataSourceImballaggi.getIstance().isReady() )	contatore++;
        		
            	
            	System.out.println(DataSourceProdotti.getIstance().isReady()+" "+DataSourceProdottiCatalogati.getIstance().isReady()+" "+DataSourceContatti.getIstance().isReady()+" "+DataSourceOrdini.getIstance().isReady()+" "+DataSourceImballaggi.getIstance().isReady());
            	percentuale = 100*contatore/5;
            	if(percentuale<100){
            		etichettaBarra.setContents("Caricamento dati: "+percentuale+"%");
            		barra.setPercentDone(percentuale);
            		schedule(50);
            	}else{
            		thisWin.destroy();
            	}
            	
            }  
        }.schedule(50);
		
	}
	
}
