package com.Expenses.AuthService.Configuration;


import com.Expenses.AuthService.Repository.UserInfoRepository;
import com.Expenses.AuthService.Repository.UserRoleRepository;
import com.Expenses.AuthService.Service.JwtTokenService;
import com.Expenses.AuthService.Service.UserDetailServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//    private final JwtTokenService jwtTokenService;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/auth/v1/login",
//                                "/auth/v1/signup",
//                                "/auth/v1/refresh-token",
//                                "/apis/swaggers/**",
//                                "/apis/docs/**",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .authenticationProvider(authenticationProvider());
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return authProvider;
//    }
//}

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@Data
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailsServiceImpl;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    UserDetailsService userDetailsService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository){
        return new UserDetailServiceImpl(userInfoRepository,passwordEncoder,userRoleRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter,AuthenticationProvider authenticationProvider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/v1/login",
                                "/auth/v1/signup",
                                "/auth/v1/refreshToken"
//                                "/apis/swaggers/**",
//                                "/apis/docs/**",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        System.out.println(authProvider+"auth provider working at login 2");
        return authProvider;
    }
}


