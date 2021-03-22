package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {

    Map<Long, User> getAllUsers();
    void createOrUpdateUser(User user);
    Optional<User> getUserById(Long userId);

}
