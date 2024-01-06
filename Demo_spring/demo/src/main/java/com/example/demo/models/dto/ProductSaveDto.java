package com.example.demo.models.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ProductSaveDto {
    private Integer id;

    @NotBlank(message = "Nazwa produktu nie może być pusta")
    @Size(min = 5, max = 255, message = "Nazwa produktu musi być od 5 do 255 znaków")
    private String name;

    @NotBlank(message = "Opis produktu nie może być pusty")
    @Size(min = 10, max = 1023, message = "Opis produktu musi być od 10 do 1023 znaków")
    private String desc;

    @NotBlank(message = "Link do obrazka produktu nie może być pusty")
    @Size(min = 5, max = 255, message = "Link do obrazka produktu musi być od 5 do 255 znaków")
    private String imgUri;

    @NotNull(message = "Cena jest wymagana")
    private BigDecimal price;
    private String ingredients;

    private Integer categoryId;

}
