package hello;

import java.rmi.RemoteException;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tfmiFreightMatching.CreateSearchSuccessData;

class User {
    public String firstName;
    public String lastName;
}

@RestController
public class AssetController {
    @GetMapping(value = "/asset/shipment")
    public String assetShipmentGet(@RequestBody User user) throws RemoteException {
        return new Search().shipmentSearchPostalCode2CityState(Login.sessionToken).toString();
        // return user;
    }

    @PostMapping(value = "/asset")
    public String postAsset(@RequestBody AssetPostModel props) throws RemoteException {
        return new AssetPost(props).process();
    }

    @PatchMapping(value = "/asset")
    public String updateAsset(@RequestBody AssetUpdateModel props) throws RemoteException {

        return new AssetUpdate(props).process();
    }

    @DeleteMapping(value = "/asset")
    public String deleteAsset(@RequestParam(value = "type") String type,
            @RequestParam(value = "ids") Optional<String[]> ids) throws RemoteException {
        return new AssetDelete(type, ids).process();
    }
}