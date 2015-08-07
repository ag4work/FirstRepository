package service;


import dao.UserDAO;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import service.DTO.UserDTO;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {

    private static final int USER_ID = 1;
    private static final int NOT_EXIST_USER_ID = 2;
    private static final String USER_LOGIN = "testUserLogin";

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserData() throws Exception {
        UserDTO userDTO = userService.getUserById(USER_ID);
        System.out.println(userDTO);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getUserId(), new Integer(USER_ID));
    }

//    @Test
//    public void testGetNotExistUserData() throws Exception {
//        UserData userData = userService.getUserData(NOT_EXIST_USER_ID);
//        Assert.assertNull(userData);
//    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceGenericBasedImpl();
        }

        @Bean
        public UserDAO userDao() {
            UserDAO userDao = mock(UserDAO.class);

            User user = new User();
            user.setUserId(USER_ID);
            user.setName("Egor");
            user.setEmail("asdf@asd.ru");
            user.setPassword("111");
            user.setPassport("1212, OVD Perm");
            user.setRole(1);
            user.setLastname("Titiv");


            when(userDao.get(USER_ID)).thenReturn(user);
            when(userDao.get(NOT_EXIST_USER_ID)).thenReturn(null);

            return userDao;
        }
    }
}