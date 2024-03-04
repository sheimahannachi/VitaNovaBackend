package com.example.vitanovabackend.Service;

import com.example.vitanovabackend.DAO.Entities.User;
import com.example.vitanovabackend.DAO.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User AddUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Implement other methods as required




    @Override
    public List<User> SearchByNameAndLastName(String name, String LastName){
        return userRepository.findAllByFirstNameAndLastName(name,LastName);
    }


    @Override
    public int ArchiveUser(Long Id) {
        User user = userRepository.findById(Id).get();
        if (user != null) {
            user.setArchive(true);
            userRepository.save(user);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<User> SearchByName(String name) {
        return null;
    }

    public List<User> SearchByLastName(String LastName){
        return userRepository.findAllByLastName(LastName);

    }

}
