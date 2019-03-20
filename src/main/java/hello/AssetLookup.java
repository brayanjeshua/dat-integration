package hello;

import java.rmi.RemoteException;

import com.transcore.connexion.sample.BaseSampleClient;

import hello.models.AssetLookupModel;

import com.tcore.tfmiFreightMatching.LookupAssetRequestDocument;
import com.tcore.tfmiFreightMatching.LookupAssetResponseDocument;
import com.tcore.tfmiFreightMatching.LookupAssetResult;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

public class AssetLookup extends BaseSampleClient {
    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    private AssetLookupModel props;

    public AssetLookup(final AssetLookupModel props) {
        this.props = props;
    }

    public String process() throws RemoteException {
        final LookupAssetRequestDocument LookupRequestDoc = LookupAssetRequestDocument.Factory.newInstance();
        props.fill(LookupRequestDoc.addNewLookupAssetRequest().addNewLookupAssetOperation());

        // Validate the request document before executing the operation
        validate(LookupRequestDoc);

        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final LookupAssetResponseDocument responseDoc = stub.lookupAsset(LookupRequestDoc, null, null,
                sessionHeaderDocument(Login.sessionToken));

        final LookupAssetResult result = responseDoc.getLookupAssetResponse().getLookupAssetResult();

        if (!result.isSetLookupAssetSuccessData()) {
            throw new RemoteException("Lookup Request Failed: " + result.getServiceError().getMessage() + " : "
                    + result.getServiceError().getDetailedMessage());
        }

        return result.toString();
    }
}
