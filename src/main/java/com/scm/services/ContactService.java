package com.scm.services;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {
     //save contacts
     Contact save(Contact contact);
     //Update contact
     Contact update(Contact contact);

     //get contact
     List<Contact> getAll();

     //get contact by id
     Contact getById(String id);

     //delete contact
     void delete(String id);

     //searchcontact
     Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String order,User user);
     Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String order,User user);
     Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size,int page,String sortBy,String order,User user);
       //get contact by userid
       List<Contact> getByUserid(String userid);

       Page<Contact> getByUser(User user,int page,int size,String sortField,String direction);
}