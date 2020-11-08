package com.kongallis.forum.services;


import com.kongallis.forum.dao.PostRepository;
import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.exceptions.PostNotFoundException;
import com.kongallis.forum.exceptions.UserNotFoundException;
import com.kongallis.forum.models.Post;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    @Transactional
    public List<PostDto> listAllPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findAll();
         posts = posts.stream().filter(post ->
                post.getUser().getId() == userId).collect(Collectors.toList());
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }
    

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setCreatedDate(post.getCreatedDate());
        postDto.setBody(post.getBody());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

    @Transactional
    public PostDto readSinglePostFromSingleUser(Long userId, Long postId) throws PostNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("LOG MESSAGE: User with id " + userId + " was not found."));
        List<Post> posts = user.getPostList();

        for (Post singlePost  : posts) {
            if (singlePost.getPostId() == postId) {
                return mapFromPostToDto(postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("LOG MESSAGE: Post with id " + postId + " could not be extracted.")));
            }
        }
        return null;
    }
}
