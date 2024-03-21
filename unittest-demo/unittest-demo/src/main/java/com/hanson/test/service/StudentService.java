package com.hanson.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanson.test.entity.Student;

/**
 * @author hanson
 * @date 2024/3/21 18:41
 */
public interface StudentService extends IService<Student>{

    public void computeScore();
}
