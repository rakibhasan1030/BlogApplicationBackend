package org.rakibhasan.blog.services;

import org.rakibhasan.blog.payloads.CommentDto;
import org.springframework.stereotype.Service;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

    void deleteComment(Integer commentId);

}
