package org.rakibhasan.blog.controllers;


import org.rakibhasan.blog.entities.Comment;
import org.rakibhasan.blog.payloads.ApiResponse;
import org.rakibhasan.blog.payloads.CommentDto;
import org.rakibhasan.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("userId") Integer userId,
            @PathVariable("postId") Integer postId
    ) {
        CommentDto comment = this.commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(
            @PathVariable("commentId") Integer commentId
    ) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Comment successfully deleted"), HttpStatus.OK);
    }

}
