package com.klu.security.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.klu.security.model.User;
import com.klu.security.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter 
{

@Autowired
private JwtUtil jwtUtil;

@Autowired
private UserRepository userRepository;

@Override
protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
throws ServletException, IOException 
{

String authHeader = request.getHeader("Authorization");

if(authHeader != null && authHeader.startsWith("Bearer ")) 
{

String token = authHeader.substring(7);

try 
{

String username = jwtUtil.extractUsername(token);

User user = userRepository.findByUsername(username);

if(user != null) 
{

UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(username,null,List.of(new SimpleGrantedAuthority(user.getRole())));

SecurityContextHolder.getContext().setAuthentication(authToken);

}

}
catch(Exception e) 
{
System.out.println("Invalid token");
}

}

filterChain.doFilter(request,response);

}

}