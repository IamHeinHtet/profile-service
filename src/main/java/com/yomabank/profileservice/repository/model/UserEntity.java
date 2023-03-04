package com.yomabank.profileservice.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="nrc")
    private String nrc;
    @JsonIgnoreProperties("user")
    //@JsonIgnore
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContactInfoEntity> contactInfoList = new ArrayList<>();
    @JsonIgnoreProperties("user")
    //@JsonIgnore
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<AddressEntity> addressList= new ArrayList<>();

    public void setContactInfo(ContactInfoEntity contact) {
        if(contact != null) {
            contact.setUser(this);
            this.contactInfoList.add(contact);
        }
    }

    public void setAddress(AddressEntity address) {
        if(address != null) {
            address.setUser(this);
            this.addressList.add(address);
        }
    }
}
