package hello.models;

class DeleteAssetsByAssetIdsModel extends AbstractModel<com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds> {

    public String[] ids;

    @Override
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

    @Override
    public com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId fill(
            com.tcore.tfmiFreightMatching.DeleteAssetByPostersReferenceId instance) throws java.rmi.RemoteException {

        instance.setPostersReferenceId(id);

        return instance;
    }
}

/**
 * @apiDefine AssetDeleteModel
 * @apiVersion 1.0.0
 * @apiDescription Deletes one or more posted assets.
 * 
 * @apiParam {Object} [deleteAssetsByAssetIds] Delete one or more assets by
 *           AssetId.
 * @apiParam {AssetId[]} deleteAssetsByAssetIds.ids Asset ids to delete.
 * @apiParamExample {json} AssetId
 * 
 *                  { "ids": [ "TS0PdTDs", "TS0PdTDt"] }
 * 
 * @apiParam {Object} [deleteAssetByPostersReferenceId] Delete an asset by
 *           PostersReferenceId.
 * @apiParam {string{0..8}} deleteAssetByPostersReferenceId.id poster id.
 * @apiParamExample {json} PostersReferenceId
 * 
 *                  { "id": "customId" }
 * 
 * @apiParam {Boolean} [deleteAllMyAssets] Delete all assets owned by the
 *           requester.
 * @apiParamExample {json} deleteAllMyAssets
 * 
 *                  true
 * 
 * @apiParam {Boolean} [deleteAllMyGroupsAssets] Delete all assets belonging by
 *           the requester or to other member’s of the requester’s sharing
 *           group.
 * @apiParamExample {json} deleteAllMyGroupsAssets
 * 
 *                  false
 * 
 * @apiExample {json} Asset ids
 * 
 *             { "deleteAssetsByAssetIds": { "ids": ["TS0PdTDs", "TS0PdTDt"] } }
 * 
 * @apiExample {json} Posters reference id
 * 
 *             { "deleteAssetByPostersReferenceId": { "id": "customId" } }
 * 
 * @apiExample {json} My assets
 * 
 *             { "deleteAllMyAssets": true }
 * 
 * @apiExample {json} My groups assets
 * 
 *             { "deleteAllMyGroupsAssets": true }
 * 
 */

/**
 * @apiDefine AssetDeleteModel
 * @apiVersion 1.1.0
 * @apiDescription Deletes one or more posted assets.
 * 
 * @apiParam (ByAssetId) {AssetId[]} deleteAssetsByAssetIds Delete one or more
 *           assets by AssetId.
 * @apiParamExample {json} deleteAssetsByAssetIds
 * 
 *                  [ "TS0PdTDs", "TS0PdTDt"]
 * 
 * @apiParam (ByPostersId) {string{0..8}} deleteAssetByPostersReferenceId Delete
 *           an asset by PostersReferenceId.
 * @apiParamExample {json} deleteAssetByPostersReferenceId
 * 
 *                  "customId"
 * 
 * @apiParam (ByAllMyAssets) {Boolean} deleteAllMyAssets Delete all assets owned
 *           by the requester.
 * @apiParamExample {json} deleteAllMyAssets
 * 
 *                  true
 * 
 * @apiParam (ByAllMyGroupsAssets) {Boolean} deleteAllMyGroupsAssets Delete all
 *           assets belonging by the requester or to other member’s of the
 *           requester’s sharing group.
 * @apiParamExample {json} deleteAllMyGroupsAssets
 * 
 *                  false
 * 
 * @apiExample {json} ByAssetId
 * 
 *             body:
 * 
 *             { "deleteAssetsByAssetIds": [ "TS0PdTDs", "TS0PdTDt"] }
 * 
 * @apiExample {json} ByPostersId
 * 
 *             body:
 * 
 *             { "deleteAssetByPostersReferenceId": "customId" }
 * 
 * @apiExample {json} ByAllMyAssets
 * 
 *             body:
 * 
 *             { "deleteAllMyAssets": true }
 * 
 * @apiExample {json} ByAllMyGroupsAssets
 * 
 *             body:
 * 
 *             { "deleteAllMyGroupsAssets": true }
 */
public class AssetDeleteModel extends AbstractModel<com.tcore.tfmiFreightMatching.DeleteAssetOperation> {

    public String[] deleteAssetsByAssetIds = null;
    public String deleteAssetByPostersReferenceId = null;

    public Boolean deleteAllMyAssets = null;
    public Boolean deleteAllMyGroupsAssets = null;

    @Override
    public com.tcore.tfmiFreightMatching.DeleteAssetOperation fill(
            com.tcore.tfmiFreightMatching.DeleteAssetOperation instance) throws java.rmi.RemoteException {

        if (deleteAssetsByAssetIds != null) {
            com.tcore.tfmiFreightMatching.DeleteAssetsByAssetIds instanceAssetIds = instance
                    .addNewDeleteAssetsByAssetIds();

            for (String id : deleteAssetsByAssetIds)
                instanceAssetIds.addAssetIds(id);
        } else if (deleteAssetByPostersReferenceId != null)
            instance.addNewDeleteAssetByPostersReferenceId().setPostersReferenceId(deleteAssetByPostersReferenceId);
        else if (deleteAllMyAssets != null)
            instance.addNewDeleteAllMyAssets();
        else if (deleteAllMyGroupsAssets != null)
            instance.addNewDeleteAllMyGroupsAssets();

        return instance;
    }

}
