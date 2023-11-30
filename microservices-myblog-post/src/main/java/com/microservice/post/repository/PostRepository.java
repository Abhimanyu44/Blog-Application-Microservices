package com.microservice.post.repository;

import com.microservice.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// Difference b/w CrudRepository and JpaRepository
// CrudRepository does not support pagination
// In CrudRepository findAll() method returns Iterable but In JpaRepository it returns List of Entity Classes
public interface PostRepository extends JpaRepository<Post, String> {

}
