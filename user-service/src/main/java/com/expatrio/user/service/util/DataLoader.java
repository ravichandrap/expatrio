package com.expatrio.user.service.util;

import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
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
        adminRoles.add(new Role(1001L));

        Set<Role> userRoles = new HashSet<>(1);
        userRoles.add(new Role(1002L));


        List<UserEntity> users = List.of(new UserEntity(
                "Daniel",
                "Ruf",
                "Daniel@example.com",
                "test1234",
                "+49 117 231 2987",
                userRoles
        ), new UserEntity(
                "Samir",
                " Jouni",
                "Samir.Jouni@example.com",
                "test1234",
                "+49 117 231 0000",
                adminRoles
        ), new UserEntity(
                "Christoph",
                "Guttandin",
                "Guttandin.Christoph@example.com",
                "test1234",
                "+49 117 231 1111",
                userRoles
        ), new UserEntity(
                "Fabian",
                "Morón Zirfas",
                "mz.Fabian@example.com",
                "test1234",
                "+49 117 231 2222",
                userRoles
        ),new UserEntity(
                "Billie",
                "Thompson",
                "t.Billie@example.com",
                "test1234",
                "+49 117 231 3333",
                userRoles
        ),new UserEntity(
                "Tobias",
                "Bieniek",
                "rc.Tobias@example.com",
                "test1234",
                "+49 117 231 4444",
                Set.of(new Role(1001L), new Role(1002L))
        ));

        userRepository.saveAll(users);
    }

}
