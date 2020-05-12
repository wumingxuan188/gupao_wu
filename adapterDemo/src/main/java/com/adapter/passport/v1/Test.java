package com.adapter.passport.v1;

import com.adapter.passport.ResultMsg;

public class Test {
    public static void main(String[] args) {
        LoginAdapter adapter = new LoginAdapter();
        adapter.login("tom", "11");
        ResultMsg msg = adapter.loginForPhone("154541", null);
        System.err.println(msg);
    }
}
