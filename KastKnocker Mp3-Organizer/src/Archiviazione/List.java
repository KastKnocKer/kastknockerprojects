package Archiviazione;
/** 
 * Interfaccia: indica tutti i metodi necessari per una lista.
 * 
 * @author Castelli Andrea 
 * */

public interface List {
/**
 * Indica se la lista è vuota
 * @return boolean
 */
public boolean isEmpty();
/**
 * Ritorna l'elemento in testa alla lista
 * @return Object
 */
public Object head();
/**
 * Ritorna la lista senza l'elemento in testa
 * @return Lista
 */
public List tail();
/**
 * Ritorna l'ultimo elemento
 * @return Object
 */
public Object last();
/**
 * Inserisce l'elemento dato nella lista
 * @param Object
 */
public void insert(Object o);
/**
 * Rimuove dalla lista l'elemento dato
 * @param Object
 */
public void remove(Object o);
/**
 * Ritorna il numero di elementi della lista
 * @return Numero elementi lista
 */
public int length();
/**
 * Indica se la lista contiene l'oggetto passato
 * @param Object
 * @return boolean
 */
public boolean contains(Object o);
/**
 * Restituisce l'elemento della posizione indicata
 * @param row
 * @return Object
 */
public Object getObjPos(int row);

}
