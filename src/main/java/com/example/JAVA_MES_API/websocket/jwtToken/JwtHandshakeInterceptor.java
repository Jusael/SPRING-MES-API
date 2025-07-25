package com.example.JAVA_MES_API.websocket.jwtToken;

import java.security.PublicKey;
import java.util.Map;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.example.JAVA_MES_API.api.security.JwtTokenProvider;

import org.hibernate.annotations.Comment;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.example.JAVA_MES_API.websocket.exception.WebSocketExceptionHandler;
import com.example.JAVA_MES_API.websocket.jwtToken.WebJwtTokenProvider;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(JwtHandshakeInterceptor.class);
	
	private WebJwtTokenProvider webJwtTokenProvider;

	public JwtHandshakeInterceptor(WebJwtTokenProvider webJwtTokenProvider) {
		this.webJwtTokenProvider = webJwtTokenProvider;
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		String queryString = request.getURI().getQuery();
		
		log.info("Handshake 요청 URI: " + request.getURI());
		

		
		log.info(queryString);

		if (queryString != null && queryString.startsWith("token=")) {
			String totkString = queryString.substring(6);

			if (webJwtTokenProvider.validateCheck(totkString)) {
				String userString = webJwtTokenProvider.getUserId(totkString);
				
				attributes.put("userId", userString);
			}
			
			return true;
		}

		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// 필요시 로깅
	}
}
