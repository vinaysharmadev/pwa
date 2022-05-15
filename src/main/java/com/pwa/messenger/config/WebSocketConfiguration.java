package com.pwa.messenger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwa.messenger.controllers.MessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        logger.info("connecting to GCloud Messenger app");
        config.enableSimpleBroker( "/messenger");
        config.setApplicationDestinationPrefixes("/messenger");
        config.setUserDestinationPrefix("/messenger");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        logger.info("Registry entry");
        registry
                .addEndpoint("/messenger-connect")
                .setAllowedOrigins("https://app2.pw")
                .withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        logger.info("Message conversion entry");

        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }



}
