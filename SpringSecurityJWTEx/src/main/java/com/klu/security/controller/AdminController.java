package com.klu.security.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
//http://localhost:9078/admin/dashboard
@GetMapping("/dashboard")
public String dashboard()
{

return "Admin Dashboard API working";

}

}