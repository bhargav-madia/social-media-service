package com.cs.socialmedia.service;

import com.cs.socialmedia.exception.ResourceNotFoundException;
import com.cs.socialmedia.persistence.model.User;
import com.cs.socialmedia.persistence.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void shouldFollowUser() {
        User user = unit.follow(1L,2L);
        assertThat(user.getFollowees()).isEqualTo(Set.of(2L));
    }

    @Test
    public void shouldUnfollowUser() {
        User user = unit.unfollow(1L,2L);
        assertThat(user.getFollowees()).isNotEqualTo(Set.of(2L));
    }

    @Test
    public void shouldThrow404Exception() {
        assertThatThrownBy(() -> unit.unfollow(5L,5L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No user found");
    }
}
