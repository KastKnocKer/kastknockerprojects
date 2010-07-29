package gestionale.client.UI;

import gestionale.client.DB;
import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.Liste;
import gestionale.client.DataBase.DataSourceContatti;
import gestionale.client.DataBase.DataSourceProdotti;
import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class TreeContatti extends Tree{
	
	private static TreeContatti thistree;
	private static TreeGrid tg;
	private static TreeNode root;
	
	private static String lastContactClicked;
    
	private final static DBConnectionAsync rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	
    
	public TreeContatti(){
		super();
		thistree=this;
        thistree.setModelType(TreeModelType.CHILDREN);  
        thistree.setNameProperty("Contatti");
        root = new TreeNode("Root");
    	thistree.setRoot(root);
        
		//this.aggiornaTreeContatti();

		
		
		
	}
	
	public static void aggiornaTreeContatti(){
		
		Vector<String> vts = Liste.getVettoreTipoSoggetto();
		Vector<Contatto> v = Liste.getVettoreContatti();
		Contatto c;
		TreeNode[] tna = new TreeNode[vts.size()];
		TreeNode[][] tnMatrix = new TreeNode[vts.size()][v.size()];
		
		int[] indici = new int[v.size()];
		
		for(int i=0; i<vts.size(); i++){
			indici[i] = 0;
		}
		
		TreeNode tn;
		
		for(int i=0; i<vts.size(); i++){
			tn = new TreeNode( vts.get(i) );
			tn.setTitle( vts.get(i) );
			tna[i] = tn;
			System.out.println("LALALALA" + vts.get(i) );
		}
		
		
		for( int i=0; i<v.size(); i++){
			c = v.get(i);
			for(int k=0; k<vts.size(); k++){
				if( c.getTipoSoggetto().equals(tna[k].getTitle()) ){
					tn = new TreeNode( c.getRagioneSociale() );
					tn.setTitle(c.getRagioneSociale());
					tnMatrix[k][ indici[k] ] = tn;
					indici[k]++;
					break;
				}
				
				
			}
			
			
		}
		
		for( int i=0; i<vts.size(); i++){
			tna[i].setChildren(tnMatrix[i]);
		}

	

		
		root.setChildren(tna);
		
		
        if(tg != null) {
        	tg.sort(0, SortDirection.DESCENDING);
        	tg.sort(0, SortDirection.ASCENDING);
        	
        }
	}
	
	public TreeGrid getTreeGrid(){
		
		tg = new TreeGrid();
        
        tg.setLoadDataOnDemand(false);  
		tg.setWidth100();
        tg.setHeight100(); 
        tg.setDataSource( DataSourceContatti.getIstance() );  
        //tg.setCanEdit(true);  
        tg.setAutoFetchData(true);
        tg.setAutoSaveEdits(true);
        tg.setCanFreezeFields(true);  
        tg.setCanReparentNodes(true);
        tg.setCanDrag(false);
		
        TreeGridField nameField = new TreeGridField("Name");
        tg.setFields(nameField);
        
        tg.addRecordClickHandler(new RecordClickHandler() {
			
        	public void onRecordClick(RecordClickEvent event) {
				lastContactClicked = event.getRecord().getAttribute("Name");
				Menu menu = new Menu();
				
				MenuItem mi_dettagli = new MenuItem("Mostra dettagli");
				MenuItem mi_modifica = new MenuItem("Modifica Contatto");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Contatto");
				
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						
						Finestra window = new Finestra();  
						window.setTitle(lastContactClicked);
						Contatto contatto = null;
						for(int i=0; i<Liste.getVettoreContatti().size(); i++){
							contatto = Liste.getVettoreContatti().get(i);
							if( contatto.getRagioneSociale().equals(lastContactClicked)){
								window.addItem(new Label(contatto.getHtmlText()));
								break;
							}
						}
						window.setWidth(300);  
						window.setHeight(230);
						window.setCanDragReposition(true);  
						window.setCanDragResize(true);  
						window.centerInPage();
						window.draw();
						
					}
				});
				
				mi_modifica.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						Contatto contatto = null;
						for(int i=0; i<Liste.getVettoreContatti().size(); i++){
							contatto = Liste.getVettoreContatti().get(i);
							if( contatto.getRagioneSociale().equals(lastContactClicked)){
								new PanelContatti(contatto);
								break;
							}
						}
					}
				});
				
				mi_rimuovi.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						if( Window.confirm("Sei sicuro di voler rimuovere: "+lastContactClicked+"?") ){
							DB db = new DB();
							Contatto contatto = null;
							for(int i=0; i<Liste.getVettoreContatti().size(); i++){
								contatto = Liste.getVettoreContatti().get(i);
								if( contatto.getRagioneSociale().equals(lastContactClicked)){
									String query = "DELETE FROM contatti WHERE ID='" + contatto.getID()+ "'";
					        		db.eseguiUpdateToDB(query);
					        		Liste.getVettoreContatti().remove(i);
									break;
								}
							}
			        		
			        		
			        		TreeContatti.aggiornaTreeContatti();
						}
					}
				});
				
				
				
				menu.addItem( mi_dettagli );
				menu.addItem( mi_modifica );
				menu.addItem( mi_rimuovi );
				
				menu.setAutoDraw(true);
				menu.showContextMenu();
			}
		});

        
		return tg;
	}
	
	

}
