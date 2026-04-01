package com.klu.security.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.security.dto.LoginRequest;
import com.klu.security.jwt.JwtUtil;
import com.klu.security.model.User;
import com.klu.security.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController 
{

@Autowired
private JwtUtil jwtUtil;

@Autowired
private UserRepository userRepository;
//http://localhost:8080/auth/login
@PostMapping("/login")
public String login(@RequestBody LoginRequest request) 
{

User user = userRepository.findByUsername(request.getUsername());

if(user == null || !user.getPassword().equals(request.getPassword())) 
{
return "Invalid username or password";
}

return jwtUtil.generateToken(user.getUsername());

}
}