package com.adapter.passport.v2;

import com.adapter.passport.ResultMsg;
import com.adapter.passport.v2.adapter.AbstractLoginAdapter;

public interface ThirdLogin {

    public ResultMsg loginForQQ(String name);

    public ResultMsg loginForWechat(String name);
}
