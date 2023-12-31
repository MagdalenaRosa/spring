package com.example.demo.models;

import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "nazwa nie moe być pusta")
    @Size(min = 2, max = 1024)
    private String name;
    @Column(length = 1024, name = "description")
    private String desc;
    @Column(length = 1024)
    @NotBlank(message = "Adres obrazka jest wymagany")
    private String urlUri;

    @NotNull(message = "Cena produktu jest wymagana")
    private BigDecimal price;

}
