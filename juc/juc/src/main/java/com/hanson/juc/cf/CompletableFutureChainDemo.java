package com.hanson.juc.cf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hanson
 * @date 2024/6/4 10:40
 */
public class CompletableFutureChainDemo {

    public static void main(String[] args) {

        Student student = new Student();

        student.setId(1);
        student.setStudentName("z3");
        student.setMajor("cs");

        student.setId(12).setStudentName("li4").setMajor("english");//链式调用
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//开启链式调用
class Student{
    private int id;

    private String studentName;

    private String major;
}
