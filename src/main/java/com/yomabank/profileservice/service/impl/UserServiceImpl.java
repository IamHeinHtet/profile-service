package com.yomabank.profileservice.service.impl;

import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.UserEntity;
import com.yomabank.profileservice.datatransferobject.UserCriteria;
import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.specification.UserSpecification;
import com.yomabank.profileservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import java.util.*;


@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public UserEntity createUser(UserEntity user) {
        user.getContactInfoList().forEach(contact -> {
            contact.setUser(user);
        });
        user.getAddressList().forEach(address -> {
            address.setUser(user);
        });
        return this.userRepo.save(user);

    }

    @Transactional
    public void deleteUserById(String id) {
        this.userRepo.deleteById(Long.parseLong(id));
    }

    @Transactional
    public UserEntity updateUser(UserEntity user, long id) {
        var userEntity = this.userRepo.findById(id);
        if (!userEntity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        var entity = userEntity.get();
        if (user.getFirstName() != null && !user.getFirstName().isEmpty())
            entity.setFirstName(user.getFirstName());
        if (user.getLastName() != null && !user.getLastName().isEmpty())
            entity.setLastName(user.getLastName());
        if (user.getNrc() != null && !user.getNrc().isEmpty()) {
            entity.setNrc(user.getNrc());
        }
        return this.userRepo.save(entity);
    }

    public Page<UserEntity> findALlUserWithCriteria(UserCriteria u) {
        Pageable pageable = PageRequest.of(u.getPage(), u.getSize());
        Specification<UserEntity> specification = (root, query, criteriaBuilder) -> {
            UserSpecification userSpecification  =  new UserSpecification(root, query, criteriaBuilder);
            Predicate predicate =  criteriaBuilder.and(userSpecification.buildQuery(u));
            query.distinct(true);
            return predicate;
        };
        return this.userRepo.findAll(specification, pageable);
    }

    public UserEntity findUserById(String id) {
        Optional<UserEntity> userEntity = this.userRepo.findById(Long.parseLong(id));
        if (!userEntity.isPresent()) throw new ResourceNotFoundException();
        return userEntity.get();
    }


}
