package org.demo.mqtt.reactive.handler;

/**
 * Handler
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 20:52
 * @Description:
 */
public interface Handler<T> {
    public void handle(T msg);
}
