package com.yomabank.profileservice.repository;

import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaSpecificationExecutor<AddressEntity>, JpaRepository<AddressEntity,Long> {
    public Page<AddressEntity> findAllByUser(UserEntity user,Specification<AddressEntity> spec, Pageable pageable);
    public Page<AddressEntity> findAllByUser(UserEntity user,Pageable pageable);

}
