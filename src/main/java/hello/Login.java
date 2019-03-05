package hello;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Value;

import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.BaseSampleClient;

public class Login extends BaseSampleClient {
	private final String email;
	private final String password;

	public static SessionToken sessionToken;

	public Login(String email, String password) throws RemoteException {
		this.email = email;
		this.password = password;
		this.sessionToken = login(this.email, this.password);
	}

	@Override
	public void run() throws RemoteException {
		System.out.println("End point: " + endpointUrl);
	}

	public String getContent() {
		return this.sessionToken.toString();
	}
}