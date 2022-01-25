package com.dirsys.worktest.service;

import com.dirsys.worktest.entity.User;
import com.dirsys.worktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;



    public User registerNewUser(User user){
        boolean exist = checkEmailIfExist(user.getEmail());
        User newUser = new User();
        if (!exist){
            newUser = new User(user.getFullName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), "USER", "ACCESS_TEST1");
            userRepository.save(newUser);

        }

        return  newUser;
    }

    public boolean checkEmailIfExist(String email){
        User user = userRepository.findUsersByEmail(email);
        return user != null;
    }


    public User getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        if (userRepository.findUsersByEmail(authenticatedUsername) != null){
            return userRepository.findUsersByEmail(authenticatedUsername);
        }else{
            throw new Exception("Does not exist!");
        }
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }


}
