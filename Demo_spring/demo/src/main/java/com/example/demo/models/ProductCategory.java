package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // to hibernate -> do baz
@NoArgsConstructor
@AllArgsConstructor // DependencyInjection
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nazwa kategorii nie może być pusta")
    @Size(min = 2, max = 1024, message = "Nazwa kategorii powinna mięścić się w dziedziale od 2-1024 znaków")
    private String name;
    @NotBlank(message = "Opis kategorii nie może być pusty")
    @Column(columnDefinition = "Text", name = "description")
    private String desc;
    @Column(length = 1024)
    @NotBlank(message = "Adres obrazka jest wymagany")
    private String imgUri;

}
