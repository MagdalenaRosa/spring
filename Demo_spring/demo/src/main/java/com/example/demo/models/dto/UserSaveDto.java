package com.example.demo.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSaveDto {
    private String firstName;
    private String lastName;
    private String email;
    private String personalNumber;
    private String password;
    private String taxNumber;
    private List<String> prefix;
    private List<String> number;
    private Integer address; // bo przechwywujemy id
}
