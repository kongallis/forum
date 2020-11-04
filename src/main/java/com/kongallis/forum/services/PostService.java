package com.kongallis.forum.services;


import com.kongallis.forum.dao.PostRepository;
import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.exceptions.PostNotFoundException;
import com.kongallis.forum.models.Post;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

        User user = userRepository.findById(userId).get();
        List<Post> posts = user.getPostList();
//        Iterable<Post> posts = postRepository.findAllById(userId).orElseThrow(() -> new PostNotFoundException("For id " + userId));
        return posts.stream().map(this::mapFromPostToDto).collect(toList());

    }


    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setCreatedDate(post.getCreatedDate());
        postDto.setBody(post.getBody());
        return postDto;
    }
}
