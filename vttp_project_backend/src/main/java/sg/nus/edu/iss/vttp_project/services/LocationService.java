package sg.nus.edu.iss.vttp_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.repositories.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo; 


    public Optional<Locations> createLocationData(Locations geofenceData) {
        Long locationId = locationRepo.createLocationData(geofenceData);
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
        Integer optionalLocation = locationRepo.updateLocation(location);

        if (optionalLocation>0) {
            Locations existingLocation = optionalLocation.updateLocation();

            // Update fields 
            existingLocation.setLocationId(location.getLocationId());
            existingLocation.setLocationName(location.getLocationName());
            existingLocation.setLatitude(location.getLatitude());
            existingLocation.setLongitude(location.getLongitude());
            existingLocation.setRadius(location.getRadius());

            return locationRepo.save(existingLocation);
        } else {
            return null; // Location with the given id not found
        }
    }

    public boolean deleteLocation(Long locationId) {
        Optional<Locations> optionalGeoFence = locationRepo.findByLocationId(locationId);

        if (optionalGeoFence.isPresent()) {
            locationRepo.deleteById(locationId);
            return true;
        } else {
            return false; // GeoFence with the given id not found
        }
    }
    
}
