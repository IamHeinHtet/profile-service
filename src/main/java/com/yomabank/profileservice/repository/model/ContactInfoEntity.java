package com.yomabank.profileservice.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="contact_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="contact_value")
    private String contactValue;
    @JsonIgnoreProperties("contactInfoList")
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    @JsonIgnoreProperties("contactInfoList")
    @ManyToOne()
    @JoinColumn(name="contact_type_id")
    private ContactTypeEntity contactType;

}
