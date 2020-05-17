package com.adapter.passport.v2.adapter;

import com.adapter.passport.ResultMsg;

public class LoginForQQAdapter extends AbstractLoginAdapter {
    public boolean support(Object object) {
        return object instanceof LoginForQQAdapter;
    }

    public ResultMsg login(String name, Object object) {
        if (!support(object)) return null;
        return super.loginForRegist(name, null);
    }
}
