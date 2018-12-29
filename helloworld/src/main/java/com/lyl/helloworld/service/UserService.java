package com.lyl.helloworld.service;

import com.lyl.helloworld.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {


    User getUser(String id);


    void deleteUser(String id);
}



