package com.expatrio.user.service.service;

import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.exception.UserNotFoundRuntimeException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private static Set<Role> adminRoles = new HashSet<>(1);
    private static Set<Role> userRoles = new HashSet<>(1);
    private static List<UserEntity> users = new ArrayList(6);

    @BeforeAll
    static void init() {
        userRoles.add(new Role(1002L,"User"));
        adminRoles.add(new Role(1001L,"Admin"));

        users = List.of(new UserEntity(
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
                1000L,
                "Tobias",
                "Bieniek",
                "rc.Tobias@example.com",
                "test1234",
                "+49 117 231 4444",
                Set.of(new Role(1001L, "Admin"), new Role(1002L,"User"))
        ));
    }

    @InjectMocks
    UserService service = new UserService();

    @Mock
    UserRepository repository;

    @BeforeEach
    void beforeEach() {
        System.out.println("--------------------");
    }

    @Test
    @DisplayName("User save")
    void save() {
        UserEntity userEntity = users.get(1);
        Mockito.when(repository.save(userEntity)).thenReturn(userEntity);
        UserEntity saveUser = service.save(userEntity);
        assert saveUser.getEmail().equalsIgnoreCase(userEntity.getEmail());
        assertEquals(saveUser.getFirstName(), userEntity.getFirstName());
        assertEquals(saveUser.getId(), userEntity.getId());
        assertEquals(saveUser.getPassword(), userEntity.getPassword());
        assertEquals(saveUser.getLastName(), userEntity.getLastName());
        assertEquals(saveUser.getRoles().size(), userEntity.getRoles().size());
    }

    @Test
    @DisplayName("Delete User By Id")
    void delete() {
        final Long id = 1000L;
        Mockito.doNothing().when(repository).deleteById(id);
        service.delete(id);
    }

    @Test
    @DisplayName("Delete User By Id, Throw NotFoundRuntimeException")
    void deleteUserNotFoundRuntimeException() {
        final Long id = 10L;
        Mockito.doThrow(UserNotFoundRuntimeException.class).when(repository).deleteById(id);
        Exception exception = Assertions.assertThrows(UserNotFoundRuntimeException.class, () -> {
            service.delete(id);
        });

        final String expectedMessage = "User Name not found : "+id;
        final String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    @DisplayName("Get all User details")
    void get() {
        Mockito.when(repository.findAll()).thenReturn(users);
        List<UserEntity> userEntities = service.get();
        assert userEntities.size() == users.size();
    }

    @Test
    @DisplayName("Get Users By Role Admin")
    void getByRoleAllByRoleAdmin() {
        final String role = "Admin";
        Mockito.when(repository.findAll()).thenReturn(users);
        List<UserEntity> userEntities = service.get(role);
        List<UserEntity> filterUsers = getCollectUserDetailsByRole(role);
        assert userEntities.size() == filterUsers.size();
    }

    @Test
    @DisplayName("Get Users By Role User")
    void getByRoleAllByRoleUser() {
        final String role = "User";
        Mockito.when(repository.findAll()).thenReturn(users);
        List<UserEntity> userEntities = service.get(role);
        List<UserEntity> filterUsers = getCollectUserDetailsByRole(role);
        assert userEntities.size() == filterUsers.size();
    }

    @Test
    @DisplayName("Get Users By Email")
    void getByEmail() {
        final String email = "rc.Tobias@example.com";

        UserEntity userEntity = new UserEntity(
                1000L,
                "Tobias",
                "Bieniek",
                "rc.Tobias@example.com",
                "test1234",
                "+49 117 231 4444",
                Set.of(new Role(1001L), new Role(1002L)));
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        Optional<UserEntity> byEmail = service.getByEmail(email);
        UserEntity entity = byEmail.orElse(new UserEntity());
        assertEquals(entity.getEmail(), userEntity.getEmail());
        assertEquals(entity.getId(), userEntity.getId());
    }

    @Test
    void validate() {
        UserEntity userEntity = new UserEntity(
                1000L,
                "Tobias",
                "Bieniek",
                "rc.Tobias@example.com",
                "test1234",
                "+49 117 231 4444",
                Set.of(new Role(1001L), new Role(1002L)));

        UserProfile profile = new UserProfile(userEntity.getEmail(), userEntity.getPassword());

        Mockito.when(repository.findByEmailAndPassword(profile.getEmail(), profile.getPassword())).thenReturn(Optional.of(userEntity));
        UserEntity saveEntity = service.validate(profile);
        assert saveEntity != null;
        assert saveEntity.getEmail().equalsIgnoreCase(profile.getEmail());
        assert saveEntity.getPassword().equalsIgnoreCase(profile.getPassword());

    }

    private List<UserEntity> getCollectUserDetailsByRole(String role) {
        return users.stream()
                .filter(userEntity -> userEntity.getRoles().stream().anyMatch(role1 -> role1.getName().equalsIgnoreCase(role)))
                .collect(Collectors.toList());
    }

}