package hello;

import java.rmi.RemoteException;
import java.util.Optional;

import com.transcore.connexion.sample.BaseSampleClient;
import com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId;
import com.tcore.tfmiFreightMatching.DeleteAssetOperation;
import com.tcore.tfmiFreightMatching.DeleteAssetRequestDocument;
import com.tcore.tfmiFreightMatching.DeleteAssetResponseDocument;
import com.tcore.tfmiFreightMatching.DeleteAssetResult;
import com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;

public class AssetDelete extends BaseSampleClient {
    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }

    final String type;
    final Optional<String[]> ids;

    public AssetDelete(final String type, final Optional<String[]> ids) {
        this.type = type;
        this.ids = ids;
    }

    public String process() throws RemoteException {
        final DeleteAssetRequestDocument deleteRequestDoc = DeleteAssetRequestDocument.Factory.newInstance();
        final DeleteAssetOperation operation = deleteRequestDoc.addNewDeleteAssetRequest().addNewDeleteAssetOperation();

        if (this.type.equals("deleteAssetsByAssetIds")) {
            DeleteAssetsByAssetIds deleteByAssetIds = operation.addNewDeleteAssetsByAssetIds();
            for (String id : this.ids.get())
                deleteByAssetIds.addAssetIds(id);
        } else if (this.type.equals("deleteAssetByPostersReferenceId")) {
            DeleteAssetByPostersReferenceId deleteByReferenceId = operation.addNewDeleteAssetByPostersReferenceId();
            deleteByReferenceId.setPostersReferenceId(this.ids.get()[0]);
        } else if (this.type.equals("deleteAllMyAssets")) {
            operation.addNewDeleteAllMyAssets();
        } else if (this.type.equals("deleteAllMyGroupsAssets")) {
            operation.addNewDeleteAllMyGroupsAssets();
        } else {
            throw new RemoteException("Delete Request Failed: " + this.type + " : " + "Not a valid deletion type");
        }

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

        return result.toString();
    }
}
