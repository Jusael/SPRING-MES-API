package com.example.JAVA_MES_API.websocket.jwt;

import java.security.PublicKey;
import java.util.Map;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.example.JAVA_MES_API.api.security.JwtTokenProvider;

import org.hibernate.annotations.Comment;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import com.example.JAVA_MES_API.websocket.jwt.WebJwtTokenProvider;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {

	private WebJwtTokenProvider webJwtTokenProvider;

	public JwtHandshakeInterceptor(WebJwtTokenProvider webJwtTokenProvider) {
		this.webJwtTokenProvider = webJwtTokenProvider;
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		String queryString = request.getURI().getQuery();

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
