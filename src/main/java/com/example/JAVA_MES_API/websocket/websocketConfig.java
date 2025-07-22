package com.example.JAVA_MES_API.websocket;



import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class websocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic"); // 브로커용 (구독 채널) /topic/alarms 채널 이름으로 브로커 큐에 들어가고, 구독자에게 전달.
		config.setApplicationDestinationPrefixes(".app"); //송신자의 접두사 EX stompClient.send("/app/send", {}, JSON.stringify({...}));
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws") // url 접두사 
		.setAllowedOriginPatterns("*")
		.withSockJS();
	}
	
}
