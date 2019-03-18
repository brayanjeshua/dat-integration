
package hello;

public class AssetUpdateModel {
    public String assetId = null;
    public String postersReferenceId = null;

    public String updateType = null;

    public Boolean ltl = null;
    public String[] comments = {};
    public Integer count = -1;

    public Integer lengthFeet = 0;
    public Integer weightPounds = 0;
    public Integer heightInches = 0;
    public Integer volumeCubicFeet = 0;

    public Integer stops;

    public Float baseRateDollars = -1.0f;
    public String rateBasedOn = "";
    public Integer rateMiles = -1;
}