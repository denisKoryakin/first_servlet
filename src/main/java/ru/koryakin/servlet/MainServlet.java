package ru.koryakin.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.koryakin.controller.PostController;
import ru.koryakin.repository.PostRepository;
import ru.koryakin.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    final String ALL_POSTS_PATH = "/api/posts";
    final String POST_PATH_WITH_ID = "/api/posts/\\d+";

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("ru.koryakin");
        final var service = context.getBean(PostService.class);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            // primitive routing
            if (method.equals("GET") && path.equals(ALL_POSTS_PATH)) {
                controller.all(resp);
                return;
            }
            if (method.equals("GET") && path.matches(POST_PATH_WITH_ID)) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.getById(id, resp);
                return;
            }
            if (method.equals("POST") && path.equals(ALL_POSTS_PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals("DELETE") && path.matches(POST_PATH_WITH_ID)) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

