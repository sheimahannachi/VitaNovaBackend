package com.example.vitanovabackend.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.vitanovabackend.DAO.Entities.ERole;
import com.example.vitanovabackend.DAO.Entities.User;
import com.example.vitanovabackend.DAO.Repositories.UserRepository;
import com.example.vitanovabackend.Payload.Request.LoginRequest;
import com.example.vitanovabackend.Payload.Request.SignupRequest;
import com.example.vitanovabackend.Payload.Response.MessageResponse;
import com.example.vitanovabackend.Payload.Response.UserInfoResponse;
import com.example.vitanovabackend.Security.Jwt.JwtUtils;
import com.example.vitanovabackend.Security.services.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;



    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("login controller : ");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println(jwtCookie);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:4200"); // Replace with the appropriate origin
        System.out.println(headers);

        // Return the ResponseEntity with the HttpHeaders object containing the CORS header
        return ResponseEntity.ok().headers(headers).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();
        System.out.println("strroles " +strRoles);
        if(strRoles.equals(ERole.ADMIN.toString()))user.setRole(ERole.ADMIN);
        else if(strRoles.equals(ERole.USER.toString()))user.setRole(ERole.USER);
        System.out.println(strRoles.equals(ERole.ADMIN.toString()));
        System.out.println();
        System.out.println("userRole : " + user.getRole());
        System.out.println("roleadmin " + ERole.ADMIN.toString());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

        // Set the expiration date of the cookie to a past time to delete it
        cookie = ResponseCookie.from(cookie.getName(), cookie.getValue())
                .path(cookie.getPath())
                .maxAge(0)  // Set maxAge to 0 to delete the cookie
                .httpOnly(true)
                .build();

        // Add the cookie to the response with maxAge=0 to delete it
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().body(new MessageResponse("You've been signed out!"));
    }
}