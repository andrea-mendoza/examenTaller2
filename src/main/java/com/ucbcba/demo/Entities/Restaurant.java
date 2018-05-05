package com.ucbcba.demo.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer score;

    @NotNull
    private String name;

    @NotNull
    private String direction;

    @NotNull
    private String phone;

    @NotNull
    private String sucursales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSucursales() {
        return sucursales;
    }

    public void setSucursales(String sucursales) {
        this.sucursales = sucursales;
    }
}
