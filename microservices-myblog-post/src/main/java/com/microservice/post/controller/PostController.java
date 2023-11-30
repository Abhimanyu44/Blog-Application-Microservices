package com.microservice.post.controller;

import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.service.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //  http://localhost:8081/api/posts
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savePost = postService.createPost(post);
        return new ResponseEntity<>(savePost, HttpStatus.CREATED);
    }

    //  http://localhost:8081/api/posts/{postId}
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable String postId) {
        System.out.print(postId);
        Post postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    //  http://localhost:8082/api/comments/{postId}/comments  --------->  8efac8f2-4bbb-4c07-affd-2f36d4702353
    @GetMapping("/{postId}/comments")
    //-------  CircuitBreaker Features -----------//
    @CircuitBreaker(name= "CommentBearer", fallbackMethod = "commentFallback")
    public ResponseEntity<PostDto> getPostWithComment(@PathVariable String postId) {

        PostDto postWithComment = postService.getPostWithComment(postId);

        return ResponseEntity.ok(postWithComment);
    }

    //-------  CircuitBreaker fallbackMethod Features -----------//
    public ResponseEntity<PostDto> commentFallback(String postId, Exception ex){
        System.out.print("fallback is executed because service is down : " + ex.getMessage());

        ex.printStackTrace();
        PostDto dto = new PostDto();
        dto.setPostId("1234");
        dto.setTitle("Service Down");
        dto.setDescription("Service Down");
        dto.setContent("Service Down");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
