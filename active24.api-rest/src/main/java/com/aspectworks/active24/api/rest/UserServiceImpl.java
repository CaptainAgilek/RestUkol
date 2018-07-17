package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {
    List<UserVO> users = new ArrayList<>();
    public UserServiceImpl() {
        UserVO userVO = new UserVO();
        userVO.setUsername("user1");
        userVO.setFirstName("Tomas");
        userVO.setSurname("Blabol");
        users.add(userVO);
        userVO = new UserVO();
        userVO.setUsername("user2");
        userVO.setFirstName("Martin");
        userVO.setSurname("Blabolek");
        users.add(userVO);
    }
    public void createUser(UserVO user) {
        users.add(user);
        System.out.println("Creating new user: " + user);
    }

    @Override
    public void deleteUser(String username) {
        for(Iterator<UserVO> userIterator = users.iterator(); userIterator.hasNext();)
        {
            UserVO user = userIterator.next();
            if(user.getUsername().equals(username))
            {
                userIterator.remove();
                System.out.println("Deleting user with username: " + username);
            }
        }
    }

    @Override
    public List<UserVO> getAllUsers() {
        return users;
    }
}
