package com.example.demo;

import com.example.demo.dao.ContactRepository;
import com.example.demo.entity.Contact;
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testCRUDOperations() {
        // create a new contact
        Contact contact = new Contact("John Smith");
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

}

