package sg.nus.edu.iss.vttp_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketChat {
    private String type; 
    private String content; 
    private String sender; 
    
}
