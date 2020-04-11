package org.demo.mqtt.reactive.service.impl;

import org.demo.mqtt.reactive.beans.Book;
import org.demo.mqtt.reactive.beans.BookStore;
import org.demo.mqtt.reactive.handler.Handler;
import org.demo.mqtt.reactive.model.AsyncResult;
import org.demo.mqtt.reactive.service.BookAsyncService;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * BookAsyncServiceImpl
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 21:27
 * @Description:
 */
public class BookAsyncServiceImpl implements BookAsyncService {
    @Override
    public void getBook(String name, Handler<AsyncResult<Book>> handler) {
        CompletableFuture.supplyAsync(new Supplier<Book>(){
            @Override
            public Book get() {
                Book book = null;
                for(Book b: BookStore.BOOKS){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(b.getName().equalsIgnoreCase(name)){
                        book = b;
                    }
                }
                return book;
            }
        }).thenAccept(new Consumer<Book>() {
            @Override
            public void accept(Book book) {
                handler.handle(AsyncResult.success(book));
            }
        });
    }
}
