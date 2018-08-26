package com.jrr.user.test_interface;

import javax.security.auth.callback.Callback;

/**
 * Created by user on 2018/8/25.
 */

public class Callbackutil {

    private static CallBack mCallback;

    public static void setmCallback(CallBack callback){
        mCallback = callback;

    }

    public static  void doCallbackMetod(){
        String info = "callback   即将发送的数据";
        mCallback.doSomting(info);
    }
}
