package com.niit.UserRegisterService.service;

import com.niit.UserRegisterService.domain.User;
import com.niit.UserRegisterService.exception.InvalidCredentialsException;
import com.niit.UserRegisterService.exception.UserAlreadyExistsException;
import com.niit.UserRegisterService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException{
        if (userRepository.findById(user.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) throws InvalidCredentialsException {
        User deleteUser = userRepository.findByUserNameAndCreatorEmail(user.getUserEmail(), user.getCreatorEmail());
        if (deleteUser == null)
            throw new InvalidCredentialsException();
        userRepository.delete(deleteUser);
    }
}
