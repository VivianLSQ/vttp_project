package sg.nus.edu.iss.vttp_project.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp_project.models.Locations;

@Repository
public class LocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

     private final String createGeoFenceSQL = "insert into geo_location (locationId, locationName, latitude, longitude, radius) values (?, ?, ?, ?, ?)";

    public Long createLocationData(Locations geoLocation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(createGeoFenceSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, geoLocation.getLocationId());
            ps.setString(2, geoLocation.getLocationName());
            ps.setDouble(1, geoLocation.getLatitude());
            ps.setDouble(2, geoLocation.getLongitude());
            ps.setDouble(2, geoLocation.getRadius());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
    
}
