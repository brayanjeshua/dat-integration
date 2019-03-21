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

    public LookupAssetsByAssetIdsModel queryAssetsByAssetIds = null;
    public LookupAssetByPostersReferenceIdModel queryAssetByPostersReferenceId = null;

    public Boolean queryAllMyAssets = null;
    public Boolean queryAllMyGroupsAssets = null;

    @Override
    public com.tcore.tfmiFreightMatching.LookupAssetOperation fill(
            com.tcore.tfmiFreightMatching.LookupAssetOperation instance) throws java.rmi.RemoteException {

        if (queryAssetsByAssetIds != null)
            queryAssetsByAssetIds.fill(instance.addNewQueryAssetsByAssetIds());
        else if (queryAssetByPostersReferenceId != null)
            queryAssetByPostersReferenceId.fill(instance.addNewQueryAssetByPostersReferenceId());
        else if (queryAllMyAssets != null)
            instance.addNewQueryAllMyAssets();
        else if (queryAllMyGroupsAssets != null)
            instance.addNewQueryAllMyGroupsAssets();

        return instance;
    }

}
