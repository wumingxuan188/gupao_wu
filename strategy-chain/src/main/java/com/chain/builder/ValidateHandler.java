package com.chain.builder;


import com.chain.Member;

public class ValidateHandler extends Handle{


    @Override
    protected void doPerform(Member member) {
        if(member.getLoginName()!=null && member.getLoginPass()!=null){
           handle.doPerform(member);
        }else {
            System.err.println("用户名密码为空");
        }
    }
}

