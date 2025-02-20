package com.api.parkingcontrol.configs.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig2 {

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()

                // Could define the access here or in controller
//                .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.GET, "/parking-spot/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/parking-spot").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
