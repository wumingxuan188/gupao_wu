package com.adapter.passport.v1;

import com.adapter.passport.PassportService;
import com.adapter.passport.ResultMsg;

public class LoginAdapter extends PassportService implements ILoginThird {
    public ResultMsg loginForQQ(String qq, String pass) {

        return loginForThird(qq, null);
    }

    public ResultMsg loginForWchat(String name, String pass) {
        return loginForThird(name, null);
    }

    public ResultMsg loginForPhone(String phone, String pass) {
        return loginForThird(phone, null);
    }

    public ResultMsg loginForThird(String name, String pass) {
        if (pass == null) {
            pass = "THIRD_EMPTY";
        }
        super.regist(name, pass);
        return super.login(name, pass);
    }
}
