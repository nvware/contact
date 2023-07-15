package com.nvware.demo.repository;

import com.nvware.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByName(String phone);
    Contact findByPhone(String phone);

}
