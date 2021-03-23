package com.cs.socialmedia.persistence.repository;

import com.cs.socialmedia.persistence.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final Set<Post> postSet = new TreeSet<>();

    @Override
    public Set<Post> getAllPosts() {
        return postSet;
    }

    @Override
    public void createOrUpdatePost(Post post) {
        postSet.add(post);
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return Optional.ofNullable(postSet.stream().
                filter(e -> e.getId().equals(postId)).collect(Collectors.toList()).get(0));
    }
}
