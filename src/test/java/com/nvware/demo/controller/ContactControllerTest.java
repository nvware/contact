package com.nvware.demo.controller;

import com.nvware.demo.entity.Contact;
import com.nvware.demo.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testCRUDOperations() {

        // create a new contact
        Contact contact = new Contact("Jane Smith", "9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts", contact, Contact.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // get the created contact by ID
        Long id = response.getBody().getId();
        ResponseEntity<Contact> response2 = restTemplate.getForEntity("/contacts/" + id, Contact.class);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals("John Smith", response2.getBody().getName());

        // update the contact
        contact.setName("Jane Doe");
        HttpEntity<Contact> requestEntity = new HttpEntity<>(contact);
        ResponseEntity<Contact> response3 = restTemplate.exchange("/contacts/" + id, HttpMethod.PUT, requestEntity, Contact.class);
        assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals("Jane Doe", response3.getBody().getName());

        // get all contacts and check that the created contact is in the list
        ResponseEntity<String> response4 = restTemplate.getForEntity("/contacts", String.class);
        assertEquals(HttpStatus.OK, response4.getStatusCode());
        assertTrue(response4.getBody().contains("Jane Doe"));

        // delete the contact
        restTemplate.delete("/contacts/" + id);
        ResponseEntity<Contact> response5 = restTemplate.getForEntity("/contacts/" + id, Contact.class);
        assertEquals(HttpStatus.NOT_FOUND, response5.getStatusCode());


    }

    /**
     @Test
     public void createContact() throws Exception {
        // create a new contact
        Contact contact = new Contact("Jane Smith","9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts",contact,Contact.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
     }

     @Test
     public void updateContact() throws Exception {
        // create a new contact
        Contact contact = new Contact("Jane Smith","9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts",contact,Contact.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        // get the created contact by ID
        Long id = response.getBody().getId();
        ResponseEntity<Contact> response2 = restTemplate.getForEntity("/contacts/" + id,Contact.class);
        assertEquals(HttpStatus.OK,response2.getStatusCode());
        assertEquals("John Smith",response2.getBody().getName());

        // update the contact
        contact.setName("Jane Doe");
        HttpEntity<Contact> requestEntity = new HttpEntity<>(contact);
        ResponseEntity<Contact> response3 = restTemplate.exchange("/contacts/" + id,HttpMethod.PUT,requestEntity,Contact.class);
        assertEquals(HttpStatus.OK,response3.getStatusCode());
        assertEquals("Jane Doe",response3.getBody().getName());
     }

     @Test
     public void deleteContact() throws Exception {
        // create a new contact
        Contact contact = new Contact("Jane Smith","9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts",contact,Contact.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        // get the created contact by ID
        Long id = response.getBody().getId();
        ResponseEntity<Contact> response2 = restTemplate.getForEntity("/contacts/" + id,Contact.class);
        assertEquals(HttpStatus.OK,response2.getStatusCode());
        assertEquals("John Smith",response2.getBody().getName());

        // delete the contact
        restTemplate.delete("/contacts/" + id);
        ResponseEntity<Contact> response5 = restTemplate.getForEntity("/contacts/" + id,Contact.class);
        assertEquals(HttpStatus.NOT_FOUND,response5.getStatusCode());
     }

     @Test
     public void getContactById() throws Exception {
        // create a new contact
        Contact contact = new Contact("Jane Smith","9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts",contact,Contact.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        // get the created contact by ID
        Long id = response.getBody().getId();
        ResponseEntity<Contact> response2 = restTemplate.getForEntity("/contacts/" + id,Contact.class);
        assertEquals(HttpStatus.OK,response2.getStatusCode());
        assertEquals("John Smith",response2.getBody().getName());

     }


     @Test
     public void getAllContacts() throws Exception {
        // get all contacts and check that the created contact is in the list
        ResponseEntity<String> response4 = restTemplate.getForEntity("/contacts",String.class);
        assertEquals(HttpStatus.OK,response4.getStatusCode());
        assertTrue(response4.getBody().contains("Jane Doe"));
     }

     @Test
     public void getContactByPhone() throws Exception {
        // create a new contact
        Contact contact = new Contact("Jane Smith","9876543210");
        ResponseEntity<Contact> response = restTemplate.postForEntity("/contacts",contact,Contact.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        // get the created contact by ID
        String phone = response.getBody().getPhone();
        ResponseEntity<Contact> response2 = restTemplate.getForEntity("/contacts/" + phone,Contact.class);
        assertEquals(HttpStatus.OK,response2.getStatusCode());
        assertEquals("John Smith",response2.getBody().getName());
     }
      */

}

