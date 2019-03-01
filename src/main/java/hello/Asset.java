package hello;

import java.rmi.RemoteException;

import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.BaseSampleClient;
import com.tcore.tcoreTypes.StateProvince;

public class Asset extends BaseSampleClient {
    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    public Asset(SessionToken sessionToken, String originCity, StateProvince.Enum originState, String destinationCity,
            StateProvince.Enum destinationState, float destinationLatitude, float destinationLongitude) {
    }
}
