package com.chain.builder;


import com.chain.Member;

public class LoginHandler extends Handle {

    @Override
    protected void doPerform(Member member) {
        System.err.println("登录成功");
       // member.setRoleName("普通用户");
        member.setRoleName("管理员");
        handle.doPerform(member);
    }
}
