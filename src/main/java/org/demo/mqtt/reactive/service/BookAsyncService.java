package org.demo.mqtt.reactive.service;

import org.demo.mqtt.reactive.beans.Book;
import org.demo.mqtt.reactive.handler.Handler;
import org.demo.mqtt.reactive.model.AsyncResult;

/**
 * BookAsyncService
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 21:20
 * @Description:
 */
public interface BookAsyncService {
    void getBook(String name, Handler<AsyncResult<Book>> handler);
}
