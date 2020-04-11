package org.demo.mqtt.reactive.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * BookStore
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 21:23
 * @Description:
 */
public class BookStore {
    public static List<Book> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new Book("围城", "钱钟书", 2000));
        BOOKS.add(new Book("边城","沈从文",1951));
    }
}
