package hello;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tcoreTypes.StateProvince;

@RestController
public class AssetController {
    @PostMapping(value = "/asset/shipment")
    public Asset asset(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
            @RequestParam(value = "originCity") String originCity,
            @RequestParam(value = "originState") StateProvince.Enum originState,
            @RequestParam(value = "destinationCity") String destinationCity,
            @RequestParam(value = "destinationState") StateProvince.Enum destinationState,
            @RequestParam(value = "destinationLatitude") float destinationLatitude,
            @RequestParam(value = "destinationLongitude") float destinationLongitude) throws RemoteException {

        final Login session = new Login(email, password);

        return new Asset(session.getToken(), destinationCity, destinationState, destinationCity, destinationState,
                destinationLongitude, destinationLongitude);
    }
}