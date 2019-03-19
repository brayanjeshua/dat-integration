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

import hello.models.AssetDeleteModel;
import hello.models.AssetPostModel;
import hello.models.AssetUpdateModel;

class User {
    public String firstName;
    public String lastName;
}

@RestController
public class AssetController {
    @GetMapping(value = "/asset/shipment/postalcode2citystate")
    public String shipmentSearchPostalCode2CityState() throws RemoteException {
        new Search().run();
        return "hola";
        // return new
        // Search().shipmentSearchPostalCode2CityState(Login.sessionToken).toString();
    }
    /*
     * @GetMapping(value = "/asset/shipment/searchstate2zone") public String
     * shipmentSearchState2Zone() throws RemoteException { return new
     * Search().shipmentSearchState2Zone(Login.sessionToken).toString(); }
     * 
     * @GetMapping(value = "/asset/equipment/searchpostalcode2citystate") public
     * String equipmentSearchPostalCode2CityState() throws RemoteException { return
     * new
     * Search().equipmentSearchPostalCode2CityState(Login.sessionToken).toString();
     * }
     * 
     * @GetMapping(value = "/asset/equipment/searchstate2zone") public String
     * equipmentSearchState2Zone() throws RemoteException { return new
     * Search().equipmentSearchState2Zone(Login.sessionToken).toString(); }
     */

    @PostMapping(value = "/asset")
    public String postAsset(@RequestBody AssetPostModel props) throws RemoteException {
        return new AssetPost(props).process();
    }

    @PatchMapping(value = "/asset")
    public String updateAsset(@RequestBody AssetUpdateModel props) throws RemoteException {

        return new AssetUpdate(props).process();
    }

    @DeleteMapping(value = "/asset")
    public String deleteAsset(@RequestBody AssetDeleteModel props) throws RemoteException {
        return new AssetDelete(props).process();
    }
}