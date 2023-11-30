package com.microservice.comment.controller;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;

//  http://localhost:8082/api/cpmments

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
       // System.out.println(comment);
        Comment comment1 = commentService.createComment(comment);

        return ResponseEntity.ok(comment1);
    }

//    http://localhost:8082/api/comments/{postId}
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable String postId){
        List<Comment> allCommentsByPostId = commentService.getAllCommentsByPostId(postId);

        return ResponseEntity.ok(allCommentsByPostId);

    }


}
