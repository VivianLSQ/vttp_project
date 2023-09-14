package sg.nus.edu.iss.vttp_project.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; 
    private String email; 
    private String username;
    private String password;
    private String roles; 

    //Can be saved as favourites or frequently visited places for easy access
    private String homeAddress;
    private String workAddress; 
}
