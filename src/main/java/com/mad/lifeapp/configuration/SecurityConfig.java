package com.mad.lifeapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.secret}")
    public String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
//        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(request ->
//<<<<<<< HEAD
//                        request
////                                .requestMatchers(HttpMethod.POST,"/users/send").permitAll()
//=======
                        request.requestMatchers(HttpMethod.POST, "/users/send").permitAll()
//>>>>>>> 46f3c20343326addbd63e8ab1f8b495f29ea5ce5
//                                .requestMatchers(HttpMethod.POST,"/users/refresh-token").permitAll()
//                                .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
//                                .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
//                                .requestMatchers(HttpMethod.POST,"/users/verification").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/users/*/email").permitAll()
//                                .requestMatchers(HttpMethod.PUT,"/users/*/update-password").permitAll()
//                                .requestMatchers(HttpMethod.POST,"verifications/send").permitAll()
//                                .anyRequest().authenticated());
                                .anyRequest().permitAll());
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // 👈 cho tất cả origin
        config.setAllowedMethods(Collections.singletonList("*"));         // GET, POST, PUT, DELETE...
        config.setAllowedHeaders(Collections.singletonList("*"));         // tất cả header
        config.setAllowCredentials(false); // 👈 để true nếu muốn gửi cookie (và dùng origin cụ thể)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

