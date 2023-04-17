package ru.koryakin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.koryakin.controller.PostController;
import ru.koryakin.repository.*;
import ru.koryakin.service.PostService;

@Configuration
public class JavaConfig {

    @Bean
    public PostController postController() {
        return new PostController(postService());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryStubImpl();
    }
}
