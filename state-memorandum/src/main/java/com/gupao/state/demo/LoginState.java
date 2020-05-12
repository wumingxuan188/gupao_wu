package com.gupao.state.demo;

public class LoginState extends  UserState {
    @Override
    public void collect() {
        System.err.println("收藏成功");
    }

    @Override
    public void comment(String data) {
        System.err.println("评论的内容:"+data);
    }
}
