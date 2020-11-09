package com.kongallis.forum.services;

import com.kongallis.forum.dao.PostRepository;
import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.CommentDto;
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

    /**
     *
     * @param userId the id of a user
     * @param postId the id of a post
     * @return all the comments of a single post
     */
    @Transactional
    public List<CommentDto> listAllCommentsOfPost(Long userId, Long postId) {
        // Checks if a user exists in the database
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " was not found."));

        // Checks if a post exists in the database
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with id " + postId + " was not found."));

        // Checks if user has a post with this id
        if (post.getUser().getId() != userId) {
            throw new PostNotFoundException("Post with id " + postId + " was not found for the user with id " + userId);
        }
        List<Comment> comments =post.getCommentList();
        return comments.stream().map(this::mapFromCommentToDto).collect(toList());
    }


    /**
     * Converts a Comment object to a Comment Data Transfer Object
     *
     * @param comment a comment object
     * @return a comment dto object
     */
    private CommentDto mapFromCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setPostId(comment.getPost().getPostId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getUser().getEmail());
        commentDto.setAvatar(comment.getUser().getAvatar());
        commentDto.setName( String.format("%s %s", comment.getUser().getFirstName(), comment.getUser().getLastName()));
        return commentDto;
    }
}
