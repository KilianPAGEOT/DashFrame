package com.github.dashframe.models;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.lang.NonNull;

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

    @Column
    private String type;

    @Column
    private int refreshRate;

    @OneToMany(mappedBy = "widget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name = "name")
    private Map<String, WidgetParameter> parameters;

    public Widget() {}

    public Widget(Service service, String name, int position, int column_pos, String type, int refreshRate) {
        this.service = service;
        this.name = name;
        this.position = position;
        this.column_pos = column_pos;
        this.createdAt = new Date();
        this.type = type;
        this.refreshRate = refreshRate;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setParameters(Map<String, WidgetParameter> parameters) {
        this.parameters = parameters;
    }

    public Map<String, WidgetParameter> getParameters() {
        return this.parameters;
    }

    @NonNull
    public String getParameter(String name, @NonNull String defaultValue) {
        var params = this.getParameters();
        if (params != null) {
            var param = params.get(name);
            if (param != null) {
                return param.getValue();
            }
        }
        return defaultValue;
    }

    public long getParameterAsLong(String name, long defaultValue) {
        var params = this.getParameters();
        if (params != null) {
            var param = params.get(name);
            if (param != null) {
                return param.getLongValue(defaultValue);
            }
        }
        return defaultValue;
    }

    @NonNull
    public WidgetParameter getParameterOrThrow(String name) {
        var params = this.getParameters();
        if (params != null) {
            var param = params.get(name);
            if (param != null) {
                return param;
            }
        }
        throw new IllegalStateException(
            "Missing parameter \"" + name + "\" for widget #" + this.getId() + " of type " + this.getType()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Widget other) {
            return Objects.equals(other.getId(), this.getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return (
            "Widget{id = " +
            this.getId() +
            ", name = " +
            this.getName() +
            ", type = " +
            this.getType() +
            ", refreshRate" +
            this.getRefreshRate() +
            '}'
        );
    }
}
