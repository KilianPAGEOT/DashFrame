package com.github.dashframe.service;

import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO repo;

    public void processOAuthPostLogin(String name, String email, String hashPassword, String oauthToken, boolean isAdmin) {
        User existUser = repo.findByEmail(email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setHashPassword(hashPassword);
            newUser.setToken(oauthToken);
            newUser.setAdmin(isAdmin);
            repo.save(newUser);
        }

    }

}
