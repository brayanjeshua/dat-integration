
package hello.models;

public class AssetPostModel extends AbstractModel<com.tcore.tfmiFreightMatching.PostAssetOperation> {

    public ShipmentModel shipment = null;
    // public EquipmentModel equipment = null;

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

        if (includeAsset != null)
            instance.setIncludeAsset(includeAsset);

        return instance;
    }

    public String assetType;

    public String equipmentType;

    public String originType; // [place,]

    public String secondaryOriginType = "";
    public String originCountry = "";
    public String originCode = "";
    public String originCity = "";
    public String originStateProvince = "";
    public String originCounty = "";
    public Float originLatitude = 0.0f;
    public Float originLongitude = 0.0f;

    public String destinationType; // [place,]

    public String secondaryDestinationType = "";
    public String destinationCountry = "";
    public String destinationCode = "";
    public String destinationCity = "";
    public String destinationStateProvince = "";
    public String destinationCounty = "";
    public Float destinationLatitude = 0.0f;
    public Float destinationLongitude = 0.0f;

    public Float baseRateDollars = -1.0f;
    public String rateBasedOn = "";
    public Integer rateMiles = -1;

    public String[] destAreaStateProvinces = {};
    public String[] destAreaZones = {};

    //

    // ** Optional parameters **

    // * Optional dimensions parameters *
    public Integer lengthFeet = 0;
    public Integer weightPounds = 0;
    public Integer heightInches = 0;
    public Integer volumeCubicFeet = 0;
}