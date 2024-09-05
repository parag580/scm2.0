package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

//repositories used to interaction with databases
// they contain all method which interact with database
@Repository
public interface UserRepo extends JpaRepository<User,String>{
      //extra methods db related operations
      //custom query methods
      //custom finder methods

      
      //findBypattern and email is the feild name inbelow method
      Optional<User> findByEmail(String email);
      Optional<User> findByEmailAndPassword(String email,String password);

      
}
