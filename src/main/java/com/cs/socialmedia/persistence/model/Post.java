package com.cs.socialmedia.persistence.model;

import java.time.LocalDateTime;

public class Post implements Comparable<Post> {

    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime postTime;

    public Post (Long id, Long userId, String content) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        //sorting by latest
        if(this.getPostTime().isAfter(o.postTime))
            return -1;
        else if(this.getPostTime().isBefore(o.postTime))
            return 1;
        else {
            return (int) (this.getId()-o.getId());
        }
    }
}
