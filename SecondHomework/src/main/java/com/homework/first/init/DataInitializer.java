package com.homework.first.init;

import com.homework.first.domain.PostService;
import com.homework.first.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Initializing application ...");
        if(postService.count() == 0) {
            Post post = new Post("firstTitle", "firstAuthor");
            log.info("Creating new posts: {}", post);
            postService.add(post);
        }
    }
}
