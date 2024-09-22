package org.rakibhasan.blog.controllers;

import jakarta.validation.Valid;
import org.rakibhasan.blog.payloads.ApiResponse;
import org.rakibhasan.blog.payloads.PostDto;
import org.rakibhasan.blog.payloads.PostResponse;
import org.rakibhasan.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // POST - CREATE A POST
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ) {
        PostDto newPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    //PUT - UPDATE A POST
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //DELETE - DELETE A POST
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse(true, "Post deleted successfully!"), HttpStatus.OK);
    }

    // GET - GET ALL POSTS BY POST ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getAllPostsById(
            @PathVariable Integer postId
    ) {
        PostDto postsById = this.postService.getPostById(postId);
        return new ResponseEntity<>(postsById, HttpStatus.OK);
    }

    // GET - GET ALL POSTS BY CATEGORY ID
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(
            @PathVariable Integer categoryId
    ) {
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
    }

    // GET - GET ALL POSTS BY USER ID
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUser(
            @PathVariable Integer userId
    ) {
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser, HttpStatus.OK);
    }

    // GET - GET ALL THE POSTS
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

}






















































