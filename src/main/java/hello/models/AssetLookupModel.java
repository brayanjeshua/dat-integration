package hello.models;

class LookupAssetsByAssetIdsModel extends AbstractModel<com.tcore.tfmiFreightMatching.QueryAssetsByAssetIds> {

    public String[] ids;

    @Override
    public com.tcore.tfmiFreightMatching.QueryAssetsByAssetIds fill(
            com.tcore.tfmiFreightMatching.QueryAssetsByAssetIds instance) throws java.rmi.RemoteException {

        for (String id : ids)
            instance.addAssetIds(id);

        return instance;
    }
}

class LookupAssetByPostersReferenceIdModel
        extends AbstractModel<com.tcore.tfmiFreightMatching.QueryAssetByPostersReferenceId> {

    public String id;

    @Override
    public com.tcore.tfmiFreightMatching.QueryAssetByPostersReferenceId fill(
            com.tcore.tfmiFreightMatching.QueryAssetByPostersReferenceId instance) throws java.rmi.RemoteException {

        instance.setPostersReferenceId(id);

        return instance;
    }
}

public class AssetLookupModel extends AbstractModel<com.tcore.tfmiFreightMatching.LookupAssetOperation> {

    public LookupAssetsByAssetIdsModel lookupAssetsByAssetIds = null;
    public LookupAssetByPostersReferenceIdModel lookupAssetByPostersReferenceId = null;

    public Boolean lookupAllMyAssets = null;
    public Boolean lookupAllMyGroupsAssets = null;

    @Override
    public com.tcore.tfmiFreightMatching.LookupAssetOperation fill(
            com.tcore.tfmiFreightMatching.LookupAssetOperation instance) throws java.rmi.RemoteException {

        if (lookupAssetsByAssetIds != null)
            lookupAssetsByAssetIds.fill(instance.addNewQueryAssetsByAssetIds());
        else if (lookupAssetByPostersReferenceId != null)
            lookupAssetByPostersReferenceId.fill(instance.addNewQueryAssetByPostersReferenceId());
        else if (lookupAllMyAssets != null)
            instance.addNewQueryAllMyAssets();
        else if (lookupAllMyGroupsAssets != null)
            instance.addNewQueryAllMyGroupsAssets();

        return instance;
    }

}
