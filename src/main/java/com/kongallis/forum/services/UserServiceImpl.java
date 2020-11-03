package com.kongallis.forum.services;

import com.kongallis.forum.dao.UserRepository;
import com.kongallis.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
