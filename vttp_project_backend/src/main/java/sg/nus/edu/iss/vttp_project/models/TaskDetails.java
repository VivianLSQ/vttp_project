package sg.nus.edu.iss.vttp_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetails {

    private String id; 
    private Boolean isCompleted; 
    private String category; //or label (can be color coded)
    private String priority;
    


    
}
