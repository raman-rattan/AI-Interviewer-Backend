package com.aiinterviewer.interviewSession.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "user already present")
public class UserAlreadyPresent extends Exception{
}
