package com.example.demo.models;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory category;

    // kilka produktów do róznych skladników
    @ManyToMany
    @JoinTable(name = "pivot product ingredient")
    private List<Ingredients> ingredients;

}
