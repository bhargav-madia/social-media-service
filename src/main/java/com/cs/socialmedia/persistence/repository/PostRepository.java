package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.Post;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface PostRepository {

    Set<Post> getAllPosts();
    void createOrUpdatePost(Post post);
    Optional<Post> getPostById(Long postId);
}
