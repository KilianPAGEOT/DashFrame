package com.github.dashframe.service;

import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO repo;

    public void processOAuthPostLogin(String name, String username, String hashPassword, String oauthToken, boolean isAdmin) {
        User existUser = repo.findByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setUsername(username);
            newUser.setHashPassword(hashPassword);
            newUser.setToken(oauthToken);
            newUser.setAdmin(isAdmin);
            repo.save(newUser);
        }else {
            existUser.setToken(oauthToken);
            repo.save(existUser);
        }

    }

}
