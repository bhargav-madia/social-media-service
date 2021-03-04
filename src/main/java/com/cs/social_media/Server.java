package com.cs.social_media;

import com.cs.social_media.persistence.model.User;
import com.cs.social_media.persistence.repository.PostRepository;
import com.cs.social_media.persistence.repository.UserRepository;
import com.cs.social_media.service.PostService;
import com.cs.social_media.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan("com.cs.social_media")
public class Server {

    private static UserService userService;
    private static PostService postService;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Server.class, args);

        initializeServices(context);

        User user1 = userService.createUser("bhargav");
        User user2 =userService.createUser("John");
        User user3 =userService.createUser("Dave");

        userService.follow(user1.getId(), user2.getId());
        userService.follow(user1.getId(), user3.getId());
        userService.unfollow(user1.getId(),user2.getId());


        postService.createPost(user1.getId(), "abc");

        Thread.sleep(2000);
        postService.createPost(user1.getId(), "def");

        Thread.sleep(2000);
        postService.createPost(user2.getId(),"xyz");

        Thread.sleep(2000);
        postService.createPost(user3.getId(),"klmn");

        System.out.println("All users...: "+userService.getAllUsers());

        System.out.println("All Posts....: "+postService.getAllPosts());

        List<String> requiredNewsFeed = postService.getNewsFeed(user1.getId());

        System.out.println("News feed seen to user1...:" + requiredNewsFeed);

    }

    private static void initializeServices (ApplicationContext context) {
        userService = new UserService(context.getBean(UserRepository.class));
        postService = new PostService(context.getBean(UserRepository.class),
                context.getBean(PostRepository.class));
    }

}
