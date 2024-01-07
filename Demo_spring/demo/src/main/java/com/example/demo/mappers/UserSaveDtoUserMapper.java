
package com.example.demo.mappers;

import java.util.List;

import com.example.demo.models.Address;
import com.example.demo.models.PhoneNumber;
import com.example.demo.models.User;
import com.example.demo.models.UserDetails;
import com.example.demo.models.dto.UserSaveDto;

public class UserSaveDtoUserMapper {

        public static User fromDtoToEntity(UserSaveDto userSaveDto) {
                var details = UserDetails.builder()
                                .taxNumber(userSaveDto.getTaxNumber())
                                .personalNumber(userSaveDto.getPersonalNumber())
                                .build();
                var phones = List.of(
                                PhoneNumber.builder()
                                                .prefix(userSaveDto.getPrefix())
                                                .number(userSaveDto.getNumber())
                                                .build());
                var addresses = List.of(
                                Address.builder()
                                                .addressId(userSaveDto.getAddress())
                                                .build());
                return User.builder()
                                .firstName(userSaveDto.getFirstName())
                                .lastName(userSaveDto.getLastName())
                                .email(userSaveDto.getEmail())
                                .password("secretPass")
                                .details(details)
                                .phoneNumber(phones)
                                .address(addresses)
                                .build();
        }
}
