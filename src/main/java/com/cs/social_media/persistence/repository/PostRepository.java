package com.cs.social_media.persistence.repository;

import com.cs.social_media.persistence.model.Post;
import com.cs.social_media.persistence.model.User;

import java.util.Map;
import java.util.Optional;

public interface PostRepository {

    Map<Long, Post> getAllPosts();
    void createOrUpdatePost(Post post);
    Optional<Post> getPostById(Long postId);
}
