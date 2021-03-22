package com.cs.socialmedia.service;

import com.cs.socialmedia.exception.ResourceNotFoundException;
import com.cs.socialmedia.persistence.model.Post;
import com.cs.socialmedia.persistence.model.User;
import com.cs.socialmedia.persistence.repository.PostRepository;
import com.cs.socialmedia.persistence.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    private static final int MAX_FEED_COUNT = 20;

    public PostService (UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(Long userId, String content) {
        Map<Long, Post> postMap = postRepository.getAllPosts();
        Long postId = postMap.isEmpty() ? 1L : postMap.size() + 1;
        this.addPostToUser(userId, postId);
        Post newPost = new Post(postId, userId, content);
        postRepository.createOrUpdatePost(newPost);
        return newPost;
    }

    private void addPostToUser(Long userId, Long postId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        user.getPostIds().add(postId);
        userRepository.createOrUpdateUser(user);
    }

    public List<String> getNewsFeed(Long userId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        Set<Long> userIds = new HashSet<>();
        userIds.add(userId);
        userIds.addAll(user.getFollowees());

        //getting user data for self and followees
        List<User> allUsers = userIds.stream()
                .map(id -> userRepository.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("No user found for id "+id))
                ).collect(Collectors.toList());

        //getting all postIds from the list
        Set<Long> allPostIds = allUsers.stream()
                .map(User::getPostIds).collect(Collectors.toSet()).stream()
                .flatMap(List::stream).collect(Collectors.toSet());

        //get the related post from the postIds
        List<Post> allPosts = allPostIds.stream()
                .map(id -> postRepository.getPostById(id).orElseThrow(() -> new ResourceNotFoundException("no post found for the id "+id)))
                .collect(Collectors.toList());

        //sort based on timeline and output the CONTENT for first 20
        List<String> sortedPostContent = allPosts.stream()
                .sorted(Comparator.comparing(Post :: getPostTime).reversed())
                .limit(MAX_FEED_COUNT)
                .map(Post::getContent)
                .collect(Collectors.toList());

        System.out.println(sortedPostContent);

       return sortedPostContent;
    }

    public Post getPostById(Long postId) {
        Post post = postRepository.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("No Post found"));
        return post;
    }

    // returns all post from DB - for testing purpose
    public List<Post> getAllPosts () {
        return postRepository.getAllPosts().entrySet().stream()
                .map(Map.Entry ::getValue).collect(Collectors.toList());
    }
}
