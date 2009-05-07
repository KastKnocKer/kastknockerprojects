package Liste;
import java.io.File;
import java.util.Vector;

public class ListaOrdini extends Vector{
	
	public static ListaOrdini LinkListaOrdini;
	public static Vector<String> LinkVectorOrdini;
	public static String IDUltimoOrdine;
	public static String DataUltimoOrdine;
	
	public ListaOrdini(){
		super();
		LinkListaOrdini=this;
		this.caricaOrdini();
		this.getVectorOrdini();
	}
	
	public void caricaOrdini(){
		Vector V = Database.eseguiQuery("SELECT * FROM Ordine_Query;");
		Ordine ordine;
		for(int i=0; i < V.size(); i++){
			ordine = new Ordine();
			String [] record = (String[]) V.elementAt(i);
			ordine.setIDOrdine(record[0]);
			ordine.setDataAperturaOrdine( record[1] );
			this.add( ordine );
		}
		IDUltimoOrdine=( (Ordine) this.get(0)).getIDOrdine();
		DataUltimoOrdine=( (Ordine) this.get(0)).getDataAperturaOrdine();
	}
	
	public void getVectorOrdini(){
		Vector<String> V = new Vector<String>();
		Ordine ordine;
		for(int i=0; i < LinkListaOrdini.size(); i++){
			ordine = (Ordine) LinkListaOrdini.get(i);
			V.add(ordine.getDataAperturaOrdine().substring(0, 10));
		}
		LinkVectorOrdini = V;
	}
	
	public String getIdOrdineDaData(String data){
		String tmp;
		Ordine ordine;
		for(int i=0; i<ListaOrdini.LinkListaOrdini.size();i++){
			ordine = (Ordine) ListaOrdini.LinkListaOrdini.get(i);
			System.out.println(ordine.getDataAperturaOrdine());
			if( ordine.getDataAperturaOrdine().equals(data)){
				return ordine.getIDOrdine();
			}
		}
		return null;
	}

	
}
