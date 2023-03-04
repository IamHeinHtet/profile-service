package com.yomabank.profileservice.repository;

import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfoEntity,Long> {
}
