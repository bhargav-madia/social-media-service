package com.cs.social_media.service;

import com.cs.social_media.persistence.repository.PostRepositoryImpl;
import com.cs.social_media.persistence.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest {

    private PostRepositoryImpl postRepository = new PostRepositoryImpl();
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private PostService postServiceUnit;
    private UserService userServiceUnit;

    @Before
    public void setup() throws InterruptedException {
        postServiceUnit = new PostService(userRepository, postRepository);
        userServiceUnit = new UserService(userRepository);
        userServiceUnit.createUser("Bhargav");
        userServiceUnit.createUser("John");
        userServiceUnit.createUser("Dave");

        userServiceUnit.follow(1l,2l);
        postServiceUnit.createPost(1L,"Post of Bhargav here..");
        Thread.sleep(2000);
        postServiceUnit.createPost(2L, "post of John here..");
    }

    @Test
    public void shouldGetPostFeed() {
        List<String> newsFeedVisible =  postServiceUnit.getNewsFeed(1L);
        assertThat(newsFeedVisible.get(0)).isEqualTo("post of John here..");
        assertThat(newsFeedVisible.get(1)).isEqualTo("Post of Bhargav here..");
        System.out.println(newsFeedVisible);
    }

}
