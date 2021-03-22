package com.cs.socialmedia.persistence.model;

import java.util.*;

public class User {

    private Long id;
    private String name;
    private List<Long> postIds = new ArrayList<>();
    private Set<Long> followees = new HashSet<>();

    User(){}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Long> postIds) {
        this.postIds = postIds;
    }

    public Set<Long> getFollowees() {
        return followees;
    }

    public void addFollowees(Set<Long> followees) {
        this.followees = followees;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postIds=" + postIds +
                ", followees=" + followees +
                '}';
    }
}
