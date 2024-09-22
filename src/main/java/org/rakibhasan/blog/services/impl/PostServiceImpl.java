package org.rakibhasan.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Category;
import org.rakibhasan.blog.entities.Post;
import org.rakibhasan.blog.entities.User;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.PostDto;
import org.rakibhasan.blog.payloads.PostResponse;
import org.rakibhasan.blog.repositories.CategoryRepository;
import org.rakibhasan.blog.repositories.PostRepository;
import org.rakibhasan.blog.repositories.UserRepository;
import org.rakibhasan.blog.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Post oldPost = this.postRepository.findById(postId).orElseThrow((
                () -> new ResourceNotFoundException("Post", postId)
        ));
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setImage(post.getImage());
        Post updatedPost = this.postRepository.save(oldPost);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow((
                () -> new ResourceNotFoundException("Post", postId)
        ));
        this.postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow((
                () -> new ResourceNotFoundException("Post", postId)
        ));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pages = this.postRepository.findAll(pageable);
        List<PostDto> allPosts = pages.getContent().stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPosts);
        postResponse.setPageNumber(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalElements(pages.getTotalElements());
        postResponse.setTotalPages(pages.getTotalPages());
        postResponse.setLastPage(pages.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow((
                () -> new ResourceNotFoundException("Category", categoryId)
        ));
        List<Post> posts = this.postRepository.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException(userId)
        ));
        List<Post> posts = this.postRepository.findByUser(user);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return List.of();
    }
}










































