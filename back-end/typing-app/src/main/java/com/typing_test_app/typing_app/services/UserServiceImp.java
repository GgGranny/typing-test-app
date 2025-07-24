package com.typing_test_app.typing_app.services;

import com.typing_test_app.typing_app.interfaces.UserService;
import com.typing_test_app.typing_app.model.User;
import com.typing_test_app.typing_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean saveUser(User user) {
        boolean userPresent = userRepo.existsByUsername(user.getUsername());
        if(!userPresent) {
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean userAuthenticate(User user) {
        User savedUser = userRepo.findByUsername(user.getUsername());
        if(savedUser == null) {
            return false;
        }
        if(savedUser.getUsername().equals(user.getUsername()) && encoder.matches(user.getPassword(), savedUser.getPassword())) {
            return true;
        }else {
            return false;
        }
    }


    @Override
    public User userLogout(User user) {
        return null;
    }
}
