package hello;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Value;

import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.BaseSampleClient;

public class Login extends BaseSampleClient {
	public static SessionToken sessionToken;
	private final String email;
	private final String password;
	final SessionToken token;

	private final String primary;
	// private final String secondary;

	public Login(String email, String password) throws RemoteException {
		this.email = email;
		this.password = password;
		this.token = login(this.email, this.password);
		this.primary = this.token.xgetPrimary().getStringValue();
		// this.secondary = this.token.xgetSecondary().getStringValue();
		this.sessionToken = this.token;
		System.out.println(this.token);
	}

	@Override
	public void run() throws RemoteException {
		System.out.println("End point: " + endpointUrl);
	}

	public String getContent() {
		return this.token.toString();
	}

	public SessionToken getToken() {
		return this.token;
	}
}