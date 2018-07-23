package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="/users" , description = "Operations about users")
@RequestMapping("/users")
public class UserController {

    /**
     * U fieldu temer vzdy pouzivat modifikator private, pokud k tomu neni duvod. Zde zadny neni. Interface nepojmenovavat
     * suffixem Interface, ale nedavat tam nic a tride davat suffix Impl.
     */
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Creates new user.")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserVO user){
        userService.createUser(user);
    }

    @ApiOperation(value = "Deletes specified user.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public void deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
    }

    @ApiOperation(value = "Returns all users.")
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserVO> getAllUsers() {
        return userService.getAllUsers();
    }
}
