package com.example.springbootthreaddemo.demo03;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/16-21:41
 */
public class InterruptDemo implements Runnable{

    private int i=1;
    @Override
    public void run() {
//        Thread.currentThread().isInterrupted()=false;\
//        表示一个中断的标记  interrupted=fasle
        while(!Thread.currentThread().isInterrupted()){
            //
            System.out.println("Test:"+i++);
        }
        //
    }

    public static void main(String[] args) {
        Thread thread=new Thread(new InterruptDemo());
        thread.start();
        thread.interrupt(); //设置 interrupted=true;
    }
}
