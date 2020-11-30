package com.expatrio.auth.security;

import com.expatrio.auth.beans.MyUserDetails;
import com.expatrio.auth.beans.Role;
import com.expatrio.auth.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //TO-DO : api call to get user details
        //User user = userRepository.getUserByUsername(username);

        Role role = new Role();
        role.setId(1);
        role.setName("Admin");
        User user = new User();
        user.setId(11L);
        user.setPassword("Password");
        user.setRoles(Set.of(role));
        user.setUsername("rc.reddy@outlook.com");



        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}