package hello;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.StateProvince;

@RestController
public class AssetController {
    @RequestMapping(value = "/asset/shipment")
    public AssetShipment assetShipment(@RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "equipmentType") String equipmentType,
            @RequestParam(value = "lengthFeet") Integer lengthFeet,
            @RequestParam(value = "weightPounds") Integer weightPounds,
            @RequestParam(value = "originCity") String originCity,
            @RequestParam(value = "originState") String originState,
            @RequestParam(value = "destinationCity") String destinationCity,
            @RequestParam(value = "destinationState") String destinationState,
            @RequestParam(value = "destinationLatitude") float destinationLatitude,
            @RequestParam(value = "destinationLongitude") float destinationLongitude) throws RemoteException {

        final Login session = new Login(email, password);

        System.out.println(EquipmentType.Enum.forString(equipmentType));

        return new AssetShipment(session.getToken(), EquipmentType.Enum.forString(equipmentType), lengthFeet,
                weightPounds, originCity, StateProvince.Enum.forString(originState), destinationCity,
                StateProvince.Enum.forString(destinationState), destinationLatitude, destinationLongitude);
    }
}