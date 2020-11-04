package com.kongallis.forum.services;

import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.dto.UserDto;
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
}
