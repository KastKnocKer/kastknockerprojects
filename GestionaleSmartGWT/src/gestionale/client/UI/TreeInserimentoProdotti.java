package gestionale.client.UI;

import gestionale.client.Liste;
import gestionale.client.ProdottoOBJ;
import gestionale.shared.Contatto;

import java.util.Vector;

import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;
import com.smartgwt.client.widgets.tree.events.LeafContextClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafContextClickHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class TreeInserimentoProdotti extends Tree{
	
	private static TreeInserimentoProdotti thistree;
	private static TreeNode[] tna_Categoria;
	
	
	private static TreeGrid tg;
	private static TreeNode[] tna_Clienti;
	private static TreeNode[] tna_Fornitori;
	private static TreeNode[] tna_Trasportatori;
	private static TreeNode tnClienti;
	private static TreeNode tnFornitori;
	private static TreeNode tnTrasportatori;
	private static TreeNode root;
    
    
	public TreeInserimentoProdotti(){
		super();
		thistree=this;
		
		Vector<String[]> v = ProdottoOBJ.getTabCategoria();
		System.out.println(v);
		if (v != null){
		
		tna_Categoria = new TreeNode[v.size()];
		
		
		for(int i=0; i<v.size(); i++){
			TreeNode tn = new TreeNode(v.get(i)[0]);
			tn.setTitle(v.get(i)[0]);
			tn.setChildren( this.getTipologie(v.get(i)[0]) );
			tna_Categoria[i] = tn;
		}
		
		
        thistree.setModelType(TreeModelType.CHILDREN);  
        thistree.setNameProperty("Contatti");
        
        root = new TreeNode("Root",tna_Categoria);
    	thistree.setRoot(root);
        
		//this.aggiornaTreeContatti();

		
		}
		
	}
	
	public static void aggiornaTreeContatti(){
		
		
        
	}
	
	public TreeGrid getTreeGrid(){
		
		tg = new TreeGrid();
        tg.setWidth100();
        tg.setHeight100(); 
        tg.setShowEdges(true);
        tg.setData(thistree);
        tg.setCanReorderRecords(true);  
        tg.setCanAcceptDroppedRecords(false);  
        tg.setCanDragRecordsOut(true); 
        
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
	
	public TreeNode[] getTipologie(String categoria){
		Vector<String[]> v = ProdottoOBJ.getTabTipologia();
		TreeNode[] tna = new TreeNode[v.size()];
		int k = 0;
		for(int i=0; i<v.size(); i++){
			String s = v.get(i)[1];
			if(s.equals(categoria)){
			TreeNode tn = new TreeNode(v.get(i)[0]);
			tn.setTitle(v.get(i)[0]);
			tn.setChildren( this.getVarieta(v.get(i)[0]) );
			tna[k] = tn;
			k++;}
		}
		
		return tna;
	}
	
	public TreeNode[] getVarieta(String categoria){
		Vector<String[]> v = ProdottoOBJ.getTabVarieta();
		TreeNode[] tna = new TreeNode[v.size()];
		int k = 0;
		for(int i=0; i<v.size(); i++){
			String s = v.get(i)[1];
			if(s.equals(categoria)){
			TreeNode tn = new TreeNode(v.get(i)[0]);
			tn.setTitle(v.get(i)[0]);
			tn.setChildren( this.getSottoVarieta(v.get(i)[0]) );
			tna[k] = tn;
			k++;}
		}
		
		return tna;
	}
	
	public TreeNode[] getSottoVarieta(String categoria){
		Vector<String[]> v = ProdottoOBJ.getTabSottovarieta();
		TreeNode[] tna = new TreeNode[v.size()];
		int k = 0;
		for(int i=0; i<v.size(); i++){
			String s = v.get(i)[1];
			if(s.equals(categoria)){
			TreeNode tn = new TreeNode(v.get(i)[0]);
			tn.setTitle(v.get(i)[0]);
			tn.setChildren( this.getCalibro(v.get(i)[0]) );
			tna[k] = tn;
			k++;}
		}
		
		return tna;
	}
	
	public TreeNode[] getCalibro(String categoria){
		Vector<String[]> v = ProdottoOBJ.getTabSottovarieta();
		TreeNode[] tna = new TreeNode[v.size()];
		int k = 0;
		for(int i=0; i<v.size(); i++){
			String s = v.get(i)[1];
			if(s.equals(categoria)){
			TreeNode tn = new TreeNode(v.get(i)[0]);
			tn.setTitle(v.get(i)[0]);
			//tn.setChildren( this.getImballaggio(v.get(i)[0]) );
			tna[k] = tn;
			k++;}
		}
		
		return tna;
	}

}
