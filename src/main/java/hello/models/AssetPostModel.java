
package hello.models;

// SHIPMENT SPECIFIC MODELS

class TruckStopIdsModel extends AbstractModel<com.tcore.tfmiFreightMatching.TruckStopIds> {

    public Integer[] ids;

    @Override
    public com.tcore.tfmiFreightMatching.TruckStopIds fill(com.tcore.tfmiFreightMatching.TruckStopIds instance) {

        for (Integer id : ids)
            instance.addIds(id);

        return instance;
    }

}

class AlternateClosestModel extends AbstractModel<com.tcore.tfmiFreightMatching.AlternateClosestTruckStops> {
    public PlaceModel alternateOrigin;

    @Override
    public com.tcore.tfmiFreightMatching.AlternateClosestTruckStops fill(
            com.tcore.tfmiFreightMatching.AlternateClosestTruckStops instance) throws java.rmi.RemoteException {

        alternateOrigin.fill(instance.addNewAlternateOrigin());

        return instance;
    }
}

class TruckStopsModel extends AbstractModel<com.tcore.tfmiFreightMatching.TruckStops> {

    public Boolean closest = null;
    public String[] enhancements = null;
    public String posterDisplayName = null;

    public TruckStopIdsModel truckStopIds = null;
    public AlternateClosestModel alternateClosest = null;

    @Override
    public com.tcore.tfmiFreightMatching.TruckStops fill(com.tcore.tfmiFreightMatching.TruckStops instance)
            throws java.rmi.RemoteException {

        if (truckStopIds != null)
            truckStopIds.fill(instance.addNewTruckStopIds());
        else if (closest != null)
            instance.addNewClosest();
        else if (alternateClosest != null)
            alternateClosest.fill(instance.addNewAlternateClosest());

        if (enhancements != null)
            for (String enhancement : enhancements)
                instance.addEnhancements(
                        com.tcore.tfmiFreightMatching.TruckStopVideoEnhancement.Enum.forString(enhancement));

        if (posterDisplayName != null)
            instance.setPosterDisplayName(posterDisplayName);

        return instance;
    }
}

class RateModel extends AbstractModel<com.tcore.tfmiFreightMatching.ShipmentRate> {

    public Float baseRateDollars;
    public String rateBasedOn;
    public Integer rateMiles = null;

    @Override
    public com.tcore.tfmiFreightMatching.ShipmentRate fill(com.tcore.tfmiFreightMatching.ShipmentRate instance)
            throws java.rmi.RemoteException {

        instance.setBaseRateDollars(baseRateDollars.floatValue());
        instance.setRateBasedOn(com.tcore.tfmiFreightMatching.RateBasedOnType.Enum.forString(rateBasedOn));
        if (rateMiles != null)
            instance.setRateMiles(rateMiles.intValue());

        return instance;
    }
}

class ShipmentModel extends AbstractModel<com.tcore.tfmiFreightMatching.Shipment> {

    public String equipmentType;

    public PlaceModel origin;
    public PlaceModel destination;
    public TruckStopsModel truckStops = null;
    public RateModel rate = null;

    @Override
    public com.tcore.tfmiFreightMatching.Shipment fill(com.tcore.tfmiFreightMatching.Shipment instance)
            throws java.rmi.RemoteException {

        instance.setEquipmentType(com.tcore.tcoreTypes.EquipmentType.Enum.forString(equipmentType));

        origin.fill(instance.addNewOrigin());
        destination.fill(instance.addNewDestination());
        if (truckStops != null)
            truckStops.fill(instance.addNewTruckStops());
        if (rate != null)
            rate.fill(instance.addNewRate());

        return instance;
    }
}

// EQUIPMENT SPECIFIC MODELS

class AreaModel extends AbstractModel<com.tcore.tcoreTypes.Area> {

    public String[] stateProvinces = null;
    public String[] zones = null;

    @Override
    public com.tcore.tcoreTypes.Area fill(com.tcore.tcoreTypes.Area instance) throws java.rmi.RemoteException {

        if (stateProvinces != null)
            for (String state : stateProvinces)
                instance.addStateProvinces(com.tcore.tcoreTypes.StateProvince.Enum.forString(state));
        if (zones != null)
            for (String zone : zones)
                instance.addZones(com.tcore.tcoreTypes.Zone.Enum.forString(zone));

        return instance;
    }
}

class EquipmentDestinationModel extends AbstractModel<com.tcore.tfmiFreightMatching.EquipmentDestination> {

    public PlaceModel place = null;
    public AreaModel area = null;

    public Boolean open = null;

    @Override
    public com.tcore.tfmiFreightMatching.EquipmentDestination fill(
            com.tcore.tfmiFreightMatching.EquipmentDestination instance) throws java.rmi.RemoteException {
        if (place != null)
            place.fill(instance.addNewPlace());
        else if (area != null)
            area.fill(instance.addNewArea());
        else if (open != null)
            instance.addNewOpen();

        return instance;
    }
}

class EquipmentModel extends AbstractModel<com.tcore.tfmiFreightMatching.Equipment> {

    public String equipmentType;

    public PlaceModel origin;
    public EquipmentDestinationModel destination;

    @Override
    public com.tcore.tfmiFreightMatching.Equipment fill(com.tcore.tfmiFreightMatching.Equipment instance)
            throws java.rmi.RemoteException {

        instance.setEquipmentType(com.tcore.tcoreTypes.EquipmentType.Enum.forString(equipmentType));

        origin.fill(instance.addNewOrigin());
        destination.fill(instance.addNewDestination());

        return instance;
    }
}

public class AssetPostModel extends AbstractModel<com.tcore.tfmiFreightMatching.PostAssetOperation> {

    public ShipmentModel shipment = null;
    public EquipmentModel equipment = null;
    public DimensionsModel dimensions = null;

    public String postersReferenceId = null;
    public Boolean ltl = null;
    public Integer count = null;
    public Integer stops = null;
    // public Availability
    // public Boolean alarm;
    public Boolean includeAsset = null;

    public String[] comments = null;

    @Override
    public com.tcore.tfmiFreightMatching.PostAssetOperation fill(
            com.tcore.tfmiFreightMatching.PostAssetOperation instance) throws java.rmi.RemoteException {

        if (shipment != null)
            shipment.fill(instance.addNewShipment());
        else if (equipment != null)
            equipment.fill(instance.addNewEquipment());
        if (dimensions != null)
            dimensions.fill(instance.addNewDimensions());

        if (postersReferenceId != null)
            instance.setPostersReferenceId(postersReferenceId);
        if (ltl != null)
            instance.setLtl(ltl);
        if (count != null)
            instance.setCount(count);
        if (stops != null)
            instance.setStops(stops);
        if (includeAsset != null)
            instance.setIncludeAsset(includeAsset);

        if (comments != null)
            for (String comment : comments)
                instance.addComments(comment);

        return instance;
    }
}