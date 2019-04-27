package hello.models;

import java.util.Calendar;

/**
 * @api {OBJECT} PostalCode PostalCode
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {String} country
 * @apiParam {String} code
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
 * @apiParam {String} country
 * @apiParam {String} code
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
 * @api {OBJECT} CityAndState CityAndState
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {String{0..30}} city
 * @apiParam {String} stateProvince
 * @apiParam {String} [county]
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
 * @apiParam {String{0..30}} city
 * @apiParam {String} stateProvince
 * @apiParam {String} [county]
 * @apiParam {[PostalCode](#api-Custom_types-ObjectPostalcode)} postalCode
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
        instance.setCounty(county);

        postalCode.fill(instance.addNewPostalCode());

        return instance;
    }

}

/**
 * @api {OBJECT} Coordinates Coordinates
 * @apiGroup Custom types
 * @apiVersion 1.0.0
 * @apiParam {Number{13.00 - 86.00}} latitude
 * @apiParam {Number{-177.00 – -52.00}} longitude
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
 * @apiParam {Number{13.00 - 86.00}} latitude
 * @apiParam {Number{-177.00 – -52.00}} longitude
 * @apiParam {String{0..30}} city
 * @apiParam {String} stateProvince
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
 * @apiParam (OneOf) {[FmPostalCode](#api-Custom_types-ObjectFmpostalcode)} postalCode
 * @apiParam (OneOf) {[CityAndState](#api-Custom_types-ObjectCityandstate)} cityAndState
 * @apiParam (OneOf) {[NamedPostalCode](#api-Custom_types-ObjectNamedpostalcode)} namedPostalCode
 * @apiParam (OneOf) {[Coordinates](#api-Custom_types-ObjectCoordinates)} coordinates
 * @apiParam (OneOf) {[NamedCoordinates](#api-Custom_types-ObjectNamedcoordinates)} namedCoordinates
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
