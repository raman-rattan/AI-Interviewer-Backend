package com.aiinterviewer.interviewSession.repository;

import com.aiinterviewer.interviewSession.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

}
