package gestionale.client;

public class SessioneUtente {

	private static String username = null;
	
	public SessioneUtente(){
		
	}

	public static void setUsername(String username) {
		SessioneUtente.username = username;
	}

	public static String getUsername() {
		return username;
	}
	
}
