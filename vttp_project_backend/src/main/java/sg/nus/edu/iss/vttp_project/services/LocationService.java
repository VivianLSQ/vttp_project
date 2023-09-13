package sg.nus.edu.iss.vttp_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.repositories.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo; 


    public Optional<Locations> createLocation(Locations geofenceData) {
        Long locationId = locationRepo.createLocation(geofenceData);
        if (locationId != null) {
            geofenceData.setLocationId(locationId);
            return Optional.of(geofenceData);
        }
        return Optional.empty();
    }

    public Locations getLocationById(Long locationId) {
        Locations optionalLocation = locationRepo.getGeoLocationById(locationId);
        return optionalLocation; 
    }

    //Change in destination or radius (selected)
    public Locations updateLocation(Long locationId, Locations location) {
        Locations updatedLocation = locationRepo.updateLocation(location);
        return updatedLocation; 
    }

    public Locations deleteLocation(Long locationId) {
        //Locations deleteLocation = locationRepo.deleteLocation(locationId);
        return null; 
    }
    
}
