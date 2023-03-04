package com.yomabank.profileservice.service.impl;

import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.AddressRepo;
import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import com.yomabank.profileservice.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
    private AddressRepo addressRepo;
    private UserRepo userRepo;

    @Autowired
    public AddressServiceImpl(AddressRepo addressRepo, UserRepo userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }
    @Transactional
    public AddressEntity createAddress(String userId,AddressEntity address) {
        Optional<UserEntity> optionalUser = this.userRepo.findById(Long.parseLong(userId));
        if(!optionalUser.isPresent()) throw new ResourceNotFoundException();
        address.setUser(optionalUser.get());
        return this.addressRepo.save(address);
    }
    @Transactional
    public void deleteAddressById(String id) {
        this.addressRepo.deleteById(Long.parseLong(id));
    }
    @Transactional
    public AddressEntity updateAddressById(AddressEntity address,long id) {
        var addressEntity = this.addressRepo.findById(id);
        if(!addressEntity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        var entity = addressEntity.get();
        if(address.getAddress()!=null && !address.getAddress().isEmpty())
            entity.setAddress(address.getAddress());
        if(address.getTownshipOrCity()!=null && !address.getTownshipOrCity().isEmpty())
            entity.setTownshipOrCity(address.getTownshipOrCity());
        if(address.getDistrict()!=null && !address.getDistrict().isEmpty()) {
            entity.setDistrict( address.getDistrict());
        }
        if(address.getState()!=null && !address.getState().isEmpty()) {
            entity.setState( address.getState());
        }
        if(address.getPostalCode()!=null && !address.getPostalCode().isEmpty()) {
            entity.setPostalCode( address.getPostalCode());
        }
        return this.addressRepo.save(entity);
    }

}