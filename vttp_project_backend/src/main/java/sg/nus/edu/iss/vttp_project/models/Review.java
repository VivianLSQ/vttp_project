package sg.nus.edu.iss.vttp_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private String reviewId;
    private String userFeedback; 
    private String userRating; 
    
}
