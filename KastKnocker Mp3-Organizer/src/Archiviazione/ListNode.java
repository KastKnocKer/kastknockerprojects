package Archiviazione;

/** 
 * Lista a nodi che implementa l'interfaccia List
 * 
 * @author Castelli Andrea 
 * */

public class ListNode implements List{
	/**
	 * Variabile che linka il primo elemento.
	 */
	private Node node;
	/**
	 * Costruttori.
	 */
	public ListNode(){setNode(null);}
	
	public ListNode(Object o){setNode(new Node(o));}
	
	public ListNode(Object o,List l){if(l!=null) revInsert(l); insert(o);}
	/**
	 * Restituisce l'oggetto in testa.
	 */
	public Object head(){if(isEmpty()) return null; else return getNode().info;}
	/**
	 * Restituisce la coda della lista.
	 */
	public List tail(){
		if(isEmpty()) return null;
		else
		{
			ListNode tmp=new ListNode();
			tmp.setNode(getNode().next);
			return tmp;
		}
	}
	/**
	 * Inserisce inversamente.
	 */
	private void revInsert(List l){
		if (!l.isEmpty()){
			revInsert(l.tail());
			insert(l.head());
		}
	}
	/**
	 * Dice se la lista è vuota.
	 */
	public boolean isEmpty(){
		return (getNode()==null);
	}
	/**
	 * Restituisce l'ultimo elemento della lista.
	 */
	public Object last(){
		if(isEmpty()) return null;
		Node tmp=getNode();
		while(tmp.next!=null) tmp=tmp.next;
		return tmp.info;
	}
	/**
	 * Inserisce un oggetto nella lista.
	 */
	public void insert(Object o){
		node = new Node(o,node);
	}
	/**
	 * Rimuove un oggetto dalla lista.
	 */
	public void remove(Object o){
		if(!isEmpty()){
			Node pred=getNode();
			Node tmp=getNode().next;
			if(getNode().info.equals(o)) setNode(getNode().next);
			else
				while(tmp!=null)
					if(tmp.info.equals(o)){
						pred.next=tmp.next;
						return;
					}
					else
					{
						pred=pred.next;
						tmp=tmp.next;
					}
		}
	}
	/**
	 * Restituisce il numero di elementi della lista.
	 */
	public int length(){
		Node tmp=getNode();
		int cont=0;
		while(tmp!=null){
			tmp=tmp.next;
			cont++;
		}
		return cont;
	}
	/**
	 * Dice se la lista contiene l'oggetto richiesto.
	 */
	public boolean contains(Object o){
		Node tmp=getNode();
		while(tmp!=null){
			if(tmp.info.equals(o)) return true;
			tmp=tmp.next;
		}
		return false;
	}
	/**
	 * Restituisce la stringa dei dati della lista.
	 */
	public String toString(){
		String tmp="";
		for(Node i=getNode() ; i!=null ; i=i.next) tmp=tmp + " " + i.info.toString();
		return tmp;
	}
////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Metodi Aggiunti//////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	/**
	 * Restituisce l'elemento di posizione n richiesta. (n<lenght)
	 */
	public Object getObjPos(int Pos){
	Node tmp=getNode();
	if(isEmpty()) return null;
	if(Pos < this.length()){
		for(int i=0;i<Pos;i++) tmp=tmp.next;
		return tmp.info;
	}
	return null;
		
	}
	/**
	 * Setta il nodo dato
	 * @param node
	 */
	public void setNode(Node node) {
		this.node = node;
	}
	/**
	 * Restituisce il nodo
	 * @return Nodo
	 */
	public Node getNode() {
		return node;
	}
}
