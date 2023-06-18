package com.example.demo.init;

/**
 * @author <a href="mailto:nvware">Hamid Valizadegan</a>
 * Modified by <a href="mailto:nvware">Hamid Valizadegan</a>
 */

import com.example.demo.dao.ContactRepository;
import com.example.demo.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DefaultDataInitializer implements CommandLineRunner {

    private final ContactRepository contactRepository;

    @Autowired
    public DefaultDataInitializer(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) {
        List<Contact> defaultContacts = Arrays.asList(
                new Contact("John Doe"),
                new Contact("Jane Smith"),
                new Contact("Michael Johnson"),
                new Contact("Emily Davis"),
                new Contact("Robert Wilson"),
                new Contact("Samantha Thompson"),
                new Contact("David Anderson"),
                new Contact("Olivia Lee"),
                new Contact("Ethan Martinez"),
                new Contact("Sophia Taylor")
        );

        contactRepository.saveAll(defaultContacts);
    }
}
