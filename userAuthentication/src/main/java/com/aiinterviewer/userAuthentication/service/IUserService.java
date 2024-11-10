package com.aiinterviewer.userAuthentication.service;

import com.aiinterviewer.userAuthentication.domain.User;
import com.aiinterviewer.userAuthentication.exceptions.UserAlreadyPresent;
import com.aiinterviewer.userAuthentication.exceptions.UserNotFound;

public interface IUserService {

    User register(User user) throws UserAlreadyPresent;
    User findUserByEmailAndPass(String email, String password) throws UserNotFound;

}
