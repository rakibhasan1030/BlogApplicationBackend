package org.rakibhasan.blog.repositories;

import org.rakibhasan.blog.entities.Category;
import org.rakibhasan.blog.entities.Post;
import org.rakibhasan.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

