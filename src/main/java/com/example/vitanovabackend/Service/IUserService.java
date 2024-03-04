package com.example.vitanovabackend.Service;

import com.example.vitanovabackend.DAO.Entities.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    List<User> SearchByName(String name);
    List<User> SearchByLastName(String LastName);

    List<User> SearchByNameAndLastName(String name,String LastName);
    User AddUser(User user);
    int ArchiveUser(Long Id);

    User updateUser(User user);

}