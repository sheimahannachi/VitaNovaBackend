package com.example.vitanovabackend.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Table(name = "User")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(name = "username")
    private String username;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "weight")
    private float weight;
    @Column(name = "height")
    private float height;
    @Column(name = "password")
    private String password;
    @Column(name = "archive")
    private Boolean archive=false;
    @Column(name = "picture")
    private String picture;
    @OneToOne
    PersonalGoals personalGoals;

    @OneToOne(cascade = CascadeType.ALL)
    PeriodTracker periodTracker;

    @OneToOne(cascade = CascadeType.ALL)
    Cart cart;

    @ManyToMany
    List<Food>foods=new ArrayList<>();

    @ManyToMany( mappedBy = "membres",cascade = CascadeType.ALL)
    List<Community> communities = new ArrayList<>();




    @Column(name = "role")
   @Enumerated(EnumType.STRING)
   private ERole role ;



    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}