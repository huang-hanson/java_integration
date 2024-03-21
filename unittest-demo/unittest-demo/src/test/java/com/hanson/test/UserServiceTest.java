package com.hanson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanson.test.dao.UserDao;
import com.hanson.test.entity.User;
import com.hanson.test.service.impl.UserServiceImpl;
import com.hanson.test.util.JacksonUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * @author hanson
 * @date 2024/3/22 1:08
 */
public class UserServiceTest {

    /**
     * Mock测试：根据name查询user
     */
    @Test
    public void getUserByNameTest() {
        // mock对象
        final UserDao userDao = mock(UserDao.class);
        final UserServiceImpl userService = new UserServiceImpl();
//        userService.setUserDao(userDao);

        // stub调用
        final User user = new User();
        user.setName("admin");
        user.setPassword("pass");
        when(userDao.getUserByName("admin")).thenReturn(user);

        // 执行待测试方法
        final User user1 = userService.getUserByName("admin");
        System.out.println("查询结果：" + JacksonUtil.obj2json(user1));  //查询结果：{"name":"admin","password":"pass"}

        // 验证mock对象交互
        verify(userDao).getUserByName(anyString());

        // 验证查询结果
        Assert.assertNotNull("查询结果为空！", user1);
        Assert.assertEquals("查询结果错误！", "admin", user1.getName());
    }

    /**
     * Mock测试：保存user
     */
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    public void saveUserTest() throws Exception{
        // 执行注解初始化
        MockitoAnnotations.initMocks(this);

        // mock对象stub操作
        final User user = new User();
        user.setName("admin");
        user.setPassword("pass");
        when(userDao.getUserByName("admin")).thenReturn(user).thenReturn(null);
        when(userDao.saveUser(any(User.class))).thenReturn(1);

        // 验证用户名重复的情况
        try {
            userService.saveUser(user);
            throw new Exception();  //走到这里说明验证失败
        } catch (RuntimeException e) {
            System.out.println("重复用户名保存失败-测试通过");   //重复用户名保存失败-测试通过
        }
        verify(userDao).getUserByName("admin");

        // 验证正常保存的情况
        user.setName("user");
        final Integer integer = userService.saveUser(user);
        System.out.println("保存结果：" + integer);  //保存结果：1
        Assert.assertEquals("保存失败！", 1, integer.longValue());

        verify(userDao).saveUser(any(User.class));
        verify(userDao, times(2)).getUserByName(anyString());
    }
}
