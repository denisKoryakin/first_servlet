package ru.koryakin.repository;

import ru.koryakin.model.Post;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public interface PostRepository {

    public ConcurrentHashMap<Long, Post> all();

    public Optional<Post> getById(long id);

    public Optional<Post> save(Post post);

    public String removeById(long id);
}
