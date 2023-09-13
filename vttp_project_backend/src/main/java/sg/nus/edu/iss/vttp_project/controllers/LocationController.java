package sg.nus.edu.iss.vttp_project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.services.LocationService;
import sg.nus.edu.iss.vttp_project.services.OneMapService;

@RestController
@RequestMapping("/api/onemap")
public class LocationController {

    @Autowired
    private OneMapService mapSvc; 
    @Autowired 
    private LocationService locationSvc; 
    
    public LocationController(OneMapService mapSvc) {
        this.mapSvc = mapSvc;
    }

    @GetMapping("/routing")
    public Object getRoutingData() {
        return mapSvc.callRoutingApi();
    }

    //Able to make a get request to '/api/onemap/routing'


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
public ResponseEntity<Locations> createLocation(@RequestBody Locations geoLocation) {
    Optional<Locations> createLocationData = locationSvc.createLocationData(geoLocation);
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
    if (locationSvc.deleteLocation(locationId)) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
