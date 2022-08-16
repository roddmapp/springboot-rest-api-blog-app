package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    //Note we are injecting an interface here which means we are loose coupling: we are not tightly coupling with the classes
    //we can omit @Autowired annotation because we have a no argument constructor for the injected interface/class
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post restapi @RequestBody will convert json to a Java object
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.ACCEPTED);
    }
}

