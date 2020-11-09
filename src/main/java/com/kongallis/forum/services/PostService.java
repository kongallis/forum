package com.kongallis.forum.services;


import com.kongallis.forum.dao.PostRepository;
import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.exceptions.PostNotFoundException;
import com.kongallis.forum.exceptions.UserNotFoundException;
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

    /**
     *
     * @param userId the id of a user
     * @return all the posts of a single user
     */
    @Transactional
    public List<PostDto> listAllPostsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " was not found."));
        List<Post> posts = user.getPostList();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    /**
     * Converts a Post object to a Post Data Transfer Object
     *
     * @param post a post object
     * @return a post dto object
     */
    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setCreatedAt(post.getCreatedDate());
        postDto.setBody(post.getBody());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

    /**
     *
     * @param userId the id of a user
     * @param postId the id of a post
     * @return a single of a user
     * @throws PostNotFoundException
     */
    @Transactional
    public PostDto readSinglePostFromSingleUser(Long userId, Long postId) throws PostNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " was not found."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with id " + postId + " was not found for the user with id: " + userId));
        if (userId == post.getUser().getId()) {
            return mapFromPostToDto(post);
        } else {
            throw new PostNotFoundException("Post with id " + postId + " was not found for the user with id: " + userId);
        }
    }
}
