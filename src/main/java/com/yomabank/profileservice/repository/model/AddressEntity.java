package com.yomabank.profileservice.repository.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="address")
    private String address;

    @Column(name="township_or_city")
    private String townshipOrCity;

    @Column(name="district")
    private String district;

    @Column(name="state")
    private String state;

    @Column(name="postal_code")
    private String postalCode;
    @JsonIgnoreProperties("addressList")
    @ManyToOne()
    @JoinColumn(name="user_id")
    private UserEntity user;

}
