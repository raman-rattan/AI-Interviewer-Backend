package com.aiinterviewer.userAuthentication.security;

import com.aiinterviewer.userAuthentication.domain.User;

import java.util.Map;

public interface ITokenGenerator {
    Map<String, String> generateToken(User user);
}
