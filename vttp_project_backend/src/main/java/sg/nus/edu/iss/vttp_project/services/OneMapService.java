package sg.nus.edu.iss.vttp_project.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OneMapService {

     @Value("${onemap.api.base_url}")
    private String baseUrl; 
    private final RestTemplate restTemplate;

    public OneMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Define a method to call the routing API
    public Object callRoutingApi() {
        String apiUrl = baseUrl + "/routing"; // Replace with the actual API endpoint
        // Use restTemplate to make an HTTP request to apiUrl
        // You'll need to provide the necessary request parameters and handle the response
        // Return the response data or process it as needed
        return restTemplate.getForObject(apiUrl, Object.class);
    }
    
}
