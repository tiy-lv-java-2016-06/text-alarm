package com.theironyard.repositories;

import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EddyJ on 8/10/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByUsername(String username);
}
