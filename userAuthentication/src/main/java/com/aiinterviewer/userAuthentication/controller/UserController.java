package com.aiinterviewer.userAuthentication.controller;

import com.aiinterviewer.userAuthentication.domain.User;
import com.aiinterviewer.userAuthentication.exceptions.UserAlreadyPresent;
import com.aiinterviewer.userAuthentication.exceptions.UserNotFound;
import com.aiinterviewer.userAuthentication.security.ITokenGenerator;
import com.aiinterviewer.userAuthentication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {
    IUserService userService;
    ITokenGenerator tokenGenerator;

    @Autowired
    public UserController(IUserService userService, ITokenGenerator tokenGenerator) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyPresent {
        try {
            User newUser = userService.register(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(UserAlreadyPresent e){
            throw e;
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> findUserByEmailAndPass(@RequestBody User user) throws UserNotFound {
        try{
            User user1 = userService.findUserByEmailAndPass(user.getEmail(), user.getPassword());

            Map<String, String> token = tokenGenerator.generateToken(user1);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
