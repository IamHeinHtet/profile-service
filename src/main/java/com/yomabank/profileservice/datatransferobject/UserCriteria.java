package com.yomabank.profileservice.datatransferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {
    private int page = 0;
    private int size = 10;
    private String firstName;
    private String lastName;
    private String nrc;
    private String phone;
    private String email;
    private String address;
    private String townshipOrCity;
    private String district;
    private String state;
    private String postalCode;

}
