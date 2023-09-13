package sg.nus.edu.iss.vttp_project.controllers;

import java.util.List;

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

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;

import sg.nus.edu.iss.vttp_project.models.Locations;
import sg.nus.edu.iss.vttp_project.services.LocationService;
import sg.nus.edu.iss.vttp_project.services.OneMapService;

@RestController
@RequestMapping("/api/onemap")
public class LocationController {

    @Autowired
    private OneMapService mapSvc; 
    
    public LocationController(OneMapService mapSvc) {
        this.mapSvc = mapSvc;
    }

    @GetMapping("/routing")
    public Object getRoutingData() {
        return mapSvc.callRoutingApi();
    }

    //Able to make a get request to '/api/onemap/routing'

    //Use Location API here 
    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocations() {
    List<Locations> location = LocationService.getAllLocations();
    return new ResponseEntity<>(location, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
    Location Location = LocationService.getLocationById(id);
    if (Location != null) {
        return new ResponseEntity<>(Location, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@PostMapping
public ResponseEntity<Location> createLocation(@RequestBody Location Location) {
    Location createdLocation = LocationService.createLocation(Location);
    return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location Location) {
    Location updatedLocation = LocationService.updateLocation(id, Location);
    if (updatedLocation != null) {
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
    if (LocationService.deleteLocation(id)) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
