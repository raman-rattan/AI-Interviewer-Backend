package com.aiinterviewer.interviewSession.controller;

import com.aiinterviewer.interviewSession.domain.Session;
import com.aiinterviewer.interviewSession.domain.User;
import com.aiinterviewer.interviewSession.exceptions.SessionAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.SessionNotFound;
import com.aiinterviewer.interviewSession.exceptions.UserAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.UserNotFound;
import com.aiinterviewer.interviewSession.service.UserInterviewService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2")
public class UserInterviewController {

    UserInterviewService userInterviewService;

    @Autowired
    public UserInterviewController(UserInterviewService userInterviewService) {
        this.userInterviewService = userInterviewService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyPresent {
        try{
            User newUser = userInterviewService.registerUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch (UserAlreadyPresent e){
            throw  e;
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/interview/userDetails")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request) throws UserNotFound {
        String email = getEmailFromClaims(request);
        User user = userInterviewService.getUserDetails(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/interview/saveSession")
    public ResponseEntity<?> saveSessionToUserList(HttpServletRequest request, @RequestBody Session session) throws UserNotFound, SessionAlreadyPresent {
        String email = getEmailFromClaims(request);

        User user = userInterviewService.saveSession(email, session);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/interview/sessions")
    public ResponseEntity<?> getAllSession(HttpServletRequest request) throws UserNotFound {
        String email = getEmailFromClaims(request);

        List<Session> sessionList = userInterviewService.getAllSessions(email);
        return new ResponseEntity<>(sessionList, HttpStatus.OK);
    }

    @GetMapping("/interview/session/{sessionId}")
    public ResponseEntity<?> getOneSession(HttpServletRequest request, @PathVariable String sessionId) throws UserNotFound, SessionNotFound {
        String email = getEmailFromClaims(request);

        Session session = userInterviewService.getSingleSession(email, sessionId);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @DeleteMapping("/interview/deleteSession/{id}")
    public ResponseEntity<?> deleteSession(HttpServletRequest request, @PathVariable String id) throws UserNotFound, SessionNotFound {
        String email = getEmailFromClaims(request);

        User user = userInterviewService.deleteSession(email, id);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    public String getEmailFromClaims(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String email = (String) claims.get("email");
        System.out.println(email);
        return email;
    }
}
