package com.example.demo_blog_api.service;

import com.example.demo_blog_api.domain.User;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken (User user);
}
