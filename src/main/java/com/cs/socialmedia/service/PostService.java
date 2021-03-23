package com.cs.socialmedia.service;

import com.cs.socialmedia.exception.ResourceNotFoundException;
import com.cs.socialmedia.persistence.model.Post;
import com.cs.socialmedia.persistence.model.User;
import com.cs.socialmedia.persistence.repository.PostRepository;
import com.cs.socialmedia.persistence.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private static final int MAX_FEED_COUNT = 20;

    public PostService (UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(Long userId, String content) {
        Set<Post> postSet = postRepository.getAllPosts();
        Long postId = postSet.isEmpty() ? 1L : postSet.size() + 1;
        this.addPostToUser(userId, postId);
        Post newPost = new Post(postId, userId, content);
        postRepository.createOrUpdatePost(newPost);
        return newPost;
    }

    public void addPostToUser(Long userId, Long postId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        user.getPostIds().add(postId);
        userRepository.createOrUpdateUser(user);
    }

    public List<String> getNewsFeed(Long userId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        Set<Long> userIds = new HashSet<>();
        userIds.add(userId);
        userIds.addAll(user.getFollowees());

        Set<Post> allPosts = this.getAllPosts();
        return allPosts.stream().filter(e -> userIds.contains(e.getUserId()))
                .limit(MAX_FEED_COUNT).map(Post::getContent)
                .collect(Collectors.toList());

    }

    // returns all post from DB - for testing purpose
    public Set<Post> getAllPosts () {
        return postRepository.getAllPosts();
    }
}
