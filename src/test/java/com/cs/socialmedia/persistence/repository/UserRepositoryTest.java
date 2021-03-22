package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Test
    public void shouldPersistUser() {
        User user = new User(1L,"Bhargav");
        userRepository.createOrUpdateUser(user);
        assertThat(userRepository.getUserById(1L)).isNotNull();
        assertThat(userRepository.getUserById(1L).get().getName()).isEqualTo("Bhargav");
    }

    @Test
    public void shouldGetAllUsers() {
        User user1 = new User(1L,"John");
        userRepository.createOrUpdateUser(user1);
        User user2 = new User(2L,"Dave");
        userRepository.createOrUpdateUser(user2);

        assertThat(userRepository.getAllUsers().size()).isEqualTo(2);
    }
}
