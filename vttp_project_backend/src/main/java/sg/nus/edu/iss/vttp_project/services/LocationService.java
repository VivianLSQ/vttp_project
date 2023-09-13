package sg.nus.edu.iss.vttp_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;

import sg.nus.edu.iss.vttp_project.models.Locations;

@Service
public class LocationService {

    public static List<Locations> getAllLocations() {
        return null;
    }

    public static Location getLocationById(Long id) {
        return null;
    }

    public static Location createLocation(Location location) {
        return null;
    }

    public static Location updateLocation(Long id, Location location) {
        return null;
    }

    public static boolean deleteLocation(Long id) {
        return false;
    }
    
}
