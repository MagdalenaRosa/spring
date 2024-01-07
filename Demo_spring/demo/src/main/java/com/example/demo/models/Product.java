package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity // potrzebne dla Hibernate aby odbić model jako tabela w bazie
public class Product {

    @Id // potrzebne do zdefiniowanie klucza podst w basie
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // hibernate generuje klucze, strategia określa że istnieje unikalny klucz per
    // tabela
    private Integer id;

    @NotBlank(message = "Nazwa produktu nie może być pusta")
    @Size(min = 5, max = 255, message = "Nazwa produktu musi być od 5 do 255 znaków")
    // @Column(unique = true)
    private String name; // domyślnie 255 znaków

    @NotBlank(message = "Opis produktu nie może być pusty")
    @Size(min = 10, max = 1023, message = "Opis produktu musi być od 10 do 1023 znaków")
    @Column(length = 1023, name = "description") // pole w bazie varchar 1023 znaki
    // @Column(columnDefinition = "TEXT") // typ text mysql
    private String desc;

    @NotBlank(message = "Link do obrazka produktu nie może być pusty")
    @Size(min = 5, max = 255, message = "Link do obrazka produktu musi być od 5 do 255 znaków")
    private String imgUri; // domyślnie 255 znaków

    @NotNull(message = "Cena jest wymagana")
    private BigDecimal price;

    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

}
