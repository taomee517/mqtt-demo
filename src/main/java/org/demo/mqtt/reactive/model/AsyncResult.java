package org.demo.mqtt.reactive.model;

/**
 * AsyncResult
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 20:54
 * @Description:
 */
public interface AsyncResult<T> {
    T result();

    Throwable cause();

    boolean succeeded();

    boolean failed();

    static <T> AsyncResult<T> success(T result){
        return new SuccessResult<>(result);
    }

}
