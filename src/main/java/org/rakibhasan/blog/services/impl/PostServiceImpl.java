package org.rakibhasan.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Category;
import org.rakibhasan.blog.entities.Post;
import org.rakibhasan.blog.entities.User;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.PostDto;
import org.rakibhasan.blog.repositories.CategoryRepository;
import org.rakibhasan.blog.repositories.PostRepository;
import org.rakibhasan.blog.repositories.UserRepository;
import org.rakibhasan.blog.services.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            ModelMapper modelMapper,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException(userId)
        ));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow((
                () -> new ResourceNotFoundException(categoryId)
        ));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImage("default.png");
        post.setPublishedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepository.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto post, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return List.of();
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return List.of();
    }
}










































