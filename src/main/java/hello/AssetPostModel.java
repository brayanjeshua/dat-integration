
package hello;

public class AssetPostModel {
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
    public String postersReferenceId = "";
    public Boolean ltl = false;
    public String[] comments = {};
    public Integer count = 1;

    // * Optional dimensions parameters *
    public Integer lengthFeet = 0;
    public Integer weightPounds = 0;
    public Integer heightInches = 0;
    public Integer volumeCubicFeet = 0;

    public Integer stops = 1;

    // * Optional availability parameters *
    // public Date earliest;
    // public Date latest;

    // public Boolean alarm;
    public Boolean includeAsset = false;
}