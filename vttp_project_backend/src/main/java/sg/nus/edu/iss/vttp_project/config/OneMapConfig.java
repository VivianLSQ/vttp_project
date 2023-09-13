package sg.nus.edu.iss.vttp_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OneMapConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
