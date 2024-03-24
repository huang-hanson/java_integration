package com.hanson.mockito.service.impl;

import com.hanson.mockito.dao.SalesDao;
import com.hanson.mockito.dao.UserDao;
import com.hanson.mockito.entity.User;
import com.hanson.mockito.exception.DAOException;
import com.hanson.mockito.exception.ValidationException;
import com.hanson.mockito.util.FindUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hanson
 * @date 2024/3/22 15:47
 */
class RegistrationServiceImplTest {

    @InjectMocks
    @Spy
    private RegistrationServiceImpl registrationService;

    @Mock
    private UserDao userDao;

    @Mock
    private SalesDao salesDao;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void register() throws Exception {
        String name = null;
        String phone = "15012345678";
        try {
            registrationService.register(name, phone);
            Assertions.fail("这里说明程序挂了");// 如果执行代码能快速定位
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof ValidationException);
        }

        name ="Hanson";
        phone = null;
        try {
            registrationService.register(name, phone);
            Assertions.fail("这里说明程序挂了");// 如果执行代码能快速定位
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof ValidationException);
        }

        phone = "15012345678";
        MockedStatic<FindUtils> staticService = Mockito.mockStatic(FindUtils.class);
        staticService.when(() -> FindUtils.getAreaCode("15012345678")).thenReturn("a");// 可以返回具体的操作
        staticService.when(() -> FindUtils.getOperatorCode("15012345678")).thenReturn("b");// 可以返回具体的操作

        // 数据库操作正常
        Mockito.when(salesDao.findRep("a","b")).thenCallRealMethod();
        Mockito.when(userDao.save(name,phone,"Hanson")).thenCallRealMethod();
        User user = registrationService.register(name, phone);
        Assertions.assertEquals("Hanson",user.getRepId());

        // 数据库操作异常
        Mockito.when(userDao.save(name,phone,"Hanson")).thenThrow(new SQLException());
        try {
            registrationService.register(name,phone);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof DAOException);
        }
    }
}