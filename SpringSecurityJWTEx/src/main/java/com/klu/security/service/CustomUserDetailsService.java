package com.klu.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klu.security.model.User;
import com.klu.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService 
{

@Autowired
private UserRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username)
throws UsernameNotFoundException 
{

User user = userRepository.findByUsername(username);

if(user == null)
throw new UsernameNotFoundException("User not found");

return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),List.of(() -> user.getRole()));

}

}