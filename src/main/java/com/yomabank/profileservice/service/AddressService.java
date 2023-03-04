package com.yomabank.profileservice.service;

import com.yomabank.profileservice.exception.ResourceNotFoundException;
import com.yomabank.profileservice.repository.AddressRepo;
import com.yomabank.profileservice.repository.UserRepo;
import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface AddressService {

    public AddressEntity createAddress(String userId,AddressEntity address);
    public void deleteAddressById(String id);
    public AddressEntity updateAddressById(AddressEntity address,long id);

}