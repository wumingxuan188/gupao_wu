package com.adapter.passport.v1;

import com.adapter.passport.ResultMsg;

public interface ILoginThird {
    public ResultMsg loginForQQ(String qq, String pass);

    public ResultMsg loginForWchat(String name, String pass);

    public ResultMsg loginForPhone(String phone, String pass);

}
