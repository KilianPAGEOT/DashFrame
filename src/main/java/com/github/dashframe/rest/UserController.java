package com.github.dashframe.rest;

import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import com.github.dashframe.models.json.CreateUserRequest;
import com.github.dashframe.models.json.UserInstance;
import com.github.dashframe.rest.api.UsersApi;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public ResponseEntity<UserInstance> createUser(
        @Valid @RequestBody(required = false) CreateUserRequest createUserRequest
    ) {
        User exitUser = userDAO.findByUsername(createUserRequest.getUsername());
        if (exitUser == null) {
            User user = userDAO.save(
                new User(
                    createUserRequest.getName(),
                    createUserRequest.getUsername(),
                    createUserRequest.getHashPassword(),
                    createUserRequest.getOauthToken(),
                    false
                )
            );
            return ResponseEntity.ok(new UserInstance().id(user.getId()).name(user.getName()).isAdmin(user.isAdmin()));
        } else return ResponseEntity.badRequest().body(null);
    }

    public void processOAuthPostLogin(
        String name,
        String username,
        String hashPassword,
        String oauthToken,
        boolean isAdmin
    ) {
        User existUser = userDAO.findByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setUsername(username);
            newUser.setHashPassword(hashPassword);
            newUser.setToken(oauthToken);
            newUser.setAdmin(isAdmin);
            userDAO.save(newUser);
        } else {
            existUser.setToken(oauthToken);
            userDAO.save(existUser);
        }
    }
}
