package com.hanson.test.controller;

import com.hanson.test.entity.Student;
import com.hanson.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author hanson
 * @date 2024/3/21 18:43
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> getAllStudents(){
        return studentService.list();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id){
        return studentService.getById(id);
    }

    @PostMapping("/add")
    public Map<String, Object> addStudent(@RequestBody Student student){
        studentService.save(student);
        Map<String, Object> params = new HashMap<>();
        params.put("code",200);
        params.put("message","保存成功");
        return params;
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody Student student){
        studentService.updateById(student);
    }

    @PostMapping("/delete")
    public void deleteStudent(@RequestBody Student student){
        studentService.removeById(student);
    }
}
