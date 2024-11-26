package com.petr.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwk_set_uri;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer_uri;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        .jwtDecoder(reactiveJwtDecoder())))
                .build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
        converter.setPrincipalClaimName("preferred_username");
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) jwtGrantedAuthoritiesConverter.convert(jwt);
            List<String> roles = (List<String>) jwt.getClaimAsMap("realm_access").get("roles");
            return Flux.fromIterable(Stream.concat(
                    authorities.stream(),
                    roles.stream()
                            .filter(role -> role.startsWith("ROLE_")) // Фильтруем роли, которые начинаются с "ROLE_"
                            .map(SimpleGrantedAuthority::new)        // Преобразуем в SimpleGrantedAuthority
            ).collect(Collectors.toList()));
        });

        return converter;

        //        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
//        converter.setPrincipalClaimName("preferred_username");
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
//            var roles = (List<String>) jwt.getClaimAsMap("realm_access").get("roles");
//            return (Flux<GrantedAuthority>) Stream.concat(authorities.stream(),
//                            roles.stream()
//                                    .filter(role -> role.startsWith("ROLE_"))
//                                    .map(SimpleGrantedAuthority::new)
//                                    .map(GrantedAuthority.class::cast))
//                    .toList();
//        });
//        return converter;
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(jwk_set_uri).build();
    }

//    private static final String[] AUTH_WHITELIST = {
//            "/api/v1/auth/login",
//            "/api/v1/auth/validate",
//            "/api/v1/auth/register"
//    };
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .anyRequest().authenticated())
//                .build();

//        return http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .anyRequest().authenticated())
//                .build();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

}
