package com.transcore.connexion.sample;

import java.rmi.RemoteException;

import com.tcore.tcoreTypes.SessionToken;

import cltool4j.GlobalConfigProperties;

/**
 * Demonstrates a simple user login.
 * 
 * For clarity, this sample code does minimal error handling. When developing a production application, we
 * strongly recommend implementing full error handling as demonstrated in {@link ErrorHandling}.
 * 
 */
public class Login extends BaseSampleClient {

    public SessionToken log(final String email, final String password) throws RemoteException {
//        final String loginId = GlobalConfigProperties.singleton().getProperty("loginId1");
//        final String password = GlobalConfigProperties.singleton().getProperty("password1");

//        System.out.println("Logging in with loginId " + loginId);
//        System.out.println(login(loginId, password));
//        System.out.println("Login Successful");
		return login(email, password);
    }
    
    @Override
    public void run() throws RemoteException {
        final String loginId = GlobalConfigProperties.singleton().getProperty("loginId1");
        final String password = GlobalConfigProperties.singleton().getProperty("password1");

        System.out.println("Logging in with loginId " + loginId);
        System.out.println(login(loginId, password));
        System.out.println("Login Successful");
    }

    public static void main(final String[] args) {
    	run(args);
    }
}
