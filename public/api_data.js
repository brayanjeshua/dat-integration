define({ "api": [
  {
    "type": "post",
    "url": "/asset",
    "title": "Create",
    "version": "1.0.0",
    "name": "CreateAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Post an Asset.</p>",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectShipment\">Shipment</a>",
            "optional": false,
            "field": "shipment",
            "description": ""
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectEquipment\">Equipment</a>",
            "optional": false,
            "field": "equipment",
            "description": ""
          }
        ],
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-8",
            "optional": true,
            "field": "postersReferenceId",
            "description": "<p>Poster-supplied reference ID for this asset. Posters often use this to tag their assets with their own internal IDs.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "ltl",
            "defaultValue": "false",
            "description": "<p>“Less than truck load”. For shipments, if set true, then this shipment will not occupy a full truck. For equipment, if set true, then this equipment does not have enough available capacity to carry a full shipment. The element dimensions should be used to specify cargo/capacity size.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "size": "0-70",
            "optional": true,
            "field": "comments",
            "description": "<p>Optional comment(s) to be displayed for this asset on search results and the Truck Stop Network.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 99",
            "optional": true,
            "field": "count",
            "defaultValue": "1",
            "description": "<p>The number of assets represented by this description. This allows a requester to easily (and cheaply) post mul- tiple identical assets.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectDimensions\">Dimensions</a>",
            "optional": true,
            "field": "dimensions",
            "description": "<p>For shipments, this is the dimensions of the cargo. For equipment, this is the dimensions of the available cargo space.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-",
            "optional": true,
            "field": "stops",
            "defaultValue": "1",
            "description": "<p>Number of stops this asset must make.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectAvailability\">Availability</a>",
            "optional": true,
            "field": "availability",
            "description": "<p>For shipments, when available for pickup at its origi- nation point. For equipment, when available to pickup shipments.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "includeAsset",
            "defaultValue": "false",
            "description": "<p>If set true, then the response will return a copy of the asset (and the alarm if one was specified) as they were actually processed and posted by the Freight Matching Service. This saves the requester from doing a subse- quent LookupAsset request.</p>"
          }
        ]
      }
    }
  },
  {
    "type": "delete",
    "url": "/asset",
    "title": "Delete",
    "version": "1.1.0",
    "name": "DeleteAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Deletes one or more posted assets.</p>",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "String[]",
            "size": "0-8",
            "optional": false,
            "field": "deleteAssetsByAssetIds",
            "description": "<p>Delete one or more assets by AssetId.</p>"
          },
          {
            "group": "OneOf",
            "type": "String",
            "size": "0-8",
            "optional": false,
            "field": "deleteAssetByPostersReferenceId",
            "description": "<p>Delete an asset by PostersReferenceId.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "deleteAllMyAssets",
            "description": "<p>Delete all assets owned by the requester.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "deleteAllMyGroupsAssets",
            "description": "<p>Delete all assets belonging by the requester or to other member’s of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "deleteAssetsByAssetIds",
          "content": "\n[ \"TS0PdTDs\", \"TS0PdTDt\"]",
          "type": "json"
        },
        {
          "title": "deleteAssetByPostersReferenceId",
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
        "title": "ByAssetId",
        "content": "\nbody:\n\n{ deleteAssetsByAssetIds: [ \"TS0PdTDs\", \"TS0PdTDt\"] }",
        "type": "json"
      },
      {
        "title": "ByPostersId",
        "content": "\nbody:\n\n{ deleteAssetByPostersReferenceId: \"customId\" }",
        "type": "json"
      },
      {
        "title": "ByAllMyAssets",
        "content": "\nbody:\n\n{ deleteAllMyAssets: true }",
        "type": "json"
      },
      {
        "title": "ByAllMyGroupsAssets",
        "content": "\nbody:\n\n{ deleteAllMyGroupsAssets: true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "delete",
    "url": "/asset",
    "title": "Delete",
    "version": "1.0.0",
    "name": "DeleteAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
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
            "type": "String",
            "size": "0-8",
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
        "title": "ByAssetId",
        "content": "\n{ \"deleteAssetsByAssetIds\": { \"ids\": [\"TS0PdTDs\", \"TS0PdTDt\"] } }",
        "type": "json"
      },
      {
        "title": "ByPostersId",
        "content": "\n{ \"deleteAssetByPostersReferenceId\": { \"id\": \"customId\" } }",
        "type": "json"
      },
      {
        "title": "ByAllMyAssets",
        "content": "\n{ \"deleteAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "ByAllMyGroupsAssets",
        "content": "\n{ \"deleteAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset",
    "title": "Get",
    "version": "1.1.0",
    "name": "GetAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Lookup currently-booked assets.</p>",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "String[]",
            "size": "0-8",
            "optional": false,
            "field": "queryAssetsByAssetIds",
            "description": "<p>Lookup one or more assets by AssetId.</p>"
          },
          {
            "group": "OneOf",
            "type": "String",
            "size": "0-8",
            "optional": false,
            "field": "queryAssetByPostersReferenceId",
            "description": "<p>Lookup one or more assets by PostersReferenceId.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "queryAllMyAssets",
            "description": "<p>Lookup all assets belonging to the requester.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "queryAllMyGroupsAssets",
            "description": "<p>Lookup all assets belonging to the requester or to other members of the requester’s sharing group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "queryAssetsByAssetIds",
          "content": "\n[ \"TS0PdTDs\", \"TS0PdTDt\"]",
          "type": "json"
        },
        {
          "title": "queryAssetByPostersReferenceId",
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
        "title": "ByAssetId",
        "content": "\nbody:\n\n{ queryAssetsByAssetIds: [ \"TS0PdTDs\", \"TS0PdTDt\" ] }",
        "type": "json"
      },
      {
        "title": "ByPostersId",
        "content": "\nbody:\n\n{ queryAssetByPostersReferenceId: \"customId\" }",
        "type": "json"
      },
      {
        "title": "ByAllMyAssets",
        "content": "\nbody:\n\n{ queryAllMyAssets: true }",
        "type": "json"
      },
      {
        "title": "ByAllMyGroupsAssets",
        "content": "\nbody:\n\n{ queryAllMyGroupsAssets: true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset",
    "title": "Get",
    "version": "1.0.0",
    "name": "GetAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
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
            "type": "String",
            "size": "0-8",
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
        "title": "ByAssetId",
        "content": "\n{ \"queryAssetsByAssetIds\": { \"ids\": [ \"TS0PdTDs\", \"TS0PdTDt\"] } }",
        "type": "json"
      },
      {
        "title": "ByPostersId",
        "content": "\n{ \"queryAssetByPostersReferenceId\": { \"id\": \"customId\" } }",
        "type": "json"
      },
      {
        "title": "ByAllMyAssets",
        "content": "\n{ \"queryAllMyAssets\": true }",
        "type": "json"
      },
      {
        "title": "ByAllMyGroupsAssets",
        "content": "\n{ \"queryAllMyGroupsAssets\": true }",
        "type": "json"
      }
    ]
  },
  {
    "type": "patch",
    "url": "/asset",
    "title": "Update",
    "version": "1.0.0",
    "name": "UpdateAsset",
    "group": "Assets",
    "filename": "src/main/java/hello/AssetController.java",
    "groupTitle": "Assets",
    "description": "<p>Update an Asset.</p>",
    "parameter": {
      "fields": {
        "OneOfId": [
          {
            "group": "OneOfId",
            "type": "String",
            "size": "0-8",
            "optional": false,
            "field": "assetId",
            "description": "<p>The Asset ID of the asset to be updated.</p>"
          },
          {
            "group": "OneOfId",
            "type": "String",
            "size": "0-8",
            "optional": false,
            "field": "postersReferenceId",
            "description": "<p>The Reference ID of the asset to be updated.</p>"
          }
        ],
        "OneOf": [
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectShipmentupdate\">ShipmentUpdate</a>",
            "optional": false,
            "field": "shipmentUpdate",
            "description": "<p>Shipment-specific items which can be updated.</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectEquipmentupdate\">EquipmentUpdate</a>",
            "optional": false,
            "field": "equipmentUpdate",
            "description": "<p>There are currently no equipment-specific items which can be updated. This element is included for potential future expansion.</p>"
          }
        ]
      }
    }
  },
  {
    "type": "OBJECT",
    "url": "Area",
    "title": "Area",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>A geographic area, defined by a list of states or a region.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String[]",
            "allowedValues": [
              "\"AB\"",
              "\"AG\"",
              "\"AK\"",
              "\"AL\"",
              "\"AS\"",
              "\"AZ\"",
              "\"AR\"",
              "\"BC\"",
              "\"BJ\"",
              "\"BS\"",
              "\"CA\"",
              "\"CH\"",
              "\"CI\"",
              "\"CL\"",
              "\"CO\"",
              "\"CP\"",
              "\"CT\"",
              "\"CU\"",
              "\"DC\"",
              "\"DE\"",
              "\"DF\"",
              "\"DG\"",
              "\"EM\"",
              "\"FL\"",
              "\"GA\"",
              "\"GJ\"",
              "\"GR\"",
              "\"GU\"",
              "\"HG\"",
              "\"HI\"",
              "\"IA\"",
              "\"ID\"",
              "\"IL\"",
              "\"IN\"",
              "\"JA\"",
              "\"KS\"",
              "\"KY\"",
              "\"LA\"",
              "\"MA\"",
              "\"MB\"",
              "\"MD\"",
              "\"ME\"",
              "\"MH\"",
              "\"MI\"",
              "\"MN\"",
              "\"MO\"",
              "\"MR\"",
              "\"MS\"",
              "\"MT\"",
              "\"NA\"",
              "\"NE\"",
              "\"NL\"",
              "\"NV\"",
              "\"NB\"",
              "\"NH\"",
              "\"NJ\"",
              "\"NM\"",
              "\"NY\"",
              "\"NF\"",
              "\"NC\"",
              "\"ND\"",
              "\"NT\"",
              "\"NS\"",
              "\"NU\"",
              "\"OA\"",
              "\"OH\"",
              "\"OK\"",
              "\"ON\"",
              "\"OR\"",
              "\"PA\"",
              "\"PE\"",
              "\"PQ\"",
              "\"PR\"",
              "\"PU\"",
              "\"QA\"",
              "\"QR\"",
              "\"RI\"",
              "\"SC\"",
              "\"SD\"",
              "\"SI\"",
              "\"SK\"",
              "\"SL\"",
              "\"SO\"",
              "\"TA\"",
              "\"TL\"",
              "\"TM\"",
              "\"TN\"",
              "\"TX\"",
              "\"UT\"",
              "\"VA\"",
              "\"VI\"",
              "\"VL\"",
              "\"VT\"",
              "\"WA\"",
              "\"WV\"",
              "\"WI\"",
              "\"WY\"",
              "\"YC\"",
              "\"YT\"",
              "\"ZT\""
            ],
            "optional": true,
            "field": "stateProvinces",
            "description": "<p>A list of states and/or provinces in the desired region.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "allowedValues": [
              "\"New England\"",
              "\"North East\"",
              "\"Mid-Atlantic\"",
              "\"South East\"",
              "\"Mid-West\"",
              "\"North Central\"",
              "\"Central\"",
              "\"South\"",
              "\"Mountain\"",
              "\"West\"",
              "\"Canada\"",
              "\"Western Canada\"",
              "\"Eastern Canada\"",
              "\"Central Canada\"",
              "\"Mexico\"",
              "\"Northern Mexico\"",
              "\"Contiguous USA\""
            ],
            "optional": true,
            "field": "zones",
            "description": "<p>A list of one or more zones in the desired region.</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectArea"
  },
  {
    "type": "OBJECT",
    "url": "Availability",
    "title": "Availability",
    "group": "Custom_types",
    "description": "<p>For shipments, when available for pickup at its origi- nation point. For equipment, when available to pickup shipments. If omitted, will be automatically defaulted to now (ear- liest), and now+24 hours (latest).</p>",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "earliest",
            "description": "<p>For shipments, the earliest the shipment can be picked up. For equipment, the earliest the equipment is avail- able to pick up a shipment.</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": true,
            "field": "latest",
            "description": "<p>For shipments, the latest the shipment can be picked up. For equipment, the latest the equipment is available to pick up a shipment.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Date",
          "content": "\n2019-05-28T06:30:14.000Z",
          "type": "Date"
        }
      ]
    },
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectAvailability"
  },
  {
    "type": "OBJECT",
    "url": "CityAndState",
    "title": "CityAndState",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>City + state/province. If multiple cities of the same name exist in the state/province, it will use the optional county to disambiguate.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-30",
            "optional": false,
            "field": "city",
            "description": "<p>City name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-30",
            "optional": true,
            "field": "county",
            "description": "<p>County. If present, the county is used to disambiguate between multiple similarly named cities in the state or province.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"AB\"",
              "\"AG\"",
              "\"AK\"",
              "\"AL\"",
              "\"AS\"",
              "\"AZ\"",
              "\"AR\"",
              "\"BC\"",
              "\"BJ\"",
              "\"BS\"",
              "\"CA\"",
              "\"CH\"",
              "\"CI\"",
              "\"CL\"",
              "\"CO\"",
              "\"CP\"",
              "\"CT\"",
              "\"CU\"",
              "\"DC\"",
              "\"DE\"",
              "\"DF\"",
              "\"DG\"",
              "\"EM\"",
              "\"FL\"",
              "\"GA\"",
              "\"GJ\"",
              "\"GR\"",
              "\"GU\"",
              "\"HG\"",
              "\"HI\"",
              "\"IA\"",
              "\"ID\"",
              "\"IL\"",
              "\"IN\"",
              "\"JA\"",
              "\"KS\"",
              "\"KY\"",
              "\"LA\"",
              "\"MA\"",
              "\"MB\"",
              "\"MD\"",
              "\"ME\"",
              "\"MH\"",
              "\"MI\"",
              "\"MN\"",
              "\"MO\"",
              "\"MR\"",
              "\"MS\"",
              "\"MT\"",
              "\"NA\"",
              "\"NE\"",
              "\"NL\"",
              "\"NV\"",
              "\"NB\"",
              "\"NH\"",
              "\"NJ\"",
              "\"NM\"",
              "\"NY\"",
              "\"NF\"",
              "\"NC\"",
              "\"ND\"",
              "\"NT\"",
              "\"NS\"",
              "\"NU\"",
              "\"OA\"",
              "\"OH\"",
              "\"OK\"",
              "\"ON\"",
              "\"OR\"",
              "\"PA\"",
              "\"PE\"",
              "\"PQ\"",
              "\"PR\"",
              "\"PU\"",
              "\"QA\"",
              "\"QR\"",
              "\"RI\"",
              "\"SC\"",
              "\"SD\"",
              "\"SI\"",
              "\"SK\"",
              "\"SL\"",
              "\"SO\"",
              "\"TA\"",
              "\"TL\"",
              "\"TM\"",
              "\"TN\"",
              "\"TX\"",
              "\"UT\"",
              "\"VA\"",
              "\"VI\"",
              "\"VL\"",
              "\"VT\"",
              "\"WA\"",
              "\"WV\"",
              "\"WI\"",
              "\"WY\"",
              "\"YC\"",
              "\"YT\"",
              "\"ZT\""
            ],
            "optional": false,
            "field": "stateProvince",
            "description": "<p>State or province.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "City And State",
        "content": "\nbody:\n\n{ city: \"San Luis\", stateProvince: \"CO\" }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectCityandstate"
  },
  {
    "type": "OBJECT",
    "url": "Coordinates",
    "title": "Coordinates",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>Latitude/longitude coordinates. The Freight Matching Service will internally assign the closest known city to those coordinates. Note that this could result in a city being selected that is not in the same state/province as the coordinates.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "size": "13.00 - 86.00",
            "optional": false,
            "field": "latitude",
            "description": "<p>Latitude of the desired point.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "-177.00 – -52.00",
            "optional": false,
            "field": "longitude",
            "description": "<p>Longitude of the desired point.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "Coordinates",
        "content": "\nbody:\n\n{ latitude: 38.3, longitude: -97.5 }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectCoordinates"
  },
  {
    "type": "OBJECT",
    "url": "Dimensions",
    "title": "Dimensions",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>For shipments, this is the dimensions of the cargo. For equipment, this is the dimensions of the available cargo space.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 199",
            "optional": true,
            "field": "lengthFeet",
            "description": "<p>Length of the shipment or truck deck in feet.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 199999",
            "optional": true,
            "field": "weightPounds",
            "description": "<p>Weight of the shipment or truck carrying capacity in pounds.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 299",
            "optional": true,
            "field": "heightInches",
            "description": "<p>Height of the shipment or truck box in inches.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 9999",
            "optional": true,
            "field": "volumeCubicFeet",
            "description": "<p>Volume of the shipment or truck box in cubic feet.</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectDimensions"
  },
  {
    "type": "OBJECT",
    "url": "Equipment",
    "title": "Equipment",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "equipmentType",
            "description": "<p>The type of this equipment.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectPlace\">Place</a>",
            "optional": false,
            "field": "origin",
            "description": "<p>The origination point of this equipment.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectEquipmentdestination\">EquipmentDestination</a>",
            "optional": false,
            "field": "destination",
            "description": "<p>The desired destination (point, area, or open).</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectEquipment"
  },
  {
    "type": "OBJECT",
    "url": "EquipmentDestination",
    "title": "EquipmentDestination",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectPlace\">Place</a>",
            "optional": false,
            "field": "place",
            "description": "<p>A specific location.</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectArea\">Area</a>",
            "optional": false,
            "field": "area",
            "description": "<p>A geographic area, defined by a list of states or a region.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "open",
            "description": "<p>Represents “anywhere”.</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectEquipmentdestination"
  },
  {
    "type": "OBJECT",
    "url": "EquipmentUpdate",
    "title": "EquipmentUpdate",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "ltl",
            "description": "<p>“Less than truck load”. For shipments, if set true, then this shipment will not occupy a full truck. For equipment, if set true, then this equipment does not have enough available capacity to carry a full shipment. The element dimensions should be used to specify cargo/capacity size.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "size": "0-70",
            "optional": true,
            "field": "comments",
            "description": "<p>The number of comments on a posting exposed to truck- stops cannot be decreased. Omitting comments from an update is allowable (the previous comment(s) will be left unchanged), but including a single comment when the posting currently includes 2 will result in an error.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 99",
            "optional": true,
            "field": "count",
            "description": "<p>The number of assets represented by this description. This allows a requester to easily (and cheaply) post mul- tiple identical assets.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectDimensions\">Dimensions</a>",
            "optional": true,
            "field": "dimensions",
            "description": "<p>For shipments, this is the dimensions of the cargo. For equipment, this is the dimensions of the available cargo space.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "stops",
            "description": "<p>Number of stops this asset must make.</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetUpdateModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectEquipmentupdate"
  },
  {
    "type": "OBJECT",
    "url": "FmPostalCode",
    "title": "FmPostalCode",
    "group": "Custom_types",
    "version": "1.0.0",
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectFmpostalcode",
    "description": "<p>USPS or Canada Post postal code. The Freight Match- ing Service will use the city+state/province assigned to this postal code.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"US\"",
              "\"CA\"",
              "\"MX\""
            ],
            "optional": false,
            "field": "country",
            "description": "<p>Country code.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "5-10",
            "optional": false,
            "field": "code",
            "description": "<p>ZIP or postal code. The hyphen in 9-digit US ZIP codes and the space in Canadian postal codes are optional.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "US postal Code",
        "content": "\nbody:\n\n{ country: \"US\", code: \"99501\" }",
        "type": "json"
      },
      {
        "title": "Canada postal Code",
        "content": "\nbody:\n\n{ country: \"CA\", code: \"K1A 0B1\" }",
        "type": "json"
      }
    ]
  },
  {
    "type": "OBJECT",
    "url": "NamedCoordinates",
    "title": "NamedCoordinates",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>City + state/province + coordinates. DAT Connexion will use the city, state/province, and coordinates to resolve to a known place. If an exact match is not found, Connexion will use variations in the city spelling and proximity to the coordinates to resolve. If no resolution is found, Connexion will accept the specified city spelling as-is, and use the specified coordinates and state/province (although road mileages based on this city will not be possible).</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "size": "13.00 - 86.00",
            "optional": false,
            "field": "latitude",
            "description": "<p>Latitude of the desired point.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "-177.00 – -52.00",
            "optional": false,
            "field": "longitude",
            "description": "<p>Longitude of the desired point.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-30",
            "optional": false,
            "field": "city",
            "description": "<p>City name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"AB\"",
              "\"AG\"",
              "\"AK\"",
              "\"AL\"",
              "\"AS\"",
              "\"AZ\"",
              "\"AR\"",
              "\"BC\"",
              "\"BJ\"",
              "\"BS\"",
              "\"CA\"",
              "\"CH\"",
              "\"CI\"",
              "\"CL\"",
              "\"CO\"",
              "\"CP\"",
              "\"CT\"",
              "\"CU\"",
              "\"DC\"",
              "\"DE\"",
              "\"DF\"",
              "\"DG\"",
              "\"EM\"",
              "\"FL\"",
              "\"GA\"",
              "\"GJ\"",
              "\"GR\"",
              "\"GU\"",
              "\"HG\"",
              "\"HI\"",
              "\"IA\"",
              "\"ID\"",
              "\"IL\"",
              "\"IN\"",
              "\"JA\"",
              "\"KS\"",
              "\"KY\"",
              "\"LA\"",
              "\"MA\"",
              "\"MB\"",
              "\"MD\"",
              "\"ME\"",
              "\"MH\"",
              "\"MI\"",
              "\"MN\"",
              "\"MO\"",
              "\"MR\"",
              "\"MS\"",
              "\"MT\"",
              "\"NA\"",
              "\"NE\"",
              "\"NL\"",
              "\"NV\"",
              "\"NB\"",
              "\"NH\"",
              "\"NJ\"",
              "\"NM\"",
              "\"NY\"",
              "\"NF\"",
              "\"NC\"",
              "\"ND\"",
              "\"NT\"",
              "\"NS\"",
              "\"NU\"",
              "\"OA\"",
              "\"OH\"",
              "\"OK\"",
              "\"ON\"",
              "\"OR\"",
              "\"PA\"",
              "\"PE\"",
              "\"PQ\"",
              "\"PR\"",
              "\"PU\"",
              "\"QA\"",
              "\"QR\"",
              "\"RI\"",
              "\"SC\"",
              "\"SD\"",
              "\"SI\"",
              "\"SK\"",
              "\"SL\"",
              "\"SO\"",
              "\"TA\"",
              "\"TL\"",
              "\"TM\"",
              "\"TN\"",
              "\"TX\"",
              "\"UT\"",
              "\"VA\"",
              "\"VI\"",
              "\"VL\"",
              "\"VT\"",
              "\"WA\"",
              "\"WV\"",
              "\"WI\"",
              "\"WY\"",
              "\"YC\"",
              "\"YT\"",
              "\"ZT\""
            ],
            "optional": false,
            "field": "stateProvince",
            "description": "<p>State or province.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "Named Coordinates",
        "content": "\nbody:\n\n{ latitude: 38.3, longitude: -97.5, city: \"Galva\", stateProvince: \"KS\" }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectNamedcoordinates"
  },
  {
    "type": "OBJECT",
    "url": "NamedPostalCode",
    "title": "NamedPostalCode",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>Postal code + city + state/province. The Freight Matching Service will internally use the latitude/longitude coordinates of the city assigned to the postal code, but will list the specified city + state/province on assets/searches. This option is useful when the popularly named city is not what USPS/CP assigns to that postal code code (e.g., 97222 is officially Portland, Oregon, but residents of that ZIP refer to their locale as Milwaukie.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-30",
            "optional": false,
            "field": "city",
            "description": "<p>City name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-30",
            "optional": true,
            "field": "county",
            "description": "<p>County. If present, the county is used to disambiguate between multiple similarly named cities in the state or province.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectPostalcode\">PostalCode</a>",
            "optional": false,
            "field": "postalCode",
            "description": "<p>USPS or Canada Post postal code.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"AB\"",
              "\"AG\"",
              "\"AK\"",
              "\"AL\"",
              "\"AS\"",
              "\"AZ\"",
              "\"AR\"",
              "\"BC\"",
              "\"BJ\"",
              "\"BS\"",
              "\"CA\"",
              "\"CH\"",
              "\"CI\"",
              "\"CL\"",
              "\"CO\"",
              "\"CP\"",
              "\"CT\"",
              "\"CU\"",
              "\"DC\"",
              "\"DE\"",
              "\"DF\"",
              "\"DG\"",
              "\"EM\"",
              "\"FL\"",
              "\"GA\"",
              "\"GJ\"",
              "\"GR\"",
              "\"GU\"",
              "\"HG\"",
              "\"HI\"",
              "\"IA\"",
              "\"ID\"",
              "\"IL\"",
              "\"IN\"",
              "\"JA\"",
              "\"KS\"",
              "\"KY\"",
              "\"LA\"",
              "\"MA\"",
              "\"MB\"",
              "\"MD\"",
              "\"ME\"",
              "\"MH\"",
              "\"MI\"",
              "\"MN\"",
              "\"MO\"",
              "\"MR\"",
              "\"MS\"",
              "\"MT\"",
              "\"NA\"",
              "\"NE\"",
              "\"NL\"",
              "\"NV\"",
              "\"NB\"",
              "\"NH\"",
              "\"NJ\"",
              "\"NM\"",
              "\"NY\"",
              "\"NF\"",
              "\"NC\"",
              "\"ND\"",
              "\"NT\"",
              "\"NS\"",
              "\"NU\"",
              "\"OA\"",
              "\"OH\"",
              "\"OK\"",
              "\"ON\"",
              "\"OR\"",
              "\"PA\"",
              "\"PE\"",
              "\"PQ\"",
              "\"PR\"",
              "\"PU\"",
              "\"QA\"",
              "\"QR\"",
              "\"RI\"",
              "\"SC\"",
              "\"SD\"",
              "\"SI\"",
              "\"SK\"",
              "\"SL\"",
              "\"SO\"",
              "\"TA\"",
              "\"TL\"",
              "\"TM\"",
              "\"TN\"",
              "\"TX\"",
              "\"UT\"",
              "\"VA\"",
              "\"VI\"",
              "\"VL\"",
              "\"VT\"",
              "\"WA\"",
              "\"WV\"",
              "\"WI\"",
              "\"WY\"",
              "\"YC\"",
              "\"YT\"",
              "\"ZT\""
            ],
            "optional": false,
            "field": "stateProvince",
            "description": "<p>State or province.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "Named Postal Code",
        "content": "\nbody:\n\n{ city: \"San Luis\", stateProvince: \"CO\", postalCode: { country: \"US\", code: \"81152\" } }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectNamedpostalcode"
  },
  {
    "type": "OBJECT",
    "url": "Place",
    "title": "Place",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectFmpostalcode\">FmPostalCode</a>",
            "optional": false,
            "field": "postalCode",
            "description": "<p>USPS or Canada Post postal code. The Freight Match- ing Service will use the city+state/province assigned to this postal code).</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectCityandstate\">CityAndState</a>",
            "optional": false,
            "field": "cityAndState",
            "description": "<p>City + state/province. The Freight Matching Service will match the city + state/province against its atlas of known cities. If multiple cities of the same name exist in the state/province, it will use the optional county to disambiguate).</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectNamedpostalcode\">NamedPostalCode</a>",
            "optional": false,
            "field": "namedPostalCode",
            "description": "<p>Postal code + city + state/province. The Freight Match- ing Service will internally use the latitude/longitude coordinates of the city assigned to the postal code, but will list the specified city + state/province on as- sets/searches. This option is useful when the popularly named city is not what USPS/CP assigns to that postal code code (e.g., 97222 is officially Portland, Oregon, but residents of that ZIP refer to their locale as Milwaukie).</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectCoordinates\">Coordinates</a>",
            "optional": false,
            "field": "coordinates",
            "description": "<p>Latitude/longitude coordinates. The Freight Matching Service will internally assign the closest known city to those coordinates. Note that this could result in a city being selected that is not in the same state/province as the coordinates.</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectNamedcoordinates\">NamedCoordinates</a>",
            "optional": false,
            "field": "namedCoordinates",
            "description": "<p>City + state/province + coordinates. DAT Connex- ion will use the city, state/province, and coordinates to resolve to a known place. If an exact match is not found, Connexion will use variations in the city spelling and proximity to the coordinates to resolve. If no res- olution is found, Connexion will accept the specified city spelling as-is, and use the specified coordinates and state/province (although road mileages based on this city will not be possible).</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "Postal Code US",
        "content": "\nbody:\n\n{ postalCode: { country: \"US\", code: \"99501\" } }",
        "type": "json"
      },
      {
        "title": "postal Code Canada",
        "content": "\nbody:\n\n{ postalCode: { country: \"CA\", code: \"K1A 0B1\" } }",
        "type": "json"
      },
      {
        "title": "City And State",
        "content": "\nbody:\n\n{ cityAndState: { city: \"San Luis\", stateProvince: \"CO\" } }",
        "type": "json"
      },
      {
        "title": "Named Postal Code",
        "content": "\nbody:\n\n{ namedPostalCode: { city: \"San Luis\", stateProvince: \"CO\", postalCode: { country: \"US\", code: \"81152\" }\n} }",
        "type": "json"
      },
      {
        "title": "Coordinates",
        "content": "\nbody:\n\n{ coordinates: { latitude: 38.3, longitude: -97.5 } }",
        "type": "json"
      },
      {
        "title": "Named Coordinates",
        "content": "\nbody:\n\n{ namedCoordinates: { latitude: 38.3, longitude: -97.5, city: \"Galva\", stateProvince: \"KS\" } }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectPlace"
  },
  {
    "type": "OBJECT",
    "url": "PostalCode",
    "title": "PostalCode",
    "group": "Custom_types",
    "version": "1.0.0",
    "filename": "src/main/java/hello/models/CommonModels.java",
    "groupTitle": "Custom_types",
    "name": "ObjectPostalcode",
    "description": "<p>USPS or Canada Post postal code. The Freight Match- ing Service will use the city+state/province assigned to this postal code.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"US\"",
              "\"CA\"",
              "\"MX\""
            ],
            "optional": false,
            "field": "country",
            "description": "<p>Country code.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "5-10",
            "optional": false,
            "field": "code",
            "description": "<p>ZIP or postal code. The hyphen in 9-digit US ZIP codes and the space in Canadian postal codes are optional.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "US postal Code",
        "content": "\nbody:\n\n{ country: \"US\", code: \"99501\" }",
        "type": "json"
      },
      {
        "title": "Canada postal Code",
        "content": "\nbody:\n\n{ country: \"CA\", code: \"K1A 0B1\" }",
        "type": "json"
      }
    ]
  },
  {
    "type": "OBJECT",
    "url": "Rate",
    "title": "Rate",
    "group": "Custom_types",
    "description": "<p>Payment rate in USD (flat-rate or per-mile).</p>",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0.0 – 99999.99",
            "optional": false,
            "field": "baseRateDollars",
            "description": "<p>Rate in US Dollars (the rateBasedOn element defines whether this is a flat rate or per-mile).</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"Flat\"",
              "\"PerMile\""
            ],
            "optional": false,
            "field": "rateBasedOn",
            "description": "<p>Per-mile or flat-rate.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0 – 9999",
            "optional": true,
            "field": "rateMiles",
            "description": "<p>If the rate is based on per-mile, or flat-rate for a trip, this is the mileage used by the poster. It is supplied by the poster, and may not agree with HHG mileage or that computed by mileage vendors.</p>"
          }
        ]
      }
    },
    "examples": [
      {
        "title": "Flat Rate",
        "content": "\nbody:\n\n{ baseRateDollars: 55.0, rateBasedOn: \"Flat\", rateMiles: 550 }",
        "type": "json"
      },
      {
        "title": "Per Mile Rate",
        "content": "\nbody:\n\n{ baseRateDollars: 70.0, rateBasedOn: \"PerMile\", rateMiles: 50 }",
        "type": "json"
      },
      {
        "title": "Valid Rate",
        "content": "\nbody:\n\n{ baseRateDollars: 70.0, rateBasedOn: \"PerMile\" }",
        "type": "json"
      }
    ],
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectRate"
  },
  {
    "type": "OBJECT",
    "url": "Shipment",
    "title": "Shipment",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "equipmentType",
            "description": "<p>The type of equipment that is needed to haul this ship- ment.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectPlace\">Place</a>",
            "optional": false,
            "field": "origin",
            "description": "<p>The origination point of this shipment.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectPlace\">Place</a>",
            "optional": false,
            "field": "destination",
            "description": "<p>The destination point of this shipment.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectTruckstops\">truckStops</a>",
            "optional": true,
            "field": "truckStops",
            "description": "<p>All the information needed to advertise this shipment on the Truck Stop network. If omitted, this shipment will not be advertised on the Truck Stop Network.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectRate\">Rate</a>",
            "optional": true,
            "field": "rate",
            "description": "<p>Payment rate in USD (flat-rate or per-mile).</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectShipment"
  },
  {
    "type": "OBJECT",
    "url": "ShipmentUpdate",
    "title": "ShipmentUpdate",
    "group": "Custom_types",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "ltl",
            "description": "<p>“Less than truck load”. For shipments, if set true, then this shipment will not occupy a full truck. For equipment, if set true, then this equipment does not have enough available capacity to carry a full shipment. The element dimensions should be used to specify cargo/capacity size.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "size": "0-70",
            "optional": true,
            "field": "comments",
            "description": "<p>The number of comments on a posting exposed to truck- stops cannot be decreased. Omitting comments from an update is allowable (the previous comment(s) will be left unchanged), but including a single comment when the posting currently includes 2 will result in an error.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "1 – 99",
            "optional": true,
            "field": "count",
            "description": "<p>The number of assets represented by this description. This allows a requester to easily (and cheaply) post mul- tiple identical assets.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectDimensions\">Dimensions</a>",
            "optional": true,
            "field": "dimensions",
            "description": "<p>For shipments, this is the dimensions of the cargo. For equipment, this is the dimensions of the available cargo space.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "stops",
            "description": "<p>Number of stops this asset must make.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectTruckstops\">truckStops</a>",
            "optional": true,
            "field": "truckStops",
            "description": "<p>Truckstop locations can be altered, but the number of truckstops cannot be decreased.</p>"
          },
          {
            "group": "Parameter",
            "type": "<a href=\"#api-Custom_types-ObjectRate\">Rate</a>",
            "optional": true,
            "field": "rate",
            "description": "<p>Payment rate in USD (flat-rate or per-mile).</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetUpdateModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectShipmentupdate"
  },
  {
    "type": "OBJECT",
    "url": "TruckStops",
    "title": "TruckStops",
    "group": "Custom_types",
    "version": "1.0.0",
    "description": "<p>All the information needed to advertise this shipment on the Truck Stop network. If omitted, this shipment will not be advertised on the Truck Stop Network.</p>",
    "parameter": {
      "fields": {
        "OneOf": [
          {
            "group": "OneOf",
            "type": "Object",
            "optional": false,
            "field": "truckStopIds",
            "description": ""
          },
          {
            "group": "OneOf",
            "type": "Number[]",
            "size": "0 – 9999",
            "optional": false,
            "field": "truckStopIds.ids",
            "description": "<p>A list of TransCore/DAT identifiers for the truckstops the shipment should be displayed at.</p>"
          },
          {
            "group": "OneOf",
            "type": "Boolean",
            "optional": false,
            "field": "closest",
            "description": "<p>Select the truckstops closest to the shipment origin. The number of selected locations is determined by the poster’s subscription and contract.</p>"
          },
          {
            "group": "OneOf",
            "type": "Object",
            "optional": false,
            "field": "alternateClosest",
            "description": "<p>Select the truckstops closest to an alternate origin point. This option is typically used to display the shipment on truckstops in the desired lane. The number of selected locations is determined by the poster’s subscription and contract.</p>"
          },
          {
            "group": "OneOf",
            "type": "<a href=\"#api-Custom_types-ObjectPlace\">Place</a>",
            "optional": false,
            "field": "alternateClosest.alternateOrigin",
            "description": "<p>Origin point.</p>"
          }
        ],
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"Flash\"",
              "\"Highlight\""
            ],
            "optional": true,
            "field": "enhancements",
            "description": "<p>Optional truck stop load monitor video enhancements for this shipment’s advertisement.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "size": "0-8",
            "optional": true,
            "field": "posterDisplayName",
            "description": "<p>The short name of the poster’s company, for display on the truck stop load monitors. If omitted, the short name of the poster’s company will default to that contained in the poster’s company record.</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/hello/models/AssetPostModel.java",
    "groupTitle": "Custom_types",
    "name": "ObjectTruckstops"
  }
] });
