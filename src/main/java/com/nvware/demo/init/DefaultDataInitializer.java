package com.nvware.demo.init;

/**
 * @author <a href="mailto:nvware">Hamid Valizadegan</a>
 * Modified by <a href="mailto:nvware">Hamid Valizadegan</a>
 */

import com.nvware.demo.entity.Contact;
import com.nvware.demo.repository.ContactRepository;
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
                new Contact("John Doe", "1234567890"),
                new Contact("Jane Smith", "9876543210"),
                new Contact("Michael Johnson", "4567890123"),
                new Contact("Emily Davis", "0123456789"),
                new Contact("David Wilson", "5678901234"),
                new Contact("Sarah Anderson", "8901234567"),
                new Contact("Christopher Lee", "2345678901"),
                new Contact("Jennifer Thomas", "7890123456"),
                new Contact("Matthew Martinez", "3456789012"),
                new Contact("Olivia Brown", "9012345678")
        );

        contactRepository.saveAll(defaultContacts);
    }
}
