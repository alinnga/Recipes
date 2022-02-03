package org.hyperskill.Recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.hyperskill.Recipe.model.User;
import org.hyperskill.Recipe.model.UserDetailsImpl;
import org.hyperskill.Recipe.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Not Found" +username);
        }
        return new UserDetailsImpl(user);
    }

}
