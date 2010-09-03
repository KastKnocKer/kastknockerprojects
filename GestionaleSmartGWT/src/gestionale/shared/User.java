package gestionale.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	
	private String username;
	private String password;
	
	
	
	public User() {
	//just here because GWT wants it.
	}
	
	public User(String username, String password) {
	this.setUsername(username);
	this.setPassword(password);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
}