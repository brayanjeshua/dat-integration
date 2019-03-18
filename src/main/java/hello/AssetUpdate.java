package hello;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import com.tcore.tcoreTypes.Area;
import com.tcore.tcoreTypes.CountryCode;
import com.tcore.tcoreTypes.EquipmentType;
import com.tcore.tcoreTypes.Open;
import com.transcore.connexion.sample.BaseSampleClient;

import com.tcore.tcoreTypes.StateProvince;
import com.tcore.tcoreTypes.Zone;
import com.tcore.tfmiFreightMatching.CityAndState;
import com.tcore.tfmiFreightMatching.Dimensions;
import com.tcore.tfmiFreightMatching.Equipment;
import com.tcore.tfmiFreightMatching.EquipmentDestination;
import com.tcore.tfmiFreightMatching.FmPostalCode;
import com.tcore.tfmiFreightMatching.LatLon;
import com.tcore.tfmiFreightMatching.NamedLatLon;
import com.tcore.tfmiFreightMatching.NamedPostalCode;
import com.tcore.tfmiFreightMatching.Place;
import com.tcore.tfmiFreightMatching.PostAssetOperation;
import com.tcore.tfmiFreightMatching.PostAssetRequestDocument;
import com.tcore.tfmiFreightMatching.PostAssetResponseDocument;
import com.tcore.tfmiFreightMatching.PostAssetResult;
import com.tcore.tfmiFreightMatching.RateBasedOnType;
import com.tcore.tfmiFreightMatching.Shipment;
import com.tcore.tfmiFreightMatching.ShipmentRate;
import com.tcore.tfmiFreightMatching.ShipmentUpdate;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;
import com.tcore.tfmiFreightMatching.UpdateAssetOperation;
import com.tcore.tfmiFreightMatching.UpdateAssetOperationDocument;

public class AssetUpdate extends BaseSampleClient {

    private String assetId;
    private String postersReferenceId;

    private String updateType;

    private Boolean updateLtl;
    private Boolean ltl;
    private String[] comments;
    private Integer count;

    private Integer lengthFeet;
    private Integer weightPounds;
    private Integer heightInches;
    private Integer volumeCubicFeet;

    private Integer stops;

    private Float baseRateDollars;
    private String rateBasedOn;
    private Integer rateMiles;

    private static final Set<String> RATE_BASE_TYPES = new HashSet<String>(
            Arrays.asList(new String[] { "Flat", "PerMile" }));

    public AssetUpdate(final String assetId, final String postersReferenceId,

            final String updateType, final Boolean updateLtl, final Boolean ltl, final String[] comments,
            final Integer count,

            final Integer lengthFeet, final Integer weightPounds, final Integer heightInches,
            final Integer volumeCubicFeet,

            final Integer stops,

            final Float baseRateDollars, final String rateBasedOn, final Integer rateMiles) {
        this.assetId = assetId;
        this.postersReferenceId = postersReferenceId;

        this.updateType = updateType;
        this.updateLtl = updateLtl;
        this.ltl = ltl;
        this.comments = comments;
        this.count = count;

        this.lengthFeet = lengthFeet;
        this.weightPounds = weightPounds;
        this.heightInches = heightInches;
        this.volumeCubicFeet = volumeCubicFeet;

        this.stops = stops;

        this.baseRateDollars = baseRateDollars;
        this.rateBasedOn = rateBasedOn;
        this.rateMiles = rateMiles;
    }

    public String process() throws RemoteException {
        final UpdateAssetOperationDocument updateAssetOpDoc = UpdateAssetOperationDocument.Factory.newInstance();

        final UpdateAssetOperation operation = updateAssetOpDoc.addNewUpdateAssetOperation();

        if (this.assetId.length() > 0)
            operation.setAssetId(this.assetId);
        else if (this.postersReferenceId.length() > 0)
            operation.setPostersReferenceId(this.postersReferenceId);
        else
            throw new RemoteException("Please specify an Id.");

        switch (this.updateType) {
        case "shipmentUpdate":
            final ShipmentUpdate shipment = operation.addNewShipmentUpdate();

            if (this.updateLtl)
                shipment.setLtl(this.ltl);

            for (String comment : this.comments)
                shipment.addComments(comment);

            shipment.setCount(this.count);

            if (!this.lengthFeet.equals(0) || !this.weightPounds.equals(0) || !this.heightInches.equals(0)
                    || !this.volumeCubicFeet.equals(0)) {

                final Dimensions d = shipment.addNewDimensions();

                if (!this.lengthFeet.equals(0))
                    d.setLengthFeet(this.lengthFeet);
                if (!this.weightPounds.equals(0))
                    d.setWeightPounds(this.weightPounds);
                if (!this.heightInches.equals(0))
                    d.setHeightInches(this.heightInches);
                if (!this.volumeCubicFeet.equals(0))
                    d.setVolumeCubicFeet(this.volumeCubicFeet);
            }

            if (!this.baseRateDollars.equals(-1.0f) || RATE_BASE_TYPES.contains(this.rateBasedOn)) {
                ShipmentRate rate = shipment.addNewRate();

                rate.setBaseRateDollars(this.baseRateDollars);
                rate.setRateBasedOn(RateBasedOnType.Enum.forString(this.rateBasedOn));

                if (!this.rateMiles.equals(-1)) {
                    rate.setRateMiles(this.rateMiles);
                }
            }

            break;

        // equipment is not yet implemented on the sdk
        // case "equipmentUpdate":
        // break;

        default:
            throw new RemoteException("Not a valid update type.");
        }

        return "";
    }

    @Override
    public void run() throws RemoteException {
        System.out.println("End point: " + endpointUrl);
    }
}
