package com.example.demo.models.dto;

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
    private String taxNumber;
    private String prefix;
    private String number;
    private Integer address;
}
