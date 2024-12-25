package com.security.auth_2.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private JwtFilter jwtFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests( request->{
//                    request.requestMatchers("/").permitAll();
//                    request.anyRequest().authenticated();
//                })
////                .addFilterBefore()
////                .formLogin(form->{
////                    form.loginPage("/login");
////                    form.defaultSuccessUrl("/welcome");
////                    form.failureForwardUrl("/fail");
////                })
//                .oauth2Login(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain2(
            HttpSecurity http
    )throws Exception
    {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, AuthorizationFilter.class)
                .authorizeHttpRequests((r)->{
                    r.requestMatchers("/public/**").permitAll();
                    r.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login/error")
                        .permitAll()
                )
                .oauth2Login((oauth2) -> {oauth2
                        .defaultSuccessUrl("/auth/google/success",true);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

}
