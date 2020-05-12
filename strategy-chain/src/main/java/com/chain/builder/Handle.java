package com.chain.builder;

import com.chain.Member;

public abstract class Handle<T> {
    protected  Handle<T> handle;

    public void next(Handle<T> handle){
        this.handle=handle;
    }

    protected abstract void doPerform(Member member);
    public static class  Builder<T>{
        protected  Handle<T> head;
        protected  Handle<T> tail;
        public Builder<T> addHandel(Handle<T> handle){
              //  do {
                    if(head==null){
                        this.head=this.tail=handle;

                    }else{
                        this.tail.next(handle);
                        this.tail=handle;

                    }
              //  }while (false);
            return this;
        }
        public void built(Member member){
            this.head.doPerform(member);
        }
    }
}
