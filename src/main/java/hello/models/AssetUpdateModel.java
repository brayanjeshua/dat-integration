package hello.models;

/**
 * @api {OBJECT} ShipmentUpdate ShipmentUpdate
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Boolean} [ltl]
 * @apiParam {String[]{0.70}} [comments]
 * @apiParam {Number{1 – 99}} [count]
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions]
 * @apiParam {Number} [stops]
 * 
 * @apiParam {[truckStops](#api-Custom_types-ObjectTruckstops)} [truckStops]
 * @apiParam {[Rate](#api-Custom_types-ObjectRate)} [rate]
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
 * @apiParam {Boolean} [ltl]
 * @apiParam {String[]{0.70}} [comments]
 * @apiParam {Number{1 – 99}} [count]
 * @apiParam {[Dimensions](#api-Custom_types-ObjectDimensions)} [dimensions]
 * @apiParam {Number} [stops]
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
 * @apiParam (OneOfId) {assetId{0..8}} assetId
 * @apiParam (OneOfId) {assetId{0..8}} postersReferenceId
 * 
 * @apiParam (OneOf) {[ShipmentUpdate](#api-Custom_types-ObjectShipmentupdate)} shipmentUpdate
 * @apiParam (OneOf) {[EquipmentUpdate](#api-Custom_types-ObjectEquipmentupdate)} equipmentUpdate
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
