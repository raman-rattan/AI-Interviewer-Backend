package com.aiinterviewer.interviewSession.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "session not found")
public class SessionNotFound extends Exception{
}
