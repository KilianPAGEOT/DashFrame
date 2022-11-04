package com.github.dashframe.models;

import javax.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String hashPassword;

    @Column(nullable = false, unique = true)
    private String oauthToken;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false)
    private Date createdAt;

    public User() {

    }

    public User(Integer id, String name, String email, String hashPassword, String oauthToken, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashPassword = new BCryptPasswordEncoder().encode(hashPassword);
        this.oauthToken = oauthToken;
        this.isAdmin = isAdmin;
        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = new BCryptPasswordEncoder().encode(hashPassword);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return oauthToken;
    }

    public void setToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }

}
