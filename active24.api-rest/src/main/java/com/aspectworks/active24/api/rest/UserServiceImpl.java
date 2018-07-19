package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserVO user) {
       if(userRepository.findByUsername(user.getUsername())== null) {//check if user with same username already does not exist
           userRepository.save(user);
           System.out.println("Creating new user: " + user.getUsername());
       }
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
        System.out.println("Deleting user with username: " + username);
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userRepository.findAll();
    }
}
