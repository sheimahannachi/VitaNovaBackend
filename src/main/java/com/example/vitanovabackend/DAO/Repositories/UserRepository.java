package com.example.vitanovabackend.DAO.Repositories;

import com.example.vitanovabackend.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAllByFirstName(String firstName);
    public List<User> findAllByFirstNameAndLastName(String firstName, String lastName);
    public List<User> findAllByLastName(String lastName);

    public List<User> findByEmail(String email);
}