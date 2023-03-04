package com.yomabank.profileservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomabank.profileservice.datatransferobject.global.ResponseBody;
import com.yomabank.profileservice.datatransferobject.mapper.Mapper;
import com.yomabank.profileservice.repository.model.AddressEntity;
import com.yomabank.profileservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
    private Mapper mapper;
    private ObjectMapper objectMapper;
    private AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService, Mapper mapper, ObjectMapper objectMapper) {
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<AddressEntity>> deleteAddressById(@PathVariable("id") String id) {
        this.addressService.deleteAddressById(id);
        return ResponseEntity.ok(null);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<AddressEntity>> updateAddressById(@PathVariable("id") String id,@RequestBody AddressEntity address){
        AddressEntity address1  = this.addressService.updateAddressById(address,Long.parseLong(id));
        return ResponseEntity.ok( new ResponseBody<AddressEntity>(address1));
    }
}
