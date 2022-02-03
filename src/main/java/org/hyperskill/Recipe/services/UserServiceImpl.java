package org.hyperskill.Recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hyperskill.Recipe.model.User;
import org.hyperskill.Recipe.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepo;

    @Override
    public void register(User user){
        User userDB = userRepo.findByEmail(user.getEmail());
        if(userDB==null){
            userRepo.save(user);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
