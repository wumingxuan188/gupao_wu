package com.chain.builder;


import com.chain.Member;

public class AuthHandler extends Handle {

    @Override
    protected void doPerform(Member member) {
        if("管理员".equals(member.getRoleName())){
            System.err.println("访问成功");
            // handle.doPerform(member);
        }else {
            System.err.println("权限不足");
        }
    }
}
