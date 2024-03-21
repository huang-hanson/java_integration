package com.hanson.test.controller;

import com.hanson.test.entity.Student;
import com.hanson.test.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @author hanson
 * @date 2024/3/21 23:55
 */
public class StudentControllerTest {
    @Mock
    StudentService studentService;
    @InjectMocks
    StudentController studentController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.list()).thenReturn(Arrays.<Student>asList(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex")));

        List<Student> result = studentController.getAllStudents();
        Assert.assertEquals(Arrays.<Student>asList(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex")), result);
    }

    @Test
    public void testGetStudentById() throws Exception {
        when(studentService.getById(any())).thenReturn(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex"));

        Student result = studentController.getStudentById(Integer.valueOf(0));
        Assert.assertEquals(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex"), result);
    }

    @Test
    public void testAddStudent() throws Exception {
        when(studentService.save(any())).thenReturn(true);

        Map<String, Object> result = studentController.addStudent(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex"));
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("replaceMeWithExpectedResult", "replaceMeWithExpectedResult");
        }}, result);
    }

    @Test
    public void testUpdateStudent() throws Exception {
        when(studentService.updateById(any())).thenReturn(true);

        studentController.updateStudent(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        when(studentService.removeById(any())).thenReturn(true);

        studentController.deleteStudent(new Student(Integer.valueOf(0), "name", "classname", Integer.valueOf(0), "sex"));
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme