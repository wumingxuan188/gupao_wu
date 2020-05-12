package com.adapter.passport.v2;

import com.adapter.passport.ResultMsg;

public class Test {
    public static void main(String[] args) {
        ThirdLoginAdapter adapter = new ThirdLoginAdapter();
        ResultMsg msg = adapter.loginForQQ("1111");
        System.err.println(msg.getMsg());
    }
}
