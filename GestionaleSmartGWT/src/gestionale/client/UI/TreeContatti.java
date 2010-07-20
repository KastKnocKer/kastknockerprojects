package gestionale.client.UI;

import gestionale.client.DB;
import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.client.Liste;
import gestionale.shared.Contatto;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
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
		/*
		tnClienti = new TreeNode("Clienti");
        tnClienti.setTitle("Clienti");
        
        tnFornitori = new TreeNode("Fornitori");
        tnFornitori.setTitle("Fornitori");
        
        tnTrasportatori = new TreeNode("Trasportatori");
        tnTrasportatori.setTitle("Trasportatori");
		*/
        thistree.setModelType(TreeModelType.CHILDREN);  
        thistree.setNameProperty("Contatti");
        root = new TreeNode("Root");
    	thistree.setRoot(root);
        
		this.aggiornaTreeContatti();

		
		
		
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
		
		
		/*
		
		Vector<String> vs = new Vector<String>();
		Contatto c;
		
		for(int i = 0; i<v.size(); i++){
        	c = v.get(i);
        	if( vs.contains(c.getTipoSoggetto()) ){
        		vs.add(c.getTipoSoggetto());
        	}
        }
		
		TreeNode[] tn = new TreeNode[vs.size()];
		for(int i=0; i<vs.size(); i++){
			tn[i] = new TreeNode(vs.get(i));
			tn[i].setTitle(vs.get(i));
		}
		root.setChildren(tn);*/
		/*
		rpc.eseguiQuery("SELECT * FROM tiposoggetto", new AsyncCallback<String[][]>(){
			
			public void onFailure(Throwable caught) {
				Window.alert("Errore: Caricamento da DB Contatti");
			}

			public void onSuccess(String[][] result) {
				TreeNode[] tn = new TreeNode[result.length];
					for(int i=0; i<result.length; i++){
						tn[i] = new TreeNode(result[i][0]);
						tn[i].setTitle(result[i][0]);
					}
				root.setChildren(tn);
			}	
		});*/
		
		
		
		
		/*
        
        Vector<Contatto> v = Liste.getVettoreContatti();
        
        for(int i = 0; i<v.size(); i++){
        	Contatto c = v.get(i);
        	c.getTipoSoggetto()
        }
        
        tna_Clienti = new TreeNode[v.size()];
        tna_Fornitori = new TreeNode[v.size()];
        tna_Trasportatori = new TreeNode[v.size()];
        
        int c=0,f=0,t=0;
        for(int i=0; i<v.size();i++){
        	Contatto contatto = v.get(i);
        	TreeNode tn = new TreeNode(contatto.getRagioneSociale());
        	tn.setTitle(contatto.getRagioneSociale());
        	
        	
        	if(contatto.getTipoSoggetto().equals("Cliente")){
        		tna_Clienti[c]=tn; c++;
        	}
        	else if(contatto.getTipoSoggetto().equals("Fornitore")){
        		tna_Fornitori[f]=tn; f++;
        	}
        	else if(contatto.getTipoSoggetto().equals("Trasportatore")){
        		tna_Trasportatori[t]=tn; t++;
        	}
        	
        	tnClienti.setChildren(tna_Clienti);
            tnFornitori.setChildren(tna_Fornitori);
            tnTrasportatori.setChildren(tna_Trasportatori);
        	        	
        	
        }
        
       */
		
        if(tg != null) {
        	tg.sort(0, SortDirection.DESCENDING);
        	tg.sort(0, SortDirection.ASCENDING);
        	
        }
	}
	
	public TreeGrid getTreeGrid(){
		
		tg = new TreeGrid();
        tg.setWidth100();
        tg.setHeight100(); 
        tg.setShowEdges(false);
        tg.setData(thistree);
        tg.setCanReorderRecords(true);  
        tg.setCanAcceptDroppedRecords(false);  
        tg.setCanDragRecordsOut(true); 
        
        tg.addNodeClickHandler(new NodeClickHandler() {
			
			public void onNodeClick(NodeClickEvent event) {
				System.out.println("Click: "+ event.getNode().getTitle());
				lastContactClicked = event.getNode().getTitle();
				Menu menu = new Menu();
				
				MenuItem mi_dettagli = new MenuItem("Mostra dettagli");
				MenuItem mi_modifica = new MenuItem("Modifica Contatto");
				MenuItem mi_rimuovi = new MenuItem("Rimuovi Contatto");
				
				
				mi_dettagli.addClickHandler( new ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						System.out.println("Sorgente lieta: " + event.getSource() );
						
						Finestra window = new Finestra();  
						window.setTitle("Dragging a window");  
						window.setWidth(300);  
						window.setHeight(230);  
						window.setCanDragReposition(true);  
						window.setCanDragResize(true);  
						window.addItem(new Label("KISS"));  
						          
						Canvas canvasMain = new Canvas();  
						canvasMain.addChild(window);  
						canvasMain.draw();
						
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
        
        /*
        tg.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				
				Menu menu = new Menu();
				menu.addItem( new MenuItem("KAKAK"));
				menu.visibleAtPoint(event.getX(), event.getY());
				
				System.out.println("Click: "+ event.getRecord().);/*
				if( event.isRightButtonDown() ) Window.alert("Right");
				if( event.isLeftButtonDown() ) Window.alert("Left");
				if( event.isAltKeyDown() ) Window.alert("AltDown");
				
				
			}
		});*/
        
        /*
         tg.addLeafClickHandler(new LeafClickHandler() {
        	
			public void onLeafClick(LeafClickEvent event) {
				System.out.println("Click: "+ event.getLeaf().getTitle());
				Vector<Contatto> v = Liste.getVettoreContatti();
				Contatto c = null;
				for(int i=0;i<v.size();i++){
					c = v.get(i);
					if(c.getRagioneSociale().equals(event.getLeaf().getTitle())) break;
				}
				
				new PanelContatti(c);		//Crea e aggiunge automaticamente il tabpanel
			}
		});
         */
		
        
        
		return tg;
	}
	
	

}
