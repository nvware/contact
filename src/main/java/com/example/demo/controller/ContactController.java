package com.example.demo.controller;


import com.example.demo.entity.Contact;
import com.example.demo.dao.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

// http://localhost:8080/api/v1.0/contacts
//    body : {"name": "name2 f2"}
//    Content-Type: application/json
//    Method: Post
    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Contact newContact = contactRepository.save(contact);
        return new ResponseEntity<>(newContact, headers, HttpStatus.CREATED);
    }

// http://localhost:8080/api/v1.0/contacts/1
//    body : {"name": "Hamid Valizadegan"}
//    Content-Type: application/json
//    Method: Put
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isEmpty()) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        Contact existingContact = optionalContact.get();
        existingContact.setName(contact.getName());

        Contact updatedContact = contactRepository.save(existingContact);
        return new ResponseEntity<>(updatedContact, headers, HttpStatus.OK);
    }

// http://localhost:8080/api/v1.0/contacts/1
//    body : {"name": "Hamid Valizadegan"}
//    Method: Delete
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isEmpty()) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        Contact contact = optionalContact.get();
        contactRepository.delete(contact);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
// http://localhost:8080/api/v1.0/contacts/1
//    Content-Type: application/json
//    Method: Get
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isEmpty()) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        Contact contact = optionalContact.get();
        return new ResponseEntity<>(contact, headers, HttpStatus.OK);
    }

//    http://localhost:8080/api/v1.0/contacts
//    Content-Type: text/csv
//    Method: Get
//    Body: id,name
            //1,name1 f1
            //2,name2 f2

    @GetMapping("/contacts")
    public ResponseEntity<byte[]> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        String csvContent = "id,name\n";
        for (Contact contact : contacts) {
            csvContent += contact.getId() + "," + contact.getName() + "\n";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/csv");
        headers.setContentDispositionFormData("attachment", "contacts.csv");
        return new ResponseEntity<>(csvContent.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
    }

//    @GetMapping("/contacts")
//    public ResponseEntity<List<Contact>> getAllContacts() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/csv");
//         try {
//             List<Contact> contacts = contactRepository.findAll();
//             return new ResponseEntity<>(contacts, headers, HttpStatus.OK);
//         }
//         catch (Exception e){
//             return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//         }
//    }

}