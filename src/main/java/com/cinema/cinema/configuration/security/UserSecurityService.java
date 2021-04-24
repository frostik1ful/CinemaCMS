package com.cinema.cinema.configuration.security;

import com.cinema.cinema.database.dao.interfaces.UserDAO;
import com.cinema.cinema.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userSecurityService")
public class UserSecurityService implements UserDetailsService {
    private final UserDAO dao;
    @Autowired
    public UserSecurityService(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = dao.findUserByNickName(s);
        if (optionalUser.isPresent()){
            return new AppUser(optionalUser.get());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }


    }
}
