package com.chain.oldChain;

import com.chain.Member;

public class ValidateHandler extends Handle {
    @Override
    public void doPerform(Member member) {
        if(member.getLoginName()!=null && member.getLoginPass()!=null){
           super.handle.doPerform(member);
        }else {
            System.err.println("用户名密码为空");
        }
    }
}
