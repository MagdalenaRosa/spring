package com.example.demo.models;

public class ProductCategory {
    private Integer id;
    private String name;
    private String desc;
    private String imgUri;

    public ProductCategory(Integer id, String name, String desc, String imgUri) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgUri = imgUri;
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
}
