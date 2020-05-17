package com.adapter.passport.v2.adapter;

import com.adapter.passport.ResultMsg;

public interface ILoginAdapter {

    public boolean support(Object object);

    public ResultMsg login(String name, Object object);
}
