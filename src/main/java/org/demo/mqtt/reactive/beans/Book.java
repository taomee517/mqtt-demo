package org.demo.mqtt.reactive.beans;

import lombok.Data;

/**
 * Book
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 21:20
 * @Description:
 */
@Data
public class Book {
    private String name;
    private String author;
    private int publishedYear;

    public Book(String name, String author, int publishedYear) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
    }
}
