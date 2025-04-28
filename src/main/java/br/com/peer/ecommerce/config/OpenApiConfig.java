package br.com.peer.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();

        if ("prod".equalsIgnoreCase(activeProfile)) {
            server.setUrl("https://ecommerce-api-production-df5c.up.railway.app");
        } else {
            server.setUrl("http://localhost:8080");
        }

        return new OpenAPI().servers(List.of(server));
    }
}