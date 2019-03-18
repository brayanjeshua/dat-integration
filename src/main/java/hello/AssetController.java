package hello;

import java.rmi.RemoteException;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.StateProvince;
import com.tcore.tfmiFreightMatching.CreateSearchSuccessData;

class User {
    public String firstName;
    public String lastName;
}

@RestController
public class AssetController {
    @GetMapping(value = "/asset/shipment")
    public CreateSearchSuccessData assetShipmentGet(@RequestBody User user) throws RemoteException {
        return new Search().shipmentSearchPostalCode2CityState(Login.sessionToken);
        // return user;
    }

    @PostMapping(value = "/asset/shipment")
    public AssetShipment assetShipment(@RequestParam(value = "equipmentType") String equipmentType,
            @RequestParam(value = "lengthFeet") Integer lengthFeet,
            @RequestParam(value = "weightPounds") Integer weightPounds,
            @RequestParam(value = "originCity") String originCity,
            @RequestParam(value = "originState") String originState,
            @RequestParam(value = "destinationCity") String destinationCity,
            @RequestParam(value = "destinationState") String destinationState,
            @RequestParam(value = "destinationLatitude") float destinationLatitude,
            @RequestParam(value = "destinationLongitude") float destinationLongitude) throws RemoteException {

        System.out.println(EquipmentType.Enum.forString(equipmentType));

        return new AssetShipment(Login.sessionToken, EquipmentType.Enum.forString(equipmentType), lengthFeet,
                weightPounds, originCity, StateProvince.Enum.forString(originState), destinationCity,
                StateProvince.Enum.forString(destinationState), destinationLatitude, destinationLongitude);
    }

    @PostMapping(value = "/asset")
    public String postAsset(@RequestParam(value = "assetType") String assetType,
            @RequestParam(value = "equipmentType") String equipmentType,
            @RequestParam(value = "originType") String originType,
            @RequestParam(value = "secondaryOriginType", required = false, defaultValue = "") String secondaryOriginType,
            @RequestParam(value = "originCountry", required = false, defaultValue = "") String originCountry,
            @RequestParam(value = "originCode", required = false, defaultValue = "") String originCode,
            @RequestParam(value = "originCity", required = false, defaultValue = "") String originCity,
            @RequestParam(value = "originStateProvince", required = false, defaultValue = "") String originStateProvince,
            @RequestParam(value = "originCounty", required = false, defaultValue = "") String originCounty,
            @RequestParam(value = "originLatitude", required = false, defaultValue = "0.0") Float originLatitude,
            @RequestParam(value = "originLongitude", required = false, defaultValue = "0.0") Float originLongitude,
            @RequestParam(value = "destinationType") String destinationType,
            @RequestParam(value = "secondaryDestinationType", required = false, defaultValue = "") String secondaryDestinationType,
            @RequestParam(value = "destinationCountry", required = false, defaultValue = "") String destinationCountry,
            @RequestParam(value = "destinationCode", required = false, defaultValue = "") String destinationCode,
            @RequestParam(value = "destinationCity", required = false, defaultValue = "") String destinationCity,
            @RequestParam(value = "destinationStateProvince", required = false, defaultValue = "") String destinationStateProvince,
            @RequestParam(value = "destinationCounty", required = false, defaultValue = "") String destinationCounty,
            @RequestParam(value = "destinationLatitude", required = false, defaultValue = "0.0") Float destinationLatitude,
            @RequestParam(value = "destinationLongitude", required = false, defaultValue = "0.0") Float destinationLongitude,

            @RequestParam(value = "baseRateDollars", required = false, defaultValue = "-1") Float baseRateDollars,
            @RequestParam(value = "rateBasedOn", required = false, defaultValue = "") String rateBasedOn,
            @RequestParam(value = "rateMiles", required = false, defaultValue = "-1") Integer rateMiles,

            @RequestParam(value = "destAreaStateProvinces", required = false, defaultValue = "") String[] destAreaStateProvinces,
            @RequestParam(value = "destAreaZones", required = false, defaultValue = "") String[] destAreaZones,

            // ** Optional parameters **
            @RequestParam(value = "postersReferenceId", required = false, defaultValue = "") String postersReferenceId,
            @RequestParam(value = "ltl", required = false, defaultValue = "false") Boolean ltl,
            @RequestParam(value = "comments", required = false, defaultValue = "") String[] comments,
            @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
            // * Optional dimensions parameters *
            @RequestParam(value = "lengthFeet", required = false, defaultValue = "0") Integer lengthFeet,
            @RequestParam(value = "weightPounds", required = false, defaultValue = "0") Integer weightPounds,
            @RequestParam(value = "heightInches", required = false, defaultValue = "0") Integer heightInches,
            @RequestParam(value = "volumeCubicFeet", required = false, defaultValue = "0") Integer volumeCubicFeet,
            @RequestParam(value = "stops", required = false, defaultValue = "1") Integer stops,
            // * Optional availability parameters *
            // @RequestParam(value = "earliest", required=false) Date earliest,
            // @RequestParam(value = "latest", required=false) Date latest,
            // @RequestParam(value = "alarm", required=false) Boolean alarm,
            @RequestParam(value = "includeAsset", required = false, defaultValue = "false") Boolean includeAsset

    ) throws RemoteException {

        if (comments == null) {
            comments = new String[] {};
        }

        return new AssetPost(assetType, equipmentType,

                originType, secondaryOriginType, originCountry, originCode, originCity, originStateProvince,
                originCounty, originLatitude, originLongitude,

                destinationType, secondaryDestinationType, destinationCountry, destinationCode, destinationCity,
                destinationStateProvince, destinationCounty, destinationLatitude, destinationLongitude,

                baseRateDollars, rateBasedOn, rateMiles,

                destAreaStateProvinces, destAreaZones,

                // ** Optional parameters **
                postersReferenceId, ltl, comments, count,

                // * Optional dimensions parameters *
                lengthFeet, weightPounds, heightInches, volumeCubicFeet,

                stops,

                // * Optional availability parameters *
                // earliest,
                // latest,

                // alarm,
                includeAsset

        ).process();
    }

    @DeleteMapping(value = "/asset")
    public String deleteAsset(@RequestParam(value = "type") String type,
            @RequestParam(value = "ids") Optional<String[]> ids) throws RemoteException {
        return new AssetDelete(type, ids).process();
    }
}