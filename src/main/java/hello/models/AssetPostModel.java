
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
 * 
 * @apiDescription All the information needed to advertise this shipment on the Truck Stop network. If omitted, this
 *                 shipment will not be advertised on the Truck Stop Network.
 * 
 * @apiParam (OneOf) {Object} truckStopIds
 * @apiParam (OneOf) {Number[]{0 – 9999}} truckStopIds.ids A list of TransCore/DAT identifiers for the truckstops the
 *           shipment should be displayed at.
 * @apiParam (OneOf) {Boolean} closest Select the truckstops closest to the shipment origin. The number of selected
 *           locations is determined by the poster’s subscription and contract.
 * @apiParam (OneOf) {Object} alternateClosest Select the truckstops closest to an alternate origin point. This option
 *           is typically used to display the shipment on truckstops in the desired lane. The number of selected
 *           locations is determined by the poster’s subscription and contract.
 * @apiParam (OneOf) {[Place](#api-Custom_types-ObjectPlace)} alternateClosest.alternateOrigin Origin point.
 * @apiParam {String="Flash","Highlight"} [enhancements] Optional truck stop load monitor video enhancements for this
 *           shipment’s advertisement.
 * @apiParam {String{0-8}} [posterDisplayName] The short name of the poster’s company, for display on the truck stop
 *           load monitors. If omitted, the short name of the poster’s company will default to that contained in the
 *           poster’s company record.
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
 * @apiParam {Number{0.0 – 99999.99}} baseRateDollars Rate in US Dollars (the rateBasedOn element defines whether this
 *           is a flat rate or per-mile).
 * @apiParam {String="Flat","PerMile"} rateBasedOn Per-mile or flat-rate.
 * @apiParam {Number{0 – 9999}} [rateMiles] If the rate is based on per-mile, or flat-rate for a trip, this is the
 *           mileage used by the poster. It is supplied by the poster, and may not agree with HHG mileage or that
 *           computed by mileage vendors.
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
 * @apiParam {String} equipmentType The type of equipment that is needed to haul this ship- ment.
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} origin The origination point of this shipment.
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} destination The destination point of this shipment.
 * @apiParam {[truckStops](#api-Custom_types-ObjectTruckstops)} [truckStops] All the information needed to advertise
 *           this shipment on the Truck Stop network. If omitted, this shipment will not be advertised on the Truck Stop
 *           Network.
 * @apiParam {[Rate](#api-Custom_types-ObjectRate)} [rate] Payment rate in USD (flat-rate or per-mile).
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
 * 
 * @apiDescription A geographic area, defined by a list of states or a region.
 * 
 * @apiParam {String[]= "AB", "AG", "AK", "AL", "AS", "AZ", "AR", "BC", "BJ", "BS", "CA", "CH", "CI", "CL", "CO", "CP",
 *           "CT", "CU", "DC", "DE", "DF", "DG", "EM", "FL", "GA", "GJ", "GR", "GU", "HG", "HI", "IA", "ID", "IL", "IN",
 *           "JA", "KS", "KY", "LA", "MA", "MB", "MD", "ME", "MH", "MI", "MN", "MO", "MR", "MS", "MT", "NA", "NE", "NL",
 *           "NV", "NB", "NH", "NJ", "NM", "NY", "NF", "NC", "ND", "NT", "NS", "NU", "OA", "OH", "OK", "ON", "OR", "PA",
 *           "PE", "PQ", "PR", "PU", "QA", "QR", "RI", "SC", "SD", "SI", "SK", "SL", "SO", "TA", "TL", "TM", "TN", "TX",
 *           "UT", "VA", "VI", "VL", "VT", "WA", "WV", "WI", "WY", "YC", "YT", "ZT"} [stateProvinces] A list of states
 *           and/or provinces in the desired region.
 * @apiParam {String[]="New England", "North East", "Mid-Atlantic", "South East", "Mid-West", "North Central", "Central"
 *           "South", "Mountain", "West", "Canada", "Western Canada", "Eastern Canada", "Central Canada", "Mexico",
 *           "Northern Mexico", "Contiguous USA"} [zones] A list of one or more zones in the desired region.
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
 * @apiParam (OneOf) {[Place](#api-Custom_types-ObjectPlace)} place A specific location.
 * @apiParam (OneOf) {[Area](#api-Custom_types-ObjectArea)} area A geographic area, defined by a list of states or a
 *           region.
 * @apiParam (OneOf) {Boolean} open Represents “anywhere”.
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
 * @apiParam {String} equipmentType The type of this equipment.
 * @apiParam {[Place](#api-Custom_types-ObjectPlace)} origin The origination point of this equipment.
 * @apiParam {[EquipmentDestination](#api-Custom_types-ObjectEquipmentdestination)} destination The desired destination
 *           (point, area, or open).
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
 * @apiParam {String{0-8}} [postersReferenceId] Poster-supplied reference ID for this asset. Posters often use this to
 *           tag their assets with their own internal IDs.
 * @apiParam {Boolean} [ltl=false] “Less than truck load”. For shipments, if set true, then this shipment will not
 *           occupy a full truck. For equipment, if set true, then this equipment does not have enough available
 *           capacity to carry a full shipment. The element dimensions should be used to specify cargo/capacity size.
 * @apiParam {String[]{0-70}} [comments] Optional comment(s) to be displayed for this asset on search results and the
 *           Truck Stop Network.
 * @apiParam {Number{1 – 99}} [count=1] The number of assets represented by this description. This allows a requester to
 *           easily (and cheaply) post mul- tiple identical assets.
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions] For shipments, this is the dimensions of
 *           the cargo. For equipment, this is the dimensions of the available cargo space.
 * @apiParam {Number{0-}} [stops=1] Number of stops this asset must make.
 * 
 * @apiParam {[Availability](#api-Custom_types-ObjectAvailability)} [availability] For shipments, when available for
 *           pickup at its origi- nation point. For equipment, when available to pickup shipments.
 * @apiParam {Boolean} [includeAsset=false] If set true, then the response will return a copy of the asset (and the
 *           alarm if one was specified) as they were actually processed and posted by the Freight Matching Service.
 *           This saves the requester from doing a subse- quent LookupAsset request.
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