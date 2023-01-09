//package cn.ist.lowcoding.userservice.config;
//
//import cn.ist.lowcoding.userservice.interceptor.DefaultInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import javax.annotation.Resource;
//
//
//@EnableWebSocket
//@Configuration
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Resource
//    WebSocketHandler defaultHandler;
//
//    @Resource
//    DefaultInterceptor defaultInterceptor;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry
//                .addHandler(defaultHandler, "ws")
//                .addInterceptors(defaultInterceptor)
//                .setAllowedOrigins("*");
//    }
//}
package cn.ist.lowcoding.streamservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}