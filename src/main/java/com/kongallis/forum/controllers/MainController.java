package com.kongallis.forum.controllers;


import com.kongallis.forum.dto.CommentDto;
import com.kongallis.forum.dto.PostDto;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.services.CommentService;
import com.kongallis.forum.services.PostService;
import com.kongallis.forum.services.UserService;
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
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;


    /**
     * Fetches all the users to the front-end or a portion of users implementing server-side pagination
     *
     * @param page the page index which is used only on server-side pagination
     * @param limit the page size which is used only on server-side pagination
     * @return the users' data stored in the database
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity getUsers(@QueryParam("page") Optional<Integer> page, @QueryParam("limit") Optional<Integer> limit) {

        // Checks if the URL path contains the page index and the page size are passed as URL parameters
        if (page.isPresent() && limit.isPresent()) {
            return new ResponseEntity<>(userService.getAllUsersPaginated(page.get(), limit.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }


    /**
     * Fetches the details  of a user
     *
     * @param id the id of the user
     * @return the user's data stored in the database
     */
    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.readSingleUser(id), HttpStatus.OK);
    }


    /**
     * Fetches the posts of a user as a list of items
     *
     * @param id the id of the user
     * @return all the posts of a user stored in the database
     */
    @GetMapping(value = "/{userId}/posts", produces = "application/json")
    public ResponseEntity<PostDto> getPostsOfUser(@PathVariable("userId") Long id) {
        return new ResponseEntity(postService.listAllPostsByUserId(id), HttpStatus.OK);
    }

    /**
     * Fetches a post by its id
     *
     * @param userId the id of the user
     * @param postId the id of the post
     * @return the post stored in the database
     */
    @GetMapping(value = "/{userId}/posts/{postId}", produces = "application/json")
    public ResponseEntity<PostDto> getSinglePostOfUser(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        PostDto response = postService.readSinglePostFromSingleUser(userId, postId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Fetches the comments of a post as a list of items
     *
     * @param userId the id of the user
     * @param postId the id of the post
     * @return the comments of a post stored in the database
     */
    @GetMapping(value = "/{userId}/posts/{postId}/comments", produces = "application/json")
    public ResponseEntity<CommentDto> getCommentsOfPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        return new ResponseEntity(commentService.listAllCommentsOfPost(userId, postId), HttpStatus.OK);
    }


}
