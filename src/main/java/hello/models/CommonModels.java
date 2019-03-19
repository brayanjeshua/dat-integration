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

class AreaModel extends AbstractModel<com.tcore.tcoreTypes.Area> {

    public String[] stateProvinces = null;
    public String[] zones = null;

    @Override
    public com.tcore.tcoreTypes.Area fill(com.tcore.tcoreTypes.Area instance) throws java.rmi.RemoteException {

        if (stateProvinces != null) {
            for (String state : stateProvinces)
                instance.addStateProvinces(com.tcore.tcoreTypes.StateProvince.Enum.forString(state));
        }

        if (zones != null) {
            for (String zone : zones)
                instance.addZones(com.tcore.tcoreTypes.Zone.Enum.forString(zone));
        }

        return instance;
    }
}

class EquipmentDestination extends AbstractModel<com.tcore.tfmiFreightMatching.EquipmentDestination> {
    public PlaceModel place = null;
    public AreaModel area = null;
    public Boolean open = null;

    @Override
    public com.tcore.tfmiFreightMatching.EquipmentDestination fill(
            com.tcore.tfmiFreightMatching.EquipmentDestination instance) throws java.rmi.RemoteException {
        if (place != null)
            place.fill(instance.addNewPlace());
        else if (area != null)
            area.fill(instance.addNewArea());
        else if (open != null)
            instance.addNewOpen();

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

class RateModel extends AbstractModel<com.tcore.tfmiFreightMatching.ShipmentRate> {
    public Float baseRateDollars;
    public String rateBasedOn;
    public Integer rateMiles = null;

    @Override
    public com.tcore.tfmiFreightMatching.ShipmentRate fill(com.tcore.tfmiFreightMatching.ShipmentRate instance)
            throws java.rmi.RemoteException {

        instance.setBaseRateDollars(baseRateDollars.floatValue());
        instance.setRateBasedOn(com.tcore.tfmiFreightMatching.RateBasedOnType.Enum.forString(rateBasedOn));

        if (rateMiles != null)
            instance.setRateMiles(rateMiles.intValue());

        return instance;
    }
}

class TruckStopIdsModel extends AbstractModel<com.tcore.tfmiFreightMatching.TruckStopIds> {

    public Integer[] ids;

    @Override
    public com.tcore.tfmiFreightMatching.TruckStopIds fill(com.tcore.tfmiFreightMatching.TruckStopIds instance) {

        for (Integer id : ids)
            instance.addIds(id);

        return instance;
    }

}

class AlternateClosestModel extends AbstractModel<com.tcore.tfmiFreightMatching.AlternateClosestTruckStops> {
    public PlaceModel alternateOrigin;

    @Override
    public com.tcore.tfmiFreightMatching.AlternateClosestTruckStops fill(
            com.tcore.tfmiFreightMatching.AlternateClosestTruckStops instance) throws java.rmi.RemoteException {

        alternateOrigin.fill(instance.addNewAlternateOrigin());

        return instance;
    }
}

class TruckStopsModel extends AbstractModel<com.tcore.tfmiFreightMatching.TruckStops> {

    public TruckStopIdsModel truckStopIds = null;
    public Boolean closest = null;
    public AlternateClosestModel alternateClosest = null;

    public String[] enhancements = null;
    public String posterDisplayName = null;

    @Override
    public com.tcore.tfmiFreightMatching.TruckStops fill(com.tcore.tfmiFreightMatching.TruckStops instance)
            throws java.rmi.RemoteException {

        if (truckStopIds != null)
            truckStopIds.fill(instance.addNewTruckStopIds());
        else if (closest != null)
            instance.addNewClosest();
        else if (alternateClosest != null)
            alternateClosest.fill(instance.addNewAlternateClosest());

        if (enhancements != null)
            for (String enhancement : enhancements)
                instance.addEnhancements(
                        com.tcore.tfmiFreightMatching.TruckStopVideoEnhancement.Enum.forString(enhancement));

        if (posterDisplayName != null)
            instance.setPosterDisplayName(posterDisplayName);

        return instance;
    }
}

class ShipmentModel extends AbstractModel<com.tcore.tfmiFreightMatching.Shipment> {

    public String equipmentType;
    public PlaceModel origin;
    public PlaceModel destination;
    public TruckStopsModel truckStops = null;
    public RateModel rate = null;

    @Override
    public com.tcore.tfmiFreightMatching.Shipment fill(com.tcore.tfmiFreightMatching.Shipment instance)
            throws java.rmi.RemoteException {
        instance.setEquipmentType(com.tcore.tcoreTypes.EquipmentType.Enum.forString(equipmentType));

        origin.fill(instance.addNewOrigin());
        destination.fill(instance.addNewDestination());

        if (truckStops != null)
            truckStops.fill(instance.addNewTruckStops());

        if (rate != null)
            rate.fill(instance.addNewRate());

        return instance;
    }
}

class EquipmentModel extends AbstractModel<com.tcore.tfmiFreightMatching.Equipment> {

    public String equipmentType;
    public PlaceModel origin;
    public EquipmentDestination destination;

    @Override
    public com.tcore.tfmiFreightMatching.Equipment fill(com.tcore.tfmiFreightMatching.Equipment instance)
            throws java.rmi.RemoteException {
        instance.setEquipmentType(com.tcore.tcoreTypes.EquipmentType.Enum.forString(equipmentType));

        origin.fill(instance.addNewOrigin());
        destination.fill(instance.addNewDestination());

        return instance;
    }
}