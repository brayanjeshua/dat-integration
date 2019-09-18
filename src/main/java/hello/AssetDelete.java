package hello;

import java.rmi.RemoteException;

import com.transcore.connexion.sample.BaseSampleClient;

import hello.models.AssetDeleteModel;

import com.tcore.tfmiFreightMatching.DeleteAssetRequestDocument;
import com.tcore.tfmiFreightMatching.DeleteAssetResponseDocument;
import com.tcore.tfmiFreightMatching.DeleteAssetResult;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

public class AssetDelete extends BaseSampleClient {
    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    private AssetDeleteModel props;

    public AssetDelete(final AssetDeleteModel props) {
        this.props = props;
    }

    public String process() throws RemoteException {
        final DeleteAssetRequestDocument deleteRequestDoc = DeleteAssetRequestDocument.Factory.newInstance();
        props.fill(deleteRequestDoc.addNewDeleteAssetRequest().addNewDeleteAssetOperation());

        // Validate the request document before executing the operation
        validate(deleteRequestDoc);

        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final DeleteAssetResponseDocument responseDoc = stub.deleteAsset(deleteRequestDoc, null, null,
                sessionHeaderDocument(Login.sessionToken));

        final DeleteAssetResult result = responseDoc.getDeleteAssetResponse().getDeleteAssetResult();

        if (!result.isSetDeleteAssetSuccessData()) {
            throw new RemoteException("Delete Request Failed: " + result.getServiceError().getMessage() + " : "
                    + result.getServiceError().getDetailedMessage());
        }
	System.out.println("DELETE RESULT \n"+result.toString());
        return result.toString();
    }
}
