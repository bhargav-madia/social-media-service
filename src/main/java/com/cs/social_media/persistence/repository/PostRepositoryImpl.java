package com.cs.social_media.persistence.repository;

import com.cs.social_media.persistence.model.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final Map<Long, Post> postDBMap = new HashMap<>();

    @Override
    public Map<Long, Post> getAllPosts() {
        return postDBMap;
    }

    @Override
    public void createOrUpdatePost(Post post) {
        postDBMap.put(post.getId(), post);
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return Optional.ofNullable(postDBMap.get(postId));
    }
}
