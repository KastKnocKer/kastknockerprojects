import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Action_Listener implements ActionListener, WindowListener{

	public static String ButtonName1 = "Rinomina File Mp3";
	public static String ButtonName2 = "Rinomina Cartella Mp3";
	
	public Action_Listener(){
		
	}

	public void actionPerformed(ActionEvent E) {
		String ID = E.getActionCommand();
		if(ID.equals(ButtonName1)){
			System.out.println("Rinomina 1 Mp3");
			Mp3 Mp3Obj = new Mp3();
			Mp3Obj.importFileMp3();
			Mp3Obj.RinominaMp3();
		}
		if(ID.equals(ButtonName2)){
			System.out.println("Rinomina + Mp3");
			Mp3 Mp3Obj = new Mp3();
			Mp3Obj.importDirMp3();
			Mp3Obj.RinominaMp3();
		}
		
		
	}

	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {System.exit(0);}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	
}
