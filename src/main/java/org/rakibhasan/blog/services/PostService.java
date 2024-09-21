package org.rakibhasan.blog.services;

import org.rakibhasan.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    // CREATE POST
    PostDto createPost(PostDto post, Integer userId, Integer categoryId);

    // UPDATE POST
    PostDto updatePost(PostDto post, Integer postId);

    // DELETE POST
    void deletePost(Integer postId);

    // GET POST BY ID
    PostDto getPostById(Integer postId);

    // GET ALL POSTS
    List<PostDto> getAllPosts();

    // GET POSTS BY CATEGORY
    List<PostDto> getPostsByCategory(Integer categoryId);

    // GET POSTS BY USER
    List<PostDto> getPostsByUser(Integer userId);

    // SEARCH POSTS
    List<PostDto> searchPosts(String keyword);

}
