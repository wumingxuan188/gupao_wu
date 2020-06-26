package com.example.springbootthreaddemo.demo03;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/16-22:12
 */
public class InterruptDemo03 implements Runnable{
    static volatile boolean interrupt=false;
    @Override
    public void run() {
        while(!interrupt){
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
    }
    public static void main(String[] args) {
        interrupt=true;
    }
}
