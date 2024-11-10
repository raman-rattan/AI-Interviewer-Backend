package com.aiinterviewer.interviewSession.service;

import com.aiinterviewer.interviewSession.domain.Session;
import com.aiinterviewer.interviewSession.domain.User;
import com.aiinterviewer.interviewSession.exceptions.SessionAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.SessionNotFound;
import com.aiinterviewer.interviewSession.exceptions.UserAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.UserNotFound;

import java.util.List;

public interface IUserInterviewService {

    User registerUser(User user) throws UserAlreadyPresent;

    User getUserDetails(String email) throws UserNotFound;

    User saveSession(String email, Session session) throws UserNotFound, SessionAlreadyPresent;

    List<Session> getAllSessions(String email) throws UserNotFound;

    Session getSingleSession(String email, String sessionId) throws UserNotFound, SessionNotFound;

    User deleteSession(String email, String sessionId) throws UserNotFound, SessionNotFound;

}
