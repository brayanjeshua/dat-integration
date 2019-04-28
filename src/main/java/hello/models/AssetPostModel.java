
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

/**
 * @api {OBJECT} TruckStops TruckStops
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam (OneOf) {Object} truckStopIds
 * @apiParam (OneOf) {Number[]{0 – 9999}} truckStopIds.ids
 * @apiParam (OneOf) {Boolean} closest
 * @apiParam (OneOf) {Object} alternateClosest
 * @apiParam (OneOf) {[Place](#api-Custom_types-ObjectPlace)} alternateClosest.alternateOrigin
 * @apiParam {String="Flash","Highlight"} [enhancements]
 * @apiParam {String{0..8}} [posterDisplayName]
 */

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

/**
 * @api {OBJECT} Rate Rate
 * @apiGroup Custom types
 * @apiDescription Payment rate in USD (flat-rate or per-mile).
 * @apiVersion 1.0.0
 * @apiParam {Number{0.0 – 99999.99}} baseRateDollars Rate in US Dollars.
 * @apiParam {String="Flat","PerMile"} rateBasedOn Per-mile or flat-rate.
 * @apiParam {Number{0 – 9999}} [rateMiles] If the rate is based on per-mile, or flat-rate for a trip, this is the
 *           mileage used by the poster.
 * 
 * @apiExample {json} Flat Rate
 * 
 *             body:
 * 
 *             { baseRateDollars: 55.0, rateBasedOn: "Flat", rateMiles: 550 }
 * 
 * @apiExample {json} Per Mile Rate
 * 
 *             body:
 * 
 *             { baseRateDollars: 70.0, rateBasedOn: "PerMile", rateMiles: 50 }
 * 
 * @apiExample {json} Valid Rate
 * 
 *             body:
 * 
 *             { baseRateDollars: 70.0, rateBasedOn: "PerMile" }
 */
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

/**
 * @api {OBJECT} Shipment Shipment
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {String} equipmentType
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} origin
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} destination
 * @apiParam {[truckStops](#api-Custom_types-ObjectTruckstops)} [truckStops]
 * @apiParam {[Rate](#api-Custom_types-ObjectRate)} [rate]
 */
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

/**
 * @api {OBJECT} Area Area
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {stateProvince[]} [stateProvinces]
 * @apiParam {zone[]} [zones]
 */
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

/**
 * @api {OBJECT} EquipmentDestination EquipmentDestination
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam (OneOf) {[Place](#api-Custom_types-ObjectPlace)} place
 * @apiParam (OneOf) {[Area](#api-Custom_types-ObjectArea)} area
 * @apiParam (OneOf) {Boolean} open
 */
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

/**
 * @api {OBJECT} Equipment Equipment
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {String} equipmentType
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} origin
 * @apiParam {[EquipmentDestination](#api-Custom_types-ObjectEquipmentdestination)} destination
 */
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

/**
 * @apiDefine AssetPostModel
 * @apiVersion 1.0.0
 * @apiDescription Post an Asset.
 * 
 * @apiParam (OneOf) {[Shipment](#api-Custom_types-ObjectShipment)} shipment
 * @apiParam (OneOf) {[Equipment](#api-Custom_types-ObjectEquipment)} equipment
 * @apiParam {String[0..8]} [postersReferenceId]
 * 
 * @apiParam {Boolean} [ltl]
 * @apiParam {String[]{0.70}} [comments]
 * @apiParam {Number{1 – 99}} [count]
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions]
 * @apiParam {Number} [stops]
 * 
 * @apiParam {[Availability](#api-Custom_types-ObjectAvailability)} [availability]
 * @apiParam {Boolean} [includeAsset]
 */
public class AssetPostModel extends AbstractModel<com.tcore.tfmiFreightMatching.PostAssetOperation> {

    public ShipmentModel shipment = null;
    public EquipmentModel equipment = null;
    public DimensionsModel dimensions = null;
    public AvailabilityModel availability = null;

    public String postersReferenceId = null;
    public Boolean ltl = null;
    public Integer count = null;
    public Integer stops = null;

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
        if (availability != null)
            availability.fill(instance.addNewAvailability());

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

        if (comments != null) {
            instance.setCommentsArray(comments);
        }

        return instance;
    }
}