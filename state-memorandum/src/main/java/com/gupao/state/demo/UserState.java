package com.gupao.state.demo;

public abstract class UserState {
     protected   AppContext appContext;

    public void setAppContext(AppContext appContext) {
        this.appContext = appContext;
    }

    //收藏
    public abstract void collect();
    //评论
    public abstract  void comment(String data );
}
