import java.awt.event.*;
import javax.swing.*;


public class Main {

	/**
	 * @author KastKnocKer
	 */
	public static void main(String[] args) {
		
	JFrame JF=new JFrame("KastKnocKer Mp3-Auto-Renamer");
	JF.setVisible(true);
	JPanel JP=new JPanel();
	JButton JB1 = new JButton(Action_Listener.ButtonName1);
	JButton JB2 = new JButton(Action_Listener.ButtonName2);
	Action_Listener AL = new Action_Listener();
	JB1.addActionListener(AL);
	JB2.addActionListener(AL);
	JF.addWindowListener(AL);
	JP.add(JB1);
	JP.add(JB2);
	JF.add(JP);
	JF.pack();
		

	}



}
