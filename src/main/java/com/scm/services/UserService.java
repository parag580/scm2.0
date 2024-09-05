package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

//all standard methods for user business logic
public interface UserService {
   User saveUser(User user);
   Optional<User> getUserById(String id);//optional if a value is present it returns true else false benefit we dont need to apply if else additionaly
   Optional<User> updateUser(User user);
   void deleteUser(String id);
   boolean isUserExist(String userId);
   boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
