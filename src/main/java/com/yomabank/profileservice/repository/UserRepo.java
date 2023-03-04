package com.yomabank.profileservice.repository;

import com.yomabank.profileservice.repository.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepo extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity,Long> {
    public Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);



}
