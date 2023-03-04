package com.yomabank.profileservice.repository.model;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="contact_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactTypeEntity implements Serializable {
    @Id
    @Column(name="id")
    private String id;

    @JsonIgnoreProperties("contactType")
    @OneToMany(mappedBy ="contactType",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ContactInfoEntity> contactInfoList;

    public ContactTypeEntity(String id) {
        this.id = id;
    }
}
