package ru.koryakin.controller;

import com.google.gson.Gson;
import ru.koryakin.model.Post;
import ru.koryakin.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.all();
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.getById(id);
    if (data.isEmpty()) {
      response.getWriter().print("Post с данным id не найден");
    } else {
      final var gson = new Gson();
      response.getWriter().print(gson.toJson(data));
    }
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    if (data.isPresent()) {
      response.getWriter().print("Пост: " + gson.toJson(data) + " успешно записан");
    } else {
      response.getWriter().print("Пост с данным id не найден, перезапись не возможна");
    }
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.removeById(id);
    response.getWriter().print(data);
  }
}
