package sg.nus.edu.iss.vttp_project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.services.LocationService;

@Controller
public class LocationController {

    @Autowired 
    private LocationService locationSvc; 


@GetMapping("/{id}")
public ResponseEntity<Locations> getLocationById(@PathVariable Long locationId) {
    Locations locations = locationSvc.getLocationById(locationId);
    if (locations != null) {
        return new ResponseEntity<>(locations, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@PostMapping
public ResponseEntity<Locations> createLocation(@RequestBody Locations geofenceData) {
    Optional<Locations> createLocationData = locationSvc.createLocation(geofenceData);
    if (createLocationData.isPresent()) {
        return new ResponseEntity<>(createLocationData.get(), HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
}

@PutMapping("/{id}")
public ResponseEntity<Locations> updateLocation(@PathVariable Long locationId, @RequestBody Locations location) {
    Locations updatedLocation = locationSvc.updateLocation(locationId, location);
    if (updatedLocation != null) {
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Locations> deleteLocation(@PathVariable Long locationId) {
    if (locationSvc.deleteLocation(locationId) != null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
