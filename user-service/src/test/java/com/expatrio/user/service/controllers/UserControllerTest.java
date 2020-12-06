package com.expatrio.user.service.controllers;

import com.expatrio.user.service.UserServiceApplication;
import com.expatrio.user.service.beans.RoleDetails;
import com.expatrio.user.service.beans.UserDetails;
import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.service.UserService;
import org.apache.catalina.startup.UserConfig;
import org.junit.Assert;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import  org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

class UserControllerTest {
//
//    @Autowired
//    private static WebApplicationContext webApplicationContext;
//
//    final String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXZpQGdtYWlsLmNvbSIsImV4cCI6MTYwNjg2MDQyMCwiaWF0IjoxNjA2ODI0NDIwfQ.Zs7AlCxx8T_kTqiS4mYnkNSddPy-HKqepAiuM9egj3E";
//    private static Set<Role> adminRoles = new HashSet<>(1);
//    private static Set<Role> userRoles = new HashSet<>(1);
//    private static List<UserEntity> users = new ArrayList(6);
//
//    @Autowired
//    private static MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        mockMvc = webAppContextSetup(webApplicationContext).build();
//    }
//
//    UserDetails userDetails = UserDetails.of(1000L,
//            "Billie",
//            "Thompson",
//            "t.Billie@example.com",
//            "test1234",
//            "49 117 231 3333",
//            Set.of(new RoleDetails(1000L, "Admin")));
//
//
//
//    @BeforeAll
//     static void init() {
//        System.out.println("webApplicationContext::: " + webApplicationContext);
//        mockMvc = webAppContextSetup(webApplicationContext).build();
//        userRoles.add(new Role(1002L,"User"));
//        adminRoles.add(new Role(1001L,"Admin"));
//        users = List.of(new UserEntity(
//                "Daniel",
//                "Ruf",
//                "Daniel@example.com",
//                "test1234",
//                "+49 117 231 2987",
//                userRoles
//        ), new UserEntity(
//                "Samir",
//                " Jouni",
//                "Samir.Jouni@example.com",
//                "test1234",
//                "+49 117 231 0000",
//                adminRoles
//        ), new UserEntity(
//                "Christoph",
//                "Guttandin",
//                "Guttandin.Christoph@example.com",
//                "test1234",
//                "+49 117 231 1111",
//                userRoles
//        ), new UserEntity(
//                "Fabian",
//                "Morón Zirfas",
//                "mz.Fabian@example.com",
//                "test1234",
//                "+49 117 231 2222",
//                userRoles
//        ),new UserEntity(
//                "Billie",
//                "Thompson",
//                "t.Billie@example.com",
//                "test1234",
//                "+49 117 231 3333",
//                userRoles
//        ),new UserEntity(
//                1000L,
//                "Tobias",
//                "Bieniek",
//                "rc.Tobias@example.com",
//                "test1234",
//                "+49 117 231 4444",
//                Set.of(new Role(1001L, "Admin"), new Role(1002L,"User"))
//        ));
//    }
//
//    @InjectMocks
//    UserController userController;
//
//    @Mock
//    UserService userService;

//    @Test
//    @DisplayName("Validate User credentials by Email and Password")
//    void testValidate() {
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(service.validate(any(UserProfile.class))).thenReturn(getUserDetails(userDetails));
//        UserProfile profile = new UserProfile(userDetails.getEmail(), userDetails.getPassword());
//
//        ResponseEntity<UserDetails> response = controller.validate(profile);
//        UserDetails responseUser = Objects.requireNonNull(response.getBody());
//
//        assertEquals(response.getStatusCodeValue(),202);
//        assertEquals(responseUser.getPassword(), profile.getPassword());
//        assertEquals(responseUser.getEmail(), profile.getEmail());
//    }

//    @Test
//    @DisplayName("Validate User credentials by Email")
//    void testValidateByEmail() throws Exception {
//        final String email = userDetails.getEmail();
//        Mockito.when(userService.getByEmail(email)).thenReturn(Optional.of(getUserDetails(userDetails)));
//        ResponseEntity<Boolean> response = userController.validateByEmail(authorization, email);
//        assertEquals(response.getStatusCodeValue(),202);
//        assertEquals(response.getBody(), true);

        //when(accountServiceMock.createAccount(any(Account.class))).thenReturn(12345L);
//        mockMvc = webAppContextSetup(webApplicationContext).build();
//        mockMvc.perform(get("/api/v1/user/validate/"+email)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("Test Crate User")
//    void testCreate() throws Exception {
//        Mockito.when(service.save(any(UserEntity.class))).thenReturn(getUserDetails(userDetails));
//        ResponseEntity<UserDetailsResp> response = controller.create(authorization, userDetails);
//        UserDetails user = response.getBody().getUser();
//
//        assertEquals(response.getStatusCodeValue(),202);
//        assertEquals(user.getEmail(),userDetails.getEmail());
//        assertEquals(user.getFirstName(), userDetails.getFirstName());
//        assertEquals(user.getId(), userDetails.getId());
//        assertEquals(user.getLastName(), userDetails.getLastName());
//        assertEquals(user.getPhoneNumber(), userDetails.getPhoneNumber());
//        assertEquals(user.getPassword(), null);
//
//
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.Id").value(userDetails.getId()));
//               // .andExpect(jsonPath("$.accountType").value("SAVINGS"));
//    }

//    @Test
//    @DisplayName("Test Delete User by user id")
//    void testDelete() throws Exception {
//        final Long userId = userDetails.getId();
//        Mockito.doNothing().when(service).delete(userId);
//
//
//
//
//    }

//    @Test
//    @DisplayName("Test get all users by role")
//    void testGetAllUsersByRole() {
//        Mockito.when(service.get()).thenReturn(users);
//
//    }

//    private UserEntity getUserDetails(UserDetails user) {
//        UserEntity entity = new UserEntity();
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.map(user, entity);
//        return entity;
//    }
//
//    private UserDetails getUserDetails(UserEntity user) {
//        UserDetails userDetails = new UserDetails();
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.map(user, userDetails);
//        return userDetails;
//    }
//
//
//    @AfterEach
//    void tearDown() {
//    }
