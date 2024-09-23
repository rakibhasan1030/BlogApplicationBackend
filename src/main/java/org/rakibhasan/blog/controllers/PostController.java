package org.rakibhasan.blog.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.rakibhasan.blog.payloads.*;
import org.rakibhasan.blog.services.FileService;
import org.rakibhasan.blog.services.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.rakibhasan.blog.config.AppConstants.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Value("${project.image}")
    private String path;

    private PostService postService;
    private final FileService fileService;

    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
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
    public ResponseEntity<PostDto> getPostById(
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
            @RequestParam(value = "pageNumber", defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = SORT_DIR, required = false) String sortDirection
    ) {
        PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    // GET - SEARCH POSTS BY TITLE
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostsByTitle(
            @PathVariable String keyword
    ) {
        List<PostDto> posts = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostImageResponse> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable("postId") Integer postId
    ) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImage(fileName);
        this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(new PostImageResponse(fileName, "Image Successfully Uploaded"), HttpStatus.OK);
    }

    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadPostImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}






















































