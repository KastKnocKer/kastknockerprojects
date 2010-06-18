package gestionale.client.GUI;

import gestionale.client.DBConnection;
import gestionale.client.DBConnectionAsync;
import gestionale.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;

public class PanelLogin extends DockPanel{
	
	
	
	private static	TextBox tb;
	private static PasswordTextBox ptb;
	private static Button loginButton;
	private static DecoratorPanel dp = null;
	private static VerticalPanel vp = null;
	private static Image img = null;
	
	private DBConnectionAsync rpc;	/*Per connessione al DB*/
	
	
	
	public PanelLogin(){
		super();
		
		/**/
		rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
		ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "DBConnection";
		target.setServiceEntryPoint(moduleRelativeURL);
		/**/
		
		

		tb = new TextBox();
		tb.addStyleName("textboxlogin");
		ptb = new PasswordTextBox();
		ptb.addStyleName("textboxlogin");
		loginButton = new Button("Login");
		
		dp = new DecoratorPanel();
		vp = new VerticalPanel();
		
		img = new Image("MIAMilano.jpg");
		
		dp.add(vp);
		vp.add(new Label("Username"));
		vp.add(tb);
		vp.add(new Label("Password"));
		vp.add(ptb);
		vp.add(loginButton);
		
		this.add(dp, CENTER);
		
		
		//Aggiungo gli Handler al bottone
		
		AuthenticationHandler handler = new AuthenticationHandler<User>();
		loginButton.addClickHandler(handler);
		loginButton.addKeyUpHandler(handler);

	}
	
	
	public static void visualizzaPLogin(){		
		RootPanel.get("centro0").add(img);
		RootPanel.get("centro1").add(dp);
	}
	
//CREAZIONE HANDLER PER IL BOTTONE PER IL LOGIN
	

	private class AuthenticationHandler<T> implements ClickHandler, KeyUpHandler, AsyncCallback<User> {
		
			public void onFailure(Throwable ex) {
				Window.alert(ex.getMessage());
			}
			
			public void onSuccess(User result) {
				if(result == null){
					loginButton.setText("Login errato, riprova!!");	
				}else{
					GUI.enterAfterLogin();
				}
				
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					AsyncCallback<User> callback = new AuthenticationHandler<User>();
					rpc.authenticateUser(new User(tb.getText(),ptb.getText()),callback);
				}
			}

			public void onClick(ClickEvent event) {
				AsyncCallback<User> callback = new AuthenticationHandler<User>();
				rpc.authenticateUser(new User(tb.getText(),ptb.getText()),callback);
			}

		

			
	}

}
