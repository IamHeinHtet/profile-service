package com.yomabank.profileservice.repository.specification;

import com.yomabank.profileservice.constant.ContactType;
import com.yomabank.profileservice.datatransferobject.UserCriteria;
import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import com.yomabank.profileservice.repository.model.ContactTypeEntity;
import com.yomabank.profileservice.repository.model.UserEntity;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<UserEntity> cq;
    Root<UserEntity> userEntityRoot;
    Join<AddressEntity, UserEntity> addressJoin;
    Join<ContactInfoEntity, UserEntity> contactInfoJoin;
    Join<ContactTypeEntity, ContactInfoEntity> contactTypeJoin;

    public UserSpecification(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
        this.cq = query;
        this.userEntityRoot = root;
    }

    public Predicate[] buildQuery(UserCriteria u) {
        return getPredicates(u);
    }

    public Predicate[] getPredicates(UserCriteria u) {
        List<Predicate> predicates = new ArrayList<>();

        if (u.getFirstName() != null && !u.getFirstName().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    userEntityRoot.get("firstName"), "%" + u.getFirstName() + "%"));
        }
        if (u.getLastName() != null && !u.getLastName().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    userEntityRoot.get("lastName"), "%" + u.getLastName() + "%"));
        }
        if (u.getNrc() != null && !u.getNrc().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    userEntityRoot.get("nrc"), "%" + u.getNrc() + "%"));
        }
        if (u.getPhone() != null && !u.getPhone().isEmpty()) {
            predicates.add(criteriaBuilder.like(getContactInfoJoin().get("contactValue"), "%" + u.getPhone() + "%"));
            predicates.add(criteriaBuilder.equal(getContactTypeJoin().get("id"), ContactType.PHONE.toString()));

        }
        if (u.getEmail() != null && !u.getEmail().isEmpty()) {
            predicates.add(criteriaBuilder.like(getContactInfoJoin().get("contactValue"), "%" + u.getEmail() + "%"));
            predicates.add(criteriaBuilder.equal(getContactTypeJoin().get("id"), ContactType.EMAIL.toString()));

        }
        if (u.getAddress() != null && !u.getAddress().isEmpty()) {
            predicates.add(criteriaBuilder.like(getAddressJoin().get("address"), "%" + u.getAddress() + "%"));
        }
        if (u.getTownshipOrCity() != null && !u.getTownshipOrCity().isEmpty()) {
            predicates.add(criteriaBuilder.like(getAddressJoin().get("townshipOrCity"), "%" + u.getTownshipOrCity() + "%"));
        }
        if (u.getDistrict() != null && !u.getDistrict().isEmpty()) {
            predicates.add(criteriaBuilder.like(getAddressJoin().get("district"), "%" + u.getDistrict() + "%"));
        }
        if (u.getState() != null && !u.getState().isEmpty()) {
            predicates.add(criteriaBuilder.like(getAddressJoin().get("state"), "%" + u.getState() + "%"));
        }
        if (u.getPostalCode() != null && !u.getPostalCode().isEmpty()) {
            predicates.add(criteriaBuilder.like(getAddressJoin().get("postalCode"), "%" + u.getPostalCode() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public Join<ContactInfoEntity, UserEntity> getContactInfoJoin() {
        if (contactInfoJoin == null) {
            contactInfoJoin = userEntityRoot.join("contactInfoList");
        }
        return contactInfoJoin;
    }

    public Join<ContactTypeEntity, ContactInfoEntity> getContactTypeJoin() {
        if (contactTypeJoin == null) {
            contactTypeJoin = getContactInfoJoin().join("contactType");
        }
        return contactTypeJoin;
    }

    public Join<AddressEntity, UserEntity> getAddressJoin() {
        if (addressJoin == null) {
            addressJoin = userEntityRoot.join("addressList");
        }
        return addressJoin;
    }
}
