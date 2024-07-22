package com.hansom.rabbitMQ.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hansom.rabbitMQ.constant.Result;
import com.hansom.rabbitMQ.entity.StudentInfo;
import com.hansom.rabbitMQ.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @date 2024/7/22 14:37
 **/
@Slf4j
@Service
public class StudentServiceImpl{

    @Autowired
    private StudentMapper studentInfoMapper;

    public Result<Boolean> insertStudentData(JSONObject object) {
        try {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setName(object.getString("name"));
            studentInfo.setNumber(object.getInteger("number"));
            studentInfo.setCreateTime(new Date());
            studentInfo.setUpdateTime(new Date());

            studentInfoMapper.insert(studentInfo);
            return Result.success(true);
        } catch (Exception e) {
            return Result.fail();
        }
    }
}