package com.example.vitanovabackend.Controllers;

import com.example.vitanovabackend.DAO.Entities.*;
import com.example.vitanovabackend.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
    IUserService iUserService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/AddUser")
    public User addUser(@RequestBody User user) {
        return iUserService.AddUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/admin/UpdateUser")
    public User updateUser(@RequestBody User user) {
        return iUserService.updateUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/admin/AllUsers")
    public List<User> GetUsers() {
        return iUserService.getAllUsers();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("admin/DeleteUser/{id}")
    public void DeleteUser(@PathVariable("id") long id){
        iUserService.ArchiveUser(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("admin/ActivateUser/{id}")
    public void ActivateUser(@PathVariable("id") long id){
        iUserService.ActivateUser(id);
    }


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }




}
