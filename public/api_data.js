define({ "api": [
  {
    "type": "delete",
    "url": "/asset",
    "title": "delete assets by criteria.",
    "version": "1.1.0",
    "name": "DeleteAsset",
    "group": "Assets",
    "filename": "./src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Deletes one or more posted assets.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "AssetId[]",
            "optional": true,
            "field": "deleteAssetsByAssetIds",
            "description": "<p>Delete one or more assets by AssetId.</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "0..8",
            "optional": true,
            "field": "deleteAssetByPostersReferenceId",
            "description": "<p>Delete an asset by PostersReferenceId.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "deleteAllMyAssets",
            "description": "<p>Delete all assets owned by the requester.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "deleteAllMyGroupsAssets",
            "description": "<p>Delete all assets belonging by the requester or to other member’s of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetId",
          "content": "\n[ \"TS0PdTDs\", \"TS0PdTDt\"]",
          "type": "json"
        },
        {
          "title": "PostersReferenceId",
          "content": "\n\"customId\"",
          "type": "json"
        },
        {
          "title": "deleteAllMyAssets",
          "content": "\ntrue",
          "type": "json"
        },
        {
          "title": "deleteAllMyGroupsAssets",
          "content": "\nfalse",
          "type": "json"
        }
      ]
    },
    "examples": [
      {
        "title": "Asset ids",
        "content": "\n{ \"deleteAssetsByAssetIds\": [ \"TS0PdTDs\", \"TS0PdTDt\"] }",
        "type": "json"
      },
      {
        "title": "Posters reference id",
        "content": "\n{ \"deleteAssetByPostersReferenceId\": \"customId\" }",
        "type": "json"
      },
      {
        "title": "My assets",
        "content": "\n{ \"deleteAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "My groups assets",
        "content": "\n{ \"deleteAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "delete",
    "url": "/asset",
    "title": "delete assets by criteria.",
    "version": "1.0.0",
    "name": "DeleteAsset",
    "group": "Assets",
    "filename": "./src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Deletes one or more posted assets.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "deleteAssetsByAssetIds",
            "description": "<p>Delete one or more assets by AssetId.</p>"
          },
          {
            "group": "Parameter",
            "type": "AssetId[]",
            "optional": false,
            "field": "deleteAssetsByAssetIds.ids",
            "description": "<p>Asset ids to delete.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "deleteAssetByPostersReferenceId",
            "description": "<p>Delete an asset by PostersReferenceId.</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "0..8",
            "optional": false,
            "field": "deleteAssetByPostersReferenceId.id",
            "description": "<p>poster id.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "deleteAllMyAssets",
            "description": "<p>Delete all assets owned by the requester.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "deleteAllMyGroupsAssets",
            "description": "<p>Delete all assets belonging by the requester or to other member’s of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetId",
          "content": "\n{ \"ids\": [ \"TS0PdTDs\", \"TS0PdTDt\"] }",
          "type": "json"
        },
        {
          "title": "PostersReferenceId",
          "content": "\n{ \"id\": \"customId\" }",
          "type": "json"
        },
        {
          "title": "deleteAllMyAssets",
          "content": "\ntrue",
          "type": "json"
        },
        {
          "title": "deleteAllMyGroupsAssets",
          "content": "\nfalse",
          "type": "json"
        }
      ]
    },
    "examples": [
      {
        "title": "Asset ids",
        "content": "\n{ \"deleteAssetsByAssetIds\": { \"ids\": [\"TS0PdTDs\", \"TS0PdTDt\"] } }",
        "type": "json"
      },
      {
        "title": "Posters reference id",
        "content": "\n{ \"deleteAssetByPostersReferenceId\": { \"id\": \"customId\" } }",
        "type": "json"
      },
      {
        "title": "My assets",
        "content": "\n{ \"deleteAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "My groups assets",
        "content": "\n{ \"deleteAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset",
    "title": "Get assets by criteria.",
    "version": "1.1.0",
    "name": "GetAsset",
    "group": "Assets",
    "filename": "./src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Query asset by specific criteria, you chan choose just one of the specified as parameters. The first criteria to match will be used.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "AssetId[]",
            "optional": true,
            "field": "queryAssetsByAssetIds",
            "description": "<p>Query one or more assets by their AssetId.</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "0..8",
            "optional": true,
            "field": "queryAssetByPostersReferenceId",
            "description": "<p>Query one or more assets by a Poster reference id associated with it.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "queryAllMyAssets",
            "description": "<p>Lookup all assets belonging to the requester.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "queryAllMyGroupsAssets",
            "description": "<p>Lookup all assets belonging to the requester or to other members of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetId",
          "content": "\n[ \"TS0PdTDs\", \"TS0PdTDt\"]",
          "type": "json"
        },
        {
          "title": "PostersReferenceId",
          "content": "\n\"customId\"",
          "type": "json"
        },
        {
          "title": "queryAllMyAssets",
          "content": "\ntrue",
          "type": "json"
        },
        {
          "title": "queryAllMyGroupsAssets",
          "content": "\nfalse",
          "type": "json"
        }
      ]
    },
    "examples": [
      {
        "title": "Asset ids",
        "content": "\n{ \"queryAssetsByAssetIds\": [ \"TS0PdTDs\", \"TS0PdTDt\"] }",
        "type": "json"
      },
      {
        "title": "Posters reference id",
        "content": "\n{ \"queryAssetByPostersReferenceId\": \"customId\" }",
        "type": "json"
      },
      {
        "title": "My assets",
        "content": "\n{ \"queryAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "My groups assets",
        "content": "\n{ \"queryAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset",
    "title": "Get assets by criteria.",
    "version": "1.0.0",
    "name": "GetAsset",
    "group": "Assets",
    "filename": "./src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Query asset by specific criteria, you chan choose just one of the specified as parameters. The first criteria to match will be used.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "queryAssetsByAssetIds",
            "description": "<p>Query one or more assets by their AssetId.</p>"
          },
          {
            "group": "Parameter",
            "type": "AssetId[]",
            "optional": false,
            "field": "queryAssetsByAssetIds.ids",
            "description": "<p>Asset ids to search for.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "queryAssetByPostersReferenceId",
            "description": "<p>Query one or more assets by a Poster reference id associated with it.</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "0..8",
            "optional": false,
            "field": "queryAssetByPostersReferenceId.id",
            "description": "<p>poster id.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "queryAllMyAssets",
            "description": "<p>Lookup all assets belonging to the requester.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "queryAllMyGroupsAssets",
            "description": "<p>Lookup all assets belonging to the requester or to other members of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetId",
          "content": "\n{ \"ids\": [ \"TS0PdTDs\", \"TS0PdTDt\"] }",
          "type": "json"
        },
        {
          "title": "PostersReferenceId",
          "content": "\n{ \"id\": \"customId\" }",
          "type": "json"
        },
        {
          "title": "queryAllMyAssets",
          "content": "\ntrue",
          "type": "json"
        },
        {
          "title": "queryAllMyGroupsAssets",
          "content": "\nfalse",
          "type": "json"
        }
      ]
    },
    "examples": [
      {
        "title": "Asset ids",
        "content": "\n{ \"queryAssetsByAssetIds\": { \"ids\": [ \"TS0PdTDs\", \"TS0PdTDt\"] } }",
        "type": "json"
      },
      {
        "title": "Posters reference id",
        "content": "\n{ \"queryAssetByPostersReferenceId\": { \"id\": \"customId\" } }",
        "type": "json"
      },
      {
        "title": "My assets",
        "content": "\n{ \"queryAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "My groups assets",
        "content": "\n{ \"queryAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  }
] });
