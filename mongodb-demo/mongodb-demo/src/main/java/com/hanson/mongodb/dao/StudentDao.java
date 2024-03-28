package com.hanson.mongodb.dao;

import com.hanson.mongodb.pojo.Student;

import java.util.List;

/**
 * @author hanson
 * @date 2024/3/28 18:17
 */
public interface StudentDao {


    void save(Student student);

    void update(Student student);

    List<Student> findAll();

    void delete(Integer id);
}
