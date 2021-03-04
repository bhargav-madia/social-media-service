package com.cs.social_media.persistence.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostEntityTest {

    private Long id = 1L;
    private Long userId = 1L;
    private String content = new String("Test Content");

    private Post unit;

    @Before
    public void setUp() {
        unit = new Post(id,userId,content);
    }

    @Test
    public void shouldSetFieldsFromConstructor() {
        assertThat(unit.getId()).isNotNull().isEqualTo(id);
        assertThat(unit.getContent()).isEqualTo(content);
        assertThat(unit.getUserId()).isEqualTo(userId);
    }

    @Test
    public void canSetTheUserId() {
        unit.setUserId(userId);
        assertThat(unit.getUserId()).isEqualTo(userId);
    }

    @Test
    public void canSetTheId() {
        unit.setUserId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    public void canSetTheContent() {
        unit.setContent(content);
        assertThat(unit.getContent()).isEqualTo(content);
    }

    @Test
    public void hasAToString() {
        assertThat(unit.toString()).isEqualTo(
                "Post{" +
                        "id=" + id +
                        ", userId=" + userId +
                        ", content='" + content + '\'' +
                        ", postTime=" + unit.getPostTime() +
                        '}');
    }



}
