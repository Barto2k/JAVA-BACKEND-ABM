package frc.utn.edu.ar.ApiGW.Configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GWConfig {
    private final String uriPruebas = "http://localhost:8080";
    private final String uriNotificaciones = "http://localhost:3000";

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/pruebas/**")
                        .uri(uriPruebas))
                .route(r -> r
                        .path("/api/interesado/**")
                        .uri(uriPruebas))
                .route(r -> r
                        .path("/api/reportes/**")
                        .uri(uriPruebas))
                .route(r -> r
                        .path("/api/vehiculo/**")
                        .uri(uriPruebas))
                .route(r -> r
                        .path("/api/notificacion/**")
                        .uri(uriNotificaciones))
                .build();

    }
}
