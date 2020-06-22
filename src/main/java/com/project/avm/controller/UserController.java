package com.project.avm.controller;

import com.project.avm.model.File;
import com.project.avm.model.User;
import com.project.avm.model.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User show(@PathVariable String id){
        int userid = Integer.parseInt(id);
        return userRepository.findById(userid).get();
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> showUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value="/user" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        return userRepository.save(new User(username, password , email));

    }
}
