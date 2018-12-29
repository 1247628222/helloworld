package com.lyl.helloworld.service.impl;

import com.lyl.helloworld.entity.User;
import com.lyl.helloworld.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String id) {
        System.out.println(id+"进入实现类获取数据！");
        User user = new User();
        user.setId(id);
        user.setName("香菇");
        user.setAge(18);
        return user;
    }

    @Override
    public void deleteUser(String id) {
        System.out.println(id+"进入实现类删除数据！");
    }


}
