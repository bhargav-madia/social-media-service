package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final Map<Long, User> userDBMap = new HashMap<>();

    @Override
    public Map<Long, User> getAllUsers() { return userDBMap; }

    @Override
    public void createOrUpdateUser(User user) {
        userDBMap.put(user.getId(),user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(userDBMap.get(userId));
    }
}
