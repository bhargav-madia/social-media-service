package com.cs.social_media.service;

import com.cs.social_media.exception.ResourceNotFoundException;
import com.cs.social_media.persistence.model.User;
import com.cs.social_media.persistence.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String userName) {
        Map<Long, User> userMap = userRepository.getAllUsers();
        User user = userMap.isEmpty() ? new User (1L, userName)
                : new User((long) (userMap.size() + 1),userName);
        userRepository.createOrUpdateUser(user);
        return user;
    }

    public User follow(Long followerId, Long followeeId) {
        User user = userRepository.getUserById(followerId).orElseThrow(() ->  new ResourceNotFoundException("No user found"));
        user.getFollowees().add(followeeId);
        userRepository.createOrUpdateUser(user);
        return user;
    }

    public User unfollow(Long followerId, Long followeeId) {
        User user = userRepository.getUserById(followerId).orElseThrow(() ->  new ResourceNotFoundException("No user found"));
        user.getFollowees().remove(followeeId);
        userRepository.createOrUpdateUser(user);
        return user;
    }

    // returns all user from DB - for testing purpose
    public List<User> getAllUsers () {
        return userRepository.getAllUsers().entrySet().stream()
                .map(Map.Entry ::getValue).collect(Collectors.toList());
    }
}
