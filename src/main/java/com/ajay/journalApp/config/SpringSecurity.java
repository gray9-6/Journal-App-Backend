//package com.ajay.journalApp.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Slf4j
//public class SpringSecurity{
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        log.info("Inside AppConfig");
//
//        http.sessionManagement(management ->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(Authorize -> Authorize
//                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER","ADMIN")   // Role based Authentication, only the given role can access these apis
//                        .requestMatchers("/api/**").authenticated()   // any user with token can access these apis, regardless or role.
//                        .anyRequest().permitAll()  // other than the given above apis,any user can access apis , they don't need token
//                )
//                .csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){      // whenever new user registered with our app , we will encode the password and then store that encrypted password in our db.
//        return new BCryptPasswordEncoder();
//    }
//}
