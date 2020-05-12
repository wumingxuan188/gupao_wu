package com.gupao.state.demo;

public class UnLoginState extends UserState {
    @Override
    public void collect() {
        System.err.println("请登录。。。。");
        switch2ToLogin().collect();
    }

    @Override
    public void comment(String data) {
        System.err.println("请登录。。。。。。");
        switch2ToLogin().comment(data);
    }

    public UserState switch2ToLogin(){
        appContext.setCurrent_state(AppContext.LOGIN_STATE);
        return appContext.getCurrent_state();

    }
}
