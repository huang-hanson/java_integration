package com.hanson.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.test.entity.Student;
import com.hanson.test.mapper.StudentMapper;
import com.hanson.test.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hanson
 * @date 2024/3/21 18:41
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    public void computeScore() {
        int a = 10, b = 0;
        int c = a/b;
    }
}
