package sg.nus.edu.iss.vttp_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
     @Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").setRelayPort(61613).setClientLogin("guest")
				.setClientPasscode("guest");
    }

    //Register STOMP endpoints mapping each to a specific URL and (optionally) enabling and configuring SockJS fallback options.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocketChat").withSockJS();
    }

    /*
     //Configure message broker options
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registryConfig) {
        registryConfig.enableSimpleBroker("/topic");
        registryConfig.setApplicationDestinationPrefixes("/app");
    }
     */
}
