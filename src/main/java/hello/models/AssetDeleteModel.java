package hello.models;

class DeleteAssetsByAssetIdsModel extends AbstractModel<com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds> {

    public String[] ids;

    public com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds fill(
            com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds instance) throws java.rmi.RemoteException {

        for (String id : ids)
            instance.addAssetIds(id);

        return instance;
    }
}

class DeleteAssetByPostersReferenceIdModel
        extends AbstractModel<com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId> {

    public String id;

    public com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId fill(
            com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId instance) throws java.rmi.RemoteException {

        instance.setPostersReferenceId(id);

        return instance;
    }
}

public class AssetDeleteModel extends AbstractModel<com.tcore.tfmiFreightMatching.DeleteAssetOperation> {

    public DeleteAssetsByAssetIdsModel deleteAssetsByAssetIds = null;
    public DeleteAssetByPostersReferenceIdModel deleteAssetByPostersReferenceId = null;

    public Boolean deleteAllMyAssets = null;
    public Boolean deleteAllMyGroupsAssets = null;

    public com.tcore.tfmiFreightMatching.DeleteAssetOperation fill(
            com.tcore.tfmiFreightMatching.DeleteAssetOperation instance) throws java.rmi.RemoteException {

        if (deleteAssetsByAssetIds != null)
            deleteAssetsByAssetIds.fill(instance.addNewDeleteAssetsByAssetIds());
        else if (deleteAssetByPostersReferenceId != null)
            deleteAssetByPostersReferenceId.fill(instance.addNewDeleteAssetByPostersReferenceId());
        else if (deleteAllMyAssets != null)
            instance.addNewDeleteAllMyAssets();
        else if (deleteAllMyGroupsAssets != null)
            instance.addNewDeleteAllMyGroupsAssets();

        return instance;
    }

}
