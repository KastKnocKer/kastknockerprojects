import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import Liste.Database;


public class Pannello extends JPanel{

	public int x=10;
	public int y=0;
	public Vector<String[]> V = null;
	
	public Pannello(){
		super();
		String query ="SELECT * FROM Query_Ordine_PrezziFornitore WHERE IDOrdine=20;";
		V = Database.eseguiQuery(query);
		
	}

	
	public void paint (Graphics g){
		
		for(int i=0; i<V.size(); i++){
			String[] tmp = V.get(i);
			g.drawString(tmp[3], x+0,y+20*i);
			g.drawString(tmp[2], x+50,y+20*i);
			g.drawString(tmp[4], x+100,y+20*i);
			g.drawString(tmp[5], x+150,y+20*i);
			g.drawString(tmp[6], x+200,y+20*i);
		}
		
	}




}
