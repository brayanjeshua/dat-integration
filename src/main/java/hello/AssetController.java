package hello;

import java.rmi.RemoteException;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.StateProvince;

@RestController
public class AssetController {
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

    @DeleteMapping(value = "/asset")
    public String deleteAsset(@RequestParam(value = "type") String type,
            @RequestParam(value = "ids") Optional<String[]> ids) throws RemoteException {
        return new AssetDelete(type, ids).process();
    }
}