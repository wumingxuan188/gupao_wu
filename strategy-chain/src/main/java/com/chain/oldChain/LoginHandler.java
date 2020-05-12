package com.chain.oldChain;

import com.chain.Member;

public class LoginHandler extends Handle {
    @Override
    public void doPerform(Member member) {
        System.err.println("登录成功");
        member.setRoleName("普通用户");
        handle.doPerform(member);
    }
}
