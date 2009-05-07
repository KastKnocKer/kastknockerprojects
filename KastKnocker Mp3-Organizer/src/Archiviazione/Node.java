package Archiviazione;

/** 
 * Nodo per la lista a nodi
 * 
 * @author Castelli Andrea 
 * */

public class Node {
	Object info;
	Node next;
	/**
	 * Costruttore
	 * @param Object
	 */
	public Node(Object o){
		this(o,null);
	}
	/**
	 * Costruttore
	 * @param Object
	 * @param NextNode
	 */
	public Node(Object o, Node n){
		info=o;
		next=n;
	}
	/**
	 * Ritorna il nodo successivo
	 * @return Node
	 */
	public Node getNext(){
		return this.next;
	}

}
