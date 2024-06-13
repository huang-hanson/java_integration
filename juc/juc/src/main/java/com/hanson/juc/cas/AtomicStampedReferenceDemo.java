package com.hanson.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author hanson
 * @date 2024/6/12 22:01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
class Book {
    private int id;
    private String bookName;
}

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {

        Book javaBook = new Book(1, "javaBook");

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book mySqlBook = new Book(2, "mySqlBook");

        boolean b;

        b = stampedReference.compareAndSet(javaBook, mySqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);

        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mySqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);

        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        //Book(id=1, bookName=javaBook)	1
        //true	Book(id=2, bookName=mySqlBook)	2
        //true	Book(id=1, bookName=javaBook)	3
    }

}
