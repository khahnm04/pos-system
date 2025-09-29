package com.khahnm04.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(menagement ->
                        menagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(Authorize ->
                        Authorize.requestMatchers("/api/**").authenticated()
                                .requestMatchers("/api/super-admin/**").hasRole("ADMIN")
                                .anyRequest().permitAll())

                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)

                .csrf(AbstractHttpConfigurer::disable)

                .cors(cors ->
                        cors.configurationSource(crosConfigurationSource()))

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CorsConfigurationSource crosConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();

                // Allow cross-origin requests from these specific origins
                cfg.setAllowedOrigins(
                        Arrays.asList(
                                "http://localhost:5137", // Vite frontend port
                                "http://localhost:3000"  // Create React App frontend port
                        )
                );

                // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
                cfg.setAllowedMethods(Collections.singletonList("*"));

                // Allow sending cookies and Authorization headers
                cfg.setAllowCredentials(true);

                // Allow all request headers
                cfg.setAllowedHeaders(Collections.singletonList("*"));

                // Expose "Authorization" header to the client
                cfg.setExposedHeaders(Arrays.asList("Authorization"));

                // Set maximum time (in seconds) for the browser to cache this CORS configuration
                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }

}
