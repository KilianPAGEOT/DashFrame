package com.github.dashframe.rest;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.WidgetParameter;
import com.github.dashframe.models.json.CreateUserRequest;
import com.github.dashframe.models.json.UserInstance;
import com.github.dashframe.rest.api.UsersApi;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private WidgetParameterDAO widgetParameterDAO;

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

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<? extends Object> deleteUser(
        @Valid @PathVariable(required = false) Optional<Integer> userId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findByUsername(username);
        if (userId.isPresent() && user.isAdmin()) {
            user = userDAO.findById(userId.get().intValue());
        }
        if (user != null) {
            ArrayList<Service> services = serviceDAO.findByUser(user);
            if (services.size() != 0) {
                for (Service service : services) {
                    ArrayList<Widget> widgets = widgetDAO.findByService(service);
                    if (widgets.size() != 0) {
                        for (Widget widget : widgets) {
                            ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                            if (widgetParameters.size() != 0) {
                                for (WidgetParameter widgetParameter : widgetParameters) {
                                    widgetParameterDAO.delete(widgetParameter);
                                }
                            }
                            widgetDAO.delete(widget);
                        }
                    }
                    serviceDAO.delete(service);
                }
            }
            userDAO.delete(user);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.badRequest().body(null);
    }
}
