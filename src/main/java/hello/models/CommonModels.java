package hello.models;

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
