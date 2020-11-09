package com.kongallis.forum.services;

import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.PaginationResponse;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.exceptions.UserNotFoundException;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * @return all the users
     */
    @Transactional
    public List<UserDto> listAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    /**
     * Converts a User object to a User Data Transfer Object
     *
     * @param user a user object
     * @return a user dto object
     */
    private UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUserName());
        userDto.setName(String.format("%s %s", user.getFirstName(), user.getLastName()));
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }

    /**
     * @param id the id of a user
     * @return the details of a single user
     * @throws UserNotFoundException
     */
    @Transactional
    public UserDto readSingleUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found."));
        return mapFromUserToDto(user);
    }

    /**
     *
     * @param pageIndex the page index which is used only on server-side pagination
     * @param pageSize the page size which is used only on server-side pagination
     * @return a portion of users implementing server-side pagination
     */
    public PaginationResponse getAllUsersPaginated(int pageIndex, int pageSize) {
        List<UserDto> users = listAllUsers();
        // pageIndex denotes from where to start
        int startingPoint = (pageIndex - 1) * pageSize;
        int endingPoint = (pageIndex) * pageSize;
        if (users.size() < endingPoint) {
            endingPoint = users.size();
        }
        return mapForPagination(users.subList(startingPoint, endingPoint), users.size());

    }

    /**
     *
     * @param users the subset of users implementing server-side pagination
     * @param totalUsers the total number of users stored in the database
     * @return the pagination data transfer object
     */
    private PaginationResponse mapForPagination(List<UserDto> users, int totalUsers) {
        PaginationResponse pagination = new PaginationResponse();
        pagination.setItems(users);
        pagination.setTotal(new Long(totalUsers));
        return pagination;
    }


}
