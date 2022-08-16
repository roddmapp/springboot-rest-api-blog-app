package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //indicates the class is a Service class and we will autowire this class to other classes
public class PostServiceImpl implements PostService {

    //using constructor based dependency injection which is best practice
    //we are omitting the @Autowired configuration because as of spring 4.1 if a class is configured as a bean and we have a default constructor we can omit this annotation
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert Dto to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        //convert entity to Dto
        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        //get all the Posts from the database returns a List of Posts

        List<Post> posts = postRepository.findAll();
        //convert List of posts to List of post DTO's
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    //converted an Entity into a DTO object
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(postDto.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //converted DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}