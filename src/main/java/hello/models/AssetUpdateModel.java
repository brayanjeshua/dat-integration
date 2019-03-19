package hello.models;

class ShipmentUpdateModel extends AbstractModel<com.tcore.tfmiFreightMatching.ShipmentUpdate> {

    public Boolean ltl = null;
    public Integer count = null;
    public Integer stops = null;

    public String[] comments = null;

    public DimensionsModel dimensions = null;
    public TruckStopsModel truckStops = null;
    public RateModel rate = null;

    @Override
    public com.tcore.tfmiFreightMatching.ShipmentUpdate fill(com.tcore.tfmiFreightMatching.ShipmentUpdate instance)
            throws java.rmi.RemoteException {

        if (ltl != null)
            instance.setLtl(ltl);
        if (count != null)
            instance.setCount(count);
        if (stops != null)
            instance.setStops(stops);

        if (comments != null)
            for (String comment : comments)
                instance.addComments(comment);

        if (dimensions != null)
            dimensions.fill(instance.addNewDimensions());
        if (truckStops != null)
            truckStops.fill(instance.addNewTruckStops());
        if (rate != null)
            rate.fill(instance.addNewRate());

        return instance;
    }
}

class EquipmentUpdateModel extends AbstractModel<com.tcore.tfmiFreightMatching.EquipmentUpdate> {

    public Boolean ltl = null;
    public Integer count = null;
    public Integer stops = null;

    public String[] comments = null;

    public DimensionsModel dimensions = null;

    @Override
    public com.tcore.tfmiFreightMatching.EquipmentUpdate fill(com.tcore.tfmiFreightMatching.EquipmentUpdate instance)
            throws java.rmi.RemoteException {

        if (ltl != null)
            instance.setLtl(ltl);
        if (count != null)
            instance.setCount(count);
        if (stops != null)
            instance.setStops(stops);

        if (comments != null)
            for (String comment : comments)
                instance.addComments(comment);

        if (dimensions != null)
            dimensions.fill(instance.addNewDimensions());

        return instance;
    }
}

public class AssetUpdateModel extends AbstractModel<com.tcore.tfmiFreightMatching.UpdateAssetOperation> {

    public String assetId = null;
    public String postersReferenceId = null;

    public ShipmentUpdateModel shipmentUpdate = null;
    public EquipmentUpdateModel equipmentUpdate = null;

    @Override
    public com.tcore.tfmiFreightMatching.UpdateAssetOperation fill(
            com.tcore.tfmiFreightMatching.UpdateAssetOperation instance) throws java.rmi.RemoteException {

        if (assetId != null)
            instance.setAssetId(assetId);
        else if (postersReferenceId != null)
            instance.setPostersReferenceId(postersReferenceId);

        if (shipmentUpdate != null)
            shipmentUpdate.fill(instance.addNewShipmentUpdate());
        else if (equipmentUpdate != null)
            equipmentUpdate.fill(instance.addNewEquipmentUpdate());

        return instance;
    }
}
