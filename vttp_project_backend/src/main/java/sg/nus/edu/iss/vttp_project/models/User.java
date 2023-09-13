package sg.nus.edu.iss.vttp_project.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; 
    private String username;
    private String password;
    private String roles; 
    
    private String homeAddress;
    private String workAddress; 
}
