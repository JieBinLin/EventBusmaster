package com.ljb.eventbus_master;

/**
 * Created by L-JB on 2019/4/17 16:36.
 * 自定义一个事件类
 * 其实这个类就是一个Bean类，里面定义用来传输的数据的类型。
 */
public class MessageEvent {
    public final String message;//此时的messgae也可以是个对象
    public MessageEvent(String message) {
        this.message = message;
    }
}
