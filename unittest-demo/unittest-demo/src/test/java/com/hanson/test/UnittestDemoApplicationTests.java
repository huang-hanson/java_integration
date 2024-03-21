package com.hanson.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.test.entity.Student;
import com.hanson.test.mapper.StudentMapper;
import com.hanson.test.service.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

//@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnittestDemoApplication.class)
class UnittestDemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Controller 层单元测试
     *
     * @throws Exception
     */
    @Test
    void testAddStudent() throws Exception {
        // 创建一个Student对象作为请求的JSON体
        Student student = new Student();
        student.setId(9);
        student.setName("小乔");
        student.setClassname("初二三班");
        student.setAge(14);
        student.setSex("女");

        // 将Student对象转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        // 发送POST请求
        mockMvc.perform(MockMvcRequestBuilders.post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
                )
                // 断言返回的状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 断言返回的JSON中包含指定的code和message
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("保存成功"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Service 层单元测试
     */
    @Test
    public void getOne() {
        Student stu = studentService.getById(1);
        Assert.assertThat(stu.getName(), CoreMatchers.is("张三"));
    }

    @Test
    @Rollback(value = true)
    @Transactional
    public void insertOne() {
        Student student = new Student();
        student.setId(7);
        student.setName("王五");
        student.setClassname("大一");
        student.setAge(20);
        student.setSex("男");
        int count = studentMapper.insert(student);
        Assert.assertEquals(1, count);
    }

    /**
     * 异常测试
     */
    @Test
    public void computeScoreTest() {
        Assert.assertThrows(ArithmeticException.class, () -> {
            studentService.computeScore(); // This line should throw ArithmeticException
        });
    }

}