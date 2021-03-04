package com.cs.social_media.service;

import com.cs.social_media.persistence.model.User;
import com.cs.social_media.persistence.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class UserServiceTest {

    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private UserService unit;

    @Before
    public void setup() {
        unit = new UserService(userRepository);
        User user1 = new User(1L,"Bhargav");
        User user2 = new User(2L, "John");
        User user3 = new User(3L, "Dave");

        unit.createUser("Bhargav");
        unit.createUser("John");
        unit.createUser("Dave");

    }

    @Test
    public void canFollowUser() {
        unit.follow(1L,2L);

        User user = userRepository.getUserById(1L).get();
        assertThat(user.getFollowees()).isEqualTo(Set.of(2L));
    }

    @Test
    public void canUnfollowUser() {
        unit.unfollow(1L,2L);

        User user = userRepository.getUserById(1L).get();
        assertThat(user.getFollowees()).isNotEqualTo(Set.of(2L));


    }
}
