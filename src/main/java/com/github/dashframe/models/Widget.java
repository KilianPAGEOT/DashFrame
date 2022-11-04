package com.github.dashframe.models;

import java.util.Date;
import javax.persistence.*;

@Entity(name = "widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false)
    @OneToOne
    private Service service;

    @Column
    private String name;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false)
    private int column_pos;

    @Column(nullable = false)
    private Date createdAt;

    public Widget() {}

    public Widget(Integer id, Service service, String name, int position, int column_pos) {
        this.id = id;
        this.service = service;
        this.name = name;
        this.position = position;
        this.column_pos = column_pos;
        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getColumn_pos() {
        return column_pos;
    }

    public void setColumn_pos(int column_pos) {
        this.column_pos = column_pos;
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
