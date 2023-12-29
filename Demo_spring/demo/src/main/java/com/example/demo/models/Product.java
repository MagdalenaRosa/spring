package com.example.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Product {
@Id
    private Integer id;
    private String name;
    private String desc;
    private String urlUri;
    private BigDecimal price;

    public Product(Integer id, String name, String desc, String urlUri, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.urlUri = urlUri;
        this.price = price;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlUri() {
        return urlUri;
    }

    public void setUrlUri(String urlUri) {
        this.urlUri = urlUri;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
