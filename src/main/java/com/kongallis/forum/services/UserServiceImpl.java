package com.kongallis.forum.services;

import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.exceptions.UserNotFoundException;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;



    @Transactional
    public List<UserDto> listAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    private UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUserName());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }

    @Transactional
    public UserDto readSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("For id " + id));
        return mapFromUserToDto(user);
    }

    // pageIndex denotes from where to start
    public List<UserDto> getAllUsersPaginated(int pageIndex, int pageSize) {
        List<UserDto> users = listAllUsers();

            int startingPoint = (pageIndex - 1) * pageSize;
            int endingPoint = (pageIndex) * pageSize;
            if (users.size() < endingPoint) {
                endingPoint = users.size();
            }
            return users.subList(startingPoint, endingPoint);

    }


}
