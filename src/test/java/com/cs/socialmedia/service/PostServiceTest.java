package com.cs.socialmedia.service;

import com.cs.socialmedia.persistence.repository.PostRepositoryImpl;
import com.cs.socialmedia.persistence.repository.UserRepositoryImpl;
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
        postServiceUnit.createPost(1L,"Post1 of Bhargav here..");

        postServiceUnit.createPost(2L, "post1 of John here..");

        postServiceUnit.createPost(1L,"Post2 of Bhargav here..");

        postServiceUnit.createPost(2L, "post2 of John here..");

        postServiceUnit.createPost(1L,"Post3 of Bhargav here..");

        postServiceUnit.createPost(2L, "post3 of John here..");

        postServiceUnit.createPost(1L,"Post4 of Bhargav here..");

        postServiceUnit.createPost(2L, "post4 of John here..");

        postServiceUnit.createPost(1L,"Post5 of Bhargav here..");

        postServiceUnit.createPost(2L, "post5 of John here..");

        postServiceUnit.createPost(1L,"Post6 of Bhargav here..");

        postServiceUnit.createPost(2L, "post6 of John here..");

        postServiceUnit.createPost(1L,"Post7 of Bhargav here..");

        postServiceUnit.createPost(2L, "post7 of John here..");

        postServiceUnit.createPost(3L,"Post1 of Dave here..");

        postServiceUnit.createPost(3L, "post2 of DAve here..");

        postServiceUnit.createPost(1L,"Post8 of Bhargav here..");

        postServiceUnit.createPost(2L, "post8 of John here..");

        postServiceUnit.createPost(1L,"Post9 of Bhargav here..");
        postServiceUnit.createPost(2L, "post9 of John here..");
        Thread.sleep(2000);
        postServiceUnit.createPost(1L,"Post10 of Bhargav here..");
        Thread.sleep(2000);
        postServiceUnit.createPost(2L, "post10 of John here..");
    }

    @Test
    public void shouldGetLatestTwentyPostFeed() {
        List<String> newsFeedVisible =  postServiceUnit.getNewsFeed(1L);
        assertThat(newsFeedVisible.get(0)).isEqualTo("post10 of John here..");
        assertThat(newsFeedVisible.get(1)).isEqualTo("Post10 of Bhargav here..");

        assertThat(newsFeedVisible.size()).isEqualTo(20);
    }

}
