package com.yomabank.profileservice.service;

import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.ContactInfoRepo;
import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface ContactInfoService {

    public void deleteContactInfoById(String id);

    public ContactInfoEntity updateContactInfo(ContactInfoEntity contactInfoEntity,long id) ;
    public ContactInfoEntity createContactInfo(String userId, ContactInfoEntity contactInfoEntity) ;
}
