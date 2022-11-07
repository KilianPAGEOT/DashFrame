package com.github.dashframe.rest;

import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        userDAO.save(user);
        return user;
    }
}
