package org.studyeasy.SpringBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringBlog.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
