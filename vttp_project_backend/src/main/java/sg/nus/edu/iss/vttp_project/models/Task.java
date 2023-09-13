package sg.nus.edu.iss.vttp_project.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Integer taskId; //change to String to generate using UUID (random)
    private String content;
    private Date dateAdded; 
    private Date dueDate; 
    private String notes; 


    
}
