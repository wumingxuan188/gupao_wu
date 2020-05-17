package com.adapter.passport.v2.adapter;

import com.adapter.passport.PassportService;
import com.adapter.passport.ResultMsg;

public abstract class AbstractLoginAdapter extends PassportService implements ILoginAdapter {
    protected ResultMsg loginForRegist(String name, String pass) {
        if (pass == null) {
            pass = "THIRD_EMPTY";
        }
        super.regist(name, pass);
        return super.login(name, pass);
    }

}
