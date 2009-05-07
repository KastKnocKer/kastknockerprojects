package Archiviazione;

import java.util.Vector;

/** 
 * Classe Vettore estende la classe Vector specializzato per le stringhe
 * 
 * @author Castelli Andrea 
 **/

public class Vettore extends Vector{
	
	public Vettore(){
		super();
	}
	/**
	 * Ordina il vettore
	 */
	public void Ordina(){
		if (this.size()>2) this.QuickSort(this, 0, this.size()-1);
	}

	private void QuickSort(Vector<String> array, int left0, int right0) {

		// Dichiarazione variabili 
		int left;
		int right;
		String pivot = null;
		String tmp = null;
		// Fine dichiarazione variabili

		left = left0;
		right = right0 + 1; 
		
		pivot = array.elementAt(left0); 
		
		do {
			
			do left++; while (left <= right0 && array.elementAt(left).compareTo(pivot) < 0);
			
			
			do right--; while (array.elementAt(right).compareTo(pivot) > 0);
			
			
			if (left < right) {
				tmp = array.elementAt(left);
				array.setElementAt(array.elementAt(right), left);
				array.setElementAt(tmp, right);
			}
			
		}	
		while (left <= right);
		
		tmp = array.elementAt(left0);
		array.setElementAt(array.elementAt(right),left0);
		array.setElementAt(tmp, right);			
		 
		if (left0 < right) QuickSort(array, left0, right);
		if (left < right0) QuickSort(array, left, right0);
		}
	
	}
