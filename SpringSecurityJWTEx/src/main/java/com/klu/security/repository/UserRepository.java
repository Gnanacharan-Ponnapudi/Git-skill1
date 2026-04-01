package com.klu.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.security.model.User;

public interface UserRepository extends JpaRepository<User,Long> 
{

User findByUsername(String username);

}