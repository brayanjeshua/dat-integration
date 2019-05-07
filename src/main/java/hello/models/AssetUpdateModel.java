package hello.models;

/**
 * @api {OBJECT} ShipmentUpdate ShipmentUpdate
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Boolean} [ltl] “Less than truck load”. For shipments, if set true, then this shipment will not occupy a
 *           full truck. For equipment, if set true, then this equipment does not have enough available capacity to
 *           carry a full shipment. The element dimensions should be used to specify cargo/capacity size.
 * @apiParam {String[]{0-70}} [comments] The number of comments on a posting exposed to truck- stops cannot be
 *           decreased. Omitting comments from an update is allowable (the previous comment(s) will be left unchanged),
 *           but including a single comment when the posting currently includes 2 will result in an error.
 * @apiParam {Number{1 – 99}} [count] The number of assets represented by this description. This allows a requester to
 *           easily (and cheaply) post mul- tiple identical assets.
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions] For shipments, this is the dimensions of
 *           the cargo. For equipment, this is the dimensions of the available cargo space.
 * @apiParam {Number} [stops] Number of stops this asset must make.
 * @apiParam {[truckStops](#api-Custom_types-ObjectTruckstops)} [truckStops] Truckstop locations can be altered, but the
 *           number of truckstops cannot be decreased.
 * @apiParam {[Rate](#api-Custom_types-ObjectRate)} [rate] Payment rate in USD (flat-rate or per-mile).
 */
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

/**
 * @api {OBJECT} EquipmentUpdate EquipmentUpdate
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Boolean} [ltl] “Less than truck load”. For shipments, if set true, then this shipment will not occupy a
 *           full truck. For equipment, if set true, then this equipment does not have enough available capacity to
 *           carry a full shipment. The element dimensions should be used to specify cargo/capacity size.
 * @apiParam {String[]{0-70}} [comments] The number of comments on a posting exposed to truck- stops cannot be
 *           decreased. Omitting comments from an update is allowable (the previous comment(s) will be left unchanged),
 *           but including a single comment when the posting currently includes 2 will result in an error.
 * @apiParam {Number{1 – 99}} [count] The number of assets represented by this description. This allows a requester to
 *           easily (and cheaply) post mul- tiple identical assets.
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions] For shipments, this is the dimensions of
 *           the cargo. For equipment, this is the dimensions of the available cargo space.
 * @apiParam {Number} [stops] Number of stops this asset must make.
 */
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

/**
 * @apiDefine AssetUpdateModel
 * @apiVersion 1.0.0
 * @apiDescription Update an Asset.
 * 
 * @apiParam (OneOfId) {String{0-8}} assetId The Asset ID of the asset to be updated.
 * @apiParam (OneOfId) {String{0-8}} postersReferenceId The Reference ID of the asset to be updated.
 * 
 * @apiParam (OneOf) {[ShipmentUpdate](#api-Custom_types-ObjectShipmentupdate)} shipmentUpdate Shipment-specific items
 *           which can be updated.
 * @apiParam (OneOf) {[EquipmentUpdate](#api-Custom_types-ObjectEquipmentupdate)} equipmentUpdate There are currently no
 *           equipment-specific items which can be updated. This element is included for potential future expansion.
 */
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
