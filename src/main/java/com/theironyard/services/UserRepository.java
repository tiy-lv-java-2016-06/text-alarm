package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vasantia on 8/10/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByPhone (Integer phone);
}
