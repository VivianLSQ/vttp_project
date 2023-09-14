package sg.nus.edu.iss.vttp_project.services;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.vttp_project.exceptions.LocationAlreadyExistException;
import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.repositories.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo; 


    @Transactional(rollbackFor = {DataAccessException.class, SQLException.class, LocationAlreadyExistException.class})
    public Optional<Locations> createLocation(Locations geofenceData) {
        try{
            Long locationId = locationRepo.createLocation(geofenceData);
            if (locationId != null) {
                geofenceData.setLocationId(locationId);
                return Optional.of(geofenceData);
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            e.printStackTrace(); 
            return Optional.empty();
        }
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
