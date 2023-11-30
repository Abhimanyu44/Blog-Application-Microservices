package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private RestTemplateConfig restTemplate;
    
    @Autowired
    private PostRepository postRepo;

    public Post createPost(Post post){
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepo.save(post);
        return savedPost;
        
        
    }

    public Post getPostById(String postId) {
        Post byId = postRepo.findById(postId).get();
        return byId;
    }

    public PostDto getPostWithComment(String postId) {
        Post post = postRepo.findById(postId).get();
        ArrayList commentList = restTemplate.getRestTemplate()
                .getForObject("http://Comment-Port-Service/api/comments/" + postId, ArrayList.class);

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setComments(commentList);

        return postDto;
    }
}
