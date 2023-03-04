package com.yomabank.profileservice.config;

import com.yomabank.profileservice.repository.ContactTypeRepo;
import com.yomabank.profileservice.repository.model.ContactTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase implements CommandLineRunner {

    private final ContactTypeRepo contactTypeRepository;

    @Autowired
    public LoadDatabase(ContactTypeRepo contactTypeRepository) {
        this.contactTypeRepository = contactTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (contactTypeRepository.getTotalCount() == 0) {
            contactTypeRepository.save(new ContactTypeEntity("EMAIL"));
            contactTypeRepository.save(new ContactTypeEntity("PHONE"));
        }
    }
}