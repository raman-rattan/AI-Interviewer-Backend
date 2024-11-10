package com.aiinterviewer.interviewSession.service;

import com.aiinterviewer.interviewSession.domain.Session;
import com.aiinterviewer.interviewSession.domain.User;
import com.aiinterviewer.interviewSession.exceptions.SessionAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.SessionNotFound;
import com.aiinterviewer.interviewSession.exceptions.UserAlreadyPresent;
import com.aiinterviewer.interviewSession.exceptions.UserNotFound;
import com.aiinterviewer.interviewSession.proxy.UserProxy;
import com.aiinterviewer.interviewSession.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserInterviewService implements IUserInterviewService{
    IUserRepository userRepository;
    UserProxy userProxy;

    @Autowired
    public UserInterviewService(IUserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyPresent{
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyPresent();
        }
        else{
            userProxy.saveUser(user);
            return userRepository.save(user);
        }
    }

    @Override
    public User getUserDetails(String email) throws UserNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        else{
            return userRepository.findById(email).get();
        }
    }

    @Override
    public User saveSession(String email, Session session)  throws UserNotFound, SessionAlreadyPresent {

        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }

        User user = userRepository.findById(email).get();

        if( user.getInterviewSessions() == null){
            List<Session> list = new ArrayList<>();
            list.add(session);
            user.setInterviewSessions(list);
            return userRepository.save(user);
        }

        List<Session> sessionList = user.getInterviewSessions();

        for(Session session1 : sessionList){
            if(session1.getSessionId() == session.getSessionId()){
               throw new SessionAlreadyPresent();
            }
        }

        sessionList.add(session);
        user.setInterviewSessions(sessionList);
        return userRepository.save(user);

    }

    @Override
    public List<Session> getAllSessions(String email) throws UserNotFound{
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }

        List<Session> sessionList = userRepository.findById(email).get().getInterviewSessions();

        return sessionList;
    }

    @Override
    public Session getSingleSession(String email, String sessionId) throws UserNotFound, SessionNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }

        List<Session> sessionList = userRepository.findById(email).get().getInterviewSessions();

        for(Session session : sessionList){
            if (session.getSessionId().equals(sessionId)){
                return session;
            }
        }

        throw new SessionNotFound();
    }

    @Override
    public User deleteSession(String email, String sessionId) throws UserNotFound, SessionNotFound{
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }

        List<Session> sessionList = userRepository.findById(email).get().getInterviewSessions();

        boolean flag = true;

        Iterator<Session> iterator = sessionList.iterator();

        while (iterator.hasNext()){
            if (iterator.next().getSessionId().equals(sessionId)){
                iterator.remove();
                flag = false;
            }
        }

        if (flag){
            throw new SessionNotFound();
        }
        else{
            User user = userRepository.findById(email).get();
            user.setInterviewSessions(sessionList);
            return userRepository.save(user);
        }

    }
}
