package com.github.dashframe.models;

import com.github.dashframe.models.json.ServiceType;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false)
    @OneToOne
    private User user;

    @Column
    private ServiceType type;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String token;

    @Column(nullable = true)
    private Date expirationDate;

    @Column(nullable = false)
    private Date createdAt;

    public Service() {}

    public Service(User user, ServiceType type, String token, String username) {
        this.user = user;
        this.type = type;
        this.token = token;
        this.username = username;
        this.expirationDate = null;
        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType name) {
        this.type = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }
}
