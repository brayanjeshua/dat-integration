package hello;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Value;

import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.BaseSampleClient;

public class Login extends BaseSampleClient {

    @Value("${sessionToken.Id}")
    private String sessionTokenId;
    private final String email;
    private final String password;
    private final SessionToken token;

    private final String primary;
    // private final String secondary;

    public Login(String email, String password) throws RemoteException {
        this.email = email;
        this.password = password;
        this.token = login(this.email, this.password);
        this.primary = this.token.xgetPrimary().getStringValue();
        // this.secondary = this.token.xgetSecondary().getStringValue();

        // System.out.println(this.token);
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    public String getContent() {
        return this.primary;
    }

    public SessionToken getToken() {
        return this.token;
    }
}