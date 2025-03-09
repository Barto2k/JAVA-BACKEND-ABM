package frc.utn.edu.ar.ApiGW.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/pruebas/**").hasRole("EMPLEADO")
                        .pathMatchers("/api/pruebas").hasRole("EMPLEADO")
                        .pathMatchers("/api/interesado/**").hasAnyRole("EMPLEADO", "ADMIN", "VEHICULO")
                        .pathMatchers("/api/reportes/**").hasRole("ADMIN")
                        .pathMatchers("/api/vehiculo/**").hasRole("VEHICULO")
                        .pathMatchers("/api/notificacion/**").hasRole("EMPLEADO")
                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))

                // Desactiva CSRF
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();

        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
            authoritiesConverter.setAuthoritiesClaimName("authorities"); // Campo del JWT que contiene los roles
            authoritiesConverter.setAuthorityPrefix("ROLE_"); // Prefijo para los roles

            return Mono.just(authoritiesConverter.convert(jwt)).block();
        });

        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
    }
}
