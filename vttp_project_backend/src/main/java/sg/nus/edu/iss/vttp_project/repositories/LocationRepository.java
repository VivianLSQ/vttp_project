package sg.nus.edu.iss.vttp_project.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp_project.models.Locations;

@Repository
public class LocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

     private final String createGeoFenceSQL = "insert into geo_location (location_Id, location_name, latitude, longitude, radius) values (?, ?, ?, ?, ?)";
     private final String findByLocationIdSql = "select * from geo_location where location_id = ?";
     private final String updateLocationSql = "update geo_location set location_name = ?, latitude = ?, longitude = ?, radius = ? where location_id = ?";
     private final String deleteLocationById = "delete from geo_location where location_id = ?";
 

    public Long createLocation(Locations geoLocation) {
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
    
     public Locations getGeoLocationById(Long locationId) {
       Locations geoLocation = new Locations();

        geoLocation = jdbcTemplate.queryForObject(findByLocationIdSql, BeanPropertyRowMapper.newInstance(Locations.class), locationId);

        return geoLocation;
    }

    //Change in destination or radius (selected)
    public Locations updateLocation(Locations location) {
        int rowsUpdated = jdbcTemplate.update(updateLocationSql, location.getLocationName(), location.getLatitude(), location.getLongitude(), location.getRadius(), location.getLocationId());
        if(rowsUpdated > 0){
         Locations updatedLocation = jdbcTemplate.queryForObject(
            updateLocationSql,
            BeanPropertyRowMapper.newInstance(Locations.class),
            location.getLocationId()
        );
        
        return updatedLocation;
        } else {
            return null; // Update was not successful
    }

}

    public Locations deleteLocation(Long locationId) {
        Locations locationToDelete = null;

        int rowsAffected  = jdbcTemplate.update(deleteLocationById,locationId);
        if(rowsAffected > 0){
            return locationToDelete; 
      } else{
            return null; //EmptyResultDataAccessException 
      }
    }

}
