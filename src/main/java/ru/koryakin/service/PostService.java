package ru.koryakin.service;

import ru.koryakin.exception.NotFoundException;
import ru.koryakin.model.Post;
import ru.koryakin.repository.PostRepository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public ConcurrentHashMap<Long, Post> all() {
    return repository.all();
  }

  public Optional<Post> getById(long id) {
    try {
      return repository.getById(id);
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }

  public Optional<Post> save(Post post) {
    try {
      return repository.save(post);
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }

  public String removeById(long id) {
    return repository.removeById(id);
  }
}

