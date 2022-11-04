package com.github.dashframe.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "widget_parameters")
public class WidgetParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false)
    @OneToOne
    private Widget widget;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false, name="`value`")
    private int value;

    @Column(nullable = false)
    private Date createdAt;

    public WidgetParameter() {

    }

    public WidgetParameter(Integer id, Widget widget, int position, int value) {
        this.id = id;
        this.widget = widget;
        this.position = position;
        this.value = value;
        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
