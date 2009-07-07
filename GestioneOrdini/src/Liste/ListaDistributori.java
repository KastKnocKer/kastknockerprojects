package Liste;

import java.util.Vector;

public class ListaDistributori extends Vector<Distributore>{
	
	public static ListaDistributori LinkListaDistributori = null;
	
	public ListaDistributori(){
		this.LinkListaDistributori=this;
		this.caricaDistributori();
	}
	
	private void caricaDistributori(){
		String query = "SELECT * FROM Distributore;";
		Vector V = Database.eseguiQuery(query);
		Distributore distr;
		for(int i=0; i<V.size(); i++){
			String[] record = (String[]) V.get(i);
			distr = new Distributore();
			distr.setID(record[0]);
			distr.setNome(record[1]);
			this.add(distr);
		}
		
	}
	
	public static String getIDDistributoreDaNome(String nome){
		Distributore distr;
		for(int i=0; i<ListaDistributori.LinkListaDistributori.size(); i++){
			distr = ListaDistributori.LinkListaDistributori.get(i);
			if( distr.getNome().equals(nome) ) return distr.getID();
		}
		return null;
	}
		

}
