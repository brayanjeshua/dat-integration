package hello;

import java.rmi.RemoteException;

import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.BaseSampleClient;
import com.tcore.tcoreTypes.StateProvince;
import com.tcore.tfmiFreightMatching.CityAndState;
import com.tcore.tfmiFreightMatching.Dimensions;
import com.tcore.tfmiFreightMatching.NamedLatLon;
import com.tcore.tfmiFreightMatching.PostAssetOperation;
import com.tcore.tfmiFreightMatching.PostAssetRequestDocument;
import com.tcore.tfmiFreightMatching.PostAssetResponseDocument;
import com.tcore.tfmiFreightMatching.PostAssetResult;
import com.tcore.tfmiFreightMatching.Shipment;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

public class AssetShipment extends BaseSampleClient {
    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    final PostAssetResult postShipmentResult;

    public String getContent() {
        System.out.println(this.postShipmentResult);

        return this.postShipmentResult.toString();
    }

    public AssetShipment(final SessionToken sessionToken, final EquipmentType.Enum equipmentType,
            final Integer lengthFeet, final Integer weightPounds, final String originCity,
            final StateProvince.Enum originState, final String destinationCity,
            final StateProvince.Enum destinationState, final float destinationLatitude,
            final float destinationLongitude) throws RemoteException {

        final PostAssetRequestDocument postRequestDoc = PostAssetRequestDocument.Factory.newInstance();
        final PostAssetOperation operation = postRequestDoc.addNewPostAssetRequest().addNewPostAssetOperations();

        final Shipment shipment = operation.addNewShipment();
        shipment.setEquipmentType(equipmentType);

        final CityAndState origin = shipment.addNewOrigin().addNewCityAndState();
        origin.setCity(originCity);
        origin.setStateProvince(originState);

        // Destination - by city/state/lat/lon
        final NamedLatLon destination = shipment.addNewDestination().addNewNamedCoordinates();
        destination.setCity(destinationCity);
        destination.setStateProvince(destinationState);
        destination.setLatitude(destinationLatitude);
        destination.setLongitude(destinationLongitude);

        // Length and weight
        final Dimensions d = operation.addNewDimensions();
        d.setLengthFeet(lengthFeet);
        d.setWeightPounds(weightPounds);

        // Validate the request document before executing the operation
        validate(postRequestDoc);

        // Post
        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final PostAssetResponseDocument responseDoc = stub.postAsset(postRequestDoc, null, null,
                sessionHeaderDocument(sessionToken));
        final PostAssetResult result = responseDoc.getPostAssetResponse().getPostAssetResultsArray(0);

        // Check for errors (note - some more severe errors will result in an AxisFault
        // instead)
        if (!result.isSetPostAssetSuccessData()) {
            throw new RemoteException("Post AssetShipment Request Failed: " + result.getServiceError().getMessage()
                    + " : " + result.getServiceError().getDetailedMessage());
        }

        this.postShipmentResult = result;
    }
}
