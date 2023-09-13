package sg.nus.edu.iss.vttp_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Locations {

    private Long locationId; 
    private String locationName; 
    private double latitude; //coordinates of Geofence center
    private double longitude;
    private double radius; //in meters 
    
    
}
