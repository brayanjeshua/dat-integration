package hello;

import java.rmi.RemoteException;
import com.transcore.connexion.sample.BaseSampleClient;

import com.tcore.tfmiFreightMatching.PostAssetRequestDocument;
import com.tcore.tfmiFreightMatching.PostAssetResponseDocument;
import com.tcore.tfmiFreightMatching.PostAssetResult;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

import hello.models.AssetPostModel;

public class AssetPost extends BaseSampleClient {

    final private AssetPostModel asset;

    public AssetPost(final AssetPostModel asset) throws RemoteException {
        this.asset = asset;
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    public String process() throws RemoteException {
        final PostAssetRequestDocument postRequestDoc = PostAssetRequestDocument.Factory.newInstance();

        asset.fill(postRequestDoc.addNewPostAssetRequest().addNewPostAssetOperations());

        // Validate the request document before executing the operation
        validate(postRequestDoc);

        System.out.println(postRequestDoc.toString());
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
}
