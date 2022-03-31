package ca.dal.comparify.framework.notification.websocket;

import ca.dal.comparify.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static ca.dal.comparify.framework.security.filters.jwt.AuthenticationProviders.AUTHENTICATE_HEADER_STRING;

/**
 * @author Harsh Shah
 */
@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private TokenService tokenService;

    /**
     * @param registration
     *
     * @author Harsh Shah
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {

            /**
             * @param message
             * @param channel
             * @return
             *
             * @author Harsh Shah
             */
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                    MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                String token = accessor.getFirstNativeHeader(AUTHENTICATE_HEADER_STRING);
                Authentication authentication = token == null ? null : tokenService.getAuthentication(token);

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    Authentication user = authentication;
                    SecurityContextHolder.getContext().setAuthentication(user);
                    accessor.setUser(user);
                }
                return message;
            }
        });
    }

    /**
     * @param registry
     *
     * @author Harsh Shah
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }

    /**
     * @param config
     *
     * @author Harsh Shah
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic");
    }
}
