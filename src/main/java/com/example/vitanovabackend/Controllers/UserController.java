package com.example.vitanovabackend.Controllers;

import com.example.vitanovabackend.DAO.Entities.*;
import com.example.vitanovabackend.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
    IUserService iUserService;
    @PostMapping("/AddUser")
    public User addUser(@RequestBody User user) {
        return iUserService.AddUser(user);
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
