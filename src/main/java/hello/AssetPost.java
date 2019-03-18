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

    final private AssetPostModel props;

    private static final Set<String> PLACE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "place", "area", "open" }));
    private static final Set<String> RATE_BASE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "Flat", "PerMile" }));

    public AssetPost(final AssetPostModel props) throws RemoteException {
        this.props = props;
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    public String process() throws RemoteException {
        final PostAssetRequestDocument postRequestDoc = PostAssetRequestDocument.Factory.newInstance();
        final PostAssetOperation operation = postRequestDoc.addNewPostAssetRequest().addNewPostAssetOperations();

        if (props.assetType.equals("shipment")) {
            final Shipment shipment = operation.addNewShipment();
            this.builShipment(shipment);
        } else if (props.assetType.equals("equipment")) {
            final Equipment equipment = operation.addNewEquipment();
            this.builEquipment(equipment);
        } else
            throw new RemoteException("Asset Type: \"" + props.assetType + "\" is not valid. Request Failed.");

        // Optional information

        if (!props.postersReferenceId.isEmpty()) {
            operation.setPostersReferenceId(props.postersReferenceId);
        }

        operation.setLtl(props.ltl);

        for (String comment : props.comments)
            operation.addComments(comment);

        operation.setCount(props.count);

        if (!props.lengthFeet.equals(0) || !props.weightPounds.equals(0) || !props.heightInches.equals(0)
                || !props.volumeCubicFeet.equals(0)) {
            final Dimensions d = operation.addNewDimensions();

            if (!props.lengthFeet.equals(0))
                d.setLengthFeet(props.lengthFeet);
            if (!props.weightPounds.equals(0))
                d.setWeightPounds(props.weightPounds);
            if (!props.heightInches.equals(0))
                d.setHeightInches(props.heightInches);
            if (!props.volumeCubicFeet.equals(0))
                d.setVolumeCubicFeet(props.volumeCubicFeet);
        }

        operation.setStops(props.stops);

        // availability

        // alarm

        operation.setIncludeAsset(props.includeAsset);

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

        System.out.println(postRequestDoc.toString());

        return result.toString();
    }

    public void builShipment(Shipment shipment) throws RemoteException {
        shipment.setEquipmentType(EquipmentType.Enum.forString(props.equipmentType));

        if (!props.originType.equals("place") || !props.destinationType.equals("place")) {
            throw new RemoteException("Origin and Destination must be of Place type. Request Failed.");
        }

        Place originPlace = shipment.addNewOrigin();
        this.buildPlace(originPlace, props.secondaryOriginType, props.originCountry, props.originCode, props.originCity,
                props.originStateProvince, props.originCounty, props.originLatitude, props.originLongitude);

        Place destinationPlace = shipment.addNewDestination();
        this.buildPlace(destinationPlace, props.secondaryDestinationType, props.destinationCountry,
                props.destinationCode, props.destinationCity, props.destinationStateProvince, props.destinationCounty,
                props.destinationLatitude, props.destinationLongitude);

        if (!props.baseRateDollars.equals(-1.0f) || RATE_BASE_TYPES.contains(props.rateBasedOn)) {
            ShipmentRate rate = shipment.addNewRate();

            rate.setBaseRateDollars(props.baseRateDollars);
            rate.setRateBasedOn(RateBasedOnType.Enum.forString(props.rateBasedOn));

            if (!props.rateMiles.equals(-1)) {
                rate.setRateMiles(props.rateMiles);
            }
        }
    }

    public void builEquipment(Equipment equipment) throws RemoteException {
        equipment.setEquipmentType(EquipmentType.Enum.forString(props.equipmentType));

        if (!props.originType.equals("place") || !PLACE_TYPES.contains(props.destinationType)) {
            throw new RemoteException("Origin and Destination must be given. Request Failed.");
        }

        Place originPlace = equipment.addNewOrigin();
        this.buildPlace(originPlace, props.secondaryOriginType, props.originCountry, props.originCode, props.originCity,
                props.originStateProvince, props.originCounty, props.originLatitude, props.originLongitude);

        EquipmentDestination destination = equipment.addNewDestination();

        switch (props.destinationType) {

        case "place":
            Place destinationPlace = destination.addNewPlace();
            this.buildPlace(destinationPlace, props.secondaryDestinationType, props.destinationCountry,
                    props.destinationCode, props.destinationCity, props.destinationStateProvince,
                    props.destinationCounty, props.destinationLatitude, props.destinationLongitude);
            break;

        case "area":
            Area destinationArea = destination.addNewArea();
            for (String province : props.destAreaStateProvinces)
                destinationArea.addStateProvinces(StateProvince.Enum.forString(province));
            for (String zone : props.destAreaZones)
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
