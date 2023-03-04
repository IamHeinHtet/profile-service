package com.yomabank.profileservice.service.impl;

import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.ContactInfoRepo;
import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import com.yomabank.profileservice.service.ContactInfoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoServiceImpl.class);
    private ContactInfoRepo contactInfoRepo;
    private UserRepo userRepo;
    private ModelMapper modelMapper;
    @Autowired
    public ContactInfoServiceImpl(ContactInfoRepo contactInfoRepo,ModelMapper modelMapper,UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.contactInfoRepo = contactInfoRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void deleteContactInfoById(String id) {
        this.contactInfoRepo.deleteById(Long.parseLong(id));
    }

    @Transactional
    public ContactInfoEntity updateContactInfo(ContactInfoEntity contactInfoEntity,long id) {
        Optional<ContactInfoEntity> oldContactInfo = this.contactInfoRepo.findById(id);
        if(!oldContactInfo.isPresent()) {
            throw new ResourceNotFoundException();
        }
        this.modelMapper.map(contactInfoEntity,oldContactInfo);
        return this.contactInfoRepo.save(contactInfoEntity);
    }

    @Transactional
    public ContactInfoEntity createContactInfo(String userId, ContactInfoEntity contactInfoEntity) {
        Optional<UserEntity> optionalUser = this.userRepo.findById(Long.parseLong(userId));
        if(!optionalUser.isPresent()) throw new ResourceNotFoundException();
        contactInfoEntity.setUser(optionalUser.get());
        return this.contactInfoRepo.save(contactInfoEntity);
    }
}
