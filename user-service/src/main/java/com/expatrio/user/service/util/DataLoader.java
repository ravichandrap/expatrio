package com.expatrio.user.service.util;

import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        Set<Role> adminRoles = new HashSet<>(1);
        Set<Role> userRoles = new HashSet<>(1);
        adminRoles.add(new Role("Admin"));
        userRoles.add(new Role("User"));

        userRepository.save(new UserEntity(
                "Sravan",
                "Kuma",
                "rc.srAVAN@outlook.com",
                "test1234",
                "20202020",
                userRoles
        ));

        userRepository.save(new UserEntity(
                "KRISHNA",
                "KOND",
                "rc.divija@outlook.com",
                "test1234",
                "20202020",
                adminRoles
        ));

//        userRoles = new HashSet<>(1);
//        adminRoles.add(new Role(20L,"Admin"));
//        userRepository.save(new UserEntity(
//                "shobha",
//                "Reddy",
//                "rc.shobha@outlook.com",
//                "test1234",
//                "20202020",
//                adminRoles
//        ));
    }

}
