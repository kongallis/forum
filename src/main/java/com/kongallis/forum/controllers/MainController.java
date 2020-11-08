package com.kongallis.forum.controllers;


import com.kongallis.forum.dto.CommentDto;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.exceptions.PostNotFoundException;
import com.kongallis.forum.services.CommentService;
import com.kongallis.forum.services.PostService;
import com.kongallis.forum.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.QueryParam;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class MainController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;



    @GetMapping(produces = "application/json")
    public ResponseEntity getUsers(@QueryParam("page") Optional<Integer> page, @QueryParam("limit") Optional<Integer> limit) {
        if (page.isPresent() && limit.isPresent()) {
            return new ResponseEntity<>(userService.getAllUsersPaginated(page.get(), limit.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }


    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.readSingleUser(id), HttpStatus.OK);
    }



    @GetMapping(value = "/{userId}/posts", produces = "application/json")
    public ResponseEntity<PostDto> getPostsOfUser(@PathVariable("userId") Long id) {

        return new ResponseEntity(postService.listAllPostsByUserId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/posts/{postId}", produces = "application/json")
    public ResponseEntity<PostDto> getSinglePostOfUser(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        PostDto response = postService.readSinglePostFromSingleUser(userId, postId);
//        if (response == null) {
//            String message = String.format("WARNING: No post with id %d was found for the user with id %d", postId, userId);
//            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/posts/{postId}/comments", produces = "application/json")
    public ResponseEntity<CommentDto> getCommentsOfPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        return new ResponseEntity(commentService.listAllCommentsOfPost(userId, postId), HttpStatus.OK);
    }


}
