package com.expatrio.api.gateway.security;

import com.expatrio.api.gateway.entity.User;
import com.expatrio.api.gateway.entity.UserRepository;
import com.expatrio.api.gateway.exception.UserNotFoundRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService extends User implements UserDetailsService {

    @Autowired
    UserRepository userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = new User();
        user.setEmail(email);
        user.setPassword("password");
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setRole("ROLE_Admin");
        user.setId(2020L);
        Optional<User>  optionalUser =  Optional.of(user);// userRepo.findByEmail(email);
        optionalUser.orElseThrow(() -> new UserNotFoundRuntimeException("UserName(Email) not found"));
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),
                optionalUser.get().getPassword(), getAuthorities(optionalUser.get()));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public Collection<? extends GrantedAuthority> getAuthorities(User user){
        return List.of(new SimpleGrantedAuthority("ROLE_Admin"),
                new SimpleGrantedAuthority("ROLE_Customer"),
                new SimpleGrantedAuthority("ROLE_User"));
    }

    public UserDetails loadUserByUsernameAndPwd(String email,String password) throws UsernameNotFoundException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setRole("ROLE_Admin");
        user.setId(2020L);
        Optional<User>  optionalUser =  Optional.of(user); //userRepo.findByEmail(email);

        optionalUser.orElseThrow(() -> new UserNotFoundRuntimeException("UserName(Email) not found"));
        if (optionalUser.isPresent()){
            if(!decodePassword(password,optionalUser.get().getPassword())){
                return null;
            }
        }
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),
                optionalUser.get().getPassword(), getAuthorities(optionalUser.get()));
    }

    private boolean decodePassword(String pwd,String dbPwd) {
        return passwordEncoder.matches(pwd,dbPwd);
    }


}
