package sg.nus.edu.iss.vttp_project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp_project.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String findByIdSql = "SELECT * FROM users WHERE id=?";
    private final String findByUsernameSql = "SELECT * FROM users WHERE username=?";
    //private final String findAllLimitOffsetSql ="select * from users limit ? offset ?";


    public User getUserById(Integer id) {
        User user = new User(); 
        user = jdbcTemplate.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(User.class), id);
        return user; 
    }
   

    public User getUserByUsername(String username) {

        User user = new User(); 
        user = jdbcTemplate.queryForObject(findByUsernameSql, BeanPropertyRowMapper.newInstance(User.class), username);
        return user;
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
