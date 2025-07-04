package com.springboot.demo.SpringBootDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // add support for JDBC ... no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");
        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");
        return jdbcUserDetailsManager;
    }

    // hardcode in memory users
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails cynwell = User.builder()
//                .username("cynwell")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .build();
//        UserDetails ninni = User.builder()
//                .username("ninni")
//                .password("{noop}123")
//                .roles("MANAGER")
//                .build();
//        UserDetails inch = User.builder()
//                .username("inch")
//                .password("{noop}123")
//                .roles("EMPLOYEE")
//                .build();
//        return new InMemoryUserDetailsManager(cynwell, ninni, inch);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        // permit all for Spring Doc
                        .requestMatchers("/api.docs").permitAll()
                        .requestMatchers("/api.docs.yaml").permitAll()
                        // OPTIONS for all public
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // GET, HEAD, OPTIONS for all Role
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.HEAD, "/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        // POST, PUT, PATCH for MANAGER and ADMIN
                        .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/**").hasAnyRole("MANAGER", "ADMIN")
                        // DELETE for ADMIN only
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
        );
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());
        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
