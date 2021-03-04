package com.cs.social_media.persistence.repository;

import com.cs.social_media.persistence.model.Post;
import com.cs.social_media.persistence.model.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostRepositoryTest {

    private PostRepositoryImpl postRepository = new PostRepositoryImpl();

    @Test
    public void shouldPersistPost() {
        Post post = new Post(1L, 1L, "Test Content here...");
        postRepository.createOrUpdatePost(post);
        assertThat(postRepository.getPostById(1L)).isNotNull();

        assertThat(postRepository.getPostById(1L).get().getUserId()).isEqualTo(1L);
        assertThat(postRepository.getPostById(1L).get().getContent()).isEqualTo("Test Content here...");

    }

}
