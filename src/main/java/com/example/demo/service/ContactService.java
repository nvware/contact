package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:nvware">Hamid Valizadegan</a>
 * Modified by <a href="mailto:nvware">Hamid Valizadegan</a>
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> listContacts() {
        return contactRepository.findAll();
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact readContactByName(String contactName) {
        return contactRepository.findByName(contactName);
    }

    public Contact readContactByPhone(String phone) {
        return contactRepository.findByPhone(phone);
    }

    public Optional<Contact> readContact(Long contactId) {
        return contactRepository.findById(contactId);
    }

    public Contact updateContact(Contact newContact) {
        return contactRepository.save(newContact);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }
}
