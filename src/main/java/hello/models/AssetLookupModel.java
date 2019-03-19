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

    public LookupAssetsByAssetIdsModel LookupAssetsByAssetIds = null;
    public LookupAssetByPostersReferenceIdModel LookupAssetByPostersReferenceId = null;

    public Boolean LookupAllMyAssets = null;
    public Boolean LookupAllMyGroupsAssets = null;

    @Override
    public com.tcore.tfmiFreightMatching.LookupAssetOperation fill(
            com.tcore.tfmiFreightMatching.LookupAssetOperation instance) throws java.rmi.RemoteException {

        if (LookupAssetsByAssetIds != null)
            LookupAssetsByAssetIds.fill(instance.addNewQueryAssetsByAssetIds());
        else if (LookupAssetByPostersReferenceId != null)
            LookupAssetByPostersReferenceId.fill(instance.addNewQueryAssetByPostersReferenceId());
        else if (LookupAllMyAssets != null)
            instance.addNewQueryAllMyAssets();
        else if (LookupAllMyGroupsAssets != null)
            instance.addNewQueryAllMyGroupsAssets();

        return instance;
    }

}
