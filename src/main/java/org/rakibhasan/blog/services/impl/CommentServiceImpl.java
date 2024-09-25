package org.rakibhasan.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Comment;
import org.rakibhasan.blog.entities.Post;
import org.rakibhasan.blog.entities.User;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.CommentDto;
import org.rakibhasan.blog.repositories.CommentRepository;
import org.rakibhasan.blog.repositories.PostRepository;
import org.rakibhasan.blog.repositories.UserRepository;
import org.rakibhasan.blog.services.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException("User", userId)
        ));
        Post post = this.postRepository.findById(postId).orElseThrow((
                () -> new ResourceNotFoundException("Post", postId)
        ));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow((
                () -> new ResourceNotFoundException("Comment", commentId)
        ));
        this.commentRepository.delete(comment);
    }
}
































