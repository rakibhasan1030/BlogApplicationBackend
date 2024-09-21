package org.rakibhasan.blog.repositories;

import org.rakibhasan.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
