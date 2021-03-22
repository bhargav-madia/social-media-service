package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.Post;

import java.util.Map;
import java.util.Optional;

public interface PostRepository {

    Map<Long, Post> getAllPosts();
    void createOrUpdatePost(Post post);
    Optional<Post> getPostById(Long postId);
}
