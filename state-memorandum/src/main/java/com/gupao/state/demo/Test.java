package com.gupao.state.demo;

public class Test {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.setCurrent_state(AppContext.LOGIN_STATE);
        context.collect();
        context.comment("文章真好,6666");
    }
}
