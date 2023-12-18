package com.example.UserAuthenticationService.service;


import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exception.InvalidCredentialsException;
import com.example.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.example.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //save the user in the db
        if (userRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserIdAndPassword(String userEmail, String password) throws InvalidCredentialsException {
        //validate the credentials
        User loggedInUser = userRepository.findByUserEmailAndPassword(userEmail, password);
        if (loggedInUser == null) {
            throw new InvalidCredentialsException();
        }
        return loggedInUser;
    }
}
