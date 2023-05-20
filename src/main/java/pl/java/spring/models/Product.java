package pl.java.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

// na ekcje bazy min: @entity, @id
@Entity
public class Product {
    @Id // potrzebne do zdef klucza podst w baziwe
    @GeneratedValue(strategy = GenerationType.IDENTITY)//określa że istnieje unikalny klucz dla każdej tabeli
    private Integer id;
    private String name;
    //    @Column(length = 1023)// jak chcemy
    private String desc;
    private String imgUri;
    private BigDecimal price;

    public Product(String name, String desc, String imgUri, BigDecimal price) {

        this.name = name;
        this.desc = desc;
        this.imgUri = imgUri;
        this.price = price;
    }

    public Product() {

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

