package com.yomabank.profileservice.service;

import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.UserEntity;
import com.yomabank.profileservice.datatransferobject.UserCriteria;
import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.specification.UserSpecification;
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


public interface UserService {

    public UserEntity createUser(UserEntity user) ;
    public void deleteUserById(String id) ;
    public UserEntity updateUser(UserEntity user, long id) ;

    public Page<UserEntity> findALlUserWithCriteria(UserCriteria u) ;

    public UserEntity findUserById(String id) ;

}
