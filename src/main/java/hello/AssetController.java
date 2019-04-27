package hello;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hello.models.AssetDeleteModel;
import hello.models.AssetQueryModel;
import hello.models.AssetPostModel;
import hello.models.AssetUpdateModel;

class User {
    public String firstName;
    public String lastName;
}

@RestController
public class AssetController {

    /**
     * @api {post} /asset Create asset.
     * @apiVersion 1.0.0
     * @apiName CreateAsset
     * @apiGroup Assets
     * @apiUse AssetPostModel
     */
    @PostMapping(value = "/asset")
    public String postAsset(@RequestBody AssetPostModel props) throws RemoteException {
        return new AssetPost(props).process();
    }

    /**
     * @api {get} /asset Get assets by criteria.
     * @apiVersion 1.0.0
     * @apiName GetAsset
     * @apiGroup Assets
     * @apiUse AssetQueryModel
     */

    /**
     * @api {get} /asset Get assets by criteria.
     * @apiVersion 1.1.0
     * @apiName GetAsset
     * @apiGroup Assets
     * @apiUse AssetQueryModel
     */
    @GetMapping(value = "/asset")
    public String LookupById(@RequestBody AssetQueryModel props) throws RemoteException {
        return new AssetQuery(props).process();
    }

    @PatchMapping(value = "/asset")
    public String updateAsset(@RequestBody AssetUpdateModel props) throws RemoteException {

        return new AssetUpdate(props).process();
    }

    /**
     * @api {delete} /asset delete assets by criteria.
     * @apiVersion 1.0.0
     * @apiName DeleteAsset
     * @apiGroup Assets
     * @apiUse AssetDeleteModel
     */

    /**
     * @api {delete} /asset delete assets by criteria.
     * @apiVersion 1.1.0
     * @apiName DeleteAsset
     * @apiGroup Assets
     * @apiUse AssetDeleteModel
     */
    @DeleteMapping(value = "/asset")
    public String deleteAsset(@RequestBody AssetDeleteModel props) throws RemoteException {
        return new AssetDelete(props).process();
    }
}