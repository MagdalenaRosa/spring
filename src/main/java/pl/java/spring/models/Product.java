package pl.java.spring.models;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String name;
    private String desc;
    private String imgUri;
    private BigDecimal price;

    public Product(Integer id, String name, String desc, String imgUri, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgUri = imgUri;
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

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

