package com.kongallis.forum.services;

import com.kongallis.forum.dao.PostRepository;
import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.CommentDto;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.exceptions.PostNotFoundException;
import com.kongallis.forum.exceptions.UserNotFoundException;
import com.kongallis.forum.models.Comment;
import com.kongallis.forum.models.Post;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<CommentDto> listAllCommentsOfPost(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " was not found."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with id " + postId + " was not found."));
        if (post.getUser().getId() != userId) {
            throw new PostNotFoundException("Post with id " + postId + " was not found for the user with id " + userId);
        }
        List<Comment> comments =post.getCommentList();
        return comments.stream().map(this::mapFromCommentToDto).collect(toList());
    }


    private CommentDto mapFromCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setPostId(comment.getPost().getPostId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getUser().getEmail());
        commentDto.setAvatar(comment.getUser().getAvatar());
        commentDto.setName(comment.getUser().getName());
        return commentDto;
    }
}
