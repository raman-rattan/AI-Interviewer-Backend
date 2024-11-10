package com.aiinterviewer.userAuthentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "user not found")
public class UserNotFound extends Exception{
}
