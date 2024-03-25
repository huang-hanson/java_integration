package com.hanson.transaction.controller;


import com.hanson.transaction.domain.LogInfo;
import com.hanson.transaction.domain.UserInfo;
import com.hanson.transaction.service.LogInfoService;
import com.hanson.transaction.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanson
 * @date 2024/3/26 0:15
 */
@RestController
public class UserController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private LogInfoService logService;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    // 在此方法中使用编程式的事物
    @RequestMapping("/add")
    public int add(UserInfo userInfo) {
        // 非空效验【验证用户名和密码不为空】
        boolean flag;
        int result;
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername()) || !StringUtils.hasLength(userInfo.getPassword())) {
            flag = false;
        }
        // 开启事务（获取事务）
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        flag = userService.save(userInfo);
        result = flag ? 1 : 0;
        System.out.println("add 受影响的行数：" + result);
        // 提交事务
//        transactionManager.commit(transactionStatus);
        // 回滚事务
        transactionManager.rollback(transactionStatus);
        return result;
    }

    // 在此方法中使用声明式的事物
    // 在进入方法之前，自动开启事务，在方法之前完后，自动提交事务，如果出现异常，则自动回滚事务
    @Transactional(timeout = 1)
    @RequestMapping("/add2")
    public int add2(UserInfo userInfo) {
        boolean flag;
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = userService.save(userInfo);
        int result = flag ? 1 : 0;
        System.out.println("add2 受影响的行数：" + result);
//        int num = 10/0;
        return result;
    }


    @Transactional
    @RequestMapping("/add3")
    public int add3(UserInfo userInfo) {
        boolean flag;
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        flag = userService.save(userInfo);
        int result = flag ? 1 : 0;
        System.out.println("add2 受影响的行数：" + result);
        try {
            int num = 10 / 0;
        } catch (Exception e) {
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping("/add4")
    public int add4(UserInfo userInfo) {
        boolean flag;
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        flag = userService.save(userInfo);
        int userResult = flag ? 1 : 0;
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

    @Transactional
    @RequestMapping("/add5")
    public int add5(UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        int userResult = userService.add(userInfo);
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

    @Transactional
    @RequestMapping("/add6")
    public int add6(UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        int userResult = userService.add(userInfo);
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

    @Transactional(propagation = Propagation.NESTED)
    @RequestMapping("/add7")
    public int add7(UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return 0;
        }
        int userResult = userService.add(userInfo);
        System.out.println("添加用户：" + userResult);
        LogInfo logInfo = new LogInfo();
        logInfo.setName("添加用户");
        logInfo.setDesc("添加用户结果：" + userResult);
        int logResult = logService.add(logInfo);
        return userResult;
    }

}
