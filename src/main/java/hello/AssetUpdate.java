package hello;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import com.tcore.tfmiFreightMatching.Dimensions;
import com.tcore.tfmiFreightMatching.RateBasedOnType;
import com.tcore.tfmiFreightMatching.ShipmentRate;
import com.tcore.tfmiFreightMatching.ShipmentUpdate;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;
import com.tcore.tfmiFreightMatching.UpdateAssetOperation;
import com.tcore.tfmiFreightMatching.UpdateAssetRequestDocument;
import com.tcore.tfmiFreightMatching.UpdateAssetResponseDocument;
import com.tcore.tfmiFreightMatching.UpdateAssetResult;
import com.transcore.connexion.sample.BaseSampleClient;

public class AssetUpdate extends BaseSampleClient {

    private AssetUpdateModel props;

    private static final Set<String> RATE_BASE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "Flat", "PerMile" }));

    public AssetUpdate(final AssetUpdateModel props) {
        this.props = props;
    }

    public String process() throws RemoteException {
        final UpdateAssetRequestDocument updateAssetRequestDoc = UpdateAssetRequestDocument.Factory.newInstance();
        final UpdateAssetOperation operation = updateAssetRequestDoc.addNewUpdateAssetRequest()
                .addNewUpdateAssetOperation();

        if (props.assetId != null)
            operation.setAssetId(props.assetId);
        else if (props.postersReferenceId != null)
            operation.setPostersReferenceId(props.postersReferenceId);
        else
            throw new RemoteException("Please specify an Id.");

        switch (props.updateType) {
        case "shipmentUpdate":
            final ShipmentUpdate shipment = operation.addNewShipmentUpdate();

            if (props.ltl != null)
                shipment.setLtl(props.ltl.booleanValue());

            for (String comment : props.comments)
                shipment.addComments(comment);

            if (!props.count.equals(-1))
                shipment.setCount(props.count.intValue());

            if (props.stops != null)
                shipment.setStops(props.stops.intValue());

            if (!props.lengthFeet.equals(0) || !props.weightPounds.equals(0) || !props.heightInches.equals(0)
                    || !props.volumeCubicFeet.equals(0)) {

                final Dimensions d = shipment.addNewDimensions();

                if (!props.lengthFeet.equals(0))
                    d.setLengthFeet(props.lengthFeet.intValue());
                if (!props.weightPounds.equals(0))
                    d.setWeightPounds(props.weightPounds.intValue());
                if (!props.heightInches.equals(0))
                    d.setHeightInches(props.heightInches.intValue());
                if (!props.volumeCubicFeet.equals(0))
                    d.setVolumeCubicFeet(props.volumeCubicFeet.intValue());
            }

            if (!props.baseRateDollars.equals(-1.0f) || RATE_BASE_TYPES.contains(props.rateBasedOn)) {
                ShipmentRate rate = shipment.addNewRate();

                rate.setBaseRateDollars(props.baseRateDollars.floatValue());
                rate.setRateBasedOn(RateBasedOnType.Enum.forString(props.rateBasedOn));

                if (!props.rateMiles.equals(-1)) {
                    rate.setRateMiles(props.rateMiles.intValue());
                }
            }

            break;

        // equipment is not yet implemented on the sdk
        // case "equipmentUpdate":
        // break;

        default:
            throw new RemoteException("Not a valid update type.");
        }

        // Validate the request document before executing the operation
        validate(updateAssetRequestDoc);

        // Post
        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final UpdateAssetResponseDocument responseDoc = stub.updateAsset(updateAssetRequestDoc, null, null,
                sessionHeaderDocument(Login.sessionToken));

        final UpdateAssetResult result = responseDoc.getUpdateAssetResponse().getUpdateAssetResult();

        if (!result.isSetUpdateAssetSuccessData())
            throw new RemoteException("Update Asset Request Failed: " + result.getServiceError().getMessage() + " : "
                    + result.getServiceError().getDetailedMessage());

        System.out.println(updateAssetRequestDoc.toString());

        return result.toString();
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }
}
