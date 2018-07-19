package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserServiceInterface {
    void createUser(@RequestBody UserVO user);
    void deleteUser(@PathVariable("username") String username);
    List<UserVO> getAllUsers();
}
