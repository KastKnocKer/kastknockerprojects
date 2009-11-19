import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;


public class ClasseMouse {

	public ClasseMouse(){}
	
	static public void  Click(int number){
	
	Robot asd;
	try {
		Thread.currentThread().sleep(5000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	for (int i=0; i<number; i++){
		try {
			asd = new Robot();

			asd.mousePress(InputEvent.BUTTON1_MASK);
			asd.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	
	}
	
	}
}
