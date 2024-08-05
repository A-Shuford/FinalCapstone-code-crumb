package com.techelevator.dao;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User getUserByPhoneNumber (String phoneNumber);

    User createUser(RegisterUserDto user);
}
