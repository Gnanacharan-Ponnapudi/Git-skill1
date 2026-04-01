class file  package com.klu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.klu.security.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{

@Autowired
private JwtFilter jwtFilter;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
{

http.csrf(csrf -> csrf.disable()).formLogin(form -> form.disable()).httpBasic(basic -> basic.disable())

.authorizeHttpRequests(auth -> auth
.requestMatchers("/auth/login").permitAll()
.requestMatchers("/admin/**").hasAuthority("ADMIN")
.requestMatchers("/user/**").hasAnyAuthority("ADMIN","USER")
.anyRequest().authenticated()
)

.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

return http.build();

}

}