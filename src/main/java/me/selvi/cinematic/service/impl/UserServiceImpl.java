package me.selvi.cinematic.service.impl;

import me.selvi.cinematic.model.User;
import me.selvi.cinematic.repository.UserRepository;
import me.selvi.cinematic.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String user_name) {
        return userRepository.findByUserName(user_name);
    }

    @Override
    public User pushUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User updatedUser, Long user_id) {
        return null;
    }

    @Override
    public void deleteUserById(Long user_id) {

    }
}
