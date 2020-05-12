package com.chain.oldChain;

import com.chain.Member;

public abstract class Handle {
     Handle handle;

    public void next(Handle handle){
        this.handle=handle;
    }
    public abstract void doPerform(Member member);
}
