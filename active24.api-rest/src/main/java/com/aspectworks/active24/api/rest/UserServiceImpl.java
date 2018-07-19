package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    UserRepository ur;

    public void createUser(UserVO user) {
       if(ur.findByUsername(user.getUsername())== null) {//check if user with same username already does not exist
           ur.save(user);
           System.out.println("Creating new user: " + user.getUsername());
       }
    }

    @Override
    public void deleteUser(String username) {
        ur.delete(ur.findByUsername(username));
        System.out.println("Deleting user with username: " + username);

    }

    @Override
    public List<UserVO> getAllUsers() {
        return ur.findAll();
    }
}
