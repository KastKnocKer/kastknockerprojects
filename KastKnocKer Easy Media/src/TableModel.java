import javax.swing.table.*;

public class TableModel extends AbstractTableModel{
	
	Vettore Voti;
	public TableModel(){
		Voti=new Vettore();
		Voti.caricaLibreriaFileTxt();
	}
	
	
	public String getColumnName(int col){
		switch(col){
		case 0: return "Nome Esame"; 
		case 1: return "Crediti"; 
		case 2: return "Voto";
		default: return "***";
		}
	}
	
	public boolean isCellEditable(int row,int col){
		if(col==2){return true;}
		
		return false;
		}
	

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return Voti.size();
	}

	public Object getValueAt(int row, int col) {
		Voto VotoInEsame = (Voto) Voti.elementAt(row);
		switch(col){
		case 0: return VotoInEsame.getNomeEsame(); 
		case 1: return VotoInEsame.getCrediti(); 
		case 2: return VotoInEsame.getVoto();
		default: return "***";
		}
	}
	
	public void setValueAt(Object value, int row, int col) {
		Voto VotoInEsame = (Voto) Voti.elementAt(row);
		VotoInEsame.setVoto(Integer.parseInt(value.toString()));
        fireTableCellUpdated(row, col);
        System.out.println("Esame modificato: "+VotoInEsame.getNomeEsame()+" - "+VotoInEsame.getVoto());
        Vettore VetEx=Vettore.getLinkVettore();
        VetEx.calcolaMedia();
        VetEx.AggiornaComponenti();
    }


}
