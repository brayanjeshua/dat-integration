package hello;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import com.tcore.tcoreTypes.Area;
import com.tcore.tcoreTypes.CountryCode;
import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.Open;
import com.transcore.connexion.sample.BaseSampleClient;
import com.tcore.tcoreTypes.StateProvince;
import com.tcore.tcoreTypes.Zone;
import com.tcore.tfmiFreightMatching.CityAndState;
import com.tcore.tfmiFreightMatching.Dimensions;
import com.tcore.tfmiFreightMatching.Equipment;
import com.tcore.tfmiFreightMatching.EquipmentDestination;
import com.tcore.tfmiFreightMatching.FmPostalCode;
import com.tcore.tfmiFreightMatching.LatLon;
import com.tcore.tfmiFreightMatching.NamedLatLon;
import com.tcore.tfmiFreightMatching.NamedPostalCode;
import com.tcore.tfmiFreightMatching.Place;
import com.tcore.tfmiFreightMatching.PostAssetOperation;
import com.tcore.tfmiFreightMatching.PostAssetRequestDocument;
import com.tcore.tfmiFreightMatching.PostAssetResponseDocument;
import com.tcore.tfmiFreightMatching.PostAssetResult;
import com.tcore.tfmiFreightMatching.RateBasedOnType;
import com.tcore.tfmiFreightMatching.Shipment;
import com.tcore.tfmiFreightMatching.ShipmentRate;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

public class AssetPost extends BaseSampleClient {

    private String assetType;

    private String equipmentType;

    private String originType; // [place,]
    private String secondaryOriginType;
    private String originCountry;
    private String originCode;
    private String originCity;
    private String originStateProvince;
    private String originCounty;
    private Float originLatitude;
    private Float originLongitude;

    private String destinationType; // [place,]

    private String secondaryDestinationType;
    private String destinationCountry;
    private String destinationCode;
    private String destinationCity;
    private String destinationStateProvince;
    private String destinationCounty;
    private Float destinationLatitude;
    private Float destinationLongitude;

    private Float baseRateDollars;
    private String rateBasedOn;
    private Integer rateMiles;

    private String[] destAreaStateProvinces;
    private String[] destAreaZones;

    //

    // ** Optional parameters **
    private String postersReferenceId;
    private Boolean ltl;
    private String[] comments;
    private Integer count;

    // * Optional dimensions parameters *
    private Integer lengthFeet;
    private Integer weightPounds;
    private Integer heightInches;
    private Integer volumeCubicFeet;

    private Integer stops;

    // * Optional availability parameters *
    // private Date earliest;
    // private Date latest;

    // private Boolean alarm;
    private Boolean includeAsset = false;

    private static final Set<String> PLACE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "place", "area", "open" }));
    private static final Set<String> RATE_BASE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "Flat", "PerMile" }));

    public AssetPost(final String assetType, final String equipmentType,

            final String originType, final String secondaryOriginType, final String originCountry,
            final String originCode, final String originCity, final String originStateProvince,
            final String originCounty, final Float originLatitude, final Float originLongitude,

            final String destinationType, final String secondaryDestinationType, final String destinationCountry,
            final String destinationCode, final String destinationCity, final String destinationStateProvince,
            final String destinationCounty, final Float destinationLatitude, final Float destinationLongitude,

            final Float baseRateDollars, final String rateBasedOn, final Integer rateMiles,

            final String[] destAreaStateProvinces, final String[] destAreaZones,

            // ** Optional parameters **
            final String postersReferenceId, final Boolean ltl, final String[] comments, final Integer count,

            // * Optional dimensions parameters *
            final Integer lengthFeet, final Integer weightPounds, final Integer heightInches,
            final Integer volumeCubicFeet,

            final Integer stops,

            // * Optional availability parameters *
            // final Date earliest,
            // final Date latest,

            // final Boolean alarm,
            final Boolean includeAsset

    ) throws RemoteException {
        this.assetType = assetType;
        this.equipmentType = equipmentType;

        this.originType = originType;
        this.secondaryOriginType = secondaryOriginType;
        this.originCountry = originCountry;
        this.originCode = originCode;
        this.originCity = originCity;
        this.originStateProvince = originStateProvince;
        this.originCounty = originCounty;
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;

        this.destinationType = destinationType;
        this.secondaryDestinationType = secondaryDestinationType;
        this.destinationCountry = destinationCountry;
        this.destinationCode = destinationCode;
        this.destinationCity = destinationCity;
        this.destinationStateProvince = destinationStateProvince;
        this.destinationCounty = destinationCounty;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;

        this.baseRateDollars = baseRateDollars;
        this.rateBasedOn = rateBasedOn;
        this.rateMiles = rateMiles;

        this.destAreaStateProvinces = destAreaStateProvinces;
        this.destAreaZones = destAreaZones;

        //

        // ** Optional parameters **
        this.postersReferenceId = postersReferenceId;
        this.ltl = ltl;
        this.comments = comments;
        this.count = count;

        // * Optional dimensions parameters *
        this.lengthFeet = lengthFeet;
        this.weightPounds = weightPounds;
        this.heightInches = heightInches;
        this.volumeCubicFeet = volumeCubicFeet;

        this.stops = stops;

        // * Optional availability parameters *
        // this.earliest = earliest;
        // this.latest = latest;

        // this.alarm = alarm;
        this.includeAsset = includeAsset;
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    public String process() throws RemoteException {
        final PostAssetRequestDocument postRequestDoc = PostAssetRequestDocument.Factory.newInstance();
        final PostAssetOperation operation = postRequestDoc.addNewPostAssetRequest().addNewPostAssetOperations();

        if (this.assetType.equals("shipment")) {
            final Shipment shipment = operation.addNewShipment();
            this.builShipment(shipment);
        } else if (this.assetType.equals("equipment")) {
            final Equipment equipment = operation.addNewEquipment();
            this.builEquipment(equipment);
        } else
            throw new RemoteException("Asset Type: \"" + this.assetType + "\" is not valid. Request Failed.");

        // Optional information

        if (!this.postersReferenceId.isEmpty()) {
            operation.setPostersReferenceId(this.postersReferenceId);
        }

        operation.setLtl(this.ltl);

        for (String comment : this.comments)
            operation.addComments(comment);

        operation.setCount(this.count);

        if (!this.lengthFeet.equals(0) || !this.weightPounds.equals(0) || !this.heightInches.equals(0)
                || !this.volumeCubicFeet.equals(0)) {
            final Dimensions d = operation.addNewDimensions();

            if (!this.lengthFeet.equals(0))
                d.setLengthFeet(this.lengthFeet);
            if (!this.weightPounds.equals(0))
                d.setWeightPounds(this.weightPounds);
            if (!this.heightInches.equals(0))
                d.setHeightInches(this.heightInches);
            if (!this.volumeCubicFeet.equals(0))
                d.setVolumeCubicFeet(this.volumeCubicFeet);
        }

        operation.setStops(this.stops);

        // availability

        // alarm

        operation.setIncludeAsset(this.includeAsset);

        // Validate the request document before executing the operation
        validate(postRequestDoc);

        // Post
        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final PostAssetResponseDocument responseDoc = stub.postAsset(postRequestDoc, null, null,
                sessionHeaderDocument(Login.sessionToken));
        final PostAssetResult result = responseDoc.getPostAssetResponse().getPostAssetResultsArray(0);

        // Check for errors (note - some more severe errors will result in an AxisFault
        // instead)
        if (!result.isSetPostAssetSuccessData()) {
            throw new RemoteException("Post AssetShipment Request Failed: " + result.getServiceError().getMessage()
                    + " : " + result.getServiceError().getDetailedMessage());
        }

        return result.toString();
    }

    public void builShipment(Shipment shipment) throws RemoteException {
        shipment.setEquipmentType(EquipmentType.Enum.forString(this.equipmentType));

        if (!this.originType.equals("place") || !this.destinationType.equals("place")) {
            throw new RemoteException("Origin and Destination must be of Place type. Request Failed.");
        }

        Place originPlace = shipment.addNewOrigin();
        this.buildPlace(originPlace, this.secondaryOriginType, this.originCountry, this.originCode, this.originCity,
                this.originStateProvince, this.originCounty, this.originLatitude, this.originLongitude);

        Place destinationPlace = shipment.addNewDestination();
        this.buildPlace(destinationPlace, this.secondaryDestinationType, this.destinationCountry, this.destinationCode,
                this.destinationCity, this.destinationStateProvince, this.destinationCounty, this.destinationLatitude,
                this.destinationLongitude);

        if (!this.baseRateDollars.equals(-1.0f) || RATE_BASE_TYPES.contains(this.rateBasedOn)) {
            ShipmentRate rate = shipment.addNewRate();

            rate.setBaseRateDollars(this.baseRateDollars);
            rate.setRateBasedOn(RateBasedOnType.Enum.forString(this.rateBasedOn));

            if (!this.rateMiles.equals(-1)) {
                rate.setRateMiles(this.rateMiles);
            }
        }
    }

    public void builEquipment(Equipment equipment) throws RemoteException {
        equipment.setEquipmentType(EquipmentType.Enum.forString(this.equipmentType));

        if (!this.originType.equals("place") || !PLACE_TYPES.contains(this.destinationType)) {
            throw new RemoteException("Origin and Destination must be given. Request Failed.");
        }

        Place originPlace = equipment.addNewOrigin();
        this.buildPlace(originPlace, this.secondaryOriginType, this.originCountry, this.originCode, this.originCity,
                this.originStateProvince, this.originCounty, this.originLatitude, this.originLongitude);

        EquipmentDestination destination = equipment.addNewDestination();

        switch (this.destinationType) {

        case "place":
            Place destinationPlace = destination.addNewPlace();
            this.buildPlace(destinationPlace, this.secondaryDestinationType, this.destinationCountry,
                    this.destinationCode, this.destinationCity, this.destinationStateProvince, this.destinationCounty,
                    this.destinationLatitude, this.destinationLongitude);
            break;

        case "area":
            Area destinationArea = destination.addNewArea();
            for (String province : this.destAreaStateProvinces)
                destinationArea.addStateProvinces(StateProvince.Enum.forString(province));
            for (String zone : this.destAreaZones)
                destinationArea.addZones(Zone.Enum.forString(zone));
            break;

        case "open":
            Open a = destination.addNewOpen(); // ??
            break;

        default:
            throw new RemoteException("Destination must be given.");
        }
    }

    public void buildPlace(final Place place, final String placeType, final String country, final String code,
            final String city, final String stateProvince, final String county, final Float latitude,
            final Float longitude) throws RemoteException {
        switch (placeType) {
        case "postalCode":
            final FmPostalCode pcOrigin = place.addNewPostalCode();
            pcOrigin.setCountry(CountryCode.Enum.forString(country));
            pcOrigin.setCode(code);
            break;
        case "cityAndState":
            final CityAndState csOrigin = place.addNewCityAndState();
            csOrigin.setCity(city);
            csOrigin.setStateProvince(StateProvince.Enum.forString(stateProvince));
            // if (!county.isEmpty())
            csOrigin.setCounty(county);
            break;
        case "namedPostalCode":
            final NamedPostalCode npcOrigin = place.addNewNamedPostalCode();
            npcOrigin.setCity(city);
            npcOrigin.setStateProvince(StateProvince.Enum.forString(stateProvince));
            // if (!county.isEmpty())
            npcOrigin.setCounty(county);
            final com.tcore.tcoreTypes.PostalCode npcOriginPC = npcOrigin.addNewPostalCode();
            npcOriginPC.setCountry(CountryCode.Enum.forString(country));
            npcOriginPC.setCode(code);
            break;
        case "coordinates":
            final LatLon crdOrigin = place.addNewCoordinates();
            crdOrigin.setLatitude(latitude);
            crdOrigin.setLongitude(longitude);
            break;
        case "namedCoordinates":
            final NamedLatLon ncrdOrigin = place.addNewNamedCoordinates();
            ncrdOrigin.setLatitude(latitude);
            ncrdOrigin.setLongitude(longitude);
            ncrdOrigin.setCity(city);
            ncrdOrigin.setStateProvince(StateProvince.Enum.forString(stateProvince));
            break;
        default:
            throw new RemoteException(
                    "Inner place type: " + placeType + ". Does not match any record. Request Failed.");
        }
    }
}
