package com.github.dashframe.models;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity(name = "widget_parameters")
public class WidgetParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "`value`")
    private String value;

    @Column(nullable = false)
    private Date createdAt;

    public WidgetParameter() {}

    public WidgetParameter(Widget widget, String name, String value) {
        this.widget = widget;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public long getLongValue(long defaultValue) {
        try {
            return Long.parseLong(this.getValue());
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public void setValue(String value) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WidgetParameter other) {
            return Objects.equals(other.getId(), this.getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
