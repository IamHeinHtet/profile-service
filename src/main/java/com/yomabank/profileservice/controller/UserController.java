package com.yomabank.profileservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomabank.profileservice.datatransferobject.UserCriteria;
import com.yomabank.profileservice.datatransferobject.global.ResponseBody;
import com.yomabank.profileservice.datatransferobject.global.ResponsePaginationBody;
import com.yomabank.profileservice.datatransferobject.mapper.Mapper;
import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import com.yomabank.profileservice.repository.model.UserEntity;
import com.yomabank.profileservice.service.AddressService;
import com.yomabank.profileservice.service.ContactInfoService;
import com.yomabank.profileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private Mapper mapper;
    private ObjectMapper objectMapper;
    private UserService userService;
    private AddressService addressService;
    private ContactInfoService contactInfoService;
    @Autowired
    public UserController(ContactInfoService contactInfoService, UserService userService, AddressService addressService, Mapper mapper, ObjectMapper objectMapper) {
        this.mapper = mapper;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
        this.contactInfoService = contactInfoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<ResponsePaginationBody<List<UserEntity>>> getUsers( @RequestParam Map<String,String> allParams ){
        UserCriteria userRequest = this.objectMapper.convertValue(allParams, UserCriteria.class);
        Page<UserEntity> userListPage = this.userService.findALlUserWithCriteria(userRequest);
        ResponsePaginationBody<List<UserEntity>> response = this.mapper.convertPageableToResponsePaginationBody(userListPage);
        response.setDetails(userListPage.getContent());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseBody<UserEntity>> saveUser(@RequestBody UserEntity user) throws JsonProcessingException {
        UserEntity createdUser = this.userService.createUser(user);
        return ResponseEntity.ok( new ResponseBody<UserEntity>(createdUser));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<UserEntity>> findUserById(@PathVariable("id") String id){
        UserEntity u = this.userService.findUserById(id);
        return ResponseEntity.ok(new ResponseBody<UserEntity>(u));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<UserEntity>> updateUserById(@PathVariable("id") String id,@RequestBody UserEntity user){
        this.userService.updateUser(user,Long.parseLong(id));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<UserEntity>> deleteUserById(@PathVariable("id") String id) {
        this.userService.deleteUserById(id);
        ResponseBody response = new ResponseBody<UserEntity>();
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/{id}/addresses")
    public ResponseEntity<ResponseBody<AddressEntity>> saveAddressToUser(@PathVariable("id") String userId,@RequestBody AddressEntity address){
        AddressEntity createdAddress = this.addressService.createAddress(userId,address);
        return ResponseEntity.ok( new ResponseBody<AddressEntity>(createdAddress));
    }
    @PostMapping(value = "/{id}/contacts")
    public ResponseEntity<ResponseBody<ContactInfoEntity>> saveContactByUser(@PathVariable("id") String userId, @RequestBody ContactInfoEntity contactInfoEntity){
        ContactInfoEntity contactInfo = this.contactInfoService.createContactInfo(userId,contactInfoEntity);
        return ResponseEntity.ok( new ResponseBody<ContactInfoEntity>(contactInfo));
    }

}
