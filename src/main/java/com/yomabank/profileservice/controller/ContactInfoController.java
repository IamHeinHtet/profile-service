package com.yomabank.profileservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomabank.profileservice.datatransferobject.global.ResponseBody;
import com.yomabank.profileservice.datatransferobject.mapper.Mapper;
import com.yomabank.profileservice.repository.model.ContactInfoEntity;
import com.yomabank.profileservice.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contacts")
public class ContactInfoController {
    private Mapper mapper;
    private ObjectMapper objectMapper;
    private ContactInfoService contactInfoService;
    @Autowired
    public ContactInfoController(ContactInfoService contactInfoService, Mapper mapper, ObjectMapper objectMapper) {
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.contactInfoService = contactInfoService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<ContactInfoEntity>> deleteContactById(@PathVariable("id") String id) {
        this.contactInfoService.deleteContactInfoById(id);
        return ResponseEntity.ok(null);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseBody<ContactInfoEntity>> updateContactById(@PathVariable("id") String id,@RequestBody ContactInfoEntity contactInfoEntity){
        ContactInfoEntity contactInfoEntity1  = this.contactInfoService.updateContactInfo(contactInfoEntity,Long.parseLong(id));
        return ResponseEntity.ok( new ResponseBody<ContactInfoEntity>(contactInfoEntity1));
    }
}
