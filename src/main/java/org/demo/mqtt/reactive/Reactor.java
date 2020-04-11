package org.demo.mqtt.reactive;

import lombok.extern.slf4j.Slf4j;
import org.demo.mqtt.reactive.service.BookAsyncService;
import org.demo.mqtt.reactive.service.impl.BookAsyncServiceImpl;

/**
 * Reactor
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 20:52
 * @Description:
 */
@Slf4j
public class Reactor {
    public static void main(String[] args) throws InterruptedException {
        log.info("main start");
        String bookName = "围城";
        BookAsyncService asyncService = new BookAsyncServiceImpl();
        asyncService.getBook(bookName, result -> {
            log.info("任务线程处理异步结果，{}", Thread.currentThread());
            if(result.succeeded()){
                log.info("find id, book:{}", result.result());
                System.exit(0);
            }else {
                log.error("no book named {}", bookName);
                System.exit(1);
            }
        });
        log.info("主线程执行其它任务，{}", Thread.currentThread());
        Thread.currentThread().join();

    }
}
