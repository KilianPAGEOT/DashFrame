package com.github.dashframe.rest;

import com.github.dashframe.config.MyUserDetailsService;
import com.github.dashframe.config.TokenService;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import com.github.dashframe.models.json.CreateUserRequest;
import com.github.dashframe.models.json.UserInstance;
import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final UserController userController;

    // Fields and methods for e-mail sending
    private Cookie cookie;

    @Autowired
    public SimpleMailMessage template;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserDAO userDAO;

    private static final String pathEmailVerifier = "/email-verifier";

    private static String generateToken() {
        final SecureRandom secureRandom = new SecureRandom();
        final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (template.getFrom() != null) {
            message.setFrom(template.getFrom());
        }
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public AuthenticationController(
        TokenService tokenService,
        OAuth2AuthorizedClientService authorizedClientService,
        UserController userController
    ) {
        this.tokenService = tokenService;
        this.authorizedClientService = authorizedClientService;
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = { "text/plain", "application/json" })
    public String login(Authentication authentication) {
        if (this.userDetailsService.loadUserByUsername(authentication.getName()) == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }
        return this.tokenService.generateToken(authentication);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/register",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public ResponseEntity<UserInstance> register(
        @Valid @RequestBody(required = false) CreateUserRequest createUserRequest
    ) {
        if (createUserRequest == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = this.userDAO.findByUsername(createUserRequest.getUsername());
        if (user == null) {
            user =
                this.userDAO.save(
                        new User(
                            createUserRequest.getName(),
                            createUserRequest.getUsername(),
                            createUserRequest.getHashPassword(),
                            null,
                            false
                        )
                    );

            String emailVerificationToken = generateToken();

            user.setEmailVerificationToken(emailVerificationToken);
            userDAO.save(user);

            String emailContent = String.format(
                Objects.requireNonNull(template.getText()),
                user.getUsername(),
                pathEmailVerifier + "/" + emailVerificationToken
            );
            sendSimpleMessage(user.getUsername(), "Dashframe - Verify your e-mail address", emailContent);
            return ResponseEntity.ok(new UserInstance().id(user.getId()).name(user.getName()).isAdmin(user.isAdmin()));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = pathEmailVerifier + "/{emailVerificationToken}")
    public ResponseEntity<String> verifyEmail(
        @PathVariable String emailVerificationToken,
        HttpServletResponse response
    ) throws IOException {
        User user = this.userDAO.findByEmailVerificationToken(emailVerificationToken);
        if (user != null) {
            user.setEmailVerificationToken(null);
            this.userDAO.save(user);

            response.sendRedirect("http://localhost:5173");
            return ResponseEntity.ok("The user with the e-mail address " + user.getUsername() + " is now verified.");
        } else {
            return ResponseEntity.badRequest().body("The token does not match any user.");
        }
    }

    @GetMapping("/users-oauth2")
    public void getUserInfo(Principal user, HttpServletResponse response) throws IOException {
        if (user instanceof OAuth2AuthenticationToken) {
            getOauth2LoginInfo(user);
        }
        cookie = new Cookie("token", "OAuth2");
        cookie.setPath("/");
        cookie.setMaxAge(5 * 60 * 60);
        response.addCookie(this.cookie); //add cookie and redirect to fronted client
        response.sendRedirect("http://localhost:5173");
    }

    private void getOauth2LoginInfo(Principal user) { // function if user login with OAuth2
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient =
            this.authorizedClientService.loadAuthorizedClient(
                    authToken.getAuthorizedClientRegistrationId(),
                    authToken.getName()
                );

        if (authToken.isAuthenticated()) {
            Map<String, Object> userAttributes = authToken.getPrincipal().getAttributes();

            String userToken = authClient.getAccessToken().getTokenValue();

            this.userController.processOAuthPostLogin(
                    userAttributes.get("name") == null
                        ? (String) userAttributes.get("login")
                        : (String) userAttributes.get("name"),
                    userAttributes.get("sub") == null
                        ? userAttributes.get("id").toString()
                        : userAttributes.get("sub").toString(),
                    null,
                    userToken,
                    false
                ); // create user in db with OAuth2 information
        }
    }
}
