package hello;

import java.rmi.RemoteException;

import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;
import com.tcore.tfmiFreightMatching.UpdateAssetRequestDocument;
import com.tcore.tfmiFreightMatching.UpdateAssetResponseDocument;
import com.tcore.tfmiFreightMatching.UpdateAssetResult;
import com.transcore.connexion.sample.BaseSampleClient;

import hello.models.AssetUpdateModel;

public class AssetUpdate extends BaseSampleClient {

    private AssetUpdateModel props;

    public AssetUpdate(final AssetUpdateModel props) {
        this.props = props;
    }

    public String process() throws RemoteException {
        final UpdateAssetRequestDocument updateAssetRequestDoc = UpdateAssetRequestDocument.Factory.newInstance();
        props.fill(updateAssetRequestDoc.addNewUpdateAssetRequest().addNewUpdateAssetOperation());

        // Validate the request document before executing the operation
        validate(updateAssetRequestDoc);

        // Update
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
