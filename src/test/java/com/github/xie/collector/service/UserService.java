package com.github.xie.collector.service;

public interface UserService {
    String getUsername();

    int deleteUser(String operator, String username);
}
