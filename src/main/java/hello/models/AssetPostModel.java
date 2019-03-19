
package hello.models;

public class AssetPostModel extends AbstractModel<com.tcore.tfmiFreightMatching.PostAssetOperation> {

    public ShipmentModel shipment = null;
    public EquipmentModel equipment = null;

    public String postersReferenceId = null;
    public Boolean ltl = null;
    public String[] comments = null;
    public Integer count = null;
    public DimensionsModel dimensions = null;
    public Integer stops = null;
    // public Availability
    // public Boolean alarm;
    public Boolean includeAsset = null;

    public com.tcore.tfmiFreightMatching.PostAssetOperation fill(
            com.tcore.tfmiFreightMatching.PostAssetOperation instance) throws java.rmi.RemoteException {

        if (shipment != null)
            shipment.fill(instance.addNewShipment());
        else if (equipment != null)
            equipment.fill(instance.addNewEquipment());

        if (postersReferenceId != null)
            instance.setPostersReferenceId(postersReferenceId);

        if (ltl != null)
            instance.setLtl(ltl);

        if (comments != null)
            for (String comment : comments)
                instance.addComments(comment);

        if (count != null)
            instance.setCount(count);

        if (dimensions != null)
            dimensions.fill(instance.addNewDimensions());

        if (stops != null)
            instance.setStops(stops);

        if (includeAsset != null)
            instance.setIncludeAsset(includeAsset);

        return instance;
    }
}