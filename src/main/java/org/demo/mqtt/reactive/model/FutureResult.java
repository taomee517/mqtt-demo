//package org.demo.mqtt.reactive.model;
//
///**
// * FutureResult
// *
// * @Author: taomee
// * @Date: 2020/4/11 0011 21:31
// * @Description:
// */
//public class FutureResult<T> implements AsyncResult<T> {
//    private T result = null;
//    private boolean succeed = false;
//    private Throwable t = null;
//
//    public FutureResult(T result, boolean succeed, Throwable t) {
//        this.result = result;
//        this.succeed = succeed;
//        this.t = t;
//    }
//
//    @Override
//    public T result() {
//        return result;
//    }
//
//    @Override
//    public Throwable cause() {
//        return t;
//    }
//
//    @Override
//    public boolean succeeded() {
//        return succeed;
//    }
//
//    @Override
//    public boolean failed() {
//        return !succeed;
//    }
//
//    public static <T> FutureResult<T> success(T result){
//        this.result = result;
//        this.succeed = true;
//        this.t = null;
//    }
//}
