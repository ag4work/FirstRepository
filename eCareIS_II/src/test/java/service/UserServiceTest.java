package service;


import dao.UserDAO;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import service.DTO.UserDTO;
import utils.Mappers.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.anyShort;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {

    private static final int USER_ID = 1;
    private static final int NOT_EXIST_USER_ID = 2;
    private static final String USER_EMAIL = "egor@gmail.com";
    private static final String NOT_EXISTING_USER_EMAIL = "bill.gates@microsoft.com";
    private static final String USER_PASSWORD = "111";


    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void before(){
//        System.out.println("before");
    }

    @BeforeClass
    public static void beforeClass(){
//        System.out.println("beforeClass");
    }

    @Test
    public void testGetUserData() throws Exception {
        UserDTO userDTO = userService.getUserById(USER_ID);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getUserId(), new Integer(USER_ID));
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        UserDTO userDTO = userService.findUserByEmail(USER_EMAIL);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getEmail(),USER_EMAIL);
    }

    @Test
    public void testFindNotExistingUserByEmail() throws Exception {
        UserDTO userDTO = userService.findUserByEmail(NOT_EXISTING_USER_EMAIL);
        Assert.assertNull(userDTO);
    }

    @Test
    public void testIsUserWithEmailAndPasswordExists() throws Exception {
        UserDTO userDTO = userService.userWithEmailAndPasswordExists(
                USER_EMAIL, USER_PASSWORD);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void testIsUserWithEmailAndFakePasswordExists() throws Exception {
        String fakePassword = "123123123";
        UserDTO userDTO = userService.userWithEmailAndPasswordExists(
                NOT_EXISTING_USER_EMAIL, fakePassword);
        Assert.assertNull(userDTO);
    }

    @Test
    public void testGetAllUsers() throws Exception {

        List<UserDTO> userDTOs = userService.getAllUsers();
        Assert.assertNotNull(userDTOs);
        //todo make check for each element in the List
    }
    @Test
    public void addUserTest(){
        UserDTO userDTO = getUserDTO1();
        userService.addUser(userDTO);
        verify(userDAO,times(1)).add(UserMapper.DTOToEntity(userDTO));
        verify(userDAO, atMost(1)).add(UserMapper.DTOToEntity(userDTO));
        verify(userDAO, atLeast(1)).add(UserMapper.DTOToEntity(userDTO));

    }

    public UserDTO getUserDTO1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(USER_ID);
        userDTO.setName("Egor");
        userDTO.setEmail(USER_EMAIL);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setPassport("1212, OVD Perm");
        userDTO.setRole(1);
        userDTO.setLastname("Titov");
        userDTO.setBirthday(new Date());
        return  userDTO;
    }

    @Configuration
    static class ContextConfiguration {

//        @Bean
//        public User user1(){
//            User user = new User();
//            user.setUserId(USER_ID);
//            user.setName("Egor");
//            user.setEmail(USER_EMAIL);
//            user.setPassword(USER_PASSWORD);
//            user.setPassport("1212, OVD Perm");
//            user.setRole(1);
//            user.setLastname("Titov");
//            return user;
//        }

        @Bean
        public UserService userService() {
            return new UserServiceGenericBasedImpl();
        }

        @Bean
        public UserDAO userDao() {
            System.out.println("mock user dao init");
            UserDAO userDao = mock(UserDAO.class);

            User user = new User();
            user.setUserId(USER_ID);
            user.setName("Egor");
            user.setEmail(USER_EMAIL);
            user.setPassword(USER_PASSWORD);
            user.setPassport("1212, OVD Perm");
            user.setRole(1);
            user.setLastname("Titov");

            List<User> allUsers = new ArrayList<User>();
            allUsers.add(user);

            when(userDao.get(USER_ID)).thenReturn(user);
            when(userDao.get(NOT_EXIST_USER_ID)).thenReturn(null);
            when(userDao.getAll()).thenReturn(allUsers);

            return userDao;
        }
    }
}