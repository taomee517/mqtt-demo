package org.demo.mqtt.reactive.model;

import java.util.concurrent.Future;

/**
 * SuccessResult
 *
 * @Author: taomee
 * @Date: 2020/4/11 0011 21:44
 * @Description:
 */
public class SuccessResult<T> implements AsyncResult<T> {
    private T result;
    private boolean succeed = true;
    private Throwable t = null;

    public SuccessResult(T result) {
        this.result = result;
    }

    @Override
    public T result() {
        return result;
    }

    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public boolean succeeded() {
        return true;
    }

    @Override
    public boolean failed() {
        return false;
    }
}
