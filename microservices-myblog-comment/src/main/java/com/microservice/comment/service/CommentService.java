package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private RestTemplateConfig restTemplate;
     @Autowired
     private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {              //    27/10/23 --> 2nd
        System.out.println(comment);
        Post post = restTemplate.getRestTemplate()
                .getForObject("http://Post-Port-Service/api/posts/"+comment.getPostId(),
                Post.class);

        System.out.println(post);

        if (post != null){
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);
            return savedComment;
        } else {
            return null;
        }

    }

    public List<Comment> getAllCommentsByPostId(String postId ) {
        List<Comment> commentList = commentRepository.findByPostId(postId);

        return commentList;
    }
}
