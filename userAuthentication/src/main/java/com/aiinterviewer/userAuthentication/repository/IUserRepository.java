package com.aiinterviewer.userAuthentication.repository;

import com.aiinterviewer.userAuthentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    User findByEmailAndPassword(String email, String password);
}
