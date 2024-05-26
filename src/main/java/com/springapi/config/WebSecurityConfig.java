package com.springapi.config;

import com.springapi.security.jwt.AuthEntryPointJwt;
import com.springapi.security.jwt.AuthTokenFilter;
import com.springapi.security.services.AdminDetailsServiceImplement;
import com.springapi.security.services.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImplement userDetailsService;

    @Autowired
    AdminDetailsServiceImplement adminDetailsService;


    @Autowired
    AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(adminDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig, HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(userAuthenticationProvider())
                .authenticationProvider(adminAuthenticationProvider())
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/user/register").permitAll()
                        .requestMatchers("api/auth/user/login").permitAll()
                        .requestMatchers("/api/auth/user/change-password").authenticated()
                        .requestMatchers("/api/auth/admin/create-account").permitAll()
                        .requestMatchers("/api/auth/admin/login").permitAll()
                        .requestMatchers("/api/auth/admin/change-password").authenticated()
                        .requestMatchers("/api/auth/admin/create-account").hasAuthority("Root Admin")

                        .requestMatchers("/api/roles").hasAuthority("Root Admin")

                        .requestMatchers("/api/permissions").hasAuthority("Root Admin")

                        .requestMatchers("/api/accounts").hasAuthority("Root Admin")
                        .requestMatchers("/api/accounts/**").hasAuthority("Root Admin")

                        .requestMatchers("/api/cities").permitAll()
                        .requestMatchers("/api/cities/**").permitAll()
                        .requestMatchers("/api/cities/create").hasAuthority("Root Admin")
                        .requestMatchers("/api/cities/update/**").hasAuthority("Root Admin")
                        .requestMatchers("/api/cities/delete/**").hasAuthority("Root Admin")

                        .requestMatchers("/api/districts").permitAll()
                        .requestMatchers("/api/districts/**").permitAll()
                        .requestMatchers("/api/districts/create").hasAuthority("Root Admin")
                        .requestMatchers("/api/districts/update/**").hasAuthority("Root Admin")
                        .requestMatchers("/api/districts/delete/**").hasAuthority("Root Admin")

                        .requestMatchers("/api/types").permitAll()

                        .requestMatchers("/api/ftags").permitAll()

                        .requestMatchers("/api/servetypes").permitAll()
                        .requestMatchers("/api/servetypes/**").permitAll()

                        .requestMatchers("/api/status").permitAll()

                        .requestMatchers("/api/categories").permitAll()
                        .requestMatchers("/api/categories/**").permitAll()
                        .requestMatchers("/api/categories/create").hasAuthority("Root Admin")
                        .requestMatchers("/api/categories/update/**").hasAuthority("Root Admin")
                        .requestMatchers("/api/categories/delete/**").hasAuthority("Root Admin")

                        .requestMatchers("/api/images/**").permitAll()

                        .requestMatchers("/api/restaurant/admin").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/restaurant/update").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/restaurant/**").permitAll()

                        .requestMatchers("/api/foods/create").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/foods/update/**").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/foods/delete/**").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/foods/**").permitAll()

                        .requestMatchers("/api/combos/**").permitAll()
                        .requestMatchers("/api/combos/create").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/combos/delete/**").hasAuthority("Restaurant Admin")

                        .requestMatchers("/api/orders/restaurant").hasAuthority("Restaurant Admin")
                        .requestMatchers("/api/orders/user").hasAuthority("User")

                        .anyRequest().authenticated()
                );

        http.authenticationProvider(userAuthenticationProvider());
        http.authenticationProvider(adminAuthenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
