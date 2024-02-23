package com.example.vitanovabackend.AllServices;

import com.example.vitanovabackend.DAO.Entities.User;
import com.example.vitanovabackend.DAO.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements IUserService{
    UserRepository userRepository;


    @Override
    public User AddUser (User utilisateur){
        return userRepository.save(utilisateur);
    }


    @Override
    public int ArchiveUser(Long Id){
        User toDelete=userRepository.findById(Id).get();
        if(toDelete!=null){
            toDelete.setArchive(true);
            return 1;       }
        else return 0;


    }


    @Override
    public User updateUser(User utilisateur) {
        User existingUser = userRepository.findById(utilisateur.getIdUser()).get();

        if (existingUser != null) {
            existingUser=utilisateur;
            return userRepository.save(existingUser);
        }
        else return  null;
    }

    @Override
    public List<User> SearchByName(String name){
        return userRepository.findAllByFirstName(name);
    }

    @Override
    public List<User> SearchByNameAndLastName(String name,String LastName){
        return userRepository.findAllByFirstNameAndLastName(name,LastName);
    }
    public List<User> SearchByLastName(String LastName){
        return userRepository.findAllByLastName(LastName);

    }
}
