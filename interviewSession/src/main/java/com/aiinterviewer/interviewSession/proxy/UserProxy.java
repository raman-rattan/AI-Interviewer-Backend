package com.aiinterviewer.interviewSession.proxy;

import com.aiinterviewer.interviewSession.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="userAuthentication", url = "localhost:8081")
public interface UserProxy {

    @PostMapping("api/v1/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
