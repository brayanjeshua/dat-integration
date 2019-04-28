package hello.models;

import java.util.Calendar;

/**
 * @api {OBJECT} PostalCode PostalCode
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiUse PostalCodeBase
 */
class PostalCode extends AbstractModel<com.tcore.tcoreTypes.PostalCode> {

    public String country;
    public String code;

    @Override
    public com.tcore.tcoreTypes.PostalCode fill(com.tcore.tcoreTypes.PostalCode instance)
            throws java.rmi.RemoteException {

        instance.setCountry(com.tcore.tcoreTypes.CountryCode.Enum.forString(country));
        instance.setCode(code);

        return instance;
    }
}

/**
 * @api {OBJECT} FmPostalCode FmPostalCode
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiUse PostalCodeBase
 */
class FmPostalCode extends AbstractModel<com.tcore.tfmiFreightMatching.FmPostalCode> {

    public String country;
    public String code;

    @Override
    public com.tcore.tfmiFreightMatching.FmPostalCode fill(com.tcore.tfmiFreightMatching.FmPostalCode instance)
            throws java.rmi.RemoteException {

        instance.setCountry(com.tcore.tcoreTypes.CountryCode.Enum.forString(country));
        instance.setCode(code);

        return instance;
    }
}

/**
 * @apiDefine PostalCodeBase
 * @apiVersion 1.0.0
 *
 * @apiDescription USPS or Canada Post postal code. The Freight Match- ing Service will use the city+state/province
 *                 assigned to this postal code.
 *
 * @apiParam {String="US","CA","MX"} country Country code.
 * @apiParam {String{5-10}} code ZIP or postal code. The hyphen in 9-digit US ZIP codes and the space in Canadian postal
 *           codes are optional.
 * 
 * @apiExample {json} US postal Code
 * 
 *             body:
 * 
 *             { country: "US", code: "99501" }
 * 
 * @apiExample {json} Canada postal Code
 * 
 *             body:
 * 
 *             { country: "CA", code: "K1A 0B1" }
 */

/**
 * @apiDefine stateProvinceParam
 * @apiParam {String= "AB", "AG", "AK", "AL", "AS", "AZ", "AR", "BC", "BJ", "BS", "CA", "CH", "CI", "CL", "CO", "CP",
 *           "CT", "CU", "DC", "DE", "DF", "DG", "EM", "FL", "GA", "GJ", "GR", "GU", "HG", "HI", "IA", "ID", "IL", "IN",
 *           "JA", "KS", "KY", "LA", "MA", "MB", "MD", "ME", "MH", "MI", "MN", "MO", "MR", "MS", "MT", "NA", "NE", "NL",
 *           "NV", "NB", "NH", "NJ", "NM", "NY", "NF", "NC", "ND", "NT", "NS", "NU", "OA", "OH", "OK", "ON", "OR", "PA",
 *           "PE", "PQ", "PR", "PU", "QA", "QR", "RI", "SC", "SD", "SI", "SK", "SL", "SO", "TA", "TL", "TM", "TN", "TX",
 *           "UT", "VA", "VI", "VL", "VT", "WA", "WV", "WI", "WY", "YC", "YT", "ZT"} stateProvince State or province.
 */
/**
 * @api {OBJECT} CityAndState CityAndState
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiDescription City + state/province. If multiple cities of the same name exist in the state/province, it will use
 *                 the optional county to disambiguate.
 * 
 * @apiParam {String{0..30}} city City name.
 * @apiUse stateProvinceParam
 * @apiParam {String{0..30}} [county] County. If present, the county is used to disambiguate between multiple similarly
 *           named cities in the state or province.
 * 
 * @apiExample {json} City And State
 * 
 *             body:
 * 
 *             { city: "San Luis", stateProvince: "CO" }
 */
class CityAndState extends AbstractModel<com.tcore.tfmiFreightMatching.CityAndState> {

    public String city;
    public String stateProvince;
    public String county = null;

    @Override
    public com.tcore.tfmiFreightMatching.CityAndState fill(com.tcore.tfmiFreightMatching.CityAndState instance)
            throws java.rmi.RemoteException {

        instance.setCity(city);
        instance.setStateProvince(com.tcore.tcoreTypes.StateProvince.Enum.forString(stateProvince));
        if (county != null)
            instance.setCounty(county);

        return instance;
    }
}

/**
 * @api {OBJECT} NamedPostalCode NamedPostalCode
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiDescription Postal code + city + state/province. The Freight Matching Service will internally use the
 *                 latitude/longitude coordinates of the city assigned to the postal code, but will list the specified
 *                 city + state/province on assets/searches. This option is useful when the popularly named city is not
 *                 what USPS/CP assigns to that postal code code (e.g., 97222 is officially Portland, Oregon, but
 *                 residents of that ZIP refer to their locale as Milwaukie.
 * 
 * @apiParam {String{0..30}} city City name.
 * @apiUse stateProvinceParam
 * @apiParam {String{0..30}} [county] County. If present, the county is used to disambiguate between multiple similarly
 *           named cities in the state or province.
 * @apiParam {[PostalCode](#api-Custom_types-ObjectPostalcode)} postalCode USPS or Canada Post postal code.
 * 
 * @apiExample {json} Named Postal Code
 * 
 *             body:
 * 
 *             { city: "San Luis", stateProvince: "CO", postalCode: { country: "US", code: "81152" } }
 */
class NamedPostalCode extends AbstractModel<com.tcore.tfmiFreightMatching.NamedPostalCode> {

    public String city;
    public String stateProvince;
    public String county = null;

    public PostalCode postalCode;

    @Override
    public com.tcore.tfmiFreightMatching.NamedPostalCode fill(com.tcore.tfmiFreightMatching.NamedPostalCode instance)
            throws java.rmi.RemoteException {

        instance.setCity(city);
        instance.setStateProvince(com.tcore.tcoreTypes.StateProvince.Enum.forString(stateProvince));
        if (county != null)
            instance.setCounty(county);

        postalCode.fill(instance.addNewPostalCode());

        return instance;
    }

}

/**
 * @api {OBJECT} Coordinates Coordinates
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * 
 * @apiDescription Latitude/longitude coordinates. The Freight Matching Service will internally assign the closest known
 *                 city to those coordinates. Note that this could result in a city being selected that is not in the
 *                 same state/province as the coordinates.
 * 
 * @apiParam {Number{13.00 - 86.00}} latitude Latitude of the desired point.
 * @apiParam {Number{-177.00 – -52.00}} longitude Longitude of the desired point.
 * 
 * 
 * @apiExample {json} Coordinates
 * 
 *             body:
 * 
 *             { latitude: 38.3, longitude: -97.5 }
 */
class Coordinates extends AbstractModel<com.tcore.tfmiFreightMatching.LatLon> {

    public Float latitude;
    public Float longitude;

    @Override
    public com.tcore.tfmiFreightMatching.LatLon fill(com.tcore.tfmiFreightMatching.LatLon instance)
            throws java.rmi.RemoteException {

        instance.setLatitude(latitude);
        instance.setLongitude(longitude);

        return instance;
    }
}

/**
 * @api {OBJECT} NamedCoordinates NamedCoordinates
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * 
 * @apiDescription City + state/province + coordinates. DAT Connexion will use the city, state/province, and coordinates
 *                 to resolve to a known place. If an exact match is not found, Connexion will use variations in the
 *                 city spelling and proximity to the coordinates to resolve. If no resolution is found, Connexion will
 *                 accept the specified city spelling as-is, and use the specified coordinates and state/province
 *                 (although road mileages based on this city will not be possible).
 * 
 * @apiParam {Number{13.00 - 86.00}} latitude Latitude of the desired point.
 * @apiParam {Number{-177.00 – -52.00}} longitude Longitude of the desired point.
 * @apiParam {String{0..30}} city City name.
 * @apiUse stateProvinceParam
 * 
 * @apiExample {json} Named Coordinates
 * 
 *             body:
 * 
 *             { latitude: 38.3, longitude: -97.5, city: "Galva", stateProvince: "KS" }
 */
class NamedCoordinates extends AbstractModel<com.tcore.tfmiFreightMatching.NamedLatLon> {

    public Float latitude;
    public Float longitude;
    public String city;
    public String stateProvince;

    @Override
    public com.tcore.tfmiFreightMatching.NamedLatLon fill(com.tcore.tfmiFreightMatching.NamedLatLon instance)
            throws java.rmi.RemoteException {

        instance.setLatitude(latitude);
        instance.setLongitude(longitude);
        instance.setCity(city);
        instance.setStateProvince(com.tcore.tcoreTypes.StateProvince.Enum.forString(stateProvince));

        return instance;
    }
}

/**
 * @api {OBJECT} Place Place
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam (OneOf) {[FmPostalCode](#api-Custom_types-ObjectFmpostalcode)} postalCode USPS or Canada Post postal code.
 * @apiParam (OneOf) {[CityAndState](#api-Custom_types-ObjectCityandstate)} cityAndState City + state/province.
 * @apiParam (OneOf) {[NamedPostalCode](#api-Custom_types-ObjectNamedpostalcode)} namedPostalCode Postal code + city +
 *           state/province.
 * @apiParam (OneOf) {[Coordinates](#api-Custom_types-ObjectCoordinates)} coordinates Latitude/longitude coordinates.
 * @apiParam (OneOf) {[NamedCoordinates](#api-Custom_types-ObjectNamedcoordinates)} namedCoordinates City +
 *           state/province + coordinates.
 * 
 * @apiExample {json} Postal Code US
 * 
 *             body:
 * 
 *             { postalCode: { country: "US", code: "99501" } }
 * 
 * @apiExample {json} postal Code Canada
 * 
 *             body:
 * 
 *             { postalCode: { country: "CA", code: "K1A 0B1" } }
 * 
 * @apiExample {json} City And State
 * 
 *             body:
 * 
 *             { cityAndState: { city: "San Luis", stateProvince: "CO" } }
 * 
 * @apiExample {json} Named Postal Code
 * 
 *             body:
 * 
 *             { namedPostalCode: { city: "San Luis", stateProvince: "CO", postalCode: { country: "US", code: "81152" }
 *             } }
 * 
 * @apiExample {json} Coordinates
 * 
 *             body:
 * 
 *             { coordinates: { latitude: 38.3, longitude: -97.5 } }
 * 
 * @apiExample {json} Named Coordinates
 * 
 *             body:
 * 
 *             { namedCoordinates: { latitude: 38.3, longitude: -97.5, city: "Galva", stateProvince: "KS" } }
 */
class PlaceModel extends AbstractModel<com.tcore.tfmiFreightMatching.Place> {

    public FmPostalCode postalCode = null;
    public CityAndState cityAndState = null;
    public NamedPostalCode namedPostalCode = null;
    public Coordinates coordinates = null;
    public NamedCoordinates namedCoordinates = null;

    @Override
    public com.tcore.tfmiFreightMatching.Place fill(com.tcore.tfmiFreightMatching.Place instance)
            throws java.rmi.RemoteException {

        if (postalCode != null)
            postalCode.fill(instance.addNewPostalCode());
        else if (cityAndState != null)
            cityAndState.fill(instance.addNewCityAndState());
        else if (namedPostalCode != null)
            namedPostalCode.fill(instance.addNewNamedPostalCode());
        else if (coordinates != null)
            coordinates.fill(instance.addNewCoordinates());
        else if (namedCoordinates != null)
            namedCoordinates.fill(instance.addNewNamedCoordinates());

        return instance;
    }
}

/**
 * @api {OBJECT} Dimensions Dimensions
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Number{1 – 199}} [lengthFeet]
 * @apiParam {Number{1 – 199999}} [weightPounds]
 * @apiParam {Number{1 – 299}} [heightInches]
 * @apiParam {Number{1 – 9999}} [volumeCubicFeet]
 */
class DimensionsModel extends AbstractModel<com.tcore.tfmiFreightMatching.Dimensions> {

    public Integer lengthFeet = null;
    public Integer weightPounds = null;
    public Integer heightInches = null;
    public Integer volumeCubicFeet = null;

    @Override
    public com.tcore.tfmiFreightMatching.Dimensions fill(com.tcore.tfmiFreightMatching.Dimensions instance)
            throws java.rmi.RemoteException {

        if (lengthFeet != null)
            instance.setLengthFeet(lengthFeet);
        if (weightPounds != null)
            instance.setWeightPounds(weightPounds);
        if (heightInches != null)
            instance.setHeightInches(heightInches);
        if (volumeCubicFeet != null)
            instance.setVolumeCubicFeet(volumeCubicFeet);

        return instance;
    }
}

/**
 * @api {OBJECT} Availability Availability
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Date} [earliest]
 * @apiParamExample {Date} Date
 * 
 *                  2019-05-28T06:30:14.000Z
 * @apiParam {Date} [latest]
 */
class AvailabilityModel extends AbstractModel<com.tcore.tfmiFreightMatching.Availability> {

    public Calendar earliest = null;
    public Calendar latest = null;

    @Override
    public com.tcore.tfmiFreightMatching.Availability fill(com.tcore.tfmiFreightMatching.Availability instance)
            throws java.rmi.RemoteException {

        if (earliest != null)
            instance.setEarliest(earliest);

        if (latest != null)
            instance.setLatest(latest);

        return instance;
    }
}
