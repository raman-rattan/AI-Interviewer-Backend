package com.aiinterviewer.interviewSession.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String email;
    private String password;

    private String fullName;
    private int age;
    List<Session> interviewSessions;

    public User(){}

    public User(String fullName, String email, String password, int age, List<Session> interviewSessions) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.interviewSessions = interviewSessions;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Session> getInterviewSessions() {
        return interviewSessions;
    }

    public void setInterviewSessions(List<Session> interviewSessions) {
        this.interviewSessions = interviewSessions;
    }
}
