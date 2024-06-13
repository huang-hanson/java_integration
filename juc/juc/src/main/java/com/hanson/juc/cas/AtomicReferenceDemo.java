package com.hanson.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hanson
 * @date 2024/6/12 19:26
 */
@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Object> atomicReference = new AtomicReference<>();

        User z3 = new User("z3", 22);
        User l4 = new User("l4", 24);


        atomicReference.set(z3);

        // true	User(userName=l4, age=24)
        // false	User(userName=l4, age=24)
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
    }
}
