package com.cs.social_media.persistence.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTest {

    private Long id = 1L;
    private String name = new String("Bhargav");
    private List<Long> postIds = new ArrayList<>();
    private Set<Long> followees = new HashSet<>();

    private User unit;

    @Before
    public void setUp() {
        unit = new User(id,name);
    }

    @Test
    public void shouldSetFieldsFromConstructor() {
        assertThat(unit.getId()).isNotNull().isEqualTo(id);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    public void canSetTheUserName() {
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    public void canAddThePost() {
        unit.setPostIds(List.of(1L));
        assertThat(unit.getPostIds().get(0)).isEqualTo(1L);
    }

    @Test
    public void canAddTheFollowees(){
        User user1 = new User(2L,"John");
        User user2 = new User(3L,"Dave");
        unit.addFollowees(Set.of(user1.getId(),user2.getId()));
        assertThat(unit.getFollowees().contains(2L)).isTrue();
        assertThat(unit.getFollowees().contains(3L)).isTrue();
    }

    @Test
    public void hasAToString() {
        assertThat(unit.toString()).isEqualTo(
                "User{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", postIds=" + postIds +
                        ", followees=" + followees +
                        '}');
    }



}
