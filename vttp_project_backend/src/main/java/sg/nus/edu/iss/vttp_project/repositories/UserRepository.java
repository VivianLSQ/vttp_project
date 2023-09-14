package sg.nus.edu.iss.vttp_project.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp_project.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String addNewUserSql = "insert into users (email, username, password, homeAddress, workAddress) values (?, ?, ?, ?, ?)";
    private final String findByIdSql = "SELECT * FROM users WHERE id=?";
    private final String findByUsernameSql = "SELECT * FROM users WHERE username=?";
    private final String findByEmailSql = "SELECT * FROM users WHERE email=?";
    private final String updateUserSql = "update users set email = ?, username = ?, password = ?, homeAddress = ?, workAddress = ? where id = ?";

    User user = new User(); 

    public User getUserById(Integer id) {
        user = jdbcTemplate.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(User.class), id);
        return user; 
    }
     public User getUserByUsername(String username) {
        user = jdbcTemplate.queryForObject(findByUsernameSql, BeanPropertyRowMapper.newInstance(User.class), username);
        return user;
    } 

    public User getUserByEmail(String email) {
        user = jdbcTemplate.queryForObject(findByEmailSql, BeanPropertyRowMapper.newInstance(User.class), email);
        return user;
    } 

    public Integer addNewUser(User user){
        KeyHolder generatedKey = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(addNewUserSql, new String[] {"id"});
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword()); 
                ps.setString(3, user.getHomeAddress());
                ps.setString(4, user.getWorkAddress());

                return ps;
            }
        }; 

        jdbcTemplate.update(psc, generatedKey);

        return generatedKey.getKey().intValue();
    
    }

    //Update User (e.g. change password)
    public Integer updateUser(User user) {
        return jdbcTemplate.update(updateUserSql, user.getEmail(), user.getUsername(), user.getPassword(), user.getHomeAddress(), user.getWorkAddress(), user.getId());
    }


}
    

    /* 
        public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSql);

        while(rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            // Populate other user properties as needed

           userList.add(user);
        }
        return Collections.unmodifiableList(userList);
     } 
     */
