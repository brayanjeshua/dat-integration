package hello.models;

/**
 * @apiDefine AssetQueryModel
 * @apiVersion 1.0.0
 * @apiDescription Query asset by specific criteria, you chan choose just one of
 *                 the specified as parameters. The first criteria to match will
 *                 be used.
 * 
 * @apiParam {Object} [queryAssetsByAssetIds] Query one or more assets by their
 *           AssetId.
 * @apiParam {AssetId[]} queryAssetsByAssetIds.ids Asset ids to search for.
 * @apiParamExample {json} AssetId
 * 
 *                  { "ids": [ "TS0PdTDs", "TS0PdTDt"] }
 * 
 * @apiParam {Object} [queryAssetByPostersReferenceId] Query one or more assets
 *           by a Poster reference id associated with it.
 * @apiParam {String{0..8}} queryAssetByPostersReferenceId.id poster id.
 * @apiParamExample {json} PostersReferenceId
 * 
 *                  { "id": "customId" }
 * 
 * @apiParam {Boolean} [queryAllMyAssets] Lookup all assets belonging to the
 *           requester.
 * @apiParamExample {json} queryAllMyAssets
 * 
 *                  true
 * 
 * @apiParam {Boolean} [queryAllMyGroupsAssets] Lookup all assets belonging to
 *           the requester or to other members of the requester’s sharing group.
 * @apiParamExample {json} queryAllMyGroupsAssets
 * 
 *                  false
 * 
 * @apiExample {json} Asset ids
 * 
 *             { "queryAssetsByAssetIds": { "ids": [ "TS0PdTDs", "TS0PdTDt"] } }
 * 
 * @apiExample {json} Posters reference id
 * 
 *             { "queryAssetByPostersReferenceId": { "id": "customId" } }
 * 
 * @apiExample {json} My assets
 * 
 *             { "queryAllMyAssets": true }
 * 
 * @apiExample {json} My groups assets
 * 
 *             { "queryAllMyGroupsAssets": true }
 * 
 */

/**
 * @apiDefine AssetQueryModel
 * @apiVersion 1.1.0
 * @apiDescription Lookup currently-booked assets.
 * 
 * @apiParam (ByAssetId) {AssetId[]} queryAssetsByAssetIds Lookup one or more
 *           assets by AssetId.
 * @apiParamExample {json} queryAssetsByAssetIds
 * 
 *                  [ "TS0PdTDs", "TS0PdTDt"]
 * 
 * @apiParam (ByPostersId) {String{0..8}} queryAssetByPostersReferenceId Lookup
 *           one or more assets by PostersReferenceId.
 * @apiParamExample {json} queryAssetByPostersReferenceId
 * 
 *                  "customId"
 * 
 * @apiParam (ByAllMyAssets) {Boolean} queryAllMyAssets Lookup all assets
 *           belonging to the requester.
 * @apiParamExample {json} queryAllMyAssets
 * 
 *                  true
 * 
 * @apiParam (ByAllMyGroupsAssets) {Boolean} queryAllMyGroupsAssets Lookup all
 *           assets belonging to the requester or to other members of the
 *           requester’s sharing group.
 * @apiParamExample {json} queryAllMyGroupsAssets
 * 
 *                  false
 * 
 * @apiExample {json} ByAssetId
 * 
 *             body:
 * 
 *             { "queryAssetsByAssetIds": [ "TS0PdTDs", "TS0PdTDt"] }
 * 
 * @apiExample {json} ByPostersId
 * 
 *             body:
 * 
 *             { "queryAssetByPostersReferenceId": "customId" }
 * 
 * @apiExample {json} ByAllMyAssets
 * 
 *             body:
 * 
 *             { "queryAllMyAssets": true }
 * 
 * @apiExample {json} ByAllMyGroupsAssets
 * 
 *             body:
 * 
 *             { "queryAllMyGroupsAssets": true }
 * 
 */
public class AssetQueryModel extends AbstractModel<com.tcore.tfmiFreightMatching.LookupAssetOperation> {

    public String[] queryAssetsByAssetIds = null;
    public String queryAssetByPostersReferenceId = null;

    public Boolean queryAllMyAssets = null;
    public Boolean queryAllMyGroupsAssets = null;

    @Override
    public com.tcore.tfmiFreightMatching.LookupAssetOperation fill(
            com.tcore.tfmiFreightMatching.LookupAssetOperation instance) throws java.rmi.RemoteException {

        if (queryAssetsByAssetIds != null) {
            com.tcore.tfmiFreightMatching.QueryAssetsByAssetIds instanceAssetIds = instance
                    .addNewQueryAssetsByAssetIds();
            for (String id : queryAssetsByAssetIds)
                instanceAssetIds.addAssetIds(id);
        } else if (queryAssetByPostersReferenceId != null)
            instance.addNewQueryAssetByPostersReferenceId().setPostersReferenceId(queryAssetByPostersReferenceId);
        else if (queryAllMyAssets != null)
            instance.addNewQueryAllMyAssets();
        else if (queryAllMyGroupsAssets != null)
            instance.addNewQueryAllMyGroupsAssets();

        return instance;
    }

}
