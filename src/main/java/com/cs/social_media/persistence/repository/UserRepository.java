package com.cs.social_media.persistence.repository;

import com.cs.social_media.persistence.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {

    Map<Long, User> getAllUsers();
    void createOrUpdateUser(User user);
    Optional<User> getUserById(Long userId);

}
