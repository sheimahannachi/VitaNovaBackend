package com.example.vitanovabackend.AllServices;

import com.example.vitanovabackend.DAO.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> SearchByName(String name);
    List<User> SearchByLastName(String LastName);

    List<User> SearchByNameAndLastName(String name,String LastName);
    User AddUser(User user);
    int ArchiveUser(Long Id);

    User updateUser(User user);

}
