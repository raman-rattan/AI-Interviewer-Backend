package com.aiinterviewer.userAuthentication.service;

import com.aiinterviewer.userAuthentication.domain.User;
import com.aiinterviewer.userAuthentication.exceptions.UserAlreadyPresent;
import com.aiinterviewer.userAuthentication.exceptions.UserNotFound;
import com.aiinterviewer.userAuthentication.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public User register(User user) throws UserAlreadyPresent {
        if(iUserRepository.findById(user.getEmail()).isEmpty()){
           return iUserRepository.save(user);
        }
        else{
             throw new UserAlreadyPresent();
        }
    }

    @Override
    public User findUserByEmailAndPass(String email, String password) throws UserNotFound {

        User existUser = iUserRepository.findByEmailAndPassword(email, password);

        if(existUser == null){
            throw new UserNotFound();
        }

        return existUser;
    }
}
