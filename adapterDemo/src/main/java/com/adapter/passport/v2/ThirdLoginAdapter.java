package com.adapter.passport.v2;

import com.adapter.passport.ResultMsg;
import com.adapter.passport.v2.adapter.AbstractLoginAdapter;
import com.adapter.passport.v2.adapter.LoginForQQAdapter;
import com.adapter.passport.v2.adapter.LoginForWechaAdapter;

public class ThirdLoginAdapter implements ThirdLogin {
    public ResultMsg loginForQQ(String name) {
        return thirdLogin(name, LoginForQQAdapter.class);
    }

    public ResultMsg loginForWechat(String name) {
        return thirdLogin(name, LoginForWechaAdapter.class);
    }

    public ResultMsg thirdLogin(String name, Class<? extends AbstractLoginAdapter> clazz) {
        try {
            AbstractLoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)) {
                return adapter.login(name, adapter);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
