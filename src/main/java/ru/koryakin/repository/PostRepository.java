package ru.koryakin.repository;

import ru.koryakin.model.Post;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private static AtomicLong counter = new AtomicLong(1);
    private static ConcurrentHashMap<Long, Post> postsMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Long, Post> all() {
        return postsMap;
    }

    public Optional<Post> getById(long id) {
        // Optional чтоб не ронять программу при отсутствии Post
        return Optional.of(postsMap.get(id));
    }

    public Optional<Post> save(Post post) {
        // если номер id не задан
        if (post.getId() == 0) {
            long id = counter.getAndIncrement();
            post.setId(id);
            postsMap.put(id, post);
        } else {
            // Если id задан
            // Если post с данным id уже имеется
            if (postsMap.containsKey(post.getId())) {
                postsMap.put(post.getId(), post);
            }
        }
        return getById(post.getId());
    }

    public String removeById(long id) {
        if (postsMap.containsKey(id)) {
            postsMap.remove(id);
            return "Post успешно удален";
        } else {
            return "Пост с данным id не найден, удаление не возможно";
        }
    }
}
